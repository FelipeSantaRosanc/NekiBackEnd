package com.nekiSkills.NekiSkills.config.model.input;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SkillInput {


    @ApiModelProperty(example = "Java")
    private String name;

    @ApiModelProperty(example = "11")
    private String version;

    @ApiModelProperty(example = "Intermediário")
    private String description;

    @ApiModelProperty(example = "https://logospng.org/download/javascript/logo-javascript-512.png")
    private String imageUrl;
    
}
