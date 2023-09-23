package LeetCode.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class InorderTraversal {
    // https://leetcode.com/problems/binary-tree-inorder-traversal/description/
    // Inorder: left -> self -> right
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> answer = new ArrayList<>();
        Inorder(root, answer);
        return answer;
    }

    private void Inorder(TreeNode self, List<Integer> answer) {
        if (self == null) return;
        Inorder(self.left, answer);
        answer.add(self.value);
        Inorder(self.right, answer);
    }


    class TreeNode {
        int value;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.value = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.value = val;
            this.left = left;
            this.right = right;
        }
    }
}
