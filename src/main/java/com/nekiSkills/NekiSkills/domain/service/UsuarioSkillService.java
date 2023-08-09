package com.nekiSkills.NekiSkills.domain.service;

import java.util.List;

import com.nekiSkills.NekiSkills.domain.model.UsuarioSkill;

public interface UsuarioSkillService {

    List<UsuarioSkill> findAll();

    UsuarioSkill save(UsuarioSkill usuarioSkill);


    UsuarioSkill update(UsuarioSkill usuarioSkill);

    void delete(Integer id);

    UsuarioSkill buscarOufFalhar(Integer usuarioSkillId);

    UsuarioSkill findBySkillFromUsuario(Integer usuario_id);
}
