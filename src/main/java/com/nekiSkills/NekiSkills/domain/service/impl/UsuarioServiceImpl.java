package com.nekiSkills.NekiSkills.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekiSkills.NekiSkills.domain.exception.NegocioException;
import com.nekiSkills.NekiSkills.domain.exception.UsuarioNaoEncontradoException;
import com.nekiSkills.NekiSkills.domain.model.Usuario;
import com.nekiSkills.NekiSkills.domain.repository.UsuarioRepository;
import com.nekiSkills.NekiSkills.domain.service.UsuarioService;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private EntityManager manager;

    @Transactional
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public void detach(Usuario user) {
        manager.detach(user);
    }

    @Transactional
    public Usuario findByLogin(String login) {
        return usuarioRepository.findByLogin(login).get();
    }

    public Usuario saveUsuario(Usuario usuario) {
        detach(usuario);

        Optional<Usuario> usuarioExistente = usuarioRepository.findByLogin(
                usuario.getLogin());

        if (usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
            throw new NegocioException(
                    String.format(
                            "Já existe um usuário cadastrado com o login %s",
                            usuario.getLogin()));
        }

        return usuarioRepository.save(usuario);

    }

    @Transactional
    public boolean deleteUsuario(Integer id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            usuarioRepository.delete(usuario);
            return true;
        } else {
            return false;
        }
    }

    public Usuario buscarOuFalhar(Integer usuarioId) {
        return usuarioRepository
                .findById(usuarioId)
                .orElseThrow(() -> new UsuarioNaoEncontradoException(usuarioId));
    }
}
