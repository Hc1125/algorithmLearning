package cn.zju.zuochengyun.Sort;

public class code1_SelectionSort {
    public static void selectionSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }
    }
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    public static int[] generateRandomArray(int maxSize, int maxValue){
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue+1) * Math.random()) - (int)(maxValue * Math.random());
        }
        return arr;
    }
}
