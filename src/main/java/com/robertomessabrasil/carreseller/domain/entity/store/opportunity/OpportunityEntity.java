package com.robertomessabrasil.carreseller.domain.entity.store.opportunity;

import com.robertomessabrasil.carreseller.domain.entity.store.CustomerEntity;
import com.robertomessabrasil.carreseller.domain.entity.store.VehicleEntity;
import com.robertomessabrasil.carreseller.domain.entity.user.UserEntity;
import io.github.robertomessabrasil.jwatch.observer.EventObserver;

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

    public void validate(EventObserver eventObserver) {
        // to do
    }
}
