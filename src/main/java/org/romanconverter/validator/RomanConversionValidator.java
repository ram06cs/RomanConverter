package org.romanconverter.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.romanconverter.enums.ErrorCodeEnum;
import org.romanconverter.exception.RomanConversionException;
import org.springframework.util.StringUtils;

public class RomanConversionValidator {

    private static final Logger logger = LoggerFactory.getLogger(RomanConversionValidator.class);

    public static Long validate(String input) {
        if (!StringUtils.hasText(input)) {
            logger.warn("Validation failed: input is empty");
            throw new RomanConversionException(ErrorCodeEnum.INPUT_EMPTY,null);
        }
        try {
            Long number = Long.parseLong(input);
            if (number < 1L || number > 2200000000L) {
                logger.warn("Validation failed: number out of range {}", number);
                throw new RomanConversionException(ErrorCodeEnum.OUT_OF_RANGE,number);
            }
            return number;
        } catch (NumberFormatException e) {
            logger.warn("Validation failed: invalid format for input {}", input, e);
            throw new RomanConversionException(ErrorCodeEnum.INVALID_FORMAT,null);
        }
    }
}
