package cn.zju.threadTest;

import java.util.ArrayList;
import java.util.List;

public class threadTest5 {
    volatile List lists = new ArrayList();
    //volatile List lists = Collections.synchronizedList(new LinkedList<>());
    public void add(Object o) { lists.add(o); }
    public int size() { return lists.size(); }
    public static void main(String[] args){
        threadTest5 c = new threadTest5();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add "+i);
                /*try {
                    TimeUnit.SECONDS.sleep(1);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }*/
            }
        },"t1").start();
        new Thread(()->{
            while(true){
                if(c.size() == 5){
                    break;
                }
            }
            System.out.println("t2 结束");
        },"t2").start();
    }
}
