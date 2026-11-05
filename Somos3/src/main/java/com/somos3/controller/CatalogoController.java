package com.somos3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/catalogo")
public class CatalogoController {

    @GetMapping({"", "/", "/listado"})
    public String listado() {
        return "catalogo/listado";
    }
}
