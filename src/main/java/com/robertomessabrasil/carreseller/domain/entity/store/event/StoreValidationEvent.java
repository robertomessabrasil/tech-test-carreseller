package com.robertomessabrasil.carreseller.domain.entity.store.event;

import com.robertomessabrasil.carreseller.domain.entity.store.StoreEntity;
import io.github.robertomessabrasil.jwatch.observer.listener.Event;

import java.util.ArrayList;
import java.util.List;

public class StoreValidationEvent extends Event {
    private StoreEntity storeEntity;
    private final List<StoreValidationCode> codes = new ArrayList<>();

    public StoreValidationEvent addCode(StoreValidationCode storeValidationCode) {
        this.codes.add(storeValidationCode);
        return this;
    }

    public StoreValidationEvent(StoreEntity storeEntity) {
        this.storeEntity = storeEntity;
    }

    public StoreEntity getStoreEntity() {
        return storeEntity;
    }

    public StoreValidationEvent setStoreEntity(StoreEntity storeEntity) {
        this.storeEntity = storeEntity;
        return this;
    }

    public List<StoreValidationCode> getCodes() {
        return codes;
    }

    @Override
    public String toString() {
        return "";
    }
}
