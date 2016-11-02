package com.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by bowen on 16-4-7下午11:15.
 */
@Controller
public class LoginController {

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView getLoginPage(@RequestParam String error) {
        return new ModelAndView("login", "error", error);
    }
}
