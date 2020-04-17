package pl.coderslab.drivertips.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/multimedia")
class MultimediaController {

    @GetMapping("/{id}")
    public byte[] get(@PathVariable Long id){
        //zwroc content
        return null;
    }
}
