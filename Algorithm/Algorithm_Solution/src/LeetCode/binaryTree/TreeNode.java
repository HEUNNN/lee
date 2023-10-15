package LeetCode.binaryTree;

public class TreeNode {
    String value;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(String val) {
        this.value = val;
    }

    TreeNode(String val, TreeNode left, TreeNode right) {
        this.value = val;
        this.left = left;
        this.right = right;
    }
}
