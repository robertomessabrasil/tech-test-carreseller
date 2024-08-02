package com.robertomessabrasil.carreseller.domain.repository;

import com.robertomessabrasil.carreseller.domain.entity.user.UserEntity;
import com.robertomessabrasil.carreseller.domain.exception.InfrastructureException;
import io.github.robertomessabrasil.jwatch.observer.EventObserver;

import java.util.Optional;

public interface IUserRepository {

    UserEntity create(UserEntity user, EventObserver observer) throws InfrastructureException;;

    Optional<UserEntity> findById(int userId, EventObserver observer) throws InfrastructureException;

}
