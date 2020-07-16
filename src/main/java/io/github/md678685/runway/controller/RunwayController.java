package io.github.md678685.runway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Map;

@Controller
public class RunwayController {

    @GetMapping("/")
    public String home(Model model) {
        return "home";
    }

    @GetMapping("/hello")
    public ModelAndView hello(Principal principal) {
        return new ModelAndView("hello", Map.of("username", principal.getName()));
    }

}
