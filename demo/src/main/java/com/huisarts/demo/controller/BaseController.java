package com.huisarts.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

        @GetMapping(value = "/")
        public String base() {
            return "Hallo!";
        }


}
