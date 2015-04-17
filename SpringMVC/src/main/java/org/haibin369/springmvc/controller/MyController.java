package org.haibin369.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/MyController")
public class MyController {

    @RequestMapping(method = RequestMethod.GET, value = "/helloSpringMVC")
    public String helloSpringMVC(String username, @RequestParam(value = "msg", required = false, defaultValue = "NONE") String message, Model model) {
        model.addAttribute("msg", message);
        model.addAttribute("username", username);
        return "success";
    }

}