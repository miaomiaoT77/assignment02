package zest;


import net.jqwik.api.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZerosToEndTest {
    ZeroesToEnd zeroArray = new ZeroesToEnd();

    @Test
    void testNormalArray() {
        int[] input = {1, 2, 0, 4, 3, 0, 5, 0};
        int[] expected = {1, 2, 4, 3, 5, 0, 0, 0};
        assertArrayEquals(expected, zeroArray.pushZeroesToEnd(input));
    }

    @Test
    void testNegativeArray() {
        int[] input = {-1, -2, 0 , 0 ,-3, -4, 0,-5};
        int[] expected ={-1, -2, -3, -4, -5, 0, 0, 0 };
        assertArrayEquals(expected, zeroArray.pushZeroesToEnd(input));
    }
    @Test
    void testZeroArray() {
        int[] input = {0,0,0};
        int[] expected = {0,0,0};
        assertArrayEquals(expected, zeroArray.pushZeroesToEnd(input));
    }
    @Test
    void testOverLimitArray() {
        int[] input = {1, 0, 2, 0, 3, 0, 4, 0, 5, 0};
        int[] expected = {1, 2, 3, 4, 5, 0, 0, 0, 0, 0};
        assertArrayEquals(expected, zeroArray.pushZeroesToEnd(input));
    }


    @Test
    void testWithoutZeroArray() {
        int[] input = {1,2,3,4,5};
        int[] expected = {1,2,3,4,5};
        assertArrayEquals(expected, zeroArray.pushZeroesToEnd(input));
    }
    @Test
    void testEmptyArray() {
        int[] nums = {};
        int[] expected = {};
        assertArrayEquals(expected, zeroArray.pushZeroesToEnd(nums));
    }
    @Test
    void testOneElementArray() {
        int[] input = {1};
        int[] expected = {1};
        assertArrayEquals(expected, zeroArray.pushZeroesToEnd(input));
    }
    @Test
    //pre-condition
    void testNullArray() {
        int[] nums = null;
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> zeroArray.pushZeroesToEnd(nums));
        assertEquals("Input array is null, or its length is not within the range 0 to 10", thrown.getMessage());
    }

    //post-condition
    @Test
    void testResultArrayLengthMatchesInput() {
        int[] nums = {1, 2, 0, 4, 3, 0, 5, 0};
        int[] result = zeroArray.pushZeroesToEnd(nums);
        assertEquals(nums.length, result.length, "Result array length does not match input array length");
    }
    @Property
    boolean testArrayWithinConstraints(@ForAll("validArrays") int[] input) {
        int[] result = zeroArray.pushZeroesToEnd(input);
        return result.length == input.length;
    }

    @Provide
    Arbitrary<int[]> validArrays() {
        return Arbitraries.integers().between(-100, 100)
                .array(int[].class).ofMaxSize(10);
    }
}