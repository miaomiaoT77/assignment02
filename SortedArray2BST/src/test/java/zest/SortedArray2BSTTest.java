package zest;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;

import net.jqwik.api.*;
import net.jqwik.api.arbitraries.*;

import static org.junit.jupiter.api.Assertions.*;

public class SortedArray2BSTTest {
    SortedArray2BST converter = new SortedArray2BST();
    //jacoco-code coverage
    @Test
    void test_OddSortedArray() {
        List<Integer> expected = Arrays.asList(0, -10, 5, null, -3, null, 9);
        TreeNode root = converter.sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        List<Integer> actual = converter.levelOrder(root);
        assertEquals(expected, actual);
    }

    @Test
    void test_EvenSortedArray() {
        List<Integer> expected = Arrays.asList(3, null, 7);
        TreeNode root = converter.sortedArrayToBST(new int[]{3, 7});
        List<Integer> actual = converter.levelOrder(root);
        assertEquals(expected, actual);
    }

    @Test
    void test_SingleSortedArray() {
        List<Integer> expected = Arrays.asList(1);
        TreeNode root = converter.sortedArrayToBST(new int[]{1});
        List<Integer> actual = converter.levelOrder(root);
        assertEquals(expected, actual);
    }
    @Test
    void test_LargeSortedArray() {
        List<Integer> expected = Arrays.asList(8, 4, 12, 2, 6, 10, 14, 1, 3, 5, 7, 9, 11, 13, 15);
        TreeNode root = converter.sortedArrayToBST(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15});
        List<Integer> actual = converter.levelOrder(root);
        assertEquals(expected, actual);
    }
    @Test
    void test_NegativeSortedArray() {
        List<Integer> expected = Arrays.asList(-3, -17, -2, null, -10, null, -1);
        TreeNode root = converter.sortedArrayToBST(new int[]{-17, -10, -3, -2, -1});
        List<Integer> actual = converter.levelOrder(root);
        assertEquals(expected, actual);
    }



    // added missing branches tests
    @Test
    void test_EmptySortedArray() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> converter.sortedArrayToBST(new int[]{}));
        assertEquals("Input array cannot be empty", thrown.getMessage());
    }

    @Test
    void test_NullSortedArray() {
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> converter.sortedArrayToBST(null));
        assertEquals("Input array cannot be null", thrown.getMessage());
    }

    @Test
    void test_LevelNullSortedArray() {
        List<Integer> result = converter.levelOrder(null);
        assertTrue(result.isEmpty());
    }
    @Test
    void test_TrainingNullSortedArray() {
        List<Integer> expected = Arrays.asList(-3, -2, null, 1);
        TreeNode root = new TreeNode(-3);
        root.left = new TreeNode(-2);
        root.left.left = new TreeNode(1);
        List<Integer> actual = converter.levelOrder(root);
        assertEquals(expected, actual);
    }
    @Test
    void test_TrainingWithoutNullSortedArray() {
        List<Integer> expected = Arrays.asList(-3, -2, null, 1);
        TreeNode root = new TreeNode(-3);
        root.left = new TreeNode(-2);
        root.left.left = new TreeNode(1);
        List<Integer> actual = converter.levelOrder(root);
        assertEquals(expected, actual);

    }

    @Test
    void test_NullArray() {
        int[] nums = null;
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> converter.sortedArrayToBST(nums));
        assertEquals("Input array cannot be null", thrown.getMessage());
    }

    @Test
    void test_EmptyArray() {
        int[] nums = {};
        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class,
                () -> converter.sortedArrayToBST(nums));
        assertEquals("Input array cannot be empty", thrown.getMessage());
    }

    @Test
    void test_UnorderedArray() {
        int[] nums = {1, 3, 2}; // Not strictly increasing
        IllegalStateException thrown = assertThrows(IllegalStateException.class,
                () -> converter.sortedArrayToBST(nums));
        assertEquals("Input array must be strictly increasing", thrown.getMessage());
    }
    @Property
    boolean testTreeStructure(@ForAll("sortedArrays") int[] input) {
        TreeNode root = converter.sortedArrayToBST(input);

        // Verify height-balanced property
        return isHeightBalanced(root);
    }
    @Provide
    Arbitrary<int[]> sortedArrays() {
        return Arbitraries.integers()
                .between(-100, 100)
                .array(int[].class)
                .ofMinSize(1)
                .ofMaxSize(10)
                .filter(arr -> isStrictlyIncreasing(arr));
    }

    private boolean isHeightBalanced(TreeNode node) {
        return checkHeight(node) != -1;
    }

    private int checkHeight(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = checkHeight(node.left);
        int rightHeight = checkHeight(node.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    private boolean isStrictlyIncreasing(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] >= nums[i + 1]) {
                return false;
            }
        }
        return true;
    }
}



