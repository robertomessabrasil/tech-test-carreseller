package com.robertomessabrasil.carreseller.domain.entity.store.opportunity;

public final class OpportunityStatusVO {

    private final OpportunityStatusEnum status;

    public OpportunityStatusVO(OpportunityStatusEnum status) {
        this.status = status;
    }

    private OpportunityStatusEnum getStatus() {
        return this.status;
    }
}
