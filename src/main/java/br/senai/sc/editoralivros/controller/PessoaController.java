package br.senai.sc.editoralivros.controller;

import br.senai.sc.editoralivros.dto.PessoaDTO;
import br.senai.sc.editoralivros.model.entities.Pessoa;
import br.senai.sc.editoralivros.model.service.PessoaService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("editoralivros/pessoa")
public class PessoaController {

    private PessoaService pessoaService;

    @GetMapping
    public ResponseEntity<List<Pessoa>> findAll() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(pessoaService.findAll());
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<Object> findById(@PathVariable(value = "cpf") Long cpf) {
        Optional<Pessoa> pessoaOptional = pessoaService.findById(cpf);
        if (pessoaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nâo foi encontrado! hehe - cpf");
        }
        ;
        return ResponseEntity.status(HttpStatus.OK)
                .body(pessoaOptional.get());
    }

    @GetMapping("/login/{email}")
    public ResponseEntity<Object> findByEmail(@PathVariable(value = "email") String email) {
        Optional<Pessoa> pessoaOptional = pessoaService.findByEmail(email);
        if (pessoaOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nâo foi encontrado! hehe - email");
        }
        ;
        return ResponseEntity.status(HttpStatus.OK)
                .body(pessoaOptional.get());
    }

    @PostMapping
    public ResponseEntity<Object> save(
            @RequestBody @Valid PessoaDTO pessoaDTO) {

        if (pessoaService.existsById(pessoaDTO.getCpf())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Já existe um registro com esse CPF! hehe");
        }
        if (pessoaService.existsByEmail(pessoaDTO.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Já existe um registro com esse email! hehe");
        }
        Pessoa pessoa = new Pessoa();
        BeanUtils.copyProperties(pessoaDTO, pessoa);
        return ResponseEntity.status(HttpStatus.OK)
                .body(pessoaService.save(pessoa));
    }

    @DeleteMapping("/{cpf}")
    public ResponseEntity<String> deleteById(@PathVariable(value = "cpf") Long cpf) {
        if (!pessoaService.existsById(cpf)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Nâo foi encontrado! hehe - cpf");
        }
        pessoaService.deleteById(cpf);
        return ResponseEntity.status(HttpStatus.OK)
                .body("Deletado com sucesso! hehe");
    }


    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }
}
