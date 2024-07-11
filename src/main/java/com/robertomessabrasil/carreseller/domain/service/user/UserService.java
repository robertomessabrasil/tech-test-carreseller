package com.robertomessabrasil.carreseller.domain.service.user;

import com.robertomessabrasil.carreseller.domain.entity.user.UserEntity;
import com.robertomessabrasil.carreseller.domain.exception.InterruptException;
import com.robertomessabrasil.carreseller.domain.observer.EventObserver;
import com.robertomessabrasil.carreseller.domain.repository.IUserRepository;
import com.robertomessabrasil.carreseller.domain.service.user.event.InvalidRoleEvent;
import com.robertomessabrasil.carreseller.domain.entity.user.UserRoleEnum;
import com.robertomessabrasil.carreseller.domain.entity.user.UserRoleVO;

import java.util.List;
import java.util.Optional;

public class UserService {

    public static UserEntity createUser(UserEntity adminUser, UserEntity user, IUserRepository userRepository, EventObserver eventObserver) throws InterruptException {
        checkRole(adminUser, List.of(new UserRoleVO(UserRoleEnum.ADMIN)), eventObserver);
        user.setRole(new UserRoleVO(UserRoleEnum.STORE_USER));
        user.validate(eventObserver);
        return userRepository.create(user, eventObserver);
    }

    public static Optional<UserEntity> findUserById(int userId, IUserRepository userRepository, EventObserver eventObserver) throws InterruptException {
        return userRepository.findById(userId, eventObserver);
    }

    public static void checkRole(UserEntity user, List<UserRoleVO> roles, EventObserver eventObserver) throws InterruptException {
        for (UserRoleVO role : roles) {
            if (user.getRole().getRoleEnum().equals(role.getRoleEnum())) {
                return;
            }
        }
        eventObserver.notify(new InvalidRoleEvent());
    }

}
