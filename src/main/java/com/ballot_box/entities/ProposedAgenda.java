package com.ballot_box.entities;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
@Embeddable
public class ProposedAgenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Lob
    private Set<String> policyPriorities;
    @Lob
    private String goalsAndObjectives;
    @Lob
    private String initiativeAndPrograms;
    @Lob
    private String economicPlans;
    @Lob
    private String educationReforms;
    @Lob
    private String healthCareStrategies;
    @Lob
    private String infrastructureDevelopments;
    @Lob
    private String socailWelfarePrograms;
    @Lob
    private String enviromentSustainbility;
    @Lob
    private String publicSafetyAndJustice;
    
    @Column(name = "created_at", nullable = false)
    private LocalDate createdAt = LocalDate.now();

    @Column(name = "updated_at", nullable = false)
    private LocalDate updatedAt = LocalDate.now();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<String> getPolicyPriorities() {
        return policyPriorities;
    }

    public void setPolicyPriorities(Set<String> policyPriorities) {
        this.policyPriorities = policyPriorities;
    }

    public String getGoalsAndObjectives() {
        return goalsAndObjectives;
    }

    public void setGoalsAndObjectives(String goalsAndObjectives) {
        this.goalsAndObjectives = goalsAndObjectives;
    }

    public String getInitiativeAndPrograms() {
        return initiativeAndPrograms;
    }

    public void setInitiativeAndPrograms(String initiativeAndPrograms) {
        this.initiativeAndPrograms = initiativeAndPrograms;
    }

    public String getEconomicPlans() {
        return economicPlans;
    }

    public void setEconomicPlans(String economicPlans) {
        this.economicPlans = economicPlans;
    }

    public String getEducationReforms() {
        return educationReforms;
    }

    public void setEducationReforms(String educationReforms) {
        this.educationReforms = educationReforms;
    }

    public String getHealthCareStrategies() {
        return healthCareStrategies;
    }

    public void setHealthCareStrategies(String healthCareStrategies) {
        this.healthCareStrategies = healthCareStrategies;
    }

    public String getInfrastructureDevelopments() {
        return infrastructureDevelopments;
    }

    public void setInfrastructureDevelopments(String infrastructureDevelopments) {
        this.infrastructureDevelopments = infrastructureDevelopments;
    }

    public String getSocailWelfarePrograms() {
        return socailWelfarePrograms;
    }

    public void setSocailWelfarePrograms(String socailWelfarePrograms) {
        this.socailWelfarePrograms = socailWelfarePrograms;
    }

    public String getEnviromentSustainbility() {
        return enviromentSustainbility;
    }

    public void setEnviromentSustainbility(String enviromentSustainbility) {
        this.enviromentSustainbility = enviromentSustainbility;
    }

    public String getPublicSafetyAndJustice() {
        return publicSafetyAndJustice;
    }

    public void setPublicSafetyAndJustice(String publicSafetyAndJustice) {
        this.publicSafetyAndJustice = publicSafetyAndJustice;
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
