package io.github.robertomessabrasil.dddad.repository;

import io.github.robertomessabrasil.dddad.entity.user.UserEntity;
import io.github.robertomessabrasil.jwatch.exception.InterruptException;
import io.github.robertomessabrasil.jwatch.observer.EventObserver;

import java.util.Optional;

public interface IUserRepository {

    UserEntity create(UserEntity user, EventObserver observer) throws InterruptException;

    Optional<UserEntity> findById(int userId, EventObserver observer) throws InterruptException;

}
