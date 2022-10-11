package br.senai.sc.editoralivros.model.entities;

public class Editora {
    private String nome, CNPJ;

    public Editora(String nome, String CNPJ) {
        this.nome = nome;
        this.CNPJ = CNPJ;
    }

    public String getNome() {
        return nome;
    }


    @Override
    public String toString() {
        return "Editora{" +
                "nome='" + nome + '\'' +
                ", CNPJ='" + CNPJ + '\'' +
                '}';
    }
}
