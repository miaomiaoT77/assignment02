package zest;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ArrayRotatorTest {

    ArrayRotator myArray = new ArrayRotator();

    @Test
    void emptyArray() {
        int[] a = {};
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            myArray.rotate(a, 2);
        });
        assertEquals("Input array must not be empty", exception.getMessage());
    }

    @Test
    void nullArray() {
        int[] a = null;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            myArray.rotate(a, 2);
        });
        //System.out.println(exception.getMessage());
        assertEquals("Input array must not be null", exception.getMessage());
    }

    @Test
    void zeroRotation() {
        int[] a = {1, 2, 3, 4, 5, 6, 7};
        int[] result = myArray.rotate(a, 0);
        assertArrayEquals(a, result);
    }

    @Test
    void negativeRotationCount() {
        int[] a = {1, 2, 3, 4, 5};
        int rotationCount = -2;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            myArray.rotate(a, rotationCount);
        });
        assertEquals("Rotation count must be non-negative", exception.getMessage());
    }
    @Test
    void validRotationCount() {
        int[] a = {3, 4, 5, 8, 10};
        int rotationCount = 2;
        int[] result  = myArray.rotate(a, rotationCount);
        int[] expected = {8,10,3,4,5};
        assertArrayEquals(expected,result);
    }

    // Property-based testing

    @Provide
    Arbitrary<int[]> nonEmptyArrays() {
        return Arbitraries.integers()
                .between(-100, 100)
                .array(int[].class)
                .ofMinSize(1);
    }
    @Property
    void zeroRotationCount(
            @ForAll("nonEmptyArrays") int[] original) {
        int[] result = myArray.rotate(original, 0);
        assertArrayEquals(original, result);
    }

    // if it returns the same array, if the rotation count is equal to the size of array
    @Property
    void sameRotationAsArrayLength(
            @ForAll("nonEmptyArrays") int[] input) {
        int[] result = myArray.rotate(input, input.length);
        assertArrayEquals(input, result);
    }

    @Property
    void checkPreserveElements(
            @ForAll("nonEmptyArrays") int[] input,
            @ForAll @IntRange(min=0, max=1000) int rotations) {
        int[] result = myArray.rotate(input, rotations);
        assertEquals(input.length, result.length);

    }


}
