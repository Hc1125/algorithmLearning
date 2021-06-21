package cn.zju.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class code13_690 {
    public class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    }
    public int getImportance(List<Employee> employees, int id) {
        Employee leader = null;
        HashMap<Integer, Employee> map = new HashMap<>();
        for (Employee employee : employees) {
            map.put(employee.id, employee);
            if (employee.id == id) {
                leader = employee;
            }
        }
        int ans = 0;
        Queue<Employee> queue = new LinkedList<>();
        queue.add(leader);
        while (!queue.isEmpty()) {
            Employee e = queue.poll();
            ans += e.importance;
            for (Integer subordinate : e.subordinates) {
                queue.add(map.get(subordinate));
            }
        }
        return ans;
    }
}
