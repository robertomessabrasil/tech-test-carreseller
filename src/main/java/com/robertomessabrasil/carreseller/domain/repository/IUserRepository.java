package com.robertomessabrasil.carreseller.domain.repository;

import com.robertomessabrasil.carreseller.domain.entity.UserEntity;
import com.robertomessabrasil.carreseller.domain.error.EventObserver;

import java.util.Optional;

public interface IUserRepository {

    UserEntity create(UserEntity user, EventObserver observer);

    Optional<UserEntity> findById(int userId, EventObserver observer);

    UserEntity update(UserEntity user, EventObserver observer);
}
