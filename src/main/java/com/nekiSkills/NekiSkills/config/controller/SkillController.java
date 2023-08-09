package com.nekiSkills.NekiSkills.config.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.nekiSkills.NekiSkills.config.assembleMap.SkillInputDisassembler;
import com.nekiSkills.NekiSkills.config.assembleMap.SkillModelAssembler;
import com.nekiSkills.NekiSkills.config.model.SkillModel;
import com.nekiSkills.NekiSkills.config.model.input.SkillInput;
import com.nekiSkills.NekiSkills.domain.exception.NegocioException;
import com.nekiSkills.NekiSkills.domain.exception.SkillNaoEncontradaException;
import com.nekiSkills.NekiSkills.domain.model.Skill;
import com.nekiSkills.NekiSkills.domain.repository.SkillRepository;
import com.nekiSkills.NekiSkills.domain.service.SkillService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/skills")
public class SkillController {

    @Autowired
    private SkillService skillService;

    @Autowired
    private SkillRepository skillRepository;

    @Autowired
    private SkillModelAssembler skillModelAssembler;

    @Autowired
    private SkillInputDisassembler skillInputDisassembler;

    @GetMapping
    public List<SkillModel> findAll() {
        return skillModelAssembler.toCollectionModel(skillRepository.findAll());
    }

    @GetMapping("/{skillId}")
    public SkillModel findById(@PathVariable Integer skillId) {
        Skill skill = skillService.buscarOuFalhar(skillId);
        return skillModelAssembler.toModel(skill);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SkillModel create (@RequestBody @Valid SkillInput skillInput ){
        try{
            Skill skill = skillInputDisassembler.toDomainObjects(skillInput);
            return skillModelAssembler.toModel(skillService.save(skill));
        } catch (SkillNaoEncontradaException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{SkillId}")
    public SkillModel update(@PathVariable Integer skillId , @RequestBody @Valid SkillInput skilllInput){
        Skill skillAtual = skillService.buscarOuFalhar(skillId);

        skillInputDisassembler.copyToDomainObject(skilllInput, skillAtual);
        skillAtual = skillService.save(skillAtual);

        return skillModelAssembler.toModel(skillAtual);
    }

    @DeleteMapping("/{skillId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer skillId){
        skillService.delete(skillId);
    }


}
