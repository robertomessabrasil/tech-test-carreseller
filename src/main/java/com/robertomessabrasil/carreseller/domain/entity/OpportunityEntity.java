package com.robertomessabrasil.carreseller.domain.entity;

import com.robertomessabrasil.carreseller.valueobject.OpportunityStatusVO;

public class OpportunityEntity {
    private int id;
    private OpportunityStatusVO status;
    private UserEntity user;
    private CustomerEntity customer;
    private VehicleEntity vehicle;

    public int getId() {
        return id;
    }

    public OpportunityEntity setId(int id) {
        this.id = id;
        return this;
    }

    public OpportunityStatusVO getStatus() {
        return status;
    }

    public OpportunityEntity setStatus(OpportunityStatusVO status) {
        this.status = status;
        return this;
    }

    public UserEntity getUser() {
        return user;
    }

    public OpportunityEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public OpportunityEntity setCustomer(CustomerEntity customer) {
        this.customer = customer;
        return this;
    }

    public VehicleEntity getVehicle() {
        return vehicle;
    }

    public OpportunityEntity setVehicle(VehicleEntity vehicle) {
        this.vehicle = vehicle;
        return this;
    }
}
