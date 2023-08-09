package com.nekiSkills.NekiSkills.domain.service.impl;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nekiSkills.NekiSkills.domain.exception.UsuarioSkillNaoEncontradoException;
import com.nekiSkills.NekiSkills.domain.model.UsuarioSkill;
import com.nekiSkills.NekiSkills.domain.repository.UsuarioSkillRepository;
import com.nekiSkills.NekiSkills.domain.service.UsuarioSkillService;

import jakarta.transaction.Transactional;

@Service
public class UsuarioSkillServiceImpl implements UsuarioSkillService {

    private static final String MSG_SKILL_NAO_ENCONTRADA = "Não foi possivel encontrar a skill do tipo: ";

    @Autowired
    private UsuarioSkillRepository usuarioSkillRepository;

    public UsuarioSkill findById(Integer id) {
        Optional<UsuarioSkill> userSkill = usuarioSkillRepository.findById(id);
        return userSkill.orElseThrow(() -> new ObjectNotFoundException(
                MSG_SKILL_NAO_ENCONTRADA +
                        UsuarioSkill.class.getName(),
                id));
    }

    public List<UsuarioSkill> findAll() {
        return usuarioSkillRepository.findAll();
    }

    @Transactional
    public UsuarioSkill save(UsuarioSkill usuarioSkill) {
        return usuarioSkillRepository.save(usuarioSkill);
    }

    private void updateData(UsuarioSkill newUsuarioSkill, UsuarioSkill usuarioSkill) {
        newUsuarioSkill.setKnowLedgeLevel(usuarioSkill.getKnowLedgeLevel());
    }

    public UsuarioSkill update(UsuarioSkill usuarioSkill) {
        UsuarioSkill newUsuarioSkill = findById(usuarioSkill.getId());
        updateData(newUsuarioSkill, usuarioSkill);
        return usuarioSkillRepository.save(newUsuarioSkill);
    }

    public void delete(Integer id) {
        findById(id);
        usuarioSkillRepository.deleteById(id);
    }

    public UsuarioSkill findBySkillFromUsuario(Integer usuario_id) {
        return usuarioSkillRepository.buscarSkillUsuario(usuario_id);
    }

    public UsuarioSkill buscarOufFalhar(Integer usuarioSkillId) {
        return usuarioSkillRepository
                .findById(usuarioSkillId)
                .orElseThrow(() -> new UsuarioSkillNaoEncontradoException(usuarioSkillId));
    }
}
