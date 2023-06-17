package com.ballot_box.services;

import java.util.List;

import com.ballot_box.dao.ElectionDao;
import com.ballot_box.dao.ElectionDaoImp;
import com.ballot_box.entities.Campaign;
import com.ballot_box.entities.Election;
import com.ballot_box.exceptions.NotFoundException;
import com.ballot_box.exceptions.SomethingWentWrongException;
import com.ballot_box.exceptions.UserNotFoundException;
import com.ballot_box.utility.Connection;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.transaction.Transaction;

public class ElectionServiceImp implements ElectionService{
    static ElectionDao electionDao;

    static{
        electionDao = new ElectionDaoImp();
    }
    @Override
    public void addElection(Election election) throws SomethingWentWrongException {
        try{
            electionDao.addElection(election);
        }
        catch(Exception e){
            throw new SomethingWentWrongException("Something went Wrong, Try again later");
        }
    }

    @Override
    public List<Election> getAllElection() throws NotFoundException, SomethingWentWrongException {
        try{
            return electionDao.getAllElection();
        }
        catch(NotFoundException | NoResultException e){
            throw new NotFoundException("No Election found");
        }
        catch(Exception e){
            e.printStackTrace();
            throw new SomethingWentWrongException("Something went Wrong, Try again later");
        }
    }

    @Override
    public Election getCurrentElection() throws NotFoundException, SomethingWentWrongException {
        return getAllElection().get(0);
    }

    @Override
    public void removeElection(Election election) throws SomethingWentWrongException {
        electionDao.removeElection(election);
    }

    @Override
    public void removeCurrentElection() throws NotFoundException, SomethingWentWrongException {
       electionDao.removeElection(getCurrentElection());
    }

    @Override
    public void addCampaign(Campaign campaign) throws SomethingWentWrongException {
        electionDao.addCampaign(campaign);;
    }
    
}
