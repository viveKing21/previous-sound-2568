package com.ballot_box.dao;

import java.util.List;

import com.ballot_box.entities.Campaign;
import com.ballot_box.entities.Election;
import com.ballot_box.entities.User;
import com.ballot_box.exceptions.NotFoundException;
import com.ballot_box.exceptions.SomethingWentWrongException;
import com.ballot_box.exceptions.UserNotFoundException;
import com.ballot_box.utility.Connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class ElectionDaoImp implements ElectionDao{

    @Override
    public void addElection(Election election) throws SomethingWentWrongException {
        EntityManager entityManager = Connection.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            entityManager.persist(election);
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
    public List<Election> getAllElection() throws NotFoundException, SomethingWentWrongException {
        EntityManager entityManager = Connection.getEntityManager();

        Query query = entityManager.createQuery("SELECT E FROM Election E", Election.class);
        List<Election> elections = query.getResultList();

        entityManager.close();

        if(elections.isEmpty()) throw new NotFoundException("No elections going on");
        return elections;
    }

    @Override
    public void removeElection(Election election) throws SomethingWentWrongException {
        EntityManager entityManager = Connection.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            entityManager.remove(entityManager.merge(election));
            transaction.commit();
        }
        catch(IllegalStateException | PersistenceException e){
            transaction.rollback();
            throw new SomethingWentWrongException("Failed to delete election");
        }
    }

    @Override
    public void addCampaign(Campaign campaign) throws SomethingWentWrongException {
        EntityManager entityManager = Connection.getEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try{
            entityTransaction.begin();
            
            Query query = entityManager.createQuery("SELECT E FROM Election E", Election.class);
            Election election = (Election) query.getSingleResult();

            election.getCampaigns().add(campaign);

            entityTransaction.commit();
        }
        catch(Exception e){
            e.printStackTrace();
            entityTransaction.rollback();
            throw new SomethingWentWrongException("Something went wrong, try again later");
        }

        entityManager.close();
    }
    
}
