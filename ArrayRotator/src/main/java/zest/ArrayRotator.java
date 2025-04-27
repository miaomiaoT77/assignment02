package zest;


import java.util.Arrays;

public class ArrayRotator {

    public static int[] rotate(int[] originalArray, int rotationCount) {

        // Pre-conditions:
            // Check for null or empty array
        if (originalArray == null) {
            throw new IllegalArgumentException("Input array must not be null");
        }
        if (originalArray.length == 0) {
            throw new IllegalArgumentException("Input array must not be empty");
        }

            // Check for negative rotation count
        if (rotationCount < 0) {
            throw new IllegalArgumentException("Rotation count must be non-negative");
        }

        int arrayLength = originalArray.length;
        int[] rotatedArray = new int[arrayLength];

        // Normalize the rotation count
        rotationCount = rotationCount % arrayLength;

        // Rotate the array
        for (int i = 0; i < arrayLength; i++) {
            rotatedArray[(i + rotationCount) % arrayLength] = originalArray[i];
        }

        return rotatedArray;
    }
}
