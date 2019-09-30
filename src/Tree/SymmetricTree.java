package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class SymmetricTree {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
  /*
     Iterative
   */
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        if(root != null && root.left != null && root.right != null){
            q.offer(root.left);
            q.offer(root.right);
            while(!q.isEmpty()){
                TreeNode lef = q.poll();
                TreeNode rig = q. poll();
                if(lef==null && rig==null)
                {
                    continue;
                }
                if(lef==null && rig!=null || rig==null && lef!=null)
                {
                    return false;
                }
                if(lef.val!=rig.val)
                {
                    return false;
                }
                q.offer(lef.left);
                q.offer(rig.right);
                q.offer(lef.right);
                q.offer(rig.left);
            }
            return true;
        }

        if(root!=null)
        {
            if(root.left==null && root.right!=null || root.left!=null && root.right==null)
            {
                return false;
            }
        }

        return true;
    }

    /*
         Recursive
     */
    public boolean isSymmetric2(TreeNode root) {
        if(root == null)
            return true;
        return isSymmetric(root.right, root.left);
    }

    public boolean isSymmetric(TreeNode tree1, TreeNode tree2){
        if(tree1 == null && tree2 == null){
            return true;
        } else if(tree1 != null && tree2 != null){
            return tree1.val == tree2.val && isSymmetric(tree1.left, tree2.right) && isSymmetric(tree1.right, tree2.left);
        } else{
            return false;
        }
    }
}
