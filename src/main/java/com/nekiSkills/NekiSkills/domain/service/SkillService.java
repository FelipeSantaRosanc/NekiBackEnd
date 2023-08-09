package com.nekiSkills.NekiSkills.domain.service;

import java.util.List;

import com.nekiSkills.NekiSkills.domain.model.Skill;

public interface SkillService {

    Skill findById(Integer skillId);

    List<Skill> findAll();

    Skill save(Skill skill);

    void delete(Integer skillId);

    Skill buscarOuFalhar(Integer skillId);

}
