package SportsCodes;

public class BurstBalloons {
    public static void main(String[] args) {
        System.out.println(maxCoins(new int[]{3,1,5,8}));
    }

    public static int maxCoins(int[] nums) {
        int[][][] memo = new int[nums.length][nums.length][2];
        for (int i = 1; i < nums.length; i++) {
            // calculating for all sub arrays of size i
            for (int j = 0; j <= nums.length - i; j++) {
                // we are inside one sub array now starting from j and ending on j+i-1
                int max=0, maxK;
                for (int k = j; k < j+i; k++) {
                    // we are considering kth element to be taken out last
                    int sum = 0;
                    if (sum > max) {
                        max = sum;
                        maxK = k;
                    }
                }
            }
        }
        return memo[0][nums.length-1][0];
    }

    static int getVal(int i, int j, int[][][] memo, int[] nums) {
        if (i == j) {
            if (i == 0) return nums[i] * nums[i+1];
            if (i == nums.length - 1) return nums[nums.length - 1] * nums[nums.length - 2];
            return nums[i] * nums[i+1] * nums[i-1];
        }
        return memo[i][j][0];
    }
}
