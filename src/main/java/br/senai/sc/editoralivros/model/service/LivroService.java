package br.senai.sc.editoralivros.model.service;

import br.senai.sc.editoralivros.model.entities.Autor;
import br.senai.sc.editoralivros.model.entities.Livro;
import br.senai.sc.editoralivros.model.entities.Status;
import br.senai.sc.editoralivros.repository.LivroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Service
public class LivroService {
    private LivroRepository livroRepository;

    public Livro save(Livro livro) {
        return livroRepository.save(livro);
    }
    public boolean existsById(Long id) {
        return livroRepository.existsById(id);
    }

    public Optional<Livro> findById(Long id) {
        return livroRepository.findById(id);
    }

    public List<Livro> findByStatus(Status status) {
        return livroRepository.findByStatus(status);
    }

    public List<Livro> findByAutor(Autor autor) {
        return livroRepository.findByAutores(autor);
    }

    public List<Livro> findAll() {
        return livroRepository.findAll();
    }

    public void deleteById(Long id) {
        livroRepository.deleteById(id);
    }
}
