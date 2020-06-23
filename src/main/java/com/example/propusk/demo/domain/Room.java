package com.example.propusk.demo.domain;

import com.example.propusk.demo.state.State;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Data
public class Room {

    private Long id;
    private String name;
}
