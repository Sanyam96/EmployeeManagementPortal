package com.nagarro.services;

import com.nagarro.models.User;

/**
 * @author Sanyam Goel created on 17/9/18
 */
public interface UserService {

    User authenticateUser(String username, String password);

}