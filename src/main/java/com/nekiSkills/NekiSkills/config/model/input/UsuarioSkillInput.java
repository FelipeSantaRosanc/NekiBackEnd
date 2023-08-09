package com.nekiSkills.NekiSkills.config.model.input;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioSkillInput {

    @ApiModelProperty(example = "1")
    private Integer knowledgeLevel;

    @ApiModelProperty(example = "2023-01-04")
    private Date createdAt;

    @ApiModelProperty(example = "2023-01-04")
    private Date updatedAt;

    @ApiModelProperty(example = "User Id")
    private UsuarioIdInput user;

    @ApiModelProperty(example = "Skill Id")
    private SkillIdInput skill;

}
