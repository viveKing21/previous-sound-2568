package com.ballot_box.services;

import com.ballot_box.dao.CandidateDao;
import com.ballot_box.dao.CandidateDaoImp;
import com.ballot_box.entities.Candidate;
import com.ballot_box.exceptions.SomethingWentWrongException;

public class CandidateServiceImp implements CandidateService{

    @Override
    public void addCandidate(Candidate candidate) throws SomethingWentWrongException {
        CandidateDao candidateDao = new CandidateDaoImp();
        candidateDao.addCandidate(candidate);
    }
    
}
