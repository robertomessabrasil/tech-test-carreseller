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
import com.robertomessabrasil.carreseller.valueobject.UserRoleEnum;
import com.robertomessabrasil.carreseller.valueobject.UserRoleVO;
import org.junit.jupiter.api.BeforeEach;
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

    SecurityListener securityListener = new SecurityListener();
    ValidationListener validationListener = new ValidationListener();
    EventObserver eventObserver = new EventObserver();

    @BeforeEach
    void setUp() {

        securityListener.setEvents(List.of(new InvalidRoleEvent()));
        validationListener.setEvents(List.of(new UserValidationEvent()));
        eventObserver.subscribe(securityListener);
        eventObserver.subscribe(validationListener);

    }

    @Test
    void givenParameters_createUser() throws InterruptException {

        int adminUserId = 1;
        int userId = 2;
        UserRoleVO adminRole = new UserRoleVO(UserRoleEnum.ADMIN);
        UserRoleVO role = new UserRoleVO(UserRoleEnum.STORE_USER);

        UserEntity adminUser = new UserEntity();
        adminUser.setId(adminUserId);
        adminUser.setRole(adminRole);

        UserEntity user = new UserEntity();
        user.setId(userId);
        user.setName("Roberto Messa");

        SecurityListener securityListener = new SecurityListener();
        securityListener.setEvents(List.of(new InvalidRoleEvent()));

        ValidationListener validationListener = new ValidationListener();
        validationListener.setEvents(List.of(new UserValidationEvent()));

        EventObserver observer = new EventObserver();
        observer.subscribe(securityListener);
        observer.subscribe(validationListener);

        UserEntity userCreated = UserService.createUser(adminUser, user, this.userRepository, observer);
        assertEquals(userId, user.getId());

    }

    @Test
    void givenNoParameters_checkException() {

        int adminUserId = 1;
        int userId = 2;
        UserRoleVO adminRole = new UserRoleVO(UserRoleEnum.ADMIN);
        UserRoleVO role = new UserRoleVO(UserRoleEnum.STORE_USER);

        UserEntity adminUser = new UserEntity();
        adminUser.setId(adminUserId);
        adminUser.setRole(adminRole);

        UserEntity user = new UserEntity();
        user.setId(userId);

        this.securityListener.setEvents(List.of(new InvalidRoleEvent()));
        this.validationListener.setEvents(List.of(new UserValidationEvent()));

        Throwable exception = assertThrows(InterruptException.class, () -> {
            UserEntity userCreated = UserService.createUser(adminUser, user, this.userRepository, this.eventObserver);
        });

        assertNotNull(exception);

    }

    @Test
    void givenUserId_findUser() throws InterruptException {

        int userId = 1;

        UserEntity userEntity = new UserEntity();

        UserEntity user = new UserEntity();
        user.setId(userId);

        validationListener.setEvents(List.of(new UserValidationEvent()));

        when(this.userRepository.findById(userId, this.eventObserver)).thenReturn(Optional.of(user));

        Optional<UserEntity> userFound = UserService.findUserById(userId, this.userRepository, this.eventObserver);

        assertEquals(userId, userFound.get().getId());

    }
}
