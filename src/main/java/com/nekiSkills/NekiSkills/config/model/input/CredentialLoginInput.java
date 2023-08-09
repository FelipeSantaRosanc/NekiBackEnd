package com.nekiSkills.NekiSkills.config.model.input;

import java.util.Date;

import org.hibernate.annotations.UpdateTimestamp;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CredentialLoginInput {

    @ApiModelProperty(example = "usuarioLogin")
    private String login;

    @ApiModelProperty(example = "123456")
    private String password;

    @UpdateTimestamp
    @ApiModelProperty(example = "2023-01-04")
    private Date lastLoginDate;
}
