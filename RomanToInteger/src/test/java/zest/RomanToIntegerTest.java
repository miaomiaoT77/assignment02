package zest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import net.jqwik.api.*;
import net.jqwik.api.arbitraries.*;

class RomanToIntegerTest {
    //Jacoco-line coverage
    @Test
    void test_SingleNumbers() {
        RomanToInteger romanToInteger = new RomanToInteger();
        assertEquals(5, romanToInteger.romanToInt("V"));
        assertEquals(7, romanToInteger.romanToInt("VII"));
        assertEquals(1, romanToInteger.romanToInt("I"));
        assertEquals(4, romanToInteger.romanToInt("IV"));
    }

    @Test
    void test_MultipleNumbers() {
        RomanToInteger romanToInteger = new RomanToInteger();
        assertEquals(12, romanToInteger.romanToInt("XII"));
        assertEquals(19, romanToInteger.romanToInt("XIX"));
    }

    //branch coverage
    @Test
    void test_AddNumbers() {
        RomanToInteger romanToInteger = new RomanToInteger();
        assertEquals(6, romanToInteger.romanToInt("VI"));
        assertEquals(8, romanToInteger.romanToInt("VIII"));
    }

    @Test
    void test_SubtractNumbers() {
        RomanToInteger romanToInteger = new RomanToInteger();
        assertEquals(9, romanToInteger.romanToInt("IX"));
        assertEquals(4, romanToInteger.romanToInt("IV"));
    }

    //path coverage
    @Test
    void test_ComplexNumbers() {
        RomanToInteger romanToInteger = new RomanToInteger();
        assertEquals(17, romanToInteger.romanToInt("XVII"));
        assertEquals(13, romanToInteger.romanToInt("XIII"));
        assertEquals(90, romanToInteger.romanToInt("XC"));
        assertEquals(3999, romanToInteger.romanToInt("MMMCMXCIX"));
    }

    //testing contracts
    //normal operation when pre-conditions are met.
    @Test
    void test_NormalNumbers() {
        RomanToInteger romanToInteger = new RomanToInteger();
        //meet the pre-condition
        assertEquals(5, romanToInteger.romanToInt("V"));
        assertEquals(17, romanToInteger.romanToInt("XVII"));
        //meet the post-condition
        //boundary test
        assertEquals(1, romanToInteger.romanToInt("I"));
        assertEquals(3999, romanToInteger.romanToInt("MMMCMXCIX"));
    }

    //pre-conditions are violated.
    @Test
    void test_PreInvalidNumbers() {
        RomanToInteger romanToInteger = new RomanToInteger();
        assertThrows(AssertionError.class, () -> romanToInteger.romanToInt(null));
        assertThrows(AssertionError.class, () -> romanToInteger.romanToInt(""));
        assertThrows(AssertionError.class, () -> romanToInteger.romanToInt("MAMA"));
    }

    //post-condition are violated.
    @Test
    void test_PostInvalidNumbers() {
        RomanToInteger romanToInteger = new RomanToInteger();
        assertThrows(AssertionError.class, () -> romanToInteger.romanToInt("MMMMCX"));
        assertThrows(AssertionError.class, () -> romanToInteger.romanToInt("MMMMM"));
    }
    @Test
    void testValidFormats() {
        RomanToInteger converter = new RomanToInteger();
        assertEquals(3, converter.romanToInt("III"));
        assertEquals(4, converter.romanToInt("IV"));
    }

    @Test
    void testInvalidFormats() {
        RomanToInteger converter = new RomanToInteger();
        assertThrows(AssertionError.class, () -> converter.romanToInt("IIII"));
        assertThrows(AssertionError.class, () -> converter.romanToInt("VV"));
    }
    @Test
    void test_InvariantNumbers() {
        RomanToInteger romanToInteger = new RomanToInteger();
        romanToInteger.romanToInt("XX");
        assertTrue(romanToInteger.invariant());
    }


    @Property
    void validRomanNumber(@ForAll("RomanNumbers") String roman) {
        RomanToInteger romanToInteger = new RomanToInteger();
        int result = romanToInteger.romanToInt(roman);
        assertTrue(result >= 1 && result <= 3999);
    }

    @Provide
    Arbitrary<String> RomanNumbers() {
        return Arbitraries.strings()
                .withChars('I', 'V', 'X', 'L', 'C', 'D', 'M')
                .ofMinLength(1).ofMaxLength(15)
                .filter(s -> s.matches("^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$"));
    }
}


