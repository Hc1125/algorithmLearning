package cn.zju.leetcode;

public class code4_charupaixu {
    public static void main(String[] args) {
        int[] array = {1,141,45345,2,2,4623,434,464,323464567,78,567,3455,54};
        array=sort(array);
        for (int i : array) {
            System.out.print(i+" ");
        }
    }
    static int[] sort(int[] array){
        for (int i = 1; i < array.length; i++) {
            if(array[i]<array[i-1]){
                for(int j = i;j > 0;j--){
                    if(array[j] < array[j-1]){
                        int temp = array[j-1];
                        array[j-1]=array[j];
                        array[j] = temp;
                    }else{
                        break;
                    }
                }
            }
        }
        return array;
    }
}
