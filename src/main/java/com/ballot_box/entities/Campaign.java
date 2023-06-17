package com.ballot_box.entities;

import java.time.LocalDate;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    @Lob
    private String slogan;
    
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "proposed_agenda_id")
    private ProposedAgenda proposedAgenda;
    

    @OneToOne(mappedBy = "campaign")
    private Candidate candidate;

    @ManyToMany(mappedBy = "campaigns")
    Set<Election> elections;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL)
    private Set<Votes> votes;

    private boolean active = true;
    
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt = LocalDate.now();

    public Campaign(){
        super();
    }

    public Campaign(String title, String slogan, ProposedAgenda proposedAgenda) {
        this.title = title;
        this.slogan = slogan;
        this.proposedAgenda = proposedAgenda;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setActive(boolean isActive) {
        this.active = isActive;
    }

    public boolean getActive() {
        return active;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public ProposedAgenda getProposedAgenda() {
        return proposedAgenda;
    }

    public void setProposedAgenda(ProposedAgenda proposedAgenda) {
        this.proposedAgenda = proposedAgenda;
    }

    public Candidate getCandidate() {
        return candidate;
    }

    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
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
