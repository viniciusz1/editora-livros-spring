package br.senai.sc.editoralivros.model.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@Getter @Setter
@Entity
@Table(name = "tb_editora")

public class Editora {
    @Id
    @Column(nullable = false,length = 14, unique = true)
    private Long cnpj;

    @Column(nullable = false)
    private String nome;
}
