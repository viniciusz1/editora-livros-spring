package br.senai.sc.editoralivros.security;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class UsuarioDTO {
    @NotNull
    private String email;
    @NotNull
    private String senha;
}
