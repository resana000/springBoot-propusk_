package com.example.propusk.demo.service;

import com.example.propusk.demo.domain.User;
import com.example.propusk.demo.exception.UserAccessException;
import com.example.propusk.demo.state.State;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface EntranceService {

    ResponseEntity process(Long roomId, boolean entrance, Long keyId);

    ResponseEntity entrance(User user, Long roomId);

    boolean checkUserStateAndRoom(User user, Long roomId, boolean entrance)
            throws UserAccessException;

    ResponseEntity exit(User user, Long roomId);

    void changeState(User user, State state);

    boolean checkModulusRoomKey(User user, Long roomId);


}
