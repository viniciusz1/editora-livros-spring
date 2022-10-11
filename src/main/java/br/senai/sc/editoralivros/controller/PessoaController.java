package br.senai.sc.editoralivros.controller;

import br.senai.sc.editoralivros.dto.PessoaDTO;
import br.senai.sc.editoralivros.model.entities.Pessoa;
import br.senai.sc.editoralivros.model.service.PessoaService;
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
    public List<Pessoa> findAll() {
        return pessoaService.findAll();
    }
    @GetMapping("/{cpf}")
    public Optional<Pessoa> findById(@PathVariable(value = "cpf") Long cpf) {
        return pessoaService.findById(cpf);
    }
    @GetMapping("/{email}")
    public Optional<Pessoa> findByEmail(@PathVariable(value = "email") String email) {
       Optional<Pessoa> pessoaOptional = pessoaService.findByEmail(email);
       if(pessoaOptional.isEmpty()){
           return ResponseEntity.status(HttpStatus.NOT_FOUND)
                   .body("Nâo foi encontrado! hehe");
       };
       ResponseEntity.status(HttpStatus.OK)
               .body(pessoaOptional.get());

    }

    @PostMapping
    public <S extends Pessoa> S save(@RequestBody @Valid PessoaDTO pessoaDTO) {
        return pessoaService.save(pessoaDTO);
    }

    @DeleteMapping("/{cpf}")
    public void deleteById(@PathVariable(value = "cpf") Long aLong) {
        pessoaService.deleteById(aLong);
    }


    public PessoaController(PessoaService pessoaService){
       this.pessoaService = pessoaService;
   }
}
