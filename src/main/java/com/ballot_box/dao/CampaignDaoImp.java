package com.ballot_box.dao;

import java.util.List;

import org.hibernate.mapping.Set;

import com.ballot_box.entities.Campaign;
import com.ballot_box.exceptions.CampaignNotFoundException;
import com.ballot_box.exceptions.SomethingWentWrongException;
import com.ballot_box.utility.Connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;

public class CampaignDaoImp implements CampaignDao{

    @Override
    public void addCampaign(Campaign campaign) throws SomethingWentWrongException {
        EntityManager entityManager = Connection.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try{
            transaction.begin();
            entityManager.persist(campaign);
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
    public List<Campaign> getAllCampaign() throws CampaignNotFoundException, SomethingWentWrongException {
        EntityManager entityManager = Connection.getEntityManager();

        Query query = entityManager.createQuery("SELECT C FROM Campaign C", Campaign.class);
        List<Campaign> campaigns  = query.getResultList();

        entityManager.close();

        if(campaigns.isEmpty()) throw new CampaignNotFoundException("Campaign list is empty");
        return campaigns;
    }
}
