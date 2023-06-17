package com.ballot_box.dao;

import com.ballot_box.entities.Candidate;
import com.ballot_box.exceptions.SomethingWentWrongException;
import com.ballot_box.exceptions.UserNotFoundException;

public interface CandidateDao {
    public void addCandidate(Candidate candidate) throws SomethingWentWrongException;
}
