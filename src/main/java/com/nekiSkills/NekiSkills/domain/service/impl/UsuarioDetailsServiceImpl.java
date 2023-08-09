package com.nekiSkills.NekiSkills.domain.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.nekiSkills.NekiSkills.config.permissao.AuthUsuario;
import com.nekiSkills.NekiSkills.domain.model.Usuario;
import com.nekiSkills.NekiSkills.domain.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Component
public class UsuarioDetailsServiceImpl implements UserDetailsService {

  @Autowired
  private UsuarioRepository usuarioRepository;

  /**
   * Função para coletar os dados do usuário logado
   */
  @Transactional
  @Override
  public UserDetails loadUserByUsername(String usuarioLogin)
      throws UsernameNotFoundException {
    Usuario usuario = usuarioRepository
        .findByLogin(usuarioLogin)
        .orElseThrow(() -> new UsernameNotFoundException(
            "Usuário não encontrado com o login informado"));

    return new AuthUsuario(usuario.getId(), usuario.getLogin(), usuario.getPassword());
  }
}
