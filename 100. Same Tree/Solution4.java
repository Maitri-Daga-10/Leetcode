class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        // Case 1: both nodes are null
        if (p==null && q==null){
            return true;
        }
        // Case 2: one node is null and other is not
        if(p==null || q==null){
            return false;
        }
        // Case 3: both nodes have different values
        if(p.val != q.val){
            return false;
        }
        // checking left and right subtree
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }
}