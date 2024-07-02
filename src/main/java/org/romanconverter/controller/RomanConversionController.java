package org.romanconverter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.romanconverter.model.RomanConversionResponse;
import org.romanconverter.service.ConversionService;
import org.romanconverter.validator.RomanConversionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RomanConversionController {

    private static final Logger logger = LoggerFactory.getLogger(RomanConversionController.class);

    @Autowired
    private ConversionService conversionService;

    @GetMapping("/romannumeral")
    public ResponseEntity<RomanConversionResponse> convertToRoman(@RequestParam String query) {
        logger.info("Received request to convert number: {}", query);
        Long number = RomanConversionValidator.validate(query);
        String romanResult = conversionService.numberToRomanNumeral(number);
        RomanConversionResponse response = new RomanConversionResponse(number, romanResult);
        logger.info("Returning response: {}", response);
        return ResponseEntity.ok(response);
    }
}
