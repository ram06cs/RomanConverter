package org.romanconverter.service.impl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.romanconverter.service.ConversionService;
import org.springframework.stereotype.Service;

import java.util.TreeMap;

/**
 *
 *   Input : @number
 *   Ouput : @return Roman Numeral in string that is equivalent to the given input number
 *
 *   Considerations:
 *   1) number is passed from UI. But java has limitation for Integer data type. Integer data type is 4 byte and it cannot handle bigger numbers like 2,200,000,000.
 *     So Long data is used to handle large numbers.
 *
 *   2) UTF-8 Vinculum Characters- In Roman numerals, a vinculum is a horizontal line placed above a numeral to indicate multiplication by 1,000. For example, V̅ (line over V) represents 5000.
 *     The vinculum character is "\u0305" in Unicode
 *    Reference link used : https://aceonlinetools.com/utility/number-to-roman-numeral-converter/
 *
 *    3) TreeMap is preferred since we need to iterate through keys in sorted order.
 *
 *
 *
 *
 * Algorithm
 * *
 * 1. store the 37 possible roman numerals(for handling more than 2 billion numbers) and its corresponding numbers in descending order in a tree map(for its natural ordering of keys)
 * 2. Iterate through treemap from highest to lowest key
 *      - For each key (which represents a numerical value), the while loop checks if the remaining value is greater than or equal to the key.
        - If remaining is less than the roman key, continue to the next smaller key.
        - If remaining is greater than or equal to the key, it means the current key's Roman numeral should be appended to the result.
 *  3. UTF-8 equivalent value is added in treemap. Browsers and notepads have UTF-8 support by default. The java file might show the overline differently.
 *
 * Time Complexity - O(1)
 * Space Complexity - O(1)
 *
 *
 *
 */

@Service
public class RomanConversionService implements ConversionService {
    private static final Logger logger = LoggerFactory.getLogger(RomanConversionService.class);

    private static final TreeMap<Long, String> romanMap = new TreeMap<>();

    static {
        romanMap.put(1000000000L, "M\u0305\u0305"); // 1,000,000,000 (M̿)
        romanMap.put(900000000L, "C\u0305\u0305M\u0305\u0305"); // 900,000,000 (C̅M̿)
        romanMap.put(500000000L, "D\u0305\u0305"); // 500,000,000 (D̿)
        romanMap.put(400000000L, "C\u0305\u0305D\u0305\u0305"); // 400,000,000 (C̅D̿)

        romanMap.put(100000000L, "C\u0305\u0305"); // 100,000,000 (C̅)
        romanMap.put(90000000L, "X\u0305\u0305C\u0305\u0305"); // 90,000,000 (X̅C̅)
        romanMap.put(50000000L, "L\u0305\u0305"); // 50,000,000 (L̅)
        romanMap.put(40000000L, "X\u0305\u0305L\u0305\u0305"); // 40,000,000 (X̅L̅)

        romanMap.put(10000000L, "X\u0305\u0305"); // 10,000,000 (X̅)
        romanMap.put(9000000L, "I\u0305\u0305X\u0305\u0305"); // 9,000,000 (I̅X̅)
        romanMap.put(5000000L, "V\u0305\u0305"); // 5,000,000 (V̅)
        romanMap.put(4000000L, "I\u0305\u0305V\u0305\u0305"); // 4,000,000 (I̅V̅)

        romanMap.put(1000000L, "M\u0305"); // 1,000,000 (M̅)
        romanMap.put(900000L, "C\u0305M\u0305"); // 900,000 (C̅M)
        romanMap.put(500000L, "D\u0305"); // 500,000 (D̅)
        romanMap.put(400000L, "C\u0305D\u0305"); // 400,000 (C̅D)

        romanMap.put(100000L, "C\u0305"); // 100,000 (C̅)
        romanMap.put(90000L, "X\u0305C\u0305"); // 90,000 (X̅C)
        romanMap.put(50000L, "L\u0305"); // 50,000 (L̅)
        romanMap.put(40000L, "X\u0305L\u0305"); // 40,000 (X̅L)
        romanMap.put(10000L, "X\u0305"); // 10,000 (X̅)
        romanMap.put(9000L, "I\u0305X\u0305"); // 9,000 (I̅X)
        romanMap.put(5000L, "V\u0305"); // 5,000 (V̅)
        romanMap.put(4000L, "I\u0305V\u0305"); // 4,000 (I̅V)

        romanMap.put(1000L, "M"); // 1,000
        romanMap.put(900L, "CM"); // 900
        romanMap.put(500L, "D"); // 500
        romanMap.put(400L, "CD"); // 400
        romanMap.put(100L, "C"); // 100
        romanMap.put(90L, "XC"); // 90
        romanMap.put(50L, "L"); // 50
        romanMap.put(40L, "XL"); // 40
        romanMap.put(10L, "X"); // 10
        romanMap.put(9L, "IX"); // 9
        romanMap.put(5L, "V"); // 5
        romanMap.put(4L, "IV"); // 4
        romanMap.put(1L, "I"); // 1
    }

    @Override
    public String numberToRomanNumeral(Long number) {
        logger.debug("Valid input number: {}", number);
        StringBuilder result = new StringBuilder();
        long remaining = number;

        // Traverse the romanMap in descending order
        for (long key : romanMap.descendingKeySet()) {
            while (remaining >= key) {
                result.append(romanMap.get(key));
                remaining -= key;
            }
        }
        logger.debug("Roman numeral: {}", result.toString());
        return result.toString();

    }
}
