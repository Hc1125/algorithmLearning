package cn.zju.zuochengyun.Heap;

public class code2_HeapSort {
    public static void heapSort(int[] arr){
        if(arr == null || arr.length < 2){
            return;
        }
        // O(NlogN)
        /*for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);
        }*/
        // O(N)
        for(int i = arr.length - 1; i >= 0; i--){
            heapify(arr, i, arr.length);
        }
        int heapSize = arr.length;
        swap(arr, 0, --heapSize);
        while(heapSize > 0){
            heapify(arr, 0, heapSize);
            swap(arr, 0, --heapSize);
        }

    }

    // arr[index]刚来的数，往上
    public static void heapInsert(int[] arr, int index){
        while (arr[index] > arr[(index - 1) >> 1]){
            swap(arr, index, (index - 1) >> 1);
            index = (index - 1) >> 1;
        }
    }
    public static void heapify(int[] arr, int index, int heapSize){
        int left = index << 1 + 1;
        while(left < heapSize){
            int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
            largest = arr[largest] > arr[index] ? largest : index;
            if(largest == index){
                break;
            }
            swap(arr, largest, index);
            index = largest;
            left = index << 1 | 1;
        }
    }
    public static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
