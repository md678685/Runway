package io.github.md678685.runway.controller;

import org.springframework.web.servlet.ModelAndView;

public abstract class RunwayController {

    protected ModelAndView fillModel(ModelAndView mav) {
        return mav;
    }

}
