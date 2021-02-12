package SportsCodes;

public class StockProfitWithCooldown {
    public static void main(String[] args) {
        StockProfitWithCooldown instance = new StockProfitWithCooldown();
        System.out.println(instance.maxProfit(new int []{1,2,3,0,2}));
    }

    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int x = 0; // profit for eligible state
        int y = - prices[0]; // profit for kept state
        int z = 0; // profit for cooldown state
        for (int i = 1; i < prices.length; i++) {
            int currX = x;
            x = Math.max(x, z);
            z = y + prices[i];
            y = Math.max(y, currX - prices[i]);
        }
        return Math.max(x,z);
    }
}
