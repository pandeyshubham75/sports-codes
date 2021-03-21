package SportsCodes;

import java.util.Arrays;

public class ReorderedPowerOf2 {

    public static void main(String[] args) {
        ReorderedPowerOf2 instance = new ReorderedPowerOf2();
        System.out.println(instance.reorderedPowerOf2(1));
        System.out.println(instance.reorderedPowerOf2(10));
        System.out.println(instance.reorderedPowerOf2(16));
        System.out.println(instance.reorderedPowerOf2(24));
        System.out.println(instance.reorderedPowerOf2(46));
    }

    public boolean reorderedPowerOf2(int N) {
        int start = 1;
        int[] numMap = getNumFrequencyMap(N);
        for (int i = 1; i< 31; i++){
            if (Arrays.equals(getNumFrequencyMap(start), numMap)) return true;
            start*=2;
        }
        return false;
    }

    public int[] getNumFrequencyMap(int num) {
        int[] arr = new int[10];
        while(num>0){
            arr[num%10]++;
            num/=10;
        }
        return arr;
    }
}
