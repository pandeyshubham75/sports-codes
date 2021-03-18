package SportsCodes;

import java.util.Arrays;

public class KthLargest {

    public static void main(String[] args) {

        KthLargest instance = new KthLargest();
        int[] heap = new int[]{10,20,15,12,40,25,18};

        System.out.println(instance.findKthLargest(heap, 3));

//        instance.heapify(heap);
//        Arrays.stream(heap)
//                .forEach(e->System.out.print(e + " "));
//        for (int i = heap.length-1; i> 0; i--){
//            instance.removeFromHeap(heap, i);
//        }
//        System.out.println();
//        Arrays.stream(heap)
//                .forEach(e->System.out.print(e + " "));
    }

    public int findKthLargest(int[] nums, int k) {
        heapify(nums);
        for (int i = 0; i< k; i++){
            removeFromHeap(nums, nums.length-1-i);
        }
        return nums[nums.length-k];
    }

    public void heapify(int[] heap){
        for (int i = heap.length - 1; i >= 0; i--){
            heapifyNode(heap, i, heap.length-1);
        }
    }

    public void heapifyNode(int[] heap, int index, int endIndex){
        if (index > endIndex) return;
        int maxIndex = index;
        if (endIndex>=(index*2)+1 && heap[(index*2)+1]>heap[maxIndex]){
            maxIndex = (index*2)+1;
        }
        if (endIndex>=(index*2)+2 && heap[(index*2)+2]>heap[maxIndex]){
            maxIndex = (index*2)+2;
        }
        if (maxIndex != index){
            int tmp = heap[index];
            heap[index] = heap[maxIndex];
            heap[maxIndex] = tmp;
            heapifyNode(heap, maxIndex, endIndex);
        }
    }

    public void removeFromHeap(int[] heap, int endIndex){
        int tmp = heap[0];
        heap[0] = heap[endIndex];
        heap[endIndex] = tmp;
        heapifyNode(heap, 0, endIndex-1);
    }

}
