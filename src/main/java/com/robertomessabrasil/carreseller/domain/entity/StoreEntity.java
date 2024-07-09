package com.robertomessabrasil.carreseller.domain.entity;

import java.util.List;

public class StoreEntity {
    private int id;
    private String cnpj;
    private String storeName;
    private List<UserEntity> userEntities;
    private List<OpportunityEntity> opportunities;

    public int getId() {
        return id;
    }

    public StoreEntity setId(int id) {
        this.id = id;
        return this;
    }

    public String getCnpj() {
        return cnpj;
    }

    public StoreEntity setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public String getStoreName() {
        return storeName;
    }

    public StoreEntity setStoreName(String storeName) {
        this.storeName = storeName;
        return this;
    }

    public List<UserEntity> getUserEntities() {
        return userEntities;
    }

    public StoreEntity setUserEntities(List<UserEntity> userEntities) {
        this.userEntities = userEntities;
        return this;
    }

    public List<OpportunityEntity> getOpportunities() {
        return opportunities;
    }

    public StoreEntity setOpportunities(List<OpportunityEntity> opportunities) {
        this.opportunities = opportunities;
        return this;
    }

    public void addOpportunity(OpportunityEntity opportunity) {
        this.opportunities.add(opportunity);
    }
}
