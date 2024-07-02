package org.romanconverter.validator;

import org.junit.jupiter.api.Test;
import org.romanconverter.enums.ErrorCodeEnum;
import org.romanconverter.exception.RomanConversionException;

import static org.junit.jupiter.api.Assertions.*;

public class ConversionValidatorTest {

    @Test
    public void testValidateEmptyString() {
        RomanConversionException exception = assertThrows(RomanConversionException.class, () -> {
            RomanConversionValidator.validate("");
        });
        assertEquals(ErrorCodeEnum.INPUT_EMPTY, exception.getErrorCodeEnum());
    }

    @Test
    public void testValidateInvalidInput() {
        RomanConversionException exception = assertThrows(RomanConversionException.class, () -> {
            RomanConversionValidator.validate("200string");
        });
        assertEquals(ErrorCodeEnum.INVALID_FORMAT, exception.getErrorCodeEnum());
    }

    @Test
    public void testValidateOutOfRange() {
        RomanConversionException exception = assertThrows(RomanConversionException.class, () -> {
            RomanConversionValidator.validate("2200000001");
        });
        assertEquals(ErrorCodeEnum.OUT_OF_RANGE, exception.getErrorCodeEnum());
    }

    @Test
    public void testValidateValidInput() {
        Long number = RomanConversionValidator.validate("150");
        assertEquals(150L, number);
    }

    @Test
    public void testValidateNegativeNumber() {
        RomanConversionException exception = assertThrows(RomanConversionException.class, () -> {
            RomanConversionValidator.validate("-1");
        });
        assertEquals(ErrorCodeEnum.OUT_OF_RANGE, exception.getErrorCodeEnum());
    }

    @Test
    public void testValidateZero() {
        RomanConversionException exception = assertThrows(RomanConversionException.class, () -> {
            RomanConversionValidator.validate("0");
        });
        assertEquals(ErrorCodeEnum.OUT_OF_RANGE, exception.getErrorCodeEnum());
    }
}
