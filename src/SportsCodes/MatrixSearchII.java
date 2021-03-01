package SportsCodes;

public class MatrixSearchII {
    public static void main(String[] args) {
        MatrixSearchII m = new MatrixSearchII();
        System.out.println(m.searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50}}, 3));
        System.out.println(m.searchMatrix(new int[][]{{1,3,5,7},{10,11,16,20},{23,30,34,50}}, 13));
    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int i = 0, j = matrix[0].length - 1;
        while (i < matrix.length && j >= 0){
            if (matrix[i][j] == target) return true;
            if (matrix[i][j] > target) j--;
            else i++;
        }
        return false;
    }
}
