package LeetCode.binarySearchTree;

public class MinimumAbsoluteDifference {
    // https://leetcode.com/problems/minimum-absolute-difference-in-bst/
    // inorder(중위 순회): left → self → right
    // 이진검색 트리: 왼쪽 부분 트리는 slef보다 모두 작고, 오른쪽 부분 트리는 self 보다 크다.
    // 이진검색트리 성질 → inorder 순회를 하면 오름 차순 정렬된 리스트를 얻을 수 있다.
    int min;
    int prevVal;
    boolean first;

    public int getMinimumDifference(TreeNode root) {
        min = Integer.MAX_VALUE;
        first = true;
        inorder(root);
        return min;
    }

    private void inorder(TreeNode root) {
        if (root == null) return;

        // left
        inorder(root.left);

        // self
        if (first) {
            first = false;
        } else {
            min = Math.min(root.val - prevVal, min);
        }
        prevVal = root.val;

        // right
        inorder(root.right);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
