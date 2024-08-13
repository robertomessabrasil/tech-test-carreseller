package io.github.robertomessabrasil.dddad;

import io.github.robertomessabrasil.dddad.listener.SecurityListener;
import io.github.robertomessabrasil.dddad.entity.user.UserEntity;
import io.github.robertomessabrasil.dddad.entity.user.UserRoleEnum;
import io.github.robertomessabrasil.dddad.entity.user.UserRoleVO;
import io.github.robertomessabrasil.dddad.entity.user.event.UserValidationEvent;
import io.github.robertomessabrasil.dddad.repository.IUserRepository;
import io.github.robertomessabrasil.dddad.service.user.UserService;
import io.github.robertomessabrasil.dddad.service.user.event.InvalidRoleEvent;
import io.github.robertomessabrasil.dddad.listener.ValidationListener;
import io.github.robertomessabrasil.jwatch.exception.InterruptException;
import io.github.robertomessabrasil.jwatch.observer.EventObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserTest {

    @Mock
    IUserRepository userRepository;

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
    void givenParameters_createUser() throws InterruptException {


        int adminUserId = 1;
        UserRoleVO adminRole = new UserRoleVO(UserRoleEnum.ADMIN);
        UserEntity adminUser = new UserEntity();
        adminUser.setId(adminUserId);
        adminUser.setRole(adminRole);

        int userId = 2;
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        userEntity.setEmail("myemail@emailhost.com");

        when(this.userRepository.create(userEntity, eventObserver)).thenReturn(userEntity);

        UserEntity userCreated = UserService.createUser(adminUser, userEntity, this.userRepository, this.eventObserver);
        assertEquals(userId, userCreated.getId());

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
