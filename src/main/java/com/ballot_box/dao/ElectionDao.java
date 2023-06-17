package com.ballot_box.dao;

import java.util.List;

import com.ballot_box.entities.Campaign;
import com.ballot_box.entities.Election;
import com.ballot_box.exceptions.NotFoundException;
import com.ballot_box.exceptions.SomethingWentWrongException;

public interface ElectionDao {
    public void addElection(Election election) throws SomethingWentWrongException;
    public List<Election> getAllElection() throws NotFoundException, SomethingWentWrongException;
    public void removeElection(Election election) throws SomethingWentWrongException;
    public void addCampaign(Campaign campaign) throws SomethingWentWrongException;
}
