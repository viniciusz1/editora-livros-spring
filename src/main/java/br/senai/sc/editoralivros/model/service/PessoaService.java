package br.senai.sc.editoralivros.model.service;

import br.senai.sc.editoralivros.model.entities.Pessoa;
import br.senai.sc.editoralivros.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class PessoaService {
    private PessoaRepository pessoaDAO;

    public List<Pessoa> findAll() {
        return pessoaDAO.findAll();
    }

    public <S extends Pessoa> S save(S entity) {
        return pessoaDAO.save(entity);
    }

    public boolean existsById(Long cpf) {
        return pessoaDAO.existsById(cpf);
    }

    public Optional<Pessoa> findById(Long cpf) {
        return pessoaDAO.findById(cpf);
    }

    public void deleteById(Long aLong) {
        pessoaDAO.deleteById(aLong);
    }
    public Optional<Pessoa> findByEmail(String email) {
        return pessoaDAO.findByEmail(email);
    }


    public PessoaService(PessoaRepository pessoaDAO){
        this.pessoaDAO = pessoaDAO;
    }

    public boolean existsByEmail(String email) {
        return pessoaDAO.existsByEmail(email);
    }
}
