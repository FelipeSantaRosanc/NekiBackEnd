package com.nekiSkills.NekiSkills.config.assembleMap;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nekiSkills.NekiSkills.config.model.UsuarioSkillModel;
import com.nekiSkills.NekiSkills.domain.model.UsuarioSkill;

@Component
public class UsuarioSkillModelAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioSkillModel toModel(UsuarioSkill userSkill) {
        return modelMapper.map(userSkill, UsuarioSkillModel.class);
    }

    public List<UsuarioSkillModel> toCollectionModel(List<UsuarioSkill> user) {
        return user
                .stream()
                .map(userSkill -> toModel(userSkill))
                .collect(Collectors.toList());
    }

}
