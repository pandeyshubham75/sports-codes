import java.util.Arrays;
import java.util.Scanner;

public class YouCompleteMe {
    public static void main(String[] args) {

        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        int[][] tcs = new int[n][];
        int[] ks = new int[n];
        for (int i = 0; i < n; i++) {
            int size = s.nextInt();
            ks[i] = s.nextInt();
            tcs[i] = new int[size];
            for (int j = 0; j < size; j++) {
                tcs[i][j] = s.nextInt();
            }
        }

        for (int i = 0; i < n; i++) {
            printGenome(tcs[i], ks[i]);
        }
    }

    public static void printGenome(int[] humans, int k) {
        for (int i = 0; i < humans.length - 1; i++) {
            int cur = humans[i];
            for (int j = i+1; j < humans.length; j++) {
                if (humans[j] == k-cur) {
                    System.out.println(humans[i]+" "+humans[j]);
                    return;
                }
            }
        }
        System.out.println("NA");
    }

}
