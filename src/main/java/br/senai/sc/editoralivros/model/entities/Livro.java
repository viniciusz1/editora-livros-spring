package br.senai.sc.editoralivros.model.entities;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import java.util.List;

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

    @ManyToMany
    @JoinTable(name = "tb_livro_autor", joinColumns =
    @JoinColumn(name = "isbn_livro", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "cpf_autor", nullable = false))
    private List<Autor> autores;

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

    @OneToOne(cascade = CascadeType.ALL)
    private Arquivo arquivo;


    public void setArquivo(MultipartFile file) {
        try{
        this.arquivo = new Arquivo(file.getOriginalFilename(), file.getContentType(), file.getBytes());

        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
