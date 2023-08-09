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

import com.nekiSkills.NekiSkills.config.assembleMap.UsuarioSkillInputDisassembler;
import com.nekiSkills.NekiSkills.config.assembleMap.UsuarioSkillModelAssembler;
import com.nekiSkills.NekiSkills.config.model.UsuarioSkillModel;
import com.nekiSkills.NekiSkills.config.model.input.SkillLevelInput;
import com.nekiSkills.NekiSkills.config.model.input.UsuarioSkillInput;
import com.nekiSkills.NekiSkills.domain.exception.NegocioException;
import com.nekiSkills.NekiSkills.domain.exception.UsuarioSkillNaoEncontradoException;
import com.nekiSkills.NekiSkills.domain.model.UsuarioSkill;
import com.nekiSkills.NekiSkills.domain.repository.UsuarioSkillRepository;
import com.nekiSkills.NekiSkills.domain.service.UsuarioSkillService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "usuarioSkills")
public class UsuarioSkillController {

    @Autowired
    private UsuarioSkillService usuarioSkillService;

    @Autowired
    private UsuarioSkillRepository usuarioSkillRepository;

    @Autowired
    private UsuarioSkillModelAssembler usuarioSkillModelAssembler;

    @Autowired
    private UsuarioSkillInputDisassembler usuarioSkillInputDisassembler;

    @GetMapping
    public List<UsuarioSkillModel> findAll() {
        return usuarioSkillModelAssembler.toCollectionModel(usuarioSkillRepository.findAll());
    }

    @GetMapping("/{usuarioSkillId}")
    public UsuarioSkillModel findById(@PathVariable Integer usuarioSkillId) {
        UsuarioSkill usuarioSkill = usuarioSkillService.buscarOufFalhar(usuarioSkillId);
        return usuarioSkillModelAssembler.toModel(usuarioSkill);
    }

    @GetMapping("/usuario/{usuario_id}")
    public UsuarioSkillModel findBydUsuarioId(@PathVariable Integer usuario_id) {
        UsuarioSkill usuarioSkilll = usuarioSkillService.findBySkillFromUsuario(usuario_id);
        return usuarioSkillModelAssembler.toModel(usuarioSkilll);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioSkillModel create(@RequestBody @Valid UsuarioSkillInput usuarioSkillInput) {
        try {
            UsuarioSkill usuarioSkill = usuarioSkillInputDisassembler.toDomainObject(usuarioSkillInput);

            return usuarioSkillModelAssembler.toModel(usuarioSkillService.save(usuarioSkill));
        } catch (UsuarioSkillNaoEncontradoException e) {
            throw new NegocioException(e.getMessage());
        }
    }

    @PutMapping("/{UsuarioSkillId}")
    public UsuarioSkillModel update(@PathVariable Integer usuarioSkillId,
            @RequestBody @Valid UsuarioSkillInput usuarioSkillInput) {
        UsuarioSkill usuarioSkillAtual = usuarioSkillService.buscarOufFalhar(usuarioSkillId);

        usuarioSkillInputDisassembler.copyToDomainObject(usuarioSkillInput, usuarioSkillAtual);
        usuarioSkillAtual = usuarioSkillService.save(usuarioSkillAtual);
        return usuarioSkillModelAssembler.toModel(usuarioSkillAtual);
    }

    @PutMapping("level/{usuarioSkillId}")
    public UsuarioSkillModel updateLevel(@PathVariable Integer usuarioSkillId,
            @RequestBody @Valid SkillLevelInput skillLevelInput) {
        UsuarioSkill usuarioSkillAtual = usuarioSkillService.buscarOufFalhar(usuarioSkillId);
        usuarioSkillInputDisassembler.copyToDomainObject(skillLevelInput, usuarioSkillAtual);
        usuarioSkillAtual = usuarioSkillService.save(usuarioSkillAtual);

        return usuarioSkillModelAssembler.toModel(usuarioSkillAtual);
    }

    @DeleteMapping("/{usuarioSkillId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer usuarioSkillId) {
        usuarioSkillService.delete(usuarioSkillId);
    }

}
