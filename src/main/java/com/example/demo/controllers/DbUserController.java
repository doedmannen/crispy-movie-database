package com.example.demo.controllers;

import com.example.demo.entities.DbUser;
import com.example.demo.services.DbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class DbUserController {

    @Autowired
    DbUserService dbUserService;

    @GetMapping
    ResponseEntity<List<DbUser>> getAllUsers(){
        return new ResponseEntity<>(dbUserService.getAllUsers(), HttpStatus.OK);
    }


}
