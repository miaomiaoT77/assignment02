package zest;


import java.util.HashMap;
import java.util.Map;

public class TwoSum {
            /**
            * pre-condition
            * nums != null
            * nums.length >= 2}
            */
    public int[] findTwoSum(int[] nums, int target) {
        // pre conditions
        if (nums == null || nums.length < 2) {
            throw new IllegalArgumentException("Input array is null or empty, or less than 2 elements in array");
        }
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            //invariant check before
            checkInvariants(numMap, nums, i);
            int complement = target - nums[i];
            if (numMap.containsKey(complement)) {
                // Post-condition checks
                /**
                 * post-condition
                 * result[0] != result[1
                 * nums[result[0]] + nums[result[1]] == target
                 */
                int[] result = new int[]{numMap.get(complement), i};
                if (result[0] == result[1]) {
                    throw new IllegalStateException("used the same element twice");
                }

                if (result[0] != result[1] && result[0] >= 0 && result[1] >= 0 && result[0] < nums.length && result[1] < nums.length) {
                    return result;
                }
            }
            numMap.put(nums[i], i);
            //invariant check after
            checkInvariants(numMap, nums, i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
    private void checkInvariants(Map<Integer, Integer> numMap, int[] nums, int i) {
        if (i < 0 || i >= nums.length) {
            throw new IllegalStateException(
                    String.format("Current index is out of array",
                            i, nums.length)
            );
        }
    }

}
