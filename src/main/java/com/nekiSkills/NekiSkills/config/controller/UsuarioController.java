package com.nekiSkills.NekiSkills.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nekiSkills.NekiSkills.domain.model.Usuario;
import com.nekiSkills.NekiSkills.domain.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll(){
        List<Usuario> usuarios = usuarioService.getAll();
        if(!usuarios.isEmpty()) return new ResponseEntity<>(usuarios, HttpStatus.OK);
        return new  ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
}
