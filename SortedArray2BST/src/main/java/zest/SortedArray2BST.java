package zest;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
/**
 *
 * Constraints:
 * pre-conditions
 * nums is sorted in a strictly increasing order.
 * nums cannot be null or empty.
 * nums cannot have null element in it.
 * post-constrains
 * For any array or subarray with an even number of elements, the selected root is the middle element at the position (length/2) - 1`.
 * In output array, absent nodes should be represented as null.
 * Invariants
 */
class SortedArray2BST {

    /**
     * @throws IllegalArgumentException if nums is null or empty
     * @throws IllegalStateException if array is not strictly increasing
     */
    public TreeNode sortedArrayToBST(int[] nums) {

        if (nums == null) throw new IllegalArgumentException("Array cannot be null");
        if (nums.length == 0) throw new IllegalArgumentException("Array cannot be empty");
        if (!isIncreasing(nums)) {
            throw new IllegalStateException("Array must be strictly increasing");
        }
        return constructBST(nums, 0, nums.length - 1);
    }
    public boolean isIncreasing(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] >= nums[i + 1]) {
                return false;
            }
        }
        return true;
    }
}

    private TreeNode constructBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        if ((right - left + 1) % 2 == 0) {
            mid = Math.max(left, mid - 1);  // Ensure mid does not fall below left
        }
        TreeNode node = new TreeNode(nums[mid]);
        node.left = constructBST(nums, left, mid - 1);
        node.right = constructBST(nums, mid + 1, right);
        return node;
    }

    public List<Integer> levelOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean allNulls;

        while (!queue.isEmpty()) {
            int size = queue.size();
            allNulls = true;  // To check if all elements in the current level are null

            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (current != null) {
                    result.add(current.val);
                    queue.offer(current.left);
                    queue.offer(current.right);
                    if (current.left != null || current.right != null) {
                        allNulls = false;
                    }
                } else {
                    result.add(null);
                    queue.offer(null);
                    queue.offer(null);
                }
            }
            // Clean up all trailing nulls if only nulls are in the queue
            if (allNulls) {
                break;
            }
        }

        // Remove trailing nulls from result list to match expected format
        while (result.size() > 0 && result.get(result.size() - 1) == null) {
            result.remove(result.size() - 1);
        }

        return result;
    }
}

