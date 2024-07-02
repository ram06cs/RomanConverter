package org.romanconverter.exception.handler;

import org.romanconverter.enums.ErrorCodeEnum;
import org.romanconverter.exception.RomanConversionException;
import org.romanconverter.model.ErrorInfo;
import org.romanconverter.model.RomanConversionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RomanConversionControllerAdvice {

    @ExceptionHandler(RomanConversionException.class)
    public ResponseEntity<RomanConversionResponse> handleRomanConversionException(RomanConversionException e) {
        ErrorCodeEnum errorCode = e.getErrorCodeEnum();
        ErrorInfo errorInfo = new ErrorInfo(errorCode.getErrorCode(), errorCode.getDescription());
        RomanConversionResponse response = new RomanConversionResponse(e.getInput(), errorInfo);  // Pass the input
        return new ResponseEntity<>(response, errorCode.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RomanConversionResponse> handleException(Exception e) {
        ErrorInfo errorInfo = new ErrorInfo(500, "Internal Server Error");
        RomanConversionResponse response = new RomanConversionResponse(null, errorInfo);  // No input for general exceptions
        return ResponseEntity.status(500).body(response);
    }
}
