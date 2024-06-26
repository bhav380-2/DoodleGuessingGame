package org.app.doodlegame.controller;

import  org.app.doodlegame.service.DoodleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class HomeController {

    // @Autowired
    // private DoodleService doodleService;

    @GetMapping("/")
    public String home() {
        return "home";
    }
    

    @GetMapping("/playSolo")
    public String playSolo(Model model) {
        // model.addAttribute("isLogin", false);
        // return "jdjdidi";
        return "playSolo";
    }
}

