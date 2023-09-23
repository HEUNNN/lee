package LeetCode.naryTree;

import java.util.ArrayList;
import java.util.List;

public class PreOrderTraversal {
    // https://leetcode.com/problems/n-ary-tree-preorder-traversal/description/
    /*
    preorder: self -> children(left -> right)
    postorder: children(left -> right) -> self
    */
    // root = [1, null, 3, 2, 4, null, 5, 6]

    public List<Integer> preorder(Node root) {
        List<Integer> result = new ArrayList<>();
        traversal(result, root);
        return result;
    }

    private void traversal(List<Integer> result, Node root) {
        if (root == null) return;
        result.add(root.val); // self 탐색
        for (Node child : root.children) {
            traversal(result, child);
        }
    }

    class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val, List<Node> _children) {
            this.val = _val;
            this.children = _children;
        }
    }
}


