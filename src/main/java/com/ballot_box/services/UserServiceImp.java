package com.ballot_box.services;

import java.util.List;

import com.ballot_box.dao.UserDao;
import com.ballot_box.dao.UserDaoImp;
import com.ballot_box.entities.User;
import com.ballot_box.exceptions.SomethingWentWrongException;
import com.ballot_box.exceptions.UserNotFoundException;

import jakarta.persistence.NoResultException;

public class UserServiceImp implements UserService{
    static UserDao userDao;
    static{
        userDao = new UserDaoImp();
    }
    @Override
    public void addUser(User user) throws SomethingWentWrongException {
        try{
            userDao.addUser(user);
        }
        catch(Exception e){
            throw new SomethingWentWrongException("Something went Wrong, Try again later");
        }
    }

    @Override
    public User getUser(int userId) throws UserNotFoundException, SomethingWentWrongException {
        try{
            return userDao.getUser(userId);
        }
        catch(UserNotFoundException | NoResultException e){
            throw new UserNotFoundException("User not found");
        }
        catch(Exception e){
            throw new SomethingWentWrongException("Something went Wrong, Try again later");
        }
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException, SomethingWentWrongException {
        try{
            return userDao.getUserByUsername(username);
        }
        catch(UserNotFoundException | NoResultException e){
            throw new UserNotFoundException("User not found");
        }
        catch(Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something went Wrong, Try again later");
        }
    }

    @Override
    public List<User> getAllUsers() throws UserNotFoundException, SomethingWentWrongException {
        try{
            return userDao.getAllUsers();
        }
        catch(UserNotFoundException | NoResultException e){
            throw new UserNotFoundException("User not found");
        }
        catch(Exception e){
            throw new SomethingWentWrongException("Something went Wrong, Try again later");
        }
    }

    @Override
    public boolean userExist(String username) throws SomethingWentWrongException {
        try{
            User user = getUserByUsername(username);
            return user != null;
        }
        catch(UserNotFoundException | NoResultException e){
            return false;
        }
        catch(Exception e){
            throw new SomethingWentWrongException("Something went Wrong, Try again later");
        }
    }
    
}
