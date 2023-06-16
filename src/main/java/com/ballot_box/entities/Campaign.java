package com.ballot_box.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

public class Campaign {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String logo;
    private String slogan;
    
    @Embedded
    @Column(name = "proposed_agenda", nullable = false)
    private ProposedAgenda proposedAgenda;

    @OneToOne
    private Candidate candidate;
    
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt = LocalDate.now();

    public Campaign(int id, String title, String logo, String slogan, ProposedAgenda proposedAgenda) {
        this.id = id;
        this.title = title;
        this.logo = logo;
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

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getSlogan() {
        return slogan;
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

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDate updatedAt) {
        this.updatedAt = updatedAt;
    }
    
}
