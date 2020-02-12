package BinarySearchTree;

import java.util.ArrayList;
import java.util.List;

/*
    Two elements of a binary search tree (BST) are swapped by mistake.

    Recover the tree without changing its structure.

    Example 1:

    Input: [1,3,null,null,2]

       1
      /
     3
      \
       2

    Output: [3,1,null,null,2]

       3
      /
     1
      \
       2
    Example 2:

    Input: [3,1,4,null,null,2]

      3
     / \
    1   4
       /
      2

    Output: [2,1,4,null,null,3]

      2
     / \
    1   4
       /
      3
 */
public class RecoverBinarySearchTree {
    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    /*
        Make use of inorder traversal and record last node.  O(1) space
        if current node value <= last visited node value, then we update the value for n1 and n2
        After the traversal, we will swap the value of n1 and n2
     */
    class Solution {
        TreeNode last;
        TreeNode n1, n2;
        public void recoverTree(TreeNode root) {
            inorder(root);
            int tmp = n1.val;
            n1.val = n2.val;
            n2.val = tmp;
        }

        public void inorder(TreeNode root){
            if(root == null) return;
            inorder(root.left);
            if(last != null){
                if(root.val <= last.val){
                    if(n1 == null){
                        n1 = last;
                        n2 = root;
                    } else{
                        n2 = root;
                    }
                }
            }
            last = root;
            inorder(root.right);
        }
    }

    /*
        Solution 2 : also using inorder traversal. O(n) space
     */
    class Solution2 {
        public void recoverTree(TreeNode root) {
            List<TreeNode> list = new ArrayList<>();
            inorder(list, root);
            for(int i = 0; i < list.size(); i++){
                if(list.get(i).val > list.get(i + 1).val){
                    int j = i+1;
                    while(j < list.size() && list.get(i).val > list.get(j).val){
                        j++;
                    }
                    j--;
                    int tmp = list.get(i).val;
                    list.get(i).val = list.get(j).val;
                    list.get(j).val = tmp;
                    break;
                }
            }

        }

        public void inorder(List<TreeNode> list, TreeNode root){
            if(root.left != null){
                inorder(list, root.left);
            }
            list.add(root);
            if(root.right != null){
                inorder(list, root.right);
            }
        }
    }
}
