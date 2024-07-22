package com.robertomessabrasil.carreseller.domain.repository;

import com.robertomessabrasil.carreseller.domain.entity.store.CustomerEntity;
import com.robertomessabrasil.carreseller.domain.entity.store.VehicleEntity;
import com.robertomessabrasil.carreseller.domain.entity.store.opportunity.OpportunityEntity;
import io.github.robertomessabrasil.jwatch.observer.EventObserver;

import java.util.Optional;

public interface IOpportunityRepository {

    OpportunityEntity create(int userId, int storeId, CustomerEntity customer, VehicleEntity vehicle);

    Optional<OpportunityEntity> findById(int opportunityId);

    OpportunityEntity create(OpportunityEntity opportunity, EventObserver eventObserver);
}
