package cn.zju.zuochengyun.Heap;

public class code1_Heap01 {
    public static class MyMaxHeap{
        private int[] heap;
        private final int limit;
        private int heapSize;

        public MyMaxHeap(int limit) {
            heap = new int[limit];
            this.limit = limit;
            heapSize = 0;
        }
        public boolean isEmpty(){
            return heapSize == 0;
        }
        public boolean isFull(){
            return heapSize == limit;
        }
        public void push(int value){
            if(heapSize == limit){
                throw new RuntimeException("heap is full");
            }
            heap[heapSize] = value;
            heapInsert(heap, heapSize++);
        }
        // 用户此时，让你返回最大值，并且在大根堆中，把最大值删掉
        // 剩下的数，依然保持大根堆组织
        public int pop(){
            int ans = heap[0];
            swap(heap, 0, --heapSize);
            heapify(heap, 0, heapSize);
            return ans;
        }
        // 新加进来的数，现在停在了index的位置，请依次往上移动
        // 移动到0位置，或者干不掉自己的父亲，停！
        public void heapInsert(int[] arr, int index){
            while(arr[index] > arr[(index - 1) >> 1]){
                swap(arr, index, (index - 1) >> 1);
                index = (index - 1) >> 1;
            }
        }
        // 从index位置，往下看，不断地下沉
        // 停；我的孩子都不再比我大；已经没孩子了
        private void heapify(int[] arr, int index, int heapSize){
            int left = index << 1 | 1;
            while(left < heapSize){
                /**
                 * 两个孩子中，谁的值大，把下标给largest
                 * 1）只有做孩子，left -> largest
                 * 2）同时又左孩子和右孩子，右孩子的值 <= 左孩子的值，left -> largest
                 */
                int largest = left + 1 < heapSize && arr[left + 1] > arr[left] ? left + 1 : left;
                // 父和较大的孩子之间，谁的值大，把下标给largest
                largest = arr[largest] > arr[index] ? largest : index;
                if(largest == index){
                    break;
                }
                swap(arr, largest, index);
                index = largest;
                left = index << 1 | 1;
            }
        }
        private void swap(int[] arr, int i, int j){
            int tmp = arr[i];
            arr[i] = arr[j];
            arr[j] = tmp;
        }
    }

}
