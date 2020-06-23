package com.example.propusk.demo.domain;

import com.example.propusk.demo.state.State;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Data
public class User {

    private State state;
    private Long id;
    private String name;
}
