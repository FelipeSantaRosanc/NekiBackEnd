package com.nekiSkills.NekiSkills.config.assembleMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nekiSkills.NekiSkills.config.model.UsuarioModel;
import com.nekiSkills.NekiSkills.domain.model.Usuario;

@Component
public class UsuarioModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioModel toModel(Usuario usuario){
        return modelMapper.map(usuario, UsuarioModel.class);
    }
    
}
