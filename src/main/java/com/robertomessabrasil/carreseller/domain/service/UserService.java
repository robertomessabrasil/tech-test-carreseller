package com.robertomessabrasil.carreseller.domain.service;

import com.robertomessabrasil.carreseller.domain.entity.UserEntity;
import com.robertomessabrasil.carreseller.domain.error.Event;
import com.robertomessabrasil.carreseller.domain.error.EventCodeEnum;
import com.robertomessabrasil.carreseller.domain.error.EventObserver;
import com.robertomessabrasil.carreseller.domain.exception.ObserverException;
import com.robertomessabrasil.carreseller.domain.repository.IUserRepository;
import com.robertomessabrasil.carreseller.valueobject.UserRoleVO;

import java.util.Optional;

public class UserService {

    public static UserEntity createUser(UserEntity adminUser, UserEntity user, IUserRepository userRepository, EventObserver observer) throws ObserverException {
        checkRole(adminUser, new UserRoleVO[]{UserRoleVO.buildAdminRole()}, observer);
        user.setRole(UserRoleVO.buildUserRole());
        user.validate(observer);
        return userRepository.create(user, observer);
    }

    public static UserEntity update(UserEntity adminUser, UserEntity user, IUserRepository userRepository, EventObserver observer) throws ObserverException {
        checkRole(adminUser, new UserRoleVO[]{UserRoleVO.buildAdminRole()}, observer);
        return userRepository.update(user, observer);
    }

    public static UserEntity findUserById(int userId, IUserRepository userRepository, EventObserver observer) throws ObserverException {
        Optional<UserEntity> user = userRepository.findById(userId, observer);
        if (user.isEmpty()) {
            observer.notify(new Event(EventCodeEnum.REPO_USERNOTFOUND));
        }
        return user.get();
    }

    public static void checkRole(UserEntity user, UserRoleVO[] roles, EventObserver observer) throws ObserverException {
        for (UserRoleVO role : roles) {
            if (user.getRole().getRoleEnum().equals(role.getRoleEnum())) {
                return;
            }
        }
        observer.notify(new Event(EventCodeEnum.ERROR_FORBIDDEN));
    }

}
