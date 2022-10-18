package br.senai.sc.editoralivros.model.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_livros")
@AllArgsConstructor
@Getter @Setter @EqualsAndHashCode
@NoArgsConstructor
public class Livro {
    @Id
    @Column(nullable = false, length = 13, unique = true)
    private Long ISBN;

    @Column(nullable = false, length = 50)
    private String titulo;

    @Column(nullable = false)
    private Autor autor;

    @Column(nullable = false, unique = true)
    private Integer qntdPaginas;

    @Column(nullable = true, length = 13, unique = true)
    private Editora editora;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Double paginasRevisadas = 0.0;

    @Column
    private Revisor revisor;

}
