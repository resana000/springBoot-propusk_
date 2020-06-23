package com.example.propusk.demo.controller;

import com.example.propusk.demo.domain.User;
import com.example.propusk.demo.repository.UserRepository;
import com.example.propusk.demo.service.EntranceService;
import com.example.propusk.demo.state.State;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
//http://localhost:8080/check?roomId=1&entrance=true&keyId=1
 */

@RestController
@Slf4j
public class UserController {

    @Autowired
    EntranceService entranceService;

    @Autowired
    UserRepository userRepository;


    @GetMapping("/check")
    public ResponseEntity e(@RequestParam Long roomId,
                            @RequestParam Boolean entrance,
                            @RequestParam Long keyId) {
        User user = userRepository.getUsers().get(keyId);
        log.info("user {}", user);

        return entranceService.process(roomId, entrance, keyId);

    }

    @PostMapping("user/create")
    public ResponseEntity create(@RequestBody User user){
        User newUser = userRepository.createUser(user.getId(), user.getName());
        return ResponseEntity.ok(newUser);
    }
}


