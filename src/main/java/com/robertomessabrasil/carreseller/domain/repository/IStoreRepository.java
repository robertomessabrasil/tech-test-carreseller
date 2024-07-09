package com.robertomessabrasil.carreseller.domain.repository;

import com.robertomessabrasil.carreseller.domain.entity.*;

import java.util.List;
import java.util.Optional;

public interface IStoreRepository {
    StoreEntity create(StoreEntity store);

    Optional<StoreEntity> findById(int storeId);

    List<StoreEntity> listAll();

}
