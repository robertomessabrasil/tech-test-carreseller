package com.robertomessabrasil.carreseller.valueobject;

public final class OpportunityStatusVO {

    private final OpportunityStatusEnum status;

    public static OpportunityStatusVO buildNew() {
        return new OpportunityStatusVO(OpportunityStatusEnum.NEW);
    }

    public static OpportunityStatusVO buildInProgress() {
        return new OpportunityStatusVO(OpportunityStatusEnum.IN_PROGRESS);
    }

    public static OpportunityStatusVO buildFinished() {
        return new OpportunityStatusVO(OpportunityStatusEnum.FINISHED);
    }

    private OpportunityStatusVO(OpportunityStatusEnum status) {
        this.status = status;
    }

    private OpportunityStatusEnum getStatus() {
        return this.status;
    }
}
