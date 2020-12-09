import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class TheSkylineProblem {
    public static void main(String[] args) {
        System.out.println(getSkyline(new int[][]{{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}}));
    }

    public static List<List<Integer>> getSkyline(int[][] buildings) {

        Queue<int[]> queueByY = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        Queue<int[]> queueByX = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);

        int buildingMarker = 0;

        List sol = new ArrayList<List<Integer>>();

        while (buildingMarker < buildings.length || !queueByX.isEmpty()) {
            // find which is next in line
            if (buildingMarker < buildings.length && !queueByX.isEmpty()) { // means we have both candidates
                if (buildings[buildingMarker][0] > queueByX.peek()[0] ||
                        (buildings[buildingMarker][0] == queueByX.peek()[0] && buildings[buildingMarker][1] != queueByY.peek()[1])) {
                    sol.add(new int[]{buildings[buildingMarker][0],0});
                } else if (buildings[buildingMarker][0] < queueByX.peek()[0]) {
                    // we need to consider building from earlier queue
                }
                int[] exitTuple = new int[]{buildings[buildingMarker][1], buildings[buildingMarker][2]};
                queueByX.add(exitTuple);
                queueByX.add(exitTuple);
            } else if (buildingMarker < buildings.length) { // no earlier building pending, only new building
                sol.add(new int[]{buildings[buildingMarker][0], buildings[buildingMarker][2]});
                int[] exitTuple = new int[]{buildings[buildingMarker][1], buildings[buildingMarker++][2]};
                queueByX.add(exitTuple); queueByY.add(exitTuple);
            } else { // no new building, just old buildings
                int[] exitTuple = queueByX.poll();
                queueByY.remove(exitTuple);
                
            }
        }

        queueByX.add(new int[] {1,5});
        queueByX.add(new int[] {0,7});
        queueByY.add(new int[] {1,5});
        queueByY.add(new int[] {0,7});
        System.out.println(queueByX.peek()[0]+", "+queueByX.poll()[1]); System.out.println(queueByX.peek()[0]+", "+queueByX.poll()[1]);
        System.out.println(queueByY.peek()[0]+", "+queueByY.poll()[1]); System.out.println(queueByY.peek()[0]+", "+queueByY.poll()[1]);

        List i = new ArrayList<Integer>();
        i.add(2);
        i.add(2);
        List j = new ArrayList<List<Integer>>();
        j.add(i); j.add(i);
        return j;
    }
}
