package com.nekiSkills.NekiSkills.config.assembleMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nekiSkills.NekiSkills.config.model.input.SkillLevelInput;
import com.nekiSkills.NekiSkills.config.model.input.UsuarioSkillInput;
import com.nekiSkills.NekiSkills.domain.model.UsuarioSkill;


@Component
public class UsuarioSkillInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioSkill toDomainObject(UsuarioSkillInput usuarioSkillInput) {
        return modelMapper.map(usuarioSkillInput, UsuarioSkill.class);
    }

    public void copyToDomainObject(
            UsuarioSkillInput userSkillInput,
            UsuarioSkill userSkill) {
        modelMapper.map(userSkillInput, userSkill);
    }

    public void copyToDomainObject(
            SkillLevelInput skillLevelInput,
            UsuarioSkill userSkill) {
        modelMapper.map(skillLevelInput, userSkill);
    }

}
