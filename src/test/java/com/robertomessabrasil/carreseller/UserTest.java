package com.robertomessabrasil.carreseller;

import com.robertomessabrasil.carreseller.domain.entity.user.UserEntity;
import com.robertomessabrasil.carreseller.domain.entity.user.event.UserValidationEvent;
import com.robertomessabrasil.carreseller.domain.exception.InterruptException;
import com.robertomessabrasil.carreseller.domain.observer.EventObserver;
import com.robertomessabrasil.carreseller.domain.observer.listener.security.SecurityListener;
import com.robertomessabrasil.carreseller.domain.observer.listener.validation.ValidationListener;
import com.robertomessabrasil.carreseller.domain.repository.IUserRepository;
import com.robertomessabrasil.carreseller.domain.service.user.UserService;
import com.robertomessabrasil.carreseller.domain.service.user.event.InvalidRoleEvent;
import com.robertomessabrasil.carreseller.domain.entity.user.UserRoleEnum;
import com.robertomessabrasil.carreseller.domain.entity.user.UserRoleVO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Mock
    IUserRepository userRepository;

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
    void givenParameters_createUser() throws InterruptException {


        int adminUserId = 1;
        UserRoleVO adminRole = new UserRoleVO(UserRoleEnum.ADMIN);
        UserEntity adminUser = new UserEntity();
        adminUser.setId(adminUserId);
        adminUser.setRole(adminRole);

        int userId = 2;
        UserRoleVO role = new UserRoleVO(UserRoleEnum.STORE_USER);
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setEmail("myemail@emailhost.com");

        UserEntity userCreated = UserService.createUser(adminUser, userEntity, this.userRepository, this.eventObserver);
        assertEquals(userId, userEntity.getId());

    }

    @Test
    void givenUserId_findUser() throws InterruptException {

        int userId = 1;

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);

        when(this.userRepository.findById(userId, this.eventObserver)).thenReturn(Optional.of(userEntity));

        Optional<UserEntity> foundUser = UserService.findUserById(userId, this.userRepository, this.eventObserver);

        assertEquals(userId, foundUser.get().getId());

    }
}
