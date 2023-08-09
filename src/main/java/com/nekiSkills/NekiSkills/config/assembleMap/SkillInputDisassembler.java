package com.nekiSkills.NekiSkills.config.assembleMap;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nekiSkills.NekiSkills.config.model.input.SkillInput;
import com.nekiSkills.NekiSkills.domain.model.Skill;

@Component
public class SkillInputDisassembler {

    @Autowired
    private ModelMapper modelMapper;

    public Skill toDomainObjects(SkillInput skillInput) {
        return modelMapper.map(skillInput, Skill.class);
    }

    public void copyToDomainObject(SkillInput skillInput, Skill skill) {
        modelMapper.map(skillInput, skill);
    }

}
