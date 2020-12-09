public class LongestMountainInArray {
    public static void main(String[] args) {
        System.out.println(longestMountain(new int[]{0,0,1,0,0,1,1,1,1,1}));
    }
    public static int longestMountain(int[] A) {
        int result = 0;
        int currLength = 1;
        boolean inc = true;
        for (int i = 1; i < A.length; i++) {
            if (inc ? A[i-1] < A[i] : A[i] < A[i-1]) {
                currLength++;
            } else if (A[i-1] == A[i]) {
                currLength = 1;
                inc = true;
            } else if (currLength > 1 && inc == true) {
                //direction change, now climbing down
                currLength++;
                inc = false;
            } else {
                currLength = inc ? 1 : 2;
                inc = true;
            }
            System.out.println(currLength);
            if (currLength > 2 && !inc && currLength > result) result = currLength;
        }
        return result;
    }
}
