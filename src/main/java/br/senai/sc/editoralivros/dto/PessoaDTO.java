package br.senai.sc.editoralivros.dto;

import br.senai.sc.editoralivros.model.entities.Genero;

import javax.validation.constraints.NotBlank;

public class PessoaDTO {
    @NotBlank
    private Long CPF;
    @NotBlank
    private String nome;
    @NotBlank
    private String sobrenome;
    @NotBlank
    private String email;
    @NotBlank
    private String senha;
    @NotBlank
    private Genero genero;

}
