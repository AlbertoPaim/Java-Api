package br.com.albertopaim.apirest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")

public class TesteController {
    @GetMapping
    public String teste() {
        return "tudo ok, primeiro endpoint";
    }
}
