package SportsCodes;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    public static void main(String[] args) {
        new PascalsTriangle().getRow(9).stream().forEach(x -> System.out.print(x + "    ")); // expected [1,7,21,35,35,21,7,1]
    }

    public List<Integer> getRow(int rowIndex) {
        List<Integer> list = new ArrayList<>(rowIndex+1);
//        int[] row = new int[rowIndex+1];
//        row[0] = 1;
//        row[rowIndex] = 1;
        for (int i = 0; i <= rowIndex; i++) {
            list.add(1);
//            row[i] = row[rowIndex - i] = 1;
        }
        int tmp=1, j;
        for (int i = 1; i < rowIndex; i++) {
            int oldValOfPrev=1;
            for (j = 1; j <= (i+1)/2; j++) {
                tmp = list.get(j) + oldValOfPrev;
                oldValOfPrev = list.get(j);
                list.set(j, tmp);
                list.set(rowIndex-j, tmp);
//                row[j] = row[rowIndex - j] = row[j] + row[j-1];
            }
            for (j = (i+1)/2; j <= (rowIndex+1)/2; j++) {
                list.set(j, tmp);
                list.set(rowIndex-j, tmp);
//                row[j] = row[rowIndex - j] = row[i];
            }
//            list.stream().forEach(x -> System.out.print(x + "    "));
//            System.out.println();
        }
        return list;
    }
}
