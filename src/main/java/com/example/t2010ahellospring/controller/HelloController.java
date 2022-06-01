package com.example.t2010ahellospring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/hello")
public class HelloController {

    @RequestMapping(path = "world", method = RequestMethod.GET)
    public String say () {
        return "Hello world";
    }

    @RequestMapping(path = "talk", method = RequestMethod.GET)
    public  String talk() {
        return "Talk to world";
    }

    @RequestMapping(path = "student", method = RequestMethod.GET)
    public  String Student() {
        return "Talk to world";
    }
}
