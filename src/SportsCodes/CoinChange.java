package SportsCodes;

import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) {
        CoinChange i = new CoinChange();
        System.out.println(i.coinChange(new int[]{1,2,5}, 11));
        System.out.println(i.coinChange(new int[]{2}, 3));
        System.out.println(i.coinChange(new int[]{1}, 0));
        System.out.println(i.coinChange(new int[]{1}, 1));
        System.out.println(i.coinChange(new int[]{1}, 2));
        System.out.println(i.coinChange(new int[]{1,2147483647}, 2));
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) return 0;
        if (coins.length == 0) return -1;
        Arrays.sort(coins);
        int i = 0;
        int j;
        while (i < coins.length && coins[i++] < amount);
        int maxCoinICareAbout = i;
        int[] table = new int[amount+1];
        table[0] = 0;
        for (j = 1; j < amount+1; j++) {
            table[j] = j % coins[0] == 0 ? j / coins[0] : -1;
        }
        for (i = 1; i < maxCoinICareAbout; i++){
            table[0] = 0;
            for (j = 1; j< amount+1; j++) {
                if(j < coins[i]){
                    table[j] = table[j];
                    continue;
                }
                if (table[j] == -1 && table[j-coins[i]] == -1)
                    table[j] = -1;
                else if (table[j] == -1)
                    table[j] = table[j-coins[i]] + 1;
                else if (table[j-coins[i]] == -1)
                    table[j] = table[j];
                else
                    table[j] = Math.min(table[j], table[j-coins[i]] + 1);
            }
        }
        return table[j-1];
    }
}
