package SportsCodes;

public class ClimbingStairs {
    public static void main(String[] args) {
        ClimbingStairs i = new ClimbingStairs();
        System.out.println(i.climbStairs(1));
        System.out.println(i.climbStairs(2));
        System.out.println(i.climbStairs(3));
        System.out.println(i.climbStairs(4));
        System.out.println(i.climbStairs(5));
        System.out.println(i.climbStairs(6));
    }

    public int climbStairs(int n) {
        if (n < 1) return 0;
        int noOfWays = 0;
        int ones = n; //2
        int twos = 0;
        int positions = n; //2
        while (ones >= 0) {
            if (ones == n) noOfWays++;
            else {
                int total = 1;
                int x = positions;
                int y = twos < ones ? twos : ones;
                while (y > 0) {
                    total = (total * x--) / (y--);
                }
                noOfWays+=total;
            }
            ones-=2;
            positions--;
            twos++;
        }
        return noOfWays;
    }
}
