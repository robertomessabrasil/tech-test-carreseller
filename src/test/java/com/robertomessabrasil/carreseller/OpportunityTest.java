package com.robertomessabrasil.carreseller;

import com.robertomessabrasil.carreseller.domain.entity.store.CustomerEntity;
import com.robertomessabrasil.carreseller.domain.entity.store.StoreEntity;
import com.robertomessabrasil.carreseller.domain.entity.store.VehicleEntity;
import com.robertomessabrasil.carreseller.domain.entity.store.opportunity.OpportunityEntity;
import com.robertomessabrasil.carreseller.domain.entity.user.UserEntity;
import com.robertomessabrasil.carreseller.domain.entity.user.UserRoleEnum;
import com.robertomessabrasil.carreseller.domain.entity.user.UserRoleVO;
import com.robertomessabrasil.carreseller.domain.entity.user.event.UserValidationEvent;
import com.robertomessabrasil.carreseller.domain.exception.InterruptException;
import com.robertomessabrasil.carreseller.domain.observer.EventObserver;
import com.robertomessabrasil.carreseller.domain.observer.listener.security.SecurityListener;
import com.robertomessabrasil.carreseller.domain.observer.listener.validation.ValidationListener;
import com.robertomessabrasil.carreseller.domain.repository.IOpportunityRepository;
import com.robertomessabrasil.carreseller.domain.service.StoreService;
import com.robertomessabrasil.carreseller.domain.service.user.event.InvalidRoleEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class OpportunityTest {

    @Mock
    IOpportunityRepository opportunityRepository;

    EventObserver eventObserver = new EventObserver();

    @BeforeEach
    void setUp() {

        SecurityListener securityListener = new SecurityListener();
        ValidationListener validationListener = new ValidationListener();

        securityListener.setEventsOfInterest(List.of(InvalidRoleEvent.class));
        validationListener.setEventsOfInterest(List.of(UserValidationEvent.class));
        eventObserver.subscribe(securityListener);
        eventObserver.subscribe(validationListener);

    }

    @Test
    void givenParameters_addOpportunity() throws InterruptException {

        int adminUserId = 1;
        UserRoleVO adminRole = new UserRoleVO(UserRoleEnum.ADMIN);
        UserEntity adminUser = new UserEntity();
        adminUser.setId(adminUserId);
        adminUser.setRole(adminRole);

        int storeId = 1;
        String storeName = "We'll sell your car";
        StoreEntity storeEntity = new StoreEntity();
        storeEntity.setId(storeId);
        storeEntity.setName(storeName);

        CustomerEntity customerEntity = new CustomerEntity();
        VehicleEntity vehicleEntity = new VehicleEntity();

        int opportunityId = 1;
        OpportunityEntity opportunityEntity = new OpportunityEntity();
        opportunityEntity.setId(opportunityId);

        when(this.opportunityRepository.create(any(OpportunityEntity.class), any(EventObserver.class))).thenReturn(opportunityEntity);

        OpportunityEntity createdOpportunity = StoreService.addOpportunity(adminUser, storeEntity, customerEntity, vehicleEntity, this.opportunityRepository, eventObserver);
        assertEquals(opportunityId, createdOpportunity.getId());

    }

}
