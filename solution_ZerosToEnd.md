## Problem: ZerosToEnd

# Task 1 Code Coverage

Based on structural testing, we wrote line coverage, branch coverage, path coverage.

Result:
![Zeros_jacoco.png](images/Zeros_jacoco.png)


# Task 2 Designing Contracts
### pre-condition
```java
if (arr == null || arr.length < 0 || arr.length > 10) {
        throw new IllegalArgumentException("Input array is null, or its length is not within the range 0 to 10");
}

```
### post-condition
```ja[solution_ZerosToEnd.md](solution_ZerosToEnd.md)va
    if (result[0] == result[1]) {
    throw new IllegalStateException("used the same element twice");
    }
```
### invariant
```java
// invraint
if (i < 0 || i >= arr.length) {
    throw new IllegalStateException("Index out of bounds during processing");
            }

```
# Task 3 Testing Contracts
I add  more tests based on pre-condition, post-condition and invariant.
as we can see

```java
@Test
    //pre-condition
void testNullArray() {
    int[] nums = null;
    IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
            () -> zeroArray.pushZeroesToEnd(nums));
    assertEquals("Input array is null, or its length is not within the range 0 to 10", thrown.getMessage());
}
@Test
void testOverLimitArray() {
    int[] input = {1, 0, 2, 0, 3, 0, 4, 0, 5, 0};
    int[] expected = {1, 2, 3, 4, 5, 0, 0, 0, 0, 0};
    assertArrayEquals(expected, zeroArray.pushZeroesToEnd(input));
}

@Test
void testEmptyArray() {
    int[] nums = {};
    int[] expected = {};
    assertArrayEquals(expected, zeroArray.pushZeroesToEnd(nums));
}
//post-condition
@Test
void testResultArrayLengthMatchesInput() {
    int[] nums = {1, 2, 0, 4, 3, 0, 5, 0};
    int[] result = zeroArray.pushZeroesToEnd(nums);
    assertEquals(nums.length, result.length, "Result array length does not match input array length");
}


```
all the added test passed
![allpassedtests.png](images/allpassedtests.png)

# Task 4 Property-Based Testing

```java
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

```

result:
![PBT.png](images/PBT.png)