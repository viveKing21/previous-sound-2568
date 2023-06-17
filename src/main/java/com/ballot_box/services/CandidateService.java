package com.ballot_box.services;

import com.ballot_box.entities.Candidate;
import com.ballot_box.exceptions.SomethingWentWrongException;

public interface CandidateService {
     public void addCandidate(Candidate candidate ) throws SomethingWentWrongException;
}
