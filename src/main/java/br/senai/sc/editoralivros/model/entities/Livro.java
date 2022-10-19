package br.senai.sc.editoralivros.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tb_livros")
@AllArgsConstructor
@Getter @Setter @EqualsAndHashCode
@NoArgsConstructor
public class Livro {
    @Id
    @Column(nullable = false, length = 13, unique = true)
    private Long isbn;

    @Column(nullable = false, length = 50)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "cpf_autor", nullable = false)
    private Autor autor;

    @Column(nullable = false)
    private Integer qtdPaginas;

    @ManyToOne
    @JoinColumn(name = "cnpj_editora")
    private Editora editora;

    @Column(nullable = false)
    private Status status;

    @Column(nullable = false)
    private Double paginasRevisadas = 0.0;

    @ManyToOne
    @JoinColumn(name = "cpf_revisor")
    private Revisor revisor;

}
