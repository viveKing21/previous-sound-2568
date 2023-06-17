package com.ballot_box.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    @Column(name = "house_no")
    public String houseNo;

    @Column(name = "zipcode")
    public int zipCode;

    @Column(name = "state")
    public String state;
    
    @Column(name = "country")
    public String country;
    
    @Column(name = "created_at")
    public LocalDate createdAt = LocalDate.now();

    @Column(name = "updated_at")
    public LocalDate updatedAt = LocalDate.now();

    public Address(){
        super();
    }
    public Address(String houseNo, int zipCode, String state, String country) {
        this.houseNo = houseNo;
        this.zipCode = zipCode;
        this.state = state;
        this.country = country;
    }

    
}
