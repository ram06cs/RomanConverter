package org.romanconverter.exception;

import org.romanconverter.enums.ErrorCodeEnum;

public class RomanConversionException extends RuntimeException {
    private final ErrorCodeEnum errorCodeEnum;
    private final Long input;

    public RomanConversionException(ErrorCodeEnum errorCodeEnum, Long input) {
        super(errorCodeEnum.getDescription());
        this.errorCodeEnum = errorCodeEnum;
        this.input = input;
    }

    public ErrorCodeEnum getErrorCodeEnum() {
        return errorCodeEnum;
    }

    public Long getInput() {
        return input;
    }
}
