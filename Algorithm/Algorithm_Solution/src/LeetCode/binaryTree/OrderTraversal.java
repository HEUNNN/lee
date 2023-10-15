package LeetCode.binaryTree;

import java.util.ArrayList;
import java.util.List;

public class OrderTraversal {
    // https://leetcode.com/problems/binary-tree-inorder-traversal/description/
    // Inorder: left -> self -> right
    // Preorder: self -> left -> right
    // postorder: left -> right -> self
    public static void main(String[] args) {
        TreeNode f = new TreeNode("F", null, null);
        TreeNode g = new TreeNode("G", null, null);
        TreeNode e = new TreeNode("E", f, g);
        TreeNode d = new TreeNode("D", null, null);
        TreeNode b = new TreeNode("B", d, e);
        TreeNode c = new TreeNode("C", null, null);
        TreeNode root = new TreeNode("A", b, c);

//        List<String> result = new InorderTraversal().inorderTraversal(root);
//        List<String> result = new InorderTraversal().preorderTraversal(root);
        List<String> result = new OrderTraversal().postorderTreaversal(root);
        for (String string : result) {
            System.out.println(string);
        }
    }

    public List<String> preorderTraversal(TreeNode root) {
        List<String> answer = new ArrayList<>();
        preorder(root, answer);
        return answer;
    }

    private void preorder(TreeNode self, List<String> answer) {
        if (self == null) return;
        answer.add(self.value);
        preorder(self.left, answer);
        preorder(self.right, answer);
    }

    public List<String> inorderTraversal(TreeNode root) {
        List<String> answer = new ArrayList<>();
        inorder(root, answer);
        return answer;
    }

    private void inorder(TreeNode self, List<String> answer) {
        if (self == null) return;
        inorder(self.left, answer);
        answer.add(self.value);
        inorder(self.right, answer);
    }

    public List<String> postorderTreaversal(TreeNode root) {
        List<String> answer = new ArrayList<>();
        postorder(root, answer);
        return answer;
    }

    private void postorder(TreeNode self, List<String> answer) {
        if (self == null) return;
        postorder(self.left, answer);
        postorder(self.right, answer);
        answer.add(self.value);
    }
}
