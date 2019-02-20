package com.servlets;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@org.springframework.stereotype.Controller
public class Controller {
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getData() {
        ModelAndView model = new ModelAndView("hello");
        return model;
    }
}
