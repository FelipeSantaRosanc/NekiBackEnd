package com.nekiSkills.NekiSkills.config.model;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioSkillModel {

    @ApiModelProperty(example = "1")
    private Integer id;

    @ApiModelProperty(example = "1")
    private Integer KnowledgeLevel;

    @ApiModelProperty(example = "2023-01-04")
    private Date CreatedAt;

    @ApiModelProperty(example = "2023-01-04")
    private Date UpdatedAt;

    @ApiModelProperty(example = "User Id")
    private UsuarioModel user;

    @ApiModelProperty(example = "Skill Id")
    private SkillModel skill;

}
