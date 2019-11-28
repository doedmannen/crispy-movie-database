package com.example.demo.controllers;


import com.example.demo.configs.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    MyUserDetailService userDetailService;

    @GetMapping("/whoami")
    ResponseEntity<UserDetails> getMe(){
        return new ResponseEntity<>(userDetailService.currentUserDetails(), HttpStatus.OK);
    }

}
