package org.romanconverter.enums;
import org.springframework.http.HttpStatus;

public enum ErrorCodeEnum {

    INPUT_EMPTY(100, "Input cannot be empty", HttpStatus.BAD_REQUEST),
    OUT_OF_RANGE(101, "Input is out of acceptable range. The valid range is from 1 to 2200000000", HttpStatus.BAD_REQUEST),
    INVALID_FORMAT(102, "Invalid number format", HttpStatus.BAD_REQUEST),
    GENERAL_ERROR(103, "Something went wrong. Please try later", HttpStatus.INTERNAL_SERVER_ERROR);

    private final int errorCode;
    private final String description;
    private final HttpStatus httpStatus;

    ErrorCodeEnum(int errorCode, String description, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.description = description;
        this.httpStatus = httpStatus;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

