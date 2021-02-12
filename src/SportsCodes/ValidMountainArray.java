package SportsCodes;

public class ValidMountainArray {
    public static void main(String[] args) {
        System.out.println(validMountainArray(new int[]{0,3,2,1}));
        System.out.println(validMountainArray(new int[]{2,1}));
        System.out.println(validMountainArray(new int[]{3,5,5}));
    }

    public static boolean validMountainArray(int[] arr) {
        if (arr.length < 3) return false;
        if (arr[1] <= arr[0]) return false;
        boolean inc = true;
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] > arr[i-1] && inc) continue;
            if (arr[i] < arr[i-1]) {
                if (inc) {
                    // reached peak
                    inc = false;
                }
                // if already decreasing
                continue;
            }
            return false;
        }
        return !inc;
    }
}
