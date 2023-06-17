package com.ballot_box.services;

import java.util.List;

import com.ballot_box.entities.User;
import com.ballot_box.exceptions.SomethingWentWrongException;
import com.ballot_box.exceptions.UserNotFoundException;

public interface UserService {
    public void addUser(User user) throws SomethingWentWrongException;
    public User getUser(int userId) throws UserNotFoundException, SomethingWentWrongException;
    public User getUserByUsername(String username) throws UserNotFoundException, SomethingWentWrongException;
    public List<User> getAllUsers() throws UserNotFoundException, SomethingWentWrongException;
    public boolean userExist(String username) throws SomethingWentWrongException;
}
