package com.nekiSkills.NekiSkills.domain.exception;

public class UsuarioSkillNaoEncontradoException
        extends EntidadeNaoEncontradaException {

    public UsuarioSkillNaoEncontradoException(String mensagem) {
        super(mensagem);
    }

    public UsuarioSkillNaoEncontradoException(Integer userSkillId) {
        this(
                String.format(
                        "Não existe um cadastro de skill para o usuário com código %d",
                        userSkillId));
    }
}
