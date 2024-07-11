package com.robertomessabrasil.carreseller.domain.service;

import com.robertomessabrasil.carreseller.domain.entity.store.CustomerEntity;
import com.robertomessabrasil.carreseller.domain.entity.store.StoreEntity;
import com.robertomessabrasil.carreseller.domain.entity.store.VehicleEntity;
import com.robertomessabrasil.carreseller.domain.entity.store.opportunity.OpportunityEntity;
import com.robertomessabrasil.carreseller.domain.entity.store.opportunity.OpportunityStatusEnum;
import com.robertomessabrasil.carreseller.domain.entity.store.opportunity.OpportunityStatusVO;
import com.robertomessabrasil.carreseller.domain.entity.user.UserEntity;
import com.robertomessabrasil.carreseller.domain.entity.user.UserRoleEnum;
import com.robertomessabrasil.carreseller.domain.entity.user.UserRoleVO;
import com.robertomessabrasil.carreseller.domain.exception.InterruptException;
import com.robertomessabrasil.carreseller.domain.observer.EventObserver;
import com.robertomessabrasil.carreseller.domain.repository.IOpportunityRepository;
import com.robertomessabrasil.carreseller.domain.repository.IStoreRepository;

import java.util.List;
import java.util.Optional;

import static com.robertomessabrasil.carreseller.domain.service.user.UserService.checkRole;

public class StoreService {

    public static StoreEntity createStore(UserEntity adminUser, StoreEntity store, IStoreRepository storeRepository, EventObserver eventObserver) throws InterruptException {
        checkRole(adminUser, List.of(new UserRoleVO(UserRoleEnum.ADMIN)), eventObserver);
        store.validate(eventObserver);
        return storeRepository.create(store);
    }

    public static OpportunityEntity addOpportunity(
            UserEntity user, StoreEntity store, CustomerEntity customer, VehicleEntity vehicle,
            IOpportunityRepository opportunityRepository, EventObserver eventObserver) throws InterruptException {

        checkRole(user, List.of(new UserRoleVO(UserRoleEnum.ADMIN), new UserRoleVO(UserRoleEnum.STORE_USER)), eventObserver);

        OpportunityEntity opportunity = new OpportunityEntity()
                .setUser(user)
                .setCustomer(customer)
                .setVehicle(vehicle)
                .setStatus(new OpportunityStatusVO(OpportunityStatusEnum.NEW));

        opportunity.validate(eventObserver);

        OpportunityEntity createdOpportunity = opportunityRepository.create(opportunity, eventObserver);

        store.addOpportunity(createdOpportunity);

        return createdOpportunity;
    }

    public static Optional<StoreEntity> findStoreById(int storeId, IStoreRepository storeRepository, EventObserver eventObserver) {
        return storeRepository.findById(storeId, eventObserver);
    }

}
