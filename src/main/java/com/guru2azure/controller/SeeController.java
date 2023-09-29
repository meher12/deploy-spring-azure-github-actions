package com.guru2azure.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class SeeController {

    @GetMapping("hello")
    public ResponseEntity<String> welcomeGithubAction(){
       return new ResponseEntity<>("Hello Spring to Azure by Github Actions!", HttpStatus.OK);
    }
}
