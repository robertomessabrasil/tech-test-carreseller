package io.github.robertomessabrasil.dddad.entity.user.event;

public enum UserValidationCode {
    INVALID_NAME(0),INVALID_EMAIL(1),INVALID_ROLE(2);

    private final int code;

    private UserValidationCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
