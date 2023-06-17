package com.ballot_box.services;

import java.util.List;

import javax.swing.text.html.parser.Entity;

import org.hibernate.engine.jdbc.batch.spi.Batch;

import com.ballot_box.dao.CampaignDao;
import com.ballot_box.dao.CampaignDaoImp;
import com.ballot_box.dao.UserDao;
import com.ballot_box.dao.UserDaoImp;
import com.ballot_box.entities.Campaign;
import com.ballot_box.entities.Votes;
import com.ballot_box.exceptions.CampaignNotFoundException;
import com.ballot_box.exceptions.SomethingWentWrongException;
import com.ballot_box.exceptions.UserNotFoundException;
import com.ballot_box.utility.Design;
import com.ballot_box.utility.Print;
import com.mysql.cj.util.Util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;

public class CampaignServiceImp implements CampaignService{
    static CampaignDao campaignDao;
    static{
        campaignDao = new CampaignDaoImp();
    }
    @Override
    public void addCampaign(Campaign campaign) throws SomethingWentWrongException {
        try{
            campaignDao.addCampaign(campaign);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Campaign> getAllCampaign() throws CampaignNotFoundException, SomethingWentWrongException {
        try{
            return campaignDao.getAllCampaign();
        }
        catch(CampaignNotFoundException | NoResultException e){
            throw new CampaignNotFoundException("Campaign not found");
        }
        catch(Exception e){
            throw new SomethingWentWrongException("Something went Wrong, Try again later");
        }
    }

    @Override
    public List<Campaign> getAllActiveCampaign() throws CampaignNotFoundException, SomethingWentWrongException {
        return getAllCampaign().stream().filter(campaign -> campaign.getActive()).toList();
    }

    @Override
    public void addVotes(Campaign campaign, Votes vote) throws SomethingWentWrongException {
    }
    
}
