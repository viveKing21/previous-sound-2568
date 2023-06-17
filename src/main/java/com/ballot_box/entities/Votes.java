package com.ballot_box.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class Votes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Election election;    

    @ManyToOne(fetch = FetchType.LAZY)
    private Campaign campaign;

    @OneToOne(mappedBy = "user_id", fetch = FetchType.LAZY)
    private User user;

    public Votes(){
        super();
    }
    public Votes(User user) {
        this.user = user;
    }
    public Election getElection() {
        return election;
    }
    public void setElection(Election election) {
        this.election = election;
    }
    public Campaign getCampaign() {
        return campaign;
    }
    public void setCampaign(Campaign campaign) {
        this.campaign = campaign;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    
}
