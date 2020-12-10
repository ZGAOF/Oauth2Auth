package com.csvw.oauth2auth.controller;

import com.csvw.oauth2auth.model.SysUser;
import com.csvw.oauth2auth.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/test")
public class ResController {

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/test")
    public SysUser testOauth(@RequestParam String username){
        /*List<Object[]> sysUser = userRepo.findByUsername("Leo");*/
        /*return userRepo.findById(1L).get();*/
        return userRepo.findByUsername(username);
    }
}

