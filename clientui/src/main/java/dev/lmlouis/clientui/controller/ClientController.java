package dev.lmlouis.clientui.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;



@Controller
public class ClientController {
    
    @RequestMapping(value="/", method=RequestMethod.GET)
    public String requestMethodName(Model model) {
        return "Accueil";
    }
    
}
