package io.github.robertomessabrasil.dddad.domain.exception;

public class InfrastructureException extends Exception {
    public InfrastructureException(Exception exception) {
        super(exception);
    }
}
