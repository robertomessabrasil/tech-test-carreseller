package com.robertomessabrasil.carreseller.domain.entity.store;

import com.robertomessabrasil.carreseller.domain.entity.store.event.StoreValidationCode;
import com.robertomessabrasil.carreseller.domain.entity.store.event.StoreValidationEvent;
import com.robertomessabrasil.carreseller.domain.entity.store.opportunity.OpportunityEntity;
import com.robertomessabrasil.carreseller.domain.exception.InterruptException;
import com.robertomessabrasil.carreseller.domain.observer.EventObserver;

import java.util.ArrayList;
import java.util.List;

public class StoreEntity {
    private int id;
    private String name;
    private String cnpj;
    private List<OpportunityEntity> opportunities = new ArrayList<>();

    public int getId() {
        return id;
    }

    public StoreEntity setId(int id) {
        this.id = id;
        return this;
    }

    public String getCnpj() {
        return cnpj;
    }

    public StoreEntity setCnpj(String cnpj) {
        this.cnpj = cnpj;
        return this;
    }

    public String getName() {
        return name;
    }

    public StoreEntity setName(String name) {
        this.name = name;
        return this;
    }

    public List<OpportunityEntity> getOpportunities() {
        return opportunities;
    }

    public StoreEntity setOpportunities(List<OpportunityEntity> opportunities) {
        this.opportunities = opportunities;
        return this;
    }

    public void addOpportunity(OpportunityEntity opportunity) {
        this.opportunities.add(opportunity);
    }

    public void validate(EventObserver eventObserver) throws InterruptException {
        StoreValidationEvent storeValidationEvent = new StoreValidationEvent(this);
        if (this.name == null) {
            storeValidationEvent.addCode(StoreValidationCode.INVALID_NAME);
        }
        if (this.cnpj == null) {
            storeValidationEvent.addCode(StoreValidationCode.INVALID_CNPJ);
        }
        eventObserver.notify(storeValidationEvent);
    }

}
