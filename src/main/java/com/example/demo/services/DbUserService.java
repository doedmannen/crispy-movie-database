package com.example.demo.services;

import com.example.demo.configs.MyUserDetailService;
import com.example.demo.entities.DbUser;
import com.example.demo.repos.DbUserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbUserService {
    @Autowired
    DbUserRepo repo;

    @Autowired
    MyUserDetailService userDetailService;

    public List<DbUser> getAllUsers(){
        return repo.findAll();
    }
}
