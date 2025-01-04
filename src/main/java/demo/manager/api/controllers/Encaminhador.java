package demo.manager.api.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Encaminhador {

    // @RequestMapping("/{path:[^\\.]*}")
    // public String redirectToFrontend() {
    //     // Redireciona para o arquivo index.html do Angular
    //     return "forward:/index.html";
    // }

    @RequestMapping(value = "/**/{path:[^\\.]*}")
    public String redirectToFrontend() {
        return "forward:/index.html";
    }

    @RequestMapping(value = "/f/**/{path:[^\\.]*}")
    public String redirectToFrontend2() {
        return "forward:/index.html";
    }
    
}
