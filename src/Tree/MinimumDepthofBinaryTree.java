package Tree;

import java.util.LinkedList;
import java.util.Queue;

/*
    Given a binary tree, find its minimum depth.

    The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

    Note: A leaf is a node with no children.

    Example:

    Given binary tree [3,9,20,null,null,15,7],

        3
       / \
      9  20
        /  \
       15   7
    return its minimum depth = 2.
 */
public class MinimumDepthofBinaryTree {

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
    /*
         DFS
     */
    public int minDepth(TreeNode root) {
        if(root == null)
            return 0;
        if(root.left == null && root.right == null){
            return 1;
        } else if(root.left != null && root.right != null){
            return 1 + Math.min(minDepth(root.left), minDepth(root.right));
        } else{
            if(root.left != null){
                return 1 + minDepth(root.left);
            } else{
                return 1 + minDepth(root.right);
            }
        }
    }

    /*
         BFS using queue
     */
    public int minDepth2(TreeNode root) {
        int min = 0;
        Queue<TreeNode> q = new LinkedList<>();
        if(root == null)
            return min;
        q.offer(root);
        while(!q.isEmpty()){
            min ++;
            int size = q.size();
            for(int i = 0; i < size; i++){
                TreeNode node = q.poll();
                if(node.left == null && node.right == null){
                    return min;
                }
                if(node.left != null){
                    q.offer(node.left);
                }
                if(node.right != null){
                    q.offer(node.right);
                }
            }
        }
        return min;
    }
}
