package cn.zju.zuochengyun.BinaryTree;

import java.util.ArrayList;
import java.util.List;
/*
    派对的最大快乐值
    这个公司现在要办派对，你可以决定哪些员工来，哪些员工不来，规则：
    1.如果某个员工来了，那么这个员工的所有直接下级都不能来
    2.派对的整体快乐值是所有到场的员工的快乐值的累加
    3.你的目标是让派对的整体快乐值尽量大
    给定一颗多叉树的头节点boss，请返回派对的最大快乐值。
 */
public class code12_MaxHappy {
    public static class Employee{
        public int happy;
        public List<Employee> nexts;
        public Employee(int h){
            happy = h;
            nexts = new ArrayList<>();
        }
    }
    public static class Info{
        public int yes;
        public int no;
        public Info(int y, int n) {
            yes = y;
            no = n;
        }
    }
    public static Info process(Employee boss){
        if(boss.nexts.isEmpty()){
            return new Info(boss.happy, 0);
        }
        int yes = boss.happy;
        int no = 0;
        for (Employee next : boss.nexts) {
            Info nexInfo = process(next);
            yes += nexInfo.no;
            no += Math.max(nexInfo.yes, nexInfo.no);
        }
        return new Info(yes, no);
    }
}
