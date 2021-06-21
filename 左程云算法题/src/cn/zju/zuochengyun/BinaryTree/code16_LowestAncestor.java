package cn.zju.zuochengyun.BinaryTree;



import java.util.HashMap;
import java.util.HashSet;

public class code16_LowestAncestor {
    public static TreeNode lowestAncestor1(TreeNode head, TreeNode o1, TreeNode o2) {
        if (head == null) {
            return null;
        }
        HashMap<TreeNode, TreeNode> parentMap = new HashMap<>();
        parentMap.put(head, null);
        fillParentMap(head, parentMap);
        HashSet<TreeNode> o1Set = new HashSet<>();
        TreeNode cur = o1;
        o1Set.add(cur);
        while (parentMap.get(cur) != null) {
            cur = parentMap.get(cur);
            o1Set.add(cur);
        }
        cur = o2;
        while (!o1Set.contains(cur)) {
            cur = parentMap.get(cur);
        }
        return cur;
    }

    public static void fillParentMap(TreeNode head, HashMap<TreeNode, TreeNode> parentMap) {
        if (head.left != null) {
            parentMap.put(head.left, head);
            fillParentMap(head.left, parentMap);
        }
        if (head.right != null) {
            parentMap.put(head.right, head);
            fillParentMap(head.right, parentMap);
        }
    }

    public static TreeNode lowestAncestor2(TreeNode head, TreeNode o1, TreeNode o2) {
        return process(head, o1, o2).ans;
    }
    public static class Info {
        public TreeNode ans;
        public boolean findO1;
        public boolean findO2;
         public Info(TreeNode a, boolean f1, boolean f2){
             ans = a;
             findO1 = f1;
             findO2 = f2;
         }
    }
    public static Info process(TreeNode head, TreeNode o1, TreeNode o2){
        if(head == null){
            return new Info(null, false, false);
        }
        Info leftInfo = process(head.left, o1, o2);
        Info rightInfo = process(head.right, o1, o2);
        boolean findO1 = head == o1 || leftInfo.findO1 || rightInfo.findO1;
        boolean findO2 = head == o2 || leftInfo.findO2 || rightInfo.findO2;
        TreeNode ans = null;
        if(leftInfo.ans != null){
            ans = leftInfo.ans;
        } else if(rightInfo.ans != null){
            ans = rightInfo.ans;
        } else {
            if (findO1 && findO2){
                ans = head;
            }
        }
        return new Info(ans, findO1, findO2);
    }
}
