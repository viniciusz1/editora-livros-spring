package br.senai.sc.editoralivros.controller;

import br.senai.sc.editoralivros.dto.LivroDTO;
import br.senai.sc.editoralivros.model.entities.Autor;
import br.senai.sc.editoralivros.model.entities.Livro;
import br.senai.sc.editoralivros.model.entities.Status;
import br.senai.sc.editoralivros.model.service.LivroService;
import br.senai.sc.editoralivros.util.LivroUtil;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@Controller
@RequestMapping("editoralivros/livro")
public class LivroController {

    private LivroService livroService;

    @PostMapping
    public ResponseEntity<Object> save(
            @RequestParam("livro") String livroJson,
            @RequestParam("arquivo") MultipartFile file) {

        LivroUtil util = new LivroUtil();
        Livro livro = util.convertJsonToModel(livroJson);


        if(livroService.existsById(livro.getIsbn())){
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Já existe um registro com esse ISBN! hehe");
        }

        livro.setArquivo(file);
        livro.setStatus(Status.AGUARDANDO_REVISAO);
        return ResponseEntity.status(HttpStatus.OK).body(
                livroService.save(livro));
    }

    @PutMapping
    public ResponseEntity<Object> update(@RequestBody @Valid LivroDTO livro) {
        Optional<Livro> livroOptional = livroService.findById(livro.getIsbn());
        if(livroOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        }else{
            Livro livroModel = new Livro();
            BeanUtils.copyProperties(livro, livroModel);
            livroModel.setStatus(Status.AGUARDANDO_REVISAO);
            return ResponseEntity.status(HttpStatus.OK).body(livroService.save(livroModel));
        }
    }

    @GetMapping("/{isbn}")
    public ResponseEntity<Object> findById(@PathVariable(value = "isbn") Long id) {
        Optional<Livro> livroOptional = livroService.findById(id);
        if(livroOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(livroOptional.get());
        }
    }
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Livro>> findByStatus(@PathVariable(name = "status") Status status) {
        return ResponseEntity.status(HttpStatus.FOUND).body(livroService.findByStatus(status));

    }

    @GetMapping("/autor/{autor}")
    public ResponseEntity<List<Livro>> findByAutor(@PathVariable(name = "autor") Autor autor) {
        return ResponseEntity.status(HttpStatus.FOUND).body(livroService.findByAutor(autor));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Livro>> findAll() {
        return ResponseEntity.status(HttpStatus.FOUND).body(livroService.findAll());
    }

    @GetMapping("/page")
    public ResponseEntity<Page<Livro>> findAllPage(
            @PageableDefault(
                    page = 2,
                    size =9,
                    sort = "isbn",
                    direction = Sort.Direction.ASC
            )
            Pageable pageable
    ) {
        return ResponseEntity.status(HttpStatus.FOUND).body(livroService.findAll(pageable));
    }

    @DeleteMapping("/delete/{isbn}")
    public ResponseEntity<Object> delete(@PathVariable(value = "isbn") Long id) {
        if(!livroService.existsById(id)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        }else{
            livroService.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Livro deletado com sucesso");
        }
    }
}
