package com.robertomessabrasil.carreseller.domain.entity.store.opportunity;

public enum OpportunityStatusEnum {
    NEW(0), IN_PROGRESS(1), FINISHED(2);

    final int status;

    OpportunityStatusEnum(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
    }
}
