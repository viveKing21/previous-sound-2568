package com.ballot_box.dao;

import java.util.List;

import com.ballot_box.entities.Campaign;
import com.ballot_box.exceptions.CampaignNotFoundException;
import com.ballot_box.exceptions.SomethingWentWrongException;

public interface CampaignDao {
    public void addCampaign(Campaign campaign) throws SomethingWentWrongException;
    public List<Campaign> getAllCampaign() throws CampaignNotFoundException, SomethingWentWrongException;
}
