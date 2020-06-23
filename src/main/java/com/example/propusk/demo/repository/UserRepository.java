package com.example.propusk.demo.repository;

import com.example.propusk.demo.domain.User;
import com.example.propusk.demo.state.State;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UserRepository {

    public static Map<Long, User> users = new HashMap<>();

    static {
        User user = new User();
        user.setId(1l);
        user.setName("John");
        user.setState(new State());
        users.put(user.getId(), user);
    }

    public static Map<Long, User> getUsers() {
        return users;
    }

    public User createUser(Long id, String name) {
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setState(new State());
        users.put(user.getId(), user);
        return user;
    }
}
