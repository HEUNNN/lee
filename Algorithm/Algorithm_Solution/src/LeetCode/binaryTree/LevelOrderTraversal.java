package LeetCode.binaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
    // https://leetcode.com/problems/binary-tree-level-order-traversal-ii/description/
    // level 순회 → 이진 트리를 graph라고 쳤을 때 BFS 탐색 하는 것과 같다.
    // 이 문제에서 원하는 level 순회는 leaf to root 이다.

    // root = [3,9,20,null,null,15,7]

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> answer = new ArrayList<>();
        if (root == null) return answer;

        Queue<TreeNode> treeNodeQueue = new LinkedList<>();
        treeNodeQueue.offer(root);

        while (!treeNodeQueue.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            int size = treeNodeQueue.size(); // 특정 level에 존재하는 TreeNode 개수
            for (int i = 0; i < size; i++) {
                TreeNode node = treeNodeQueue.poll();
                level.add(node.value);
                if (node.left != null) treeNodeQueue.offer(node.left); // 꺼낸 TreeNode의 left, right leaf 들을 큐에 저장한다.
                if (node.right != null) treeNodeQueue.offer(node.right);
            }

            answer.add(0, level); // level order traveral을 leaf to root로 구해야하기 때문
        }
        return answer;
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
