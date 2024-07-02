package org.romanconverter.service;

import org.romanconverter.enums.ErrorCodeEnum;
import org.romanconverter.exception.RomanConversionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import org.romanconverter.service.ConversionService;
import org.romanconverter.service.impl.RomanConversionService;

public class RomanConversionServiceTest {

    private final ConversionService conversionService = new RomanConversionService();

    @Test
    public void testServiceValidNumbers() {
        Assertions.assertEquals("II", conversionService.numberToRomanNumeral(2L));
        Assertions.assertEquals("XXVII", conversionService.numberToRomanNumeral(27L));
        Assertions.assertEquals("CXLIV", conversionService.numberToRomanNumeral(144L));
        Assertions.assertEquals("DCCC", conversionService.numberToRomanNumeral(800L));
        Assertions.assertEquals("MCMXCIV", conversionService.numberToRomanNumeral(1994L));
        Assertions.assertEquals("MMXXI", conversionService.numberToRomanNumeral(2021L));
    }

    @Test
    public void testServiceVinculumUTF8() {
        // Testing large numbers with UTF-8 vinculum
        Assertions.assertEquals("X\u0305", conversionService.numberToRomanNumeral(10000L));
        Assertions.assertEquals("X\u0305X\u0305", conversionService.numberToRomanNumeral(20000L));
        Assertions.assertEquals("L\u0305\u0305", conversionService.numberToRomanNumeral(50000000L));
        Assertions.assertEquals("C\u0305\u0305", conversionService.numberToRomanNumeral(100000000L));
        Assertions.assertEquals("D\u0305\u0305", conversionService.numberToRomanNumeral(500000000L));
        Assertions.assertEquals("M\u0305\u0305", conversionService.numberToRomanNumeral(1000000000L));
    }
}
