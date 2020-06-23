package com.example.propusk.demo.service;

import com.example.propusk.demo.domain.User;
import com.example.propusk.demo.dto.RoomStateResponse;
import com.example.propusk.demo.exception.UserAccessException;
import com.example.propusk.demo.repository.UserRepository;
import com.example.propusk.demo.state.State;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.Objects;
import java.util.Optional;

@Service
@Data
@Slf4j
public class EntranceServiceImpl implements EntranceService {

    private static final int OPEN = 200;
    private static final int BLOCK = 403;
    private static final int ERROR = 500;

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity process(Long roomId, boolean entrance, Long keyId) {
        User user = userRepository.getUsers().get(keyId);
        boolean access = checkUserStateAndRoom(user, roomId, entrance);

        if (!access)
            return createResponse(BLOCK, "not allowed");

        if (entrance) return entrance(user, roomId);
        if (!entrance) {
            return exit(user, roomId);
        } else {
            return createResponse(this.ERROR, "error");
        }
    }

    @Override
    public boolean checkUserStateAndRoom(User user, Long roomId, boolean entrance) throws UserAccessException {

        State userState = user.getState();

        if (!checkModulusRoomKey(user, roomId))
            return false;

        if (userState.getInRoomId() == null)
            return true;

        if (entrance && !userState.getBlockRoom())
            return true;

        if (entrance
                && userState.getInRoomId() == roomId
                && !userState.getBlockRoom()) {
            return true;
        }

        if (!entrance
                && userState.getInRoomId() == roomId) {
            return true;
        }

        return false;
    }

    @Override
    public ResponseEntity entrance(User user, Long roomId) {
        State state = new State(true, roomId);
        changeState(user, state);
        return createResponse(this.OPEN, "success entrance");
    }

    @Override
    public ResponseEntity exit(User user, Long roomId) {
        State state = new State(false, roomId);
        changeState(user, state);
        return createResponse(this.OPEN, "success exit");
    }

    @Override
    public void changeState(User user, State state) {
        user.setState(state);
    }

    @Override
    public boolean checkModulusRoomKey(User user, Long roomId) {
        if (user.getId() % roomId >= 1)
            return true;

        return false;
    }

    private ResponseEntity createResponse(int i, String message) {
        RoomStateResponse roomStateResponse = new RoomStateResponse();
        roomStateResponse.setResponse(i);
        roomStateResponse.setMessage(message);
        return ResponseEntity.status(i).body(roomStateResponse);
    }
}
