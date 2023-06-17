package com.ballot_box.services;

import java.util.List;

import com.ballot_box.entities.Campaign;
import com.ballot_box.entities.Votes;
import com.ballot_box.exceptions.CampaignNotFoundException;
import com.ballot_box.exceptions.SomethingWentWrongException;

public interface CampaignService {
    public void addCampaign(Campaign campaign) throws SomethingWentWrongException;
    public List<Campaign> getAllCampaign() throws CampaignNotFoundException, SomethingWentWrongException;
    public List<Campaign> getAllActiveCampaign() throws CampaignNotFoundException, SomethingWentWrongException;
    public void addVotes(Campaign campaign, Votes vote) throws SomethingWentWrongException;
}
