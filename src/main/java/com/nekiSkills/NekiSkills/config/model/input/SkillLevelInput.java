package com.nekiSkills.NekiSkills.config.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillLevelInput {

    @ApiModelProperty(example = "1")
    private Integer knowledgeLevel;
    
}
