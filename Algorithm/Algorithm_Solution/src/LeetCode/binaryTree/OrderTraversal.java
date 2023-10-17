package LeetCode.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class OrderTraversal {
    // inorder: https://leetcode.com/problems/binary-tree-inorder-traversal/description/
    // levelorder: https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/
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

        List<List<String>> levelorder = new OrderTraversal().levelorderReverse(root);
        for (List<String> strings : levelorder) {
            for (String string : strings) {
                System.out.print(string + " ");
            }
            System.out.println();
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

    private List<List<String>> levelorder(TreeNode root) {
        // Tree 구조는 graph와 달리 중복으로 연결되어 있지 않기 때문에 visited가 필요 없다.
       List<List<String>> result = new ArrayList<>();
       if (root == null) return result;

       Queue<TreeNode> queue = new LinkedList<>();

       queue.add(root);

       while (!queue.isEmpty()) {
           int qSize = queue.size();
           List<String> list = new ArrayList<>();
           for (int i = 0; i < qSize; i++) {
               TreeNode currNode = queue.poll();
               list.add(currNode.value);
               if (currNode.left != null) {
                   queue.add(currNode.left);
               }
               if (currNode.right != null) {
                   queue.add(currNode.right);
               }
           }
           result.add(list);
       }
       return result;
    }

    private List<List<String>> levelorderReverse(TreeNode root) {
        // Tree 구조는 graph와 달리 중복으로 연결되어 있지 않기 때문에 visited가 필요 없다.
        List<List<String>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();

        queue.add(root);

        while (!queue.isEmpty()) {
            int qSize = queue.size();
            List<String> list = new ArrayList<>();
            for (int i = 0; i < qSize; i++) {
                TreeNode currNode = queue.poll();
                list.add(currNode.value);
                if (currNode.left != null) {
                    queue.add(currNode.left);
                }
                if (currNode.right != null) {
                    queue.add(currNode.right);
                }
            }
            result.add(0, list);
        }
        return result;
    }
}
