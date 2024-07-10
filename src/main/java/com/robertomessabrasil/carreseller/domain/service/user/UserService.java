package com.robertomessabrasil.carreseller.domain.service.user;

import com.robertomessabrasil.carreseller.domain.entity.user.UserEntity;
import com.robertomessabrasil.carreseller.domain.exception.InterruptException;
import com.robertomessabrasil.carreseller.domain.observer.EventObserver;
import com.robertomessabrasil.carreseller.domain.repository.IUserRepository;
import com.robertomessabrasil.carreseller.domain.service.user.event.InvalidRoleEvent;
import com.robertomessabrasil.carreseller.valueobject.UserRoleEnum;
import com.robertomessabrasil.carreseller.valueobject.UserRoleVO;

import java.util.Optional;

public class UserService {

    public static UserEntity createUser(UserEntity adminUser, UserEntity user, IUserRepository userRepository, EventObserver observer) throws InterruptException {
        checkRole(adminUser, new UserRoleVO[]{new UserRoleVO(UserRoleEnum.ADMIN)}, observer);
        user.setRole(new UserRoleVO(UserRoleEnum.STORE_USER));
        user.validate(observer);
        return userRepository.create(user, observer);
    }

    public static UserEntity update(UserEntity adminUser, UserEntity user, IUserRepository userRepository, EventObserver observer) throws InterruptException {
        checkRole(adminUser, new UserRoleVO[]{new UserRoleVO(UserRoleEnum.ADMIN)}, observer);
        return userRepository.update(user, observer);
    }

    public static Optional<UserEntity> findUserById(int userId, IUserRepository userRepository, EventObserver observer) throws InterruptException {
        return userRepository.findById(userId, observer);
    }

    public static void checkRole(UserEntity user, UserRoleVO[] roles, EventObserver observer) throws InterruptException {
        for (UserRoleVO role : roles) {
            if (user.getRole().getRoleEnum().equals(role.getRoleEnum())) {
                return;
            }
        }
        observer.notify(new InvalidRoleEvent());
    }

}
