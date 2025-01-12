package ru.prokhorov.povod.exception;

import lombok.Getter;

/**
 * IntegrationException.
 *
 * @author Evgeniy_Prokhorov
 */
@Getter
public class IntegrationException extends RuntimeException {

    private final int codeStatus;

    public IntegrationException(String message, int codeStatus) {
        super(message);
        this.codeStatus = codeStatus;
    }


}
