package io.github.md678685.runway.controller;

import io.github.md678685.runway.controller.model.UserRegisterDto;
import io.github.md678685.runway.exception.UserRegisterException;
import io.github.md678685.runway.service.RunwayUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Map;
import java.util.logging.Logger;

@Controller
public class RegisterController extends RunwayController {

    private static final Logger LOGGER = Logger.getLogger(RegisterController.class.getName());

    @Autowired
    private RunwayUserService userService;

    @GetMapping("/register")
    public ModelAndView form() {
        LOGGER.info("we getting bois");
        ModelAndView mav = new ModelAndView("register/form", "userRegister", new UserRegisterDto());
        return mav;
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView submit(@ModelAttribute("userRegister") @Valid UserRegisterDto userForm, BindingResult bindingResult) {
        LOGGER.info("we submitting bois");
        if (bindingResult.hasErrors()) {
            return new ModelAndView("register/form", Map.of("userRegister", userForm, "errors", bindingResult.getAllErrors()));
        }

        ModelAndView mav;
        try {
            userService.registerUser(userForm);
            mav = new ModelAndView("redirect:/register/verify-email");
        } catch (UserRegisterException e) {
            mav = new ModelAndView("register/form", Map.of("userRegister", userForm, "error", e.getMessage()));
        }
        return mav;
    }

    @GetMapping("/register/verify-email")
    public ModelAndView verifyEmail() {
        ModelAndView mav = new ModelAndView("register/verifyEmail");
        return mav;
    }

}
