package com.ballot_box.entities;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Election {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "election_campaign",
        joinColumns = @JoinColumn(name = "election_id"),
        inverseJoinColumns = @JoinColumn(name = "campaign_id")
    )
    Set<Campaign> campaigns;

    @OneToMany(mappedBy = "election", cascade = CascadeType.ALL)
    private Set<Votes> votes;

    @Column(name = "created_at", nullable = false)
    private LocalDate createAt = LocalDate.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt = LocalDate.now();

    public Election(){
        super();
    }

    public Election(Set<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public long getId() {
        return id;
    }

    public Set<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(Set<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Set<Votes> getVotes() {
        return votes;
    }
    public void setVotes(Set<Votes> votes) {
        this.votes = votes;
    }
}
