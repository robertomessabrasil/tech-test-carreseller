package com.robertomessabrasil.carreseller;

import com.robertomessabrasil.carreseller.domain.entity.user.UserEntity;
import com.robertomessabrasil.carreseller.domain.exception.InterruptException;
import com.robertomessabrasil.carreseller.domain.observer.EventObserver;
import com.robertomessabrasil.carreseller.domain.observer.listener.security.SecurityListener;
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

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SecurityTest {

    @Mock
    IUserRepository userRepository;

    EventObserver eventObserver = new EventObserver();

    @BeforeEach
    void setUp() {

        SecurityListener securityListener = new SecurityListener();

        securityListener.setEventsOfInterest(List.of(InvalidRoleEvent.class));
        eventObserver.subscribe(securityListener);

    }

    @Test
    void givenNoAdminRole_checkException() {

        int userId = 2;
        UserRoleVO userRole = new UserRoleVO(UserRoleEnum.STORE_USER);

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setRole(userRole);

        Throwable exception = assertThrows(InterruptException.class, () -> {
            UserEntity userCreated = UserService.createUser(userEntity, userEntity, this.userRepository, this.eventObserver);
        });

        assertNotNull(exception);

    }

}
