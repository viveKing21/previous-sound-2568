package com.ballot_box.entities;


import java.time.LocalDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "experience", nullable = false)
    private byte experience;
    @Column(name = "qualification", nullable = false)
    private String qualification;
    @Column(name = "contact_no", nullable = false)
    private long contactNo;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Campaign campaign;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(name = "user", nullable = false)
    private User user;
    
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt = LocalDate.now();

    public Candidate(int id, byte experience, String qualification, long contactNo, Campaign campaign) {
        this.id = id;
        this.experience = experience;
        this.qualification = qualification;
        this.contactNo = contactNo;
        this.campaign = campaign;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getExperience() {
        return experience;
    }

    public void setExperience(byte experience) {
        this.experience = experience;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public long getContactNo() {
        return contactNo;
    }

    public void setContactNo(long contactNo) {
        this.contactNo = contactNo;
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
