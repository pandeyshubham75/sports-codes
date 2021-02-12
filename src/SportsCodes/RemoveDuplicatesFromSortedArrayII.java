package SportsCodes;

public class RemoveDuplicatesFromSortedArrayII {
    public static void main(String[] args) {
        removeDuplicates(new int[]{1,1,1,2,2,3});
        removeDuplicates(new int[]{0,0,1,1,1,1,2,3,3});
        removeDuplicates(new int[]{});
        removeDuplicates(new int[]{1});
    }
    public static int removeDuplicates(int[] nums) {
        int shift = 0, currCount = 1, i = 1;
        while (i < nums.length - shift) {
            nums[i] = nums[i+shift];
            if (nums[i] == nums[i-1]) currCount++;
            else currCount = 1;
            if (currCount == 3) {
                shift++;
                currCount--;
            } else i++;
        }
//        for (int ii = 0; ii < nums.length - shift; ii++) System.out.print(nums[ii]+ " ");
//        System.out.println();
        return nums.length - shift;
    }
}
