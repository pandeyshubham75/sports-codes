package SportsCodes;

public class StockProfitWithCooldown {
    public static void main(String[] args) {
        StockProfitWithCooldown instance = new StockProfitWithCooldown();
        System.out.println(instance.maxProfit(new int []{1,2,3,0,2}));
        System.out.println(instance.maxProfitWithTransactionFee(new int []{1,3,2,8,4,9}, 2));
        System.out.println(instance.maxProfitWithTransactionFee(new int []{1,3,7,5,10,3}, 3));
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

    public int maxProfitWithTransactionFee(int[] prices, int fee) {
        if (prices.length < 2) return 0;
        int x = 0; // profit for sold/eligible state
        int y = - prices[0]; // profit for hold state
        int prevX=0;
        for (int i = 1; i < prices.length; i++) {
            prevX = x;
            x = Math.max(y+prices[i]-fee, x);
            y = Math.max(y, prevX - prices[i]);
        }
        return Math.max(x,y);
    }
}
