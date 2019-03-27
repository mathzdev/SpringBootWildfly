package com.github.fenxlol.SpringBootWildfly.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    @RequestMapping("/teste/{nome}")
    String teste(@PathVariable String nome) {
        return "Testando " + nome + " !";
    }
}
