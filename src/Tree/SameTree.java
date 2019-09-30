package Tree;

import java.util.LinkedList;
import java.util.Queue;

public class SameTree {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }
    /*
        Iterative
     */

    public boolean check(TreeNode q, TreeNode p){
        if(p == null && q == null){
            return true;
        } else if(p != null && q != null){
            return p.val == q.val;
        } else{
            return false;
        }
    }
    public boolean isSameTree(TreeNode p, TreeNode q) {
        Queue<TreeNode> queueQ = new LinkedList<>();
        Queue<TreeNode> queueP = new LinkedList<>();
        queueQ.offer(q);
        queueP.offer(p);
        while(!queueQ.isEmpty()){
            p = queueP.poll();
            q = queueQ.poll();
            if(!check(p, q)){
                return false;
            }
            if(q != null){
                queueQ.offer(q.left);
                queueQ.offer(q.right);
            }
            if(p != null){
                queueP.offer(p.left);
                queueP.offer(p.right);
            }
        }
        return true;
    }

    public boolean isSameTree2(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        } else if(p != null && q != null){
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        } else{
            return false;
        }
    }
}
