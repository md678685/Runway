package io.github.md678685.runway.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class RegisterController {

    @GetMapping("/register")
    public ModelAndView register(@RequestParam(required = false) String returnTo) {
        return new ModelAndView("register", Map.of("returnTo", returnTo));
    }

}
