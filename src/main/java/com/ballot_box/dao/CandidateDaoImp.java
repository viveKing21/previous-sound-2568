package com.ballot_box.dao;

import com.ballot_box.entities.Candidate;
import com.ballot_box.entities.User;
import com.ballot_box.exceptions.SomethingWentWrongException;
import com.ballot_box.exceptions.UserNotFoundException;
import com.ballot_box.utility.Connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;

public class CandidateDaoImp implements CandidateDao{

    @Override
    public void addCandidate(Candidate candidate) throws SomethingWentWrongException {
        EntityManager entityManager = Connection.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            entityManager.persist(candidate);
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
    
}
