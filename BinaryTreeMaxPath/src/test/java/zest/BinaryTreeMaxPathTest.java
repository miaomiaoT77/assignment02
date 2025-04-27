package zest;

import net.jqwik.api.*;
import net.jqwik.api.constraints.Size;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeMaxPathTest {
    BinaryTreeMaxPath btree = new BinaryTreeMaxPath();
    @Test
    void emptyArray(){
        Integer[] arr = {};
        BinaryTreeMaxPath.TreeNode root = BinaryTreeMaxPath.TreeNode.constructTree(arr);
        int maxSum = btree.maxPathSum(root);
        assertEquals(0, maxSum);
    }

    @Test
    void emptyTree(){
        Integer[] arr = {};
        BinaryTreeMaxPath.TreeNode root = BinaryTreeMaxPath.TreeNode.constructTree(arr);
        assertNull(root);
    }

    @Test
    void rootNull(){
        BinaryTreeMaxPath.TreeNode root = BinaryTreeMaxPath.TreeNode.constructTree(null);
        assertNull(root);
    }

    @Test
    void oneElementArray(){
        Integer[] arr = {6};
        BinaryTreeMaxPath.TreeNode root = BinaryTreeMaxPath.TreeNode.constructTree(arr);
        int maxSum = btree.maxPathSum(root);
        assertEquals(6, maxSum);
    }

    @Test
    void validTree() {
        Integer[] arr = {1, 2, 3};
        BinaryTreeMaxPath.TreeNode root = BinaryTreeMaxPath.TreeNode.constructTree(arr);
        int maxSum = btree.maxPathSum(root);
        assertEquals(6, maxSum);
    }

    @Test
    void negativeValuesTree(){
        Integer[] arr = {-10, 9, 20, null, null, 15, 7};
        BinaryTreeMaxPath.TreeNode root = BinaryTreeMaxPath.TreeNode.constructTree(arr);
        int maxSum = btree.maxPathSum(root);
        assertEquals(42, maxSum);
    }

    @Test
    void allNegativeValuesTree(){
        Integer[] arr = {-3, -2, -1};
        BinaryTreeMaxPath.TreeNode root = BinaryTreeMaxPath.TreeNode.constructTree(arr);
        int maxSum = btree.maxPathSum(root);
        assertEquals(-1, maxSum);
    }

    @Test
    void moreComplexTree(){
        Integer[] arr = {10, 2, 10, 20, 1, null, -25, null, null, null, null, 3, 4};
        BinaryTreeMaxPath.TreeNode root = BinaryTreeMaxPath.TreeNode.constructTree(arr);
        int maxSum = btree.maxPathSum(root);
        assertEquals(42, maxSum);
    }

    @Test
    void testLeftChildIsSetCorrectly() {
        Integer[] input = {1, 2};
        BinaryTreeMaxPath.TreeNode root = BinaryTreeMaxPath.TreeNode.constructTree(input);
        assertNotNull(root);
        assertEquals(1, root.val);
        assertNotNull(root.left);
        assertEquals(2, root.left.val);
        assertNull(root.right);
    }

    // Property-based testing

    // Not used
    //    @Provide
    //    Arbitrary<List<Integer>> binaryTreeArr() {
    //        return Arbitraries.integers()
    //                .between(-10_000, 10_000)
    //                .injectNull(0.3)
    //                .list()
    //                .ofMaxSize(1023);
    //    }

    @Property
    void testNull() {
        BinaryTreeMaxPath.TreeNode result = BinaryTreeMaxPath.TreeNode.constructTree(null);
        assertNull(result);
    }

    @Property
    void testEmptyArray() {
        Integer[] input = new Integer[0];
        BinaryTreeMaxPath.TreeNode root = BinaryTreeMaxPath.TreeNode.constructTree(input);
        assertEquals(0, btree.maxPathSum(root));
    }

    @Property
    void testOneElement(
            @ForAll @Size(min = 1, max =  10) List<Integer> values) {
        Integer[] vals = values.toArray(new Integer[0]);
        BinaryTreeMaxPath.TreeNode result = BinaryTreeMaxPath.TreeNode.constructTree(vals);
        assertEquals(vals[0], result.val);
    }




}
