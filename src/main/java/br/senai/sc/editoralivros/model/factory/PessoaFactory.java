package br.senai.sc.editoralivros.model.factory;

import br.senai.sc.editoralivros.model.entities.Autor;
import br.senai.sc.editoralivros.model.entities.Pessoa;
import br.senai.sc.editoralivros.model.entities.Revisor;

public class PessoaFactory {
    public static Pessoa getPessoa(String tipoPessoa) {
        if (tipoPessoa.equals("Autor")) {
            return new Autor();
        } else if (tipoPessoa.equals("Revisor")) {
            return new Revisor();
        } else {
            return null;
        }
    }
}
