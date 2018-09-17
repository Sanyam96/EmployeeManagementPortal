package com.nagarro.services;

import com.nagarro.models.User;
import com.nagarro.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Sanyam Goel created on 17/9/18
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User authenticateUser(String username, String password) {
        return userRepository.authenticate(username, password);
    }

}