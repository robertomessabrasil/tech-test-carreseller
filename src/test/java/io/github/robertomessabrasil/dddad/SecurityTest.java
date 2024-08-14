package io.github.robertomessabrasil.dddad;

import io.github.robertomessabrasil.dddad.listener.SecurityListener;
import io.github.robertomessabrasil.dddad.entity.user.UserEntity;
import io.github.robertomessabrasil.dddad.repository.IUserRepository;
import io.github.robertomessabrasil.dddad.service.user.UserService;
import io.github.robertomessabrasil.dddad.service.user.event.InvalidRoleEvent;
import io.github.robertomessabrasil.dddad.entity.user.UserRoleEnum;
import io.github.robertomessabrasil.dddad.entity.user.UserRoleVO;
import io.github.robertomessabrasil.jwatch.exception.InterruptException;
import io.github.robertomessabrasil.jwatch.observer.EventObserver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SecurityTest {

    @Mock
    IUserRepository userRepository;

    EventObserver eventObserver = new EventObserver();

    @BeforeEach
    void setUp() {

        SecurityListener securityListener = new SecurityListener();

        securityListener.addEvent(InvalidRoleEvent.class);
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
            UserEntity userCreated = UserService.createStoreUser(userEntity, userEntity, this.userRepository, this.eventObserver);
        });

        assertNotNull(exception);

    }

}
