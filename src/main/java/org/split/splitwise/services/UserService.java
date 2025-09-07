package org.split.splitwise.services;

import org.split.splitwise.exceptions.UserAlreadyExistsException;
import org.split.splitwise.exceptions.UserNotFoundException;
import org.split.splitwise.models.User;

public interface UserService {
    public User registerUser(String name, String email, String phoneNumber, String password) throws UserAlreadyExistsException;

    User updateProfile(long userId, String password) throws UserNotFoundException;
}
