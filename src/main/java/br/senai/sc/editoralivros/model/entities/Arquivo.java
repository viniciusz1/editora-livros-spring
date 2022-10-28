package br.senai.sc.editoralivros.model.entities;

import lombok.*;
import lombok.extern.java.Log;

import javax.persistence.*;

@Entity
@Table(name = "tb_arquivos")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter @Setter
public class Arquivo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    private String nome;
    @NonNull
    private String tipo;
    @NonNull
    @Lob
    private byte[] dados;


}
