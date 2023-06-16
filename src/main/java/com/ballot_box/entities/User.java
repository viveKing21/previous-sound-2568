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
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "username", nullable = false, unique = true, length = 20)
    private String username;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Profile profile;

    @OneToOne(mappedBy = "user")
    private Candidate candidate;
    
    transient private String password;

    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt;
    
    private boolean deleted = false;

    public User(){
        super();
    }
    public User(String username, String email, String password, Profile profile, Candidate candidate){
        this.username = username;
        this.email = email;
        this.password = password;
        this.profile = profile;
        this.candidate = candidate;
    }
    
    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Profile getProfile() {
        return profile;
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
    }
    public Candidate getCandidate() {
        return candidate;
    }
    public void setCandidate(Candidate candidate) {
        this.candidate = candidate;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
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
    public boolean isDeleted() {
        return deleted;
    }
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
    @Override
    public String toString() {
        return "User [id=" + id + ", username=" + username + ", email=" + email + ", password=" + password
                + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", deleted=" + deleted + "]";
    }
    
}
