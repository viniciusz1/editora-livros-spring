package br.senai.sc.editoralivros.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/editora-livros-api")
public class FrontController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/livros")
    public String livro() {
        return "cadastro-livros";
    }

    @GetMapping("/usuarios")
    public String usuario() {
        return "cadastro-usuarios";
    }


}
