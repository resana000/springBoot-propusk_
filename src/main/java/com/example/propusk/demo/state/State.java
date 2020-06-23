package com.example.propusk.demo.state;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class State {
    public Boolean blockRoom;
    public Long inRoomId;
}

