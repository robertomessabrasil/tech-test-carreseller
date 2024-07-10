package com.robertomessabrasil.carreseller.domain.repository;

import com.robertomessabrasil.carreseller.domain.entity.user.UserEntity;
import com.robertomessabrasil.carreseller.domain.observer.EventObserver;

import java.util.Optional;

public interface IUserRepository {

    UserEntity create(UserEntity user, EventObserver observer);

    Optional<UserEntity> findById(int userId, EventObserver observer);

    UserEntity update(UserEntity user, EventObserver observer);
}
