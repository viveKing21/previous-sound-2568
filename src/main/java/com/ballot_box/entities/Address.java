package com.ballot_box.entities;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;

@Entity
@Embeddable
public class Address {
    @Column(name = "house_no", nullable = true)
    public String houseNo;
    @Column(name = "zipcode", nullable = true)
    public int zipCode;
    @Column(name = "state", nullable = true)
    public String state;
    @Column(name = "country", nullable = true)
    public String country;
    
    @Column(name = "created_at", nullable = false)
    public LocalDate createdAt = LocalDate.now();

    @Column(name = "updated_at", nullable = false)
    public LocalDate updatedAt = LocalDate.now();
}
