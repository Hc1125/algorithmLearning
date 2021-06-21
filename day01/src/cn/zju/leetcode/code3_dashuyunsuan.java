package cn.zju.leetcode;

public class code3_dashuyunsuan {
    public static void main(String[] args) {
        int[] ints1 = new int[100];
        ints1[ints1.length-1]=1;
        int n = 50;
        for (int i = 1; i <= n; i++) {
            ints1 = demo1(ints1,i);
        }
        boolean index1 = true;
        for (int i = 0; i < ints1.length; i++) {
            if(index1 && ints1[i]==0){

            }else{
                System.out.print(ints1[i]);
                index1=false;
            }
        }
        System.out.println();
        int[] ints2 = new int[100];
        ints2[ints2.length-1]=1;
        int m = 50;
        for (int i = 1; i <= m; i++) {
            ints2 = demo1(ints2,i);
        }
        boolean index2 = true;
        for (int i = 0; i < ints2.length; i++) {
            if(index2 && ints2[i]==0){

            }else{
                System.out.print(ints2[i]);
                index2=false;
            }
        }
        System.out.println();
        int[] result = demo2(ints1,ints2);
        boolean index3 = true;
        for (int i = 0; i < result.length; i++) {
            if(index3 && result[i]==0){

            }else{
                System.out.print(result[i]);
                index1=false;
            }
        }
    }
    static int[] demo1(int[] ints,int num){
        for (int i = 0; i < ints.length; i++) {
            ints[i] *= num;
        }
        for (int i = ints.length - 1; i > 0; i--) {
            ints[i-1] += ints[i]/10;
            ints[i] = ints[i] % 10;
        }
        return ints;
    }
    static int[] demo2(int[] ints1,int[] ints2){
        int[] result = new int[ints1.length*ints2.length];
        for (int i = 0; i < result.length; i++) {
            result[i]=0;
        }
        for (int i = 0; i < ints1.length; i++) {
            for (int j = 0; j < ints2.length; j++) {
                result[i+j] += ints1[i]*ints2[j];
            }
        }
        for(int i = result.length - 1; i > 0; i--){
            result[i-1] += result[i]/10;
            result[i] = result[i]%10;
        }
        return result;
    }
}
