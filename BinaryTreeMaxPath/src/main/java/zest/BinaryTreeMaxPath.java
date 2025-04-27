package zest;

public class BinaryTreeMaxPath {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int x) {
            val = x;
        }

        public static TreeNode constructTree(Integer[] array) {
            // added: checks for empty array
            if (array == null) return null;
            if (array.length == 0) return null;
            TreeNode[] nodes = new TreeNode[array.length];
            for (int i = 0; i < array.length; i++) {
                if (array[i] != null) {
                    nodes[i] = new TreeNode(array[i]);
                    if (i > 0) {
                        TreeNode parent = nodes[(i - 1) / 2];
                        if (parent != null) {
                            if (i % 2 == 1) parent.left = nodes[i];
                            else parent.right = nodes[i];
                        }
                    }
                }
            }
            return nodes[0];
        }
    }

    private int maxPathSum;

    public int maxPathSum(TreeNode root) {
        // changed this to ensure that it returns 0 because of empty array
        if (root == null) return 0;
        maxPathSum = Integer.MIN_VALUE;
        maxGain(root);
        return maxPathSum;
    }

    private int maxGain(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int leftGain = Math.max(maxGain(node.left), 0);
        int rightGain = Math.max(maxGain(node.right), 0);

        int priceNewPath = node.val + leftGain + rightGain;

        maxPathSum = Math.max(maxPathSum, priceNewPath);

        return node.val + Math.max(leftGain, rightGain);
    }
}
