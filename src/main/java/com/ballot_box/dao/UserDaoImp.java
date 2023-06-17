package com.ballot_box.dao;

import java.util.List;

import jakarta.persistence.Query;

import com.ballot_box.entities.User;
import com.ballot_box.exceptions.SomethingWentWrongException;
import com.ballot_box.exceptions.UserNotFoundException;
import com.ballot_box.utility.Connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;

public class UserDaoImp implements UserDao{

    @Override
    public void addUser(User user) throws SomethingWentWrongException{
        EntityManager entityManager = Connection.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
        }
        catch(IllegalArgumentException | IllegalStateException | PersistenceException e){
            transaction.rollback();
            throw e;
        }
        finally{
            entityManager.close();
        }
    }

    @Override
    public User getUser(int userId) throws UserNotFoundException, SomethingWentWrongException {
        EntityManager entityManager = Connection.getEntityManager();

        User user = entityManager.find(User.class, userId);

        entityManager.close();

        if(user == null){
            throw new UserNotFoundException("No user exist with this id ("+userId+")");
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() throws UserNotFoundException, SomethingWentWrongException {
        EntityManager entityManager = Connection.getEntityManager();

        Query query = entityManager.createQuery("SELECT User FROM User", User.class);
        List<User> users = query.getResultList();

        entityManager.close();

        if(users.isEmpty()) throw new UserNotFoundException("User list is empty");
        return users;
    }

    @Override
    public User getUserByUsername(String username) throws UserNotFoundException, SomethingWentWrongException {
        EntityManager entityManager = Connection.getEntityManager();

        Query query = entityManager.createQuery("SELECT U FROM User U WHERE U.username=:username", User.class);
        query.setParameter("username", username);
        User user = (User) query.getSingleResult();

        entityManager.close();
        if(user == null) throw new UserNotFoundException("No user exist with this username ("+username+")");
        return user;
    }
    
}
