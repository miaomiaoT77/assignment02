package zest;


import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BitwiseSumCalculatorTest {

    BitwiseSumCalculator bitwiseSumCalculator = new BitwiseSumCalculator();
    @Test
    void sumPositive() {
        int a = 4;
        int b = 5;
        int expected = 9;
        int result = bitwiseSumCalculator.getSum(a, b);
        assertEquals(expected, result);
    }
    @Test
    void sumNegative() {
        int a = -4;
        int b = -5;
        int expected = -9;
        int result = bitwiseSumCalculator.getSum(a, b);
        assertEquals(expected, result);
    }
    @Test
    void sumMixPositiveNegative() {
        int a = -10;
        int b = 5;
        int expected = -5;
        int result = bitwiseSumCalculator.getSum(a, b);
        assertEquals(expected, result);
    }

    @Test
    void sumPositiveWithZero() {
        int a = 0;
        int b = 5;
        int expected = 5;
        int result = bitwiseSumCalculator.getSum(a, b);
        assertEquals(expected, result);
    }
    @Test
    void sumNegativeWithZero() {
        int a = 0;
        int b = -15;
        int expected = -15;
        int result = bitwiseSumCalculator.getSum(a, b);
        assertEquals(expected, result);
    }

    // Property-based testing
    @Property
    void testingSum(@ForAll int a, @ForAll int b) {
        int expected = a + b;
        int result = bitwiseSumCalculator.getSum(a, b);
        assertEquals(expected, result);
    }

    @Property
    void testingAddingZero(@ForAll int a) {
        assertEquals(a, bitwiseSumCalculator.getSum(a, 0));
        assertEquals(a, bitwiseSumCalculator.getSum(0, a));
    }

    @Property
    void testSymmetry(@ForAll int a, @ForAll int b) {
        int sumAandB = bitwiseSumCalculator.getSum(a, b);
        int sumBandA = bitwiseSumCalculator.getSum(b, a);
        assertEquals(sumAandB, sumBandA);
    }

    
}

