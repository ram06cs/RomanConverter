package org.romanconverter.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RomanConversionResponse {
    private Long input;
    private String romanResult;
    private ErrorInfo errorInfo;

    // Constructor for success response
    public RomanConversionResponse(Long input, String romanResult) {
        this.input = input;
        this.romanResult = romanResult;
    }

    // Constructor for error response
    public RomanConversionResponse(Long input, ErrorInfo errorInfo) {
        this.input = input;
        this.errorInfo = errorInfo;
    }

    public Long getInput() {
        return input;
    }

    public String getRomanResult() {
        return romanResult;
    }

    public ErrorInfo getErrorInfo() {
        return errorInfo;
    }
}
