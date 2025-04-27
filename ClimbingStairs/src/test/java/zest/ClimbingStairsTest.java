package zest;

import net.jqwik.api.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ClimbingStairsTest {

    ClimbingStairs climbingStairs = new ClimbingStairs();
    @Test
    void baseCase1(){
        long result = climbingStairs.climbStairs(1);
        assertEquals(1, result);
    }

    @Test
    void baseCase2(){
        long result = climbingStairs.climbStairs(2);
        assertEquals(2, result);
    }

    @Test
    void fiveSteps() {
        long result = climbingStairs.climbStairs(5);
        assertEquals(8, result);
    }

    @Test
    void twentySteps() {
        long result = climbingStairs.climbStairs(20);
        assertEquals(10946, result);
    }

    @Test
    void veryLargeInput() {
        long result = climbingStairs.climbStairs(45);
        assertEquals(1836311903L, result);
    }

    // Property-based testing
    @Provide
    Arbitrary<Integer> littleNumbers() {
        return Arbitraries.integers().between(1, 45);
    }
    @Provide
    Arbitrary<Integer> largeNumbers() {
        return Arbitraries.integers().between(1, 92);
    }

    @Property
    void alwaysPositive(@ForAll("littleNumbers") int n) {
        long result = climbingStairs.climbStairs(n);
        assertTrue(result > 0);
    }

    @Property
    void checklongNumber(@ForAll("largeNumbers") int n) {
        if (n <= 90) {
            long result = climbingStairs.climbStairs(n);
            long next_result = climbingStairs.climbStairs(n + 1);
            assertTrue(result <= next_result);
        }
    }

}


