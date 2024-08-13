package io.github.robertomessabrasil.dddad.service.user;

import io.github.robertomessabrasil.dddad.entity.user.UserEntity;
import io.github.robertomessabrasil.dddad.entity.user.UserRoleEnum;
import io.github.robertomessabrasil.dddad.entity.user.UserRoleVO;
import io.github.robertomessabrasil.dddad.repository.IUserRepository;
import io.github.robertomessabrasil.dddad.service.user.event.InvalidRoleEvent;
import io.github.robertomessabrasil.jwatch.exception.InterruptException;
import io.github.robertomessabrasil.jwatch.observer.EventObserver;

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
        eventObserver.notify(new InvalidRoleEvent(user.getRole().getRoleEnum()));
    }

    private UserService() {
    }

}
