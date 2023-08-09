package com.nekiSkills.NekiSkills.domain.service;

import java.util.List;

import com.nekiSkills.NekiSkills.domain.model.Usuario;

public interface UsuarioService {
    
    List<Usuario> getAll();

    Usuario findByLogin(String login);

    Usuario saveUsuario(Usuario usuario);

    boolean deleteUsuario(Integer id);

    Usuario buscarOuFalhar(Integer usuarioId);
}
