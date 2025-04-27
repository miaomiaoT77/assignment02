package zest;

import java.util.*;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}

/**
 * Converts a sorted array into a height-balanced BST
 */
class SortedArray2BST {

    /**
     * Converts a sorted array to a height-balanced BST.
     *
     * @throws IllegalArgumentException if nums is null or empty
     * @throws IllegalStateException    if array is not strictly increasing
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null) {
            throw new IllegalArgumentException("Input array cannot be null");
        }
        if (nums.length == 0) {
            throw new IllegalArgumentException("Input array cannot be empty");
        }
        if (!isStrictlyIncreasing(nums)) {
            throw new IllegalStateException("Input array must be strictly increasing");
        }

        return constructBST(nums, 0, nums.length - 1);
    }

    /**
     * Helper method to check if the array is strictly increasing.
     */
    private boolean isStrictlyIncreasing(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] >= nums[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Constructs the BST recursively.
     */
    private TreeNode constructBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        // Select middle element as root, handling even-length arrays per constraints
        int mid = left + (right - left) / 2;
        if ((right - left + 1) % 2 == 0 && mid > left) {
            mid--; // Ensure mid is valid and doesn't go below left

        }

        TreeNode node = new TreeNode(nums[mid]);
        node.left = constructBST(nums, left, mid - 1);
        node.right = constructBST(nums, mid + 1, right);
        return node;
    }

    /**
     * Converts the tree to a level-order representation with nulls for missing nodes.
     */
    public List<Integer> levelOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);


        while (!queue.isEmpty()) {
            TreeNode current = queue.poll();
            if (current != null) {
                result.add(current.val);
                queue.offer(current.left);
                queue.offer(current.right);
            } else {
                result.add(null);
            }
        }

        // Remove trailing nulls
        while (!result.isEmpty() && result.get(result.size() - 1) == null) {
            result.remove(result.size() - 1);
        }
        return result;
    }
}
