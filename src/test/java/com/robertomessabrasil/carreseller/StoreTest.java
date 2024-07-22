package com.robertomessabrasil.carreseller;

import com.robertomessabrasil.carreseller.domain.entity.store.StoreEntity;
import com.robertomessabrasil.carreseller.domain.entity.user.UserEntity;
import com.robertomessabrasil.carreseller.domain.entity.user.UserRoleEnum;
import com.robertomessabrasil.carreseller.domain.entity.user.UserRoleVO;
import com.robertomessabrasil.carreseller.domain.entity.user.event.UserValidationEvent;
import com.robertomessabrasil.carreseller.domain.repository.IOpportunityRepository;
import com.robertomessabrasil.carreseller.domain.repository.IStoreRepository;
import com.robertomessabrasil.carreseller.domain.repository.IUserRepository;
import com.robertomessabrasil.carreseller.domain.service.StoreService;
import com.robertomessabrasil.carreseller.domain.service.user.event.InvalidRoleEvent;
import com.robertomessabrasil.carreseller.listener.SecurityListener;
import com.robertomessabrasil.carreseller.listener.ValidationListener;
import io.github.robertomessabrasil.jwatch.exception.InterruptException;
import io.github.robertomessabrasil.jwatch.observer.EventObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StoreTest {

    @Mock
    IStoreRepository storeRepository;

    EventObserver eventObserver = new EventObserver();

    @BeforeEach
    void setUp() {

        SecurityListener securityListener = new SecurityListener();
        ValidationListener validationListener = new ValidationListener();

        securityListener.addEvent(InvalidRoleEvent.class);
        validationListener.addEvent(UserValidationEvent.class);
        eventObserver.subscribe(securityListener);
        eventObserver.subscribe(validationListener);

    }

    @Test
    void givenParameters_createStore() throws InterruptException {

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

        when(this.storeRepository.create(storeEntity)).thenReturn(storeEntity);

        storeEntity = StoreService.createStore(adminUser, storeEntity, this.storeRepository, this.eventObserver);
        assertEquals(storeId, storeEntity.getId());

    }

    @Test
    void givenStoreId_findStore() {

        int storeId = 1;
        StoreEntity storeEntity = new StoreEntity();
        storeEntity.setId(storeId);

        when(this.storeRepository.findById(storeId, this.eventObserver)).thenReturn(Optional.of(storeEntity));

        Optional<StoreEntity> foundStore = StoreService.findStoreById(storeId, this.storeRepository, this.eventObserver);
        assertEquals(storeId, foundStore.get().getId());

    }

}
