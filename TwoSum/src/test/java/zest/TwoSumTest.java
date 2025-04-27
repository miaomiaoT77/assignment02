package zest;

import net.jqwik.api.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TwoSumTest {
    TwoSum solver = new TwoSum();

    // code coverage
    @Test
    void NormalTwoSum() {
        int[] arr = {2, 7, 11, 15};
        int target = 9;
        int[] result = solver.findTwoSum(arr, target);
        Assertions.assertArrayEquals(new int[]{0, 1}, result);
    }

    @Test
    void ZeroTwoSum() {
        int[] arr = {0, 0};
        int target = 0;
        int[] result = solver.findTwoSum(arr, target);
        Assertions.assertArrayEquals(new int[]{0, 1}, result);
    }

    @Test
    void NegativeTwoSum() {
        int[] arr = {-5, -3, -2, -1, -7};
        int target = -4;
        int[] result = solver.findTwoSum(arr, target);
        Assertions.assertArrayEquals(new int[]{1, 3}, result);
    }

    @Test
    void ExceedTwoSum() {
        int[] arr = {-5, -3, -2, -1, -7};
        int target = 20;
        assertThrows(IllegalArgumentException.class, () -> {
            solver.findTwoSum(arr, 20);
        });
    }

    @Test
    void RepeatTwoSum() {
        int[] arr = {2, 3, 0, 5, 3};
        int target = 6;
        int[] result = solver.findTwoSum(arr, target);
        Assertions.assertArrayEquals(new int[]{1, 4}, result);
    }

    @Test
    void IllegalInputTwoSum() {
        int[] arr = {-5};
        int target = 23;
        assertThrows(IllegalArgumentException.class, () -> {
            solver.findTwoSum(arr, 23);
        });
    }

    //testing contracts
    //pre-conditions
    @Test
    void NullInputTwoSum() {
        int[] arr = null;
        int target = 23;
        assertThrows(IllegalArgumentException.class, () -> solver.findTwoSum(arr, target), "Input cannot be null");
    }

    @Test
    void EmptyInputTwoSum() {
        int[] arr = {};
        int target = 23;
        assertThrows(IllegalArgumentException.class, () -> solver.findTwoSum(arr, target), "Input cannot be empty");
    }


    @Test
    void SingleInputTwoSum() {
        int[] arr = {1};
        int target = 2;
        assertThrows(IllegalArgumentException.class, () -> solver.findTwoSum(arr, target), "Input cannot be a single number");
    }

    // post-condition
    @Test
    void OneSolutionTwoSum() {
        int[] arr = {-5, -3, 3, 5};
        int target = 0;
        int[] result = solver.findTwoSum(arr, target);
        Assertions.assertArrayEquals(new int[]{1, 2}, result, "Each input will have exactly one solution");
        assertFalse(Arrays.equals(new int[]{0, 1, 2, 3}, result));
    }

    @Test
    void numberWillOnlyUseOnce() {
        int[] arr = {2, 6, 3, 1};
        int target = 4;
        int[] result = solver.findTwoSum(arr, target);
        Assertions.assertArrayEquals(new int[]{2, 3}, result);
        assertFalse(Arrays.equals(new int[]{0, 0}, result), "You may not use the same element of the array twice.");
    }

    @Property
    boolean validateSolution(@ForAll List<Integer> nums, @ForAll int target) {
        if (nums == null || nums.size() < 2) {
            return true;
        }

        try {
            // Convert List<Integer> to int[]
            int[] numsArray = nums.stream().mapToInt(Integer::intValue).toArray();
            int[] result = solver.findTwoSum(numsArray, target);

            return result[0] != result[1] &&
                    result[0] >= 0 && result[0] < numsArray.length &&
                    result[1] >= 0 && result[1] < numsArray.length &&
                    numsArray[result[0]] + numsArray[result[1]] == target;

        } catch (IllegalArgumentException ex) {
            return true;
        }
    }
}

