package com.robertomessabrasil.carreseller;

import com.robertomessabrasil.carreseller.domain.entity.UserEntity;
import com.robertomessabrasil.carreseller.domain.error.ErrorListener;
import com.robertomessabrasil.carreseller.domain.error.Event;
import com.robertomessabrasil.carreseller.domain.error.EventCodeEnum;
import com.robertomessabrasil.carreseller.domain.error.EventObserver;
import com.robertomessabrasil.carreseller.domain.error.exception.OperationDeniedException;
import com.robertomessabrasil.carreseller.domain.error.exception.UserNotFoundException;
import com.robertomessabrasil.carreseller.domain.exception.ObserverException;
import com.robertomessabrasil.carreseller.domain.repository.IUserRepository;
import com.robertomessabrasil.carreseller.domain.service.UserService;
import com.robertomessabrasil.carreseller.valueobject.UserRoleVO;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Mock
    IUserRepository userRepository;

    @Test
    void givenParameters_createUser() throws ObserverException {

        int adminUserId = 1;
        int userId = 2;
        UserRoleVO adminRole = UserRoleVO.buildAdminRole();
        UserRoleVO role = UserRoleVO.buildUserRole();

        UserEntity adminUser = new UserEntity();
        adminUser.setId(adminUserId);
        adminUser.setRole(adminRole);

        UserEntity user = new UserEntity();
        user.setId(userId);

        ErrorListener errorListener = new ErrorListener();
        errorListener.setEvents(List.of(
                new Event(EventCodeEnum.ERROR_FORBIDDEN),
                new Event(EventCodeEnum.ERROR_INVALIDINPUT)));

        EventObserver observer = new EventObserver();
        observer.subscribe(errorListener);

        when(this.userRepository.create(user, observer)).thenReturn(user);

        UserEntity userCreated = UserService.createUser(adminUser, user, this.userRepository, observer);
        assertEquals(userId, userCreated.getId());

    }

    @Test
    void givenUserId_findUser() throws ObserverException {

        int userId = 1;

        UserEntity userEntity = new UserEntity();

        UserEntity user = new UserEntity();
        user.setId(userId);

        ErrorListener errorListener = new ErrorListener();
        errorListener.setEvents(List.of(new Event(EventCodeEnum.ERROR_INVALIDINPUT)));

        EventObserver observer = new EventObserver();
        observer.subscribe(errorListener);

        when(this.userRepository.findById(userId, observer)).thenReturn(Optional.of(user));

        UserEntity userFound = UserService.findUserById(userId, this.userRepository, observer);

        assertEquals(userId, userFound.getId());

    }
}
