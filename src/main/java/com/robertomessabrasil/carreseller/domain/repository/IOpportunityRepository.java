package com.robertomessabrasil.carreseller.domain.repository;

import com.robertomessabrasil.carreseller.domain.entity.*;

import java.util.Optional;

public interface IOpportunityRepository {

    OpportunityEntity create(int userId, int storeId, CustomerEntity customer, VehicleEntity vehicle);

    Optional<OpportunityEntity> findById(int opportunityId);

    OpportunityEntity create(OpportunityEntity opportunity);
}
