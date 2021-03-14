package SportsCodes;

import java.util.*;

public class BinaryTreeWithFactors {
    public static void main(String[] args) {
        BinaryTreeWithFactors i = new BinaryTreeWithFactors();
//        System.out.println(i.numFactoredBinaryTrees(new int[]{2}));
//        System.out.println(i.numFactoredBinaryTrees(new int[]{2,4}));
//        System.out.println(i.numFactoredBinaryTrees(new int[]{2,4,5,10}));
        System.out.println(i.numFactoredBinaryTrees(new int[]{2,4,5,10,40}));
        System.out.println(i.numFactoredBinaryTrees(new int[]{46,144,5040,4488,544,380,4410,34,11,5,3063808,5550,34496,12,540,28,18,13,2,1056,32710656,31,91872,23,26,240,18720,33,49,4,38,37,1457,3,799,557568,32,1400,47,10,20774,1296,9,21,92928,8704,29,2162,22,1883700,49588,1078,36,44,352,546,19,523370496,476,24,6000,42,30,8,16262400,61600,41,24150,1968,7056,7,35,16,87,20,2730,11616,10912,690,150,25,6,14,1689120,43,3128,27,197472,45,15,585,21645,39,40,2205,17,48,136}));
    }

    public int numFactoredBinaryTrees(int[] arr) {
        if (arr.length < 2) return arr.length;
        Arrays.sort(arr);
        Map<Integer, Long> combinations = new HashMap();
        long sum = 0;
        long factorToAdd;
        combinations.put(arr[0], 1L);
        sum++;
        for (int i = 1; i < arr.length; i++){
            combinations.put(arr[i], 1L);
            sum++;
            // a[i] will be root
            for (int j = 0; j < i && arr[j] <= arr[i]/2; j++) {
                // a[j] will be the left child
                if (arr[i]%arr[j] != 0 || !combinations.containsKey(arr[i]/arr[j])) continue;
//                if (arr[i]/arr[j] == arr[j]) {
//                    factorToAdd = combinations.get(arr[j]);
//                    factorToAdd= (factorToAdd * factorToAdd)%1000000007;
//                } else {
//                }
                    factorToAdd = (combinations.get(arr[j]) * combinations.get(arr[i]/arr[j]));
                combinations.put(arr[i], combinations.get(arr[i])+factorToAdd);
//                System.out.println(factorToAdd);
//                System.out.println(factorToAdd);
                sum= sum+factorToAdd;
                // find if there is a right child possible by looking into set
                // if it is same, then add value of the found entry otherwise multiply product of values of two entries
            }
        }
//        System.out.println(combinations.size());
//        System.out.println(arr.length);
        return (int)(sum%1_000_000_007);
    }
}
