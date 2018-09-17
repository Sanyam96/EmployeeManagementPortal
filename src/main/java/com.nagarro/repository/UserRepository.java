package com.nagarro.repository;

import com.nagarro.models.User;

/**
 * @author Sanyam Goel created on 17/9/18
 */
public interface UserRepository {

    User authenticate(String username, String password);

}