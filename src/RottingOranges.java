public class RottingOranges {
    public static void main(String[] args) {
        RottingOranges i = new RottingOranges();
        System.out.println(i.orangesRotting(new int[][]{{2,1,1},{1,1,0},{0,1,1}}));
        System.out.println(i.orangesRotting(new int[][]{{2,1,1},{0,1,1},{1,0,1}}));
        System.out.println(i.orangesRotting(new int[][]{{0,1}}));
        System.out.println(i.orangesRotting(new int[][]{{0,2}}));
        System.out.println(i.orangesRotting(new int[][]{{1},{2}}));
    }
    public int orangesRotting(int[][] grid) {
        if (grid.length == 0) return 0;
        int[][] minTimes = new int[grid.length][grid[0].length];
        boolean minOneFreshOrange = false;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 2) minTimes[i][j] = 0; // determined already 0
                else if (grid[i][j] == 1) { minOneFreshOrange = true; minTimes[i][j] = -2; } // not determined
                else minTimes[i][j] = -3; // not a part
            }
        }
        if (!minOneFreshOrange) return 0;
        int maxTime = -1;
        int tmpMaxTime;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 0) continue;
                tmpMaxTime = findMinimumTimes(grid, i, j, minTimes);
                if (tmpMaxTime == -1) return -1;
                if (tmpMaxTime > maxTime) maxTime = tmpMaxTime;
            }
        }

        return maxTime;
    }

    public int findMinimumTimes(int[][] grid, int row, int col, int[][] minTimes) {
        if (minTimes[row][col] > -2) return minTimes[row][col];
        int oldMinTime = minTimes[row][col];
        minTimes[row][col] = -4; // mark busy
        int minTimeAmongNeighbors = -1;
        int tempMinTime;
        boolean anyNeighborBusy = false;
        if (row > 0) {
            // try previous row
            if (grid[row-1][col] > 0) {
                if (minTimes[row-1][col] == -4) anyNeighborBusy = true;
                else {
                    tempMinTime = findMinimumTimes(grid, row-1, col, minTimes);
                    if (tempMinTime == 0) {
                        minTimes[row][col] = anyNeighborBusy ? oldMinTime : 1;
                        return 1;
                    }
                    if (tempMinTime > -1 && (minTimeAmongNeighbors == -1 || tempMinTime < minTimeAmongNeighbors)) minTimeAmongNeighbors = tempMinTime;
                }
            }
        }
        if (col > 0) {
            // try previous col
            if (grid[row][col - 1] > 0) {
                if (minTimes[row][col-1] == -4) anyNeighborBusy = true;
                else {
                    tempMinTime = findMinimumTimes(grid, row, col - 1, minTimes);
                    if (tempMinTime == 0) {
                        minTimes[row][col] = anyNeighborBusy ? oldMinTime : 1;
                        return 1;
                    }
                    if (tempMinTime > -1 && (minTimeAmongNeighbors == -1 || tempMinTime < minTimeAmongNeighbors)) minTimeAmongNeighbors = tempMinTime;
                }
            }
        }
        if (row < grid.length - 1) {
            // try next row
            if (grid[row+1][col] > 0) {
                if (minTimes[row+1][col] == -4) anyNeighborBusy = true;
                else {
                    tempMinTime = findMinimumTimes(grid, row+1, col, minTimes);
                    if (tempMinTime == 0) {
                        minTimes[row][col] = anyNeighborBusy ? oldMinTime : 1;
                        return 1;
                    }
                    if (tempMinTime > -1 && (minTimeAmongNeighbors == -1 || tempMinTime < minTimeAmongNeighbors)) minTimeAmongNeighbors = tempMinTime;
                }
            }
        }
        if (col < grid[0].length - 1) {
            // try next column
            if (grid[row][col+1] > 0) {
                if (minTimes[row][col+1] == -4) anyNeighborBusy = true;
                else {
                    tempMinTime = findMinimumTimes(grid, row, col+1, minTimes);
                    if (tempMinTime == 0) {
                        minTimes[row][col] = anyNeighborBusy ? oldMinTime : 1;
                        return 1;
                    }
                    if (tempMinTime > -1 && (minTimeAmongNeighbors == -1 || tempMinTime < minTimeAmongNeighbors)) minTimeAmongNeighbors = tempMinTime;
                }
            }
        }
        if (minTimeAmongNeighbors > -1) minTimeAmongNeighbors++;
        minTimes[row][col] = anyNeighborBusy ? oldMinTime : minTimeAmongNeighbors;
        return minTimeAmongNeighbors;
    }
}
