package SportsCodes;

public class IntToRoman {
    public static void main(String[] args) {
        IntToRoman i = new IntToRoman();
        System.out.println(i.intToRoman(3));
        System.out.println(i.intToRoman(4));
        System.out.println(i.intToRoman(9));
        System.out.println(i.intToRoman(58));
        System.out.println(i.intToRoman(1994));
    }

    public String intToRoman(int num) {
        int[] nums = new int[]{
                1000,900,500,400,100,90,50,40,10,9,5,4,1
        };
        int nextNumIndex = 0;
        StringBuilder out = new StringBuilder();
        int count;
        char[] temp;
        while(num > 0){
            count = num/nums[nextNumIndex];
            temp = charForNum(nums[nextNumIndex]);
            while(count-- > 0){
                out.append(temp);
            }
            num = num%nums[nextNumIndex];
            nextNumIndex++;
        }
        return out.toString();
    }

    char[] charForNum(int num) {
        switch (num){
            case 1000 : return new char[]{'M'};
            case 900 : return new char[]{'C','M'};
            case 500 : return new char[]{'D'};
            case 400 : return new char[]{'C','D'};
            case 100 : return new char[]{'C'};
            case 90 : return new char[]{'X','C'};
            case 50 : return new char[]{'L'};
            case 40 : return new char[]{'X','L'};
            case 10 : return new char[]{'X'};
            case 9 : return new char[]{'I','X'};
            case 5 : return new char[]{'V'};
            case 4 : return new char[]{'I','V'};
            case 1 : return new char[]{'I'};
        }
        return null;
    }
}
