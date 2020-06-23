package com.example.propusk.demo.controller;

import com.example.propusk.demo.domain.User;
import com.example.propusk.demo.repository.UserRepository;
import com.example.propusk.demo.service.EntranceService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class UserControllerTest {

    @LocalServerPort
    int localServerPort;

    @Autowired
    UserController userController;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void e() {

        User user = userRepository.getUsers().get(1l);
        log.info("user {}", user);

        ResponseEntity responseEntity =
                testRestTemplate.getForEntity("http://localhost:"+localServerPort
                        +"/check?roomId=2&entrance=true&keyId=1"
                        , String.class);
        log.info("response {}" + responseEntity.getStatusCode());

        int response1 = responseEntity.getStatusCode().value();

        ResponseEntity responseEntity2 =
                testRestTemplate.getForEntity("http://localhost:"+localServerPort
                                +"/check?roomId=3&entrance=true&keyId=1"
                        , String.class);


        int response2 = responseEntity2.getStatusCode().value();

        assertTrue(response1 == 200);
        assertFalse(response2 == 200);



    }

    @Test
    void create() {
    }
}