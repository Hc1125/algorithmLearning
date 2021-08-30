package cn.zju.zuochengyun.Practice5;



import java.io.*;
import java.util.*;

public class code6_merchantsBankContest {
    // 客户经理模型
    public static class AccountManager {
        int no;       //  编号
        int power;    //  基础能力
        int curPower; //  当前能力
        int assess;   //  评判指标（1）对于第一问则为订单数目，第二问则为复杂度指标
        int strength; //  体力
        int curOrder; //  当前订单数目服务于question4
        double curStrength;  // 新体力
        StringBuilder orderDetail;  // 订单详细信息


        public AccountManager (int n, int p, int a) {
            no = n;
            power = p;
            assess = a;
            orderDetail = new StringBuilder();
        }

        public AccountManager (int n, int p, int a, int s) {
            no = n;
            power = p;
            assess = a;
            strength = s;
            orderDetail = new StringBuilder();
        }

        public AccountManager (int n, int p, int a, int s, double newS) {
            no = n;
            power = p;
            assess = a;
            strength = s;
            curStrength = newS;
            orderDetail = new StringBuilder();
        }

        public AccountManager (int n, int p, int a, int s, double newS, int curP, int curO) {
            no = n;
            power = p;
            assess = a;
            strength = s;
            curStrength = newS;
            curPower = curP;
            curOrder = curO;
            orderDetail = new StringBuilder();
        }
    }
    // 全局共享数据模型
    public static class Info {
        int P;                                  // 客户经理能力总值
        int M;                                  // 当前订单数目，随着订单分配逐步增加
        int B;                                  // 当前订单复杂度总和，随着订单分配逐步增加
        public Info() {
            P = 0;
            M = 0;
            B = 0;
        }
    }

    // 第一问
    public static void question1() {
        long begin = System.currentTimeMillis();
        List<String> qList = getContent("q2.txt"); // 获取客户经理数据
        int m = qList.size();                        // 获取客户经理数目
        Info info = new Info();
        AccountManager[] accountManagers = new AccountManager[m];
        for (int i = 0; i < m; i++) {
            String[] split = qList.get(i).split(",");
            int p = Integer.parseInt(split[1]);
            info.P += p;
            accountManagers[i] = new AccountManager(i + 1, p, 0);
        }
        PriorityQueue<AccountManager> pq = new PriorityQueue<>(new Comparator<AccountManager>() {
            // 根据指标  q/Q - m/M 转换成分配当前订单
            @Override
            public int compare(AccountManager o1, AccountManager o2) {
                double p1 = (double) o1.power / info.P;
                double m1 = info.M == 0 ? o1.assess : (double) o1.assess / info.M;
                double c1 = p1 - m1;
                double p2 = (double) o2.power / info.P;
                double m2 = info.M == 0 ? o2.assess : (double) o2.assess / info.M;
                double c2 = p2 - m2;
                return c1 == c2 ? o1.no - o2.no : (c1 < c2 ? 1 : -1);
            }
        });
        for (AccountManager accountManager : accountManagers) {
            pq.add(accountManager);
        }
        try {
            String encoding = "GBK";
            String filePath = "C:\\Users\\45929\\Desktop\\新建文件夹\\编程算法题-数据文件\\b2.txt" ;
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int seq = 1;
                // long b = System.currentTimeMillis();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    AccountManager am = pq.poll();
                    am.assess++;
                    if (am.orderDetail.length() == 0) {
                        am.orderDetail.append(seq);
                    } else {
                        am.orderDetail.append(",").append(seq);
                    }
                    info.M++;
                    pq.offer(am);
                    seq++;
//                    if (seq % 100000 == 0) {
//                        long e = System.currentTimeMillis();
//                        System.out.println(seq / 100000 + "----------" + (e - b) / 1000);
//                        b = e;
//                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        for (AccountManager accountManager : accountManagers) {
            System.out.println(accountManager.no + "---" + accountManager.power + "----" + accountManager.assess);
        }
        printResult(accountManagers);
        long end = System.currentTimeMillis();
        System.out.println((end - begin) / 1000);
    }


    // 第二问
    public static void question2() {
        List<String> qList = getContent("q2.txt"); // 获取客户经理能力数据
        List<String> aList = getContent("a2.txt"); // 获取客户经理体力数据
        int m = qList.size();                        // 获取客户经理数目
        AccountManager[] accountManagers = new AccountManager[m];
        Info info = new Info();
        for (int i = 0; i < m; i++) {
            String[] split1 = qList.get(i).split(",");
            String[] split2 = aList.get(i).split(",");
            int p = Integer.parseInt(split1[1]);
            info.P += p;
            accountManagers[i] = new AccountManager(i + 1, p, 0, Integer.parseInt(split2[1]));
        }
        PriorityQueue<AccountManager> pq = new PriorityQueue<>(new Comparator<AccountManager>() {
            // 根据指标  q/Q - p/P 转换成分配当前订单
            @Override
            public int compare(AccountManager o1, AccountManager o2) {
                double q1 = (double) o1.power / info.P;
                double m1 = info.B == 0 ? o1.assess : (double) o1.assess / info.B;
                double c1 = q1 - m1;
                double q2 = (double) o2.power / info.P;
                double m2 = info.B == 0 ? o2.assess : (double) o2.assess / info.B;
                double c2 = q2 - m2;
                return c1 == c2 ? o1.no - o2.no : (c1 < c2 ? 1 : -1);
            }
        });
        for (AccountManager accountManager : accountManagers) {
            pq.add(accountManager);
        }
        try {
            String encoding = "GBK";
            String filePath = "C:\\Users\\45929\\Desktop\\新建文件夹\\编程算法题-数据文件\\b2.txt" ;
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int seq = 1;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    String[] split = lineTxt.split(",");
                    int complex = Integer.parseInt(split[1]);
                    List<AccountManager> temp = new ArrayList<>();
                    while (!pq.isEmpty() && pq.peek().strength < complex) {
                        temp.add(pq.poll());
                    }
                    if (pq.isEmpty()) {
                        System.out.println("dateError!!!");
                        return;
                    }
                    AccountManager am = pq.poll();
                    am.assess += complex;
                    if (am.orderDetail.length() == 0) {
                        am.orderDetail.append(seq);
                    } else {
                        am.orderDetail.append(",").append(seq);
                    }
                    info.B += complex;
                    pq.offer(am);
                    for (AccountManager accountManager : temp) {
                        pq.offer(accountManager);
                    }
                    seq++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        int W = 0;
        for (AccountManager accountManager : accountManagers) {
            W += (accountManager.power - accountManager.assess) * (accountManager.power - accountManager.assess);
        }
        System.out.println("W:" + W);
        printResult(accountManagers);
    }


    // 问题3
    public static void question3() {
        List<String> qList = getContent("q.txt"); // 获取客户经理能力数据
        List<String> aList = getContent("a.txt"); // 获取客户经理体力数据
        int m = qList.size();                        // 获取客户经理数目
        AccountManager[] accountManagers = new AccountManager[m];
        Info info = new Info();
        for (int i = 0; i < m; i++) {
            String[] split1 = qList.get(i).split(",");
            String[] split2 = aList.get(i).split(",");
            int p = Integer.parseInt(split1[1]);
            info.P += p;
            accountManagers[i] = new AccountManager(i + 1, p, 0, Integer.parseInt(split2[1]), Double.parseDouble(split2[1]));
        }
        PriorityQueue<AccountManager> pq = new PriorityQueue<>(new Comparator<AccountManager>() {
            // 根据指标  q/Q - p/P 转换成分配当前订单
            @Override
            public int compare(AccountManager o1, AccountManager o2) {
                double q1 = (double) o1.power / info.P;
                double m1 = info.B == 0 ? o1.assess : (double) o1.assess / info.B;
                double c1 = q1 - m1;
                double q2 = (double) o2.power / info.P;
                double m2 = info.B == 0 ? o2.assess : (double) o2.assess / info.B;
                double c2 = q2 - m2;
                return c1 == c2 ? o1.no - o2.no : (c1 < c2 ? 1 : -1);
            }
        });
        for (AccountManager accountManager : accountManagers) {
            pq.add(accountManager);
        }
        try {
            String encoding = "GBK";
            String filePath = "C:\\Users\\45929\\Desktop\\新建文件夹\\编程算法题-数据文件\\b.txt" ;
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int seq = 1;
                Map<Integer, Integer> seqMap = new HashMap<>();
                Map<Integer, Integer> countMap = new HashMap<>();
                Queue<Integer> queue = new LinkedList<>();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    String[] split = lineTxt.split(",");
                    int complex = Integer.parseInt(split[1]);
                    List<AccountManager> temp = new ArrayList<>();
                    while (!pq.isEmpty() && pq.peek().curStrength < complex) {
                        temp.add(pq.poll());
                    }
                    if (pq.isEmpty()) {
                        System.out.println("dateError!!!");
                        return;
                    }
                    AccountManager am = pq.poll();
                    am.assess += complex;
                    if (am.orderDetail.length() == 0) {
                        am.orderDetail.append(seq);
                    } else {
                        am.orderDetail.append(",").append(seq);
                    }
                    info.B += complex;
                    int count = getRecentCount(queue, seq, am.no, seqMap, countMap, accountManagers);
                    am.curStrength = am.strength * (1 - (double) count / 50);
                    pq.offer(am);
                    for (AccountManager accountManager : temp) {
                        pq.offer(accountManager);
                    }
                    seq++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        int W = 0;
        for (AccountManager accountManager : accountManagers) {
            W += (accountManager.power - accountManager.assess) * (accountManager.power - accountManager.assess);
        }
        System.out.println("W:" + W);
        printResult(accountManagers);
    }

    // 获取最新n值，并更新其余curStrength值
    public static int getRecentCount(Queue<Integer> queue, int seq, int no, Map<Integer, Integer> seqMap, Map<Integer, Integer> countMap, AccountManager[] ams) {
        if (queue.size() == 50) {
            int poll = queue.poll();
            int pollNo = seqMap.get(poll);
            seqMap.remove(poll);
            countMap.put(pollNo, countMap.get(pollNo) - 1);
            // 更新pollNo对应am当前体力
            int tempCount = countMap.get(pollNo);
            ams[pollNo - 1].curStrength = ams[pollNo - 1].strength * (1 - (double) tempCount / 50);
        }
        queue.add(seq);
        seqMap.put(seq, no);
        countMap.put(no, countMap.getOrDefault(no, 0) + 1);
        return countMap.get(no);
    }





    // 问题4
    public static void question4() {
        long begin = System.currentTimeMillis();
        List<String> qList = getContent("q2.txt"); // 获取客户经理能力数据
        List<String> aList = getContent("a2.txt"); // 获取客户经理体力数据
        int m = qList.size();                        // 获取客户经理数目
        AccountManager[] accountManagers = new AccountManager[m];
        Info info = new Info();
        for (int i = 0; i < m; i++) {
            String[] split1 = qList.get(i).split(",");
            String[] split2 = aList.get(i).split(",");
            int p = Integer.parseInt(split1[1]);
            info.P += p;
            accountManagers[i] = new AccountManager(i + 1, p, 0, Integer.parseInt(split2[1]), Double.parseDouble(split2[1]), p, 0);
        }
        PriorityQueue<AccountManager> pq = new PriorityQueue<>(new Comparator<AccountManager>() {
            // 根据指标  q/Q - p/P 转换成分配当前订单
            @Override
            public int compare(AccountManager o1, AccountManager o2) {
                double q1 = (double) o1.curPower / info.P;
                double m1 = info.B == 0 ? o1.assess : (double) o1.assess / info.B;
                double c1 = q1 - m1;
                double q2 = (double) o2.curPower / info.P;
                double m2 = info.B == 0 ? o2.assess : (double) o2.assess / info.B;
                double c2 = q2 - m2;
                return c1 == c2 ? o1.no - o2.no : (c1 < c2 ? 1 : -1);
            }
        });
        for (AccountManager accountManager : accountManagers) {
            pq.add(accountManager);
        }
        int pOld = info.P;
        try {
            String encoding = "GBK";
            String filePath = "C:\\Users\\45929\\Desktop\\新建文件夹\\编程算法题-数据文件\\b2.txt" ;
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                int seq = 1;
                Map<Integer, Integer> seqMap = new HashMap<>();
                Map<Integer, Integer> countMap = new HashMap<>();
                Queue<Integer> queue = new LinkedList<>();
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    String[] split = lineTxt.split(",");
                    int complex = Integer.parseInt(split[1]);
                    List<AccountManager> temp = new ArrayList<>();
                    while (!pq.isEmpty() && pq.peek().curStrength < complex) {
                        temp.add(pq.poll());
                    }
                    if (pq.isEmpty()) {
                        System.out.println("dateError!!!");
                        return;
                    }
                    AccountManager am = pq.poll();
                    am.assess += complex;
                    if (am.orderDetail.length() == 0) {
                        am.orderDetail.append(seq);
                    } else {
                        am.orderDetail.append(",").append(seq);
                    }
                    info.B += complex;
                    int count = getRecentCount(queue, seq, am.no, seqMap, countMap, accountManagers);
                    am.curStrength = am.strength * (1 - (double) count / 50);
                    am.curOrder++;
                    int pre = am.curPower;
                    updatePower(am);
                    info.P += am.curPower - pre;              //更新总P
                    pq.offer(am);
                    for (AccountManager accountManager : temp) {
                        pq.offer(accountManager);
                    }
                    seq++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        int W = 0;
        int qp = 0;
        double inc = (double) pOld / info.P;
        for (AccountManager accountManager : accountManagers) {
            qp += inc * accountManager.curPower - accountManager.assess;
        }
        qp /= 100;
        for (AccountManager accountManager : accountManagers) {
            W += (inc * accountManager.curPower - accountManager.assess - qp) * (inc * accountManager.curPower - accountManager.assess - qp);
        }
        System.out.println("W:" + W);
        for (AccountManager accountManager : accountManagers) {
            System.out.println(accountManager.no + "---" + accountManager.power + "----" + accountManager.assess);
        }
        printResult(accountManagers);
        long end = System.currentTimeMillis();
        System.out.println((end - begin) / 1000);
    }
    static int base = 1000;   // 利用sigmoid函数, 默认认为订单接近5000能力就不增长了,变化范围控制在0 - 1
    public static void updatePower(AccountManager am) {
        double exp = Math.exp(-(double) am.curOrder / base);
        double tmp = 2 * (1 / (1 + exp) - 0.5);
        am.curPower = (int) (am.power * (1 + tmp));
    }



    public static void main(String[] args) {
        question4();
    }

    // 读取txt
    public static List<String> getContent(String s) {
        try {
            String encoding = "GBK";
            String filePath = "C:\\Users\\45929\\Desktop\\新建文件夹\\编程算法题-数据文件\\" + s;
            List<String> list = new ArrayList<>();
            File file = new File(filePath);
            if (file.isFile() && file.exists()) {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
                    list.add(lineTxt);
                }
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    // 打印txt
    public static void printResult(AccountManager[] accountManagers) {
        try {
            File f = new File("D:\\result\\result" + (int)(Math.random() * 10000) + ".txt");
            FileOutputStream fOut= new FileOutputStream(f);
            OutputStreamWriter output = new OutputStreamWriter(fOut);
            for (AccountManager accountManager : accountManagers) {
                output.write(accountManager.orderDetail + "\r\n");
            }
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


