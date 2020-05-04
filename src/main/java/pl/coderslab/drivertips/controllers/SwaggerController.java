package pl.coderslab.drivertips.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SwaggerController {

    private static final String SWAGGER_URL = "redirect:/swagger-ui.html";

    @RequestMapping(value = "driver-tips", method = RequestMethod.GET)
    public String swaggerMapping() {
        return SWAGGER_URL;
    }
}
