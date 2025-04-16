package com.raji.multistagedemo.service;

import com.raji.multistagedemo.model.User;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private final Map<Long, User> userMap = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    public User createUser(User user) {
        long id = idCounter.incrementAndGet();
        user.setId(id);
        userMap.put(id, user);
        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(userMap.values());
    }

    public User getUserById(Long id) {
        return userMap.get(id);
    }

    public User updateUser(Long id, User userData) {
        User existing = userMap.get(id);
        if (existing != null) {
            existing.setName(userData.getName());
            existing.setEmail(userData.getEmail());
        }
        return existing;
    }

    public void deleteUser(Long id) {
        userMap.remove(id);
    }
}
