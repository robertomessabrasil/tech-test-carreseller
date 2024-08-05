package io.github.robertomessabrasil.dddad.domain.repository;

import io.github.robertomessabrasil.dddad.domain.entity.user.UserEntity;
import io.github.robertomessabrasil.dddad.domain.exception.InfrastructureException;
import io.github.robertomessabrasil.jwatch.observer.EventObserver;

import java.util.Optional;

public interface IUserRepository {

    UserEntity create(UserEntity user, EventObserver observer) throws InfrastructureException;;

    Optional<UserEntity> findById(int userId, EventObserver observer) throws InfrastructureException;

}
