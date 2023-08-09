package com.nekiSkills.NekiSkills.config.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nekiSkills.NekiSkills.config.model.input.CredentialLoginInput;
import com.nekiSkills.NekiSkills.domain.model.Usuario;
import com.nekiSkills.NekiSkills.domain.security.JwtUtil;
import com.nekiSkills.NekiSkills.domain.service.UsuarioService;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private PasswordEncoder passwordEncoder;

    

    @PostMapping("/registro")
    public Map<String, Object> registerHandler(@RequestBody Usuario usuario) {
        String encodedPass = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(encodedPass);
        usuario = usuarioService.saveUsuario(usuario);

        Usuario usuarioResumido = new Usuario();
        usuarioResumido.setId(usuario.getId());
        usuarioResumido.setLogin(usuario.getLogin());
        usuarioResumido.setLastLogginDate(usuario.getLastLogginDate());
        String token = jwtUtil.generateTokenWithUserData(usuarioResumido);

        return Collections.singletonMap("jwt-token", token);
    }

    @PostMapping("/login")
    public Map<String, Object> loginHandler(@RequestBody CredentialLoginInput credentialLoginInput) {

        try {
            // Criando o token que sera usado no processo de autenticacao
            UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
                    credentialLoginInput.getLogin(),
                    credentialLoginInput.getPassword());
            System.out.println("Usuario logado: " + authInputToken);

            // Autenticando as credenciais de login
            authManager.authenticate(authInputToken);

            // Se o processo de autenticacao foi concluido com sucesso - etapa anterior,
            // eh gerado o JWT

            Usuario usuario = usuarioService.findByLogin(credentialLoginInput.getLogin());
            Usuario usuarioResumido = new Usuario();
            usuarioResumido.setId(usuario.getId());
            usuarioResumido.setLogin(usuario.getLogin());
            usuarioResumido.setLastLogginDate(usuario.getLastLogginDate());
            // Gerando o token JWT a partir dos dados do Usuario
            String token = jwtUtil.generateTokenWithUserData(usuarioResumido);
            System.out.println("Usuario logado: " + usuarioResumido);
            // Responde com o JWT
            return Collections.singletonMap("jwt-token", token);
        } catch (AuthenticationException authExc) {
            throw new RuntimeException("Credenciais Invalidas");
        }

    }

}
