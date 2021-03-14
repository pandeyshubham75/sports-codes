package SportsCodes;

import java.util.Iterator;
import java.util.TreeSet;

public class MaxAverageRatio {
    public static void main(String[] args) {
        MaxAverageRatio i = new MaxAverageRatio();
        System.out.println(i.maxAverageRatio(new int[][]{{2,4}, {3,9}, {4,5}, {2,10}}, 4));
    }

    public double maxAverageRatio(int[][] classes, int extraStudents) {
        TreeSet<MaxAvgNode> set = new TreeSet<>();
        for (int i = 0; i< classes.length; i++){
            set.add(new MaxAvgNode(classes[i][0], classes[i][1]));
        }
        MaxAvgNode node;
        for (int i=0; i<extraStudents; i++){
            node = set.pollFirst();
            node.x++; node.y++;
            set.add(node);
        }
        double sol = 0;
        Iterator<MaxAvgNode> it = set.iterator();
        while (it.hasNext()){
            node = it.next();
            sol += ((double)node.x/node.y);
        }
        return sol/classes.length;
    }
}

class MaxAvgNode implements Comparable<MaxAvgNode>{
    int x;
    int y;
    MaxAvgNode(int x, int y){
        this.x=x;
        this.y=y;
    }
    public int compareTo(MaxAvgNode node){
        return ((((double)node.x+1)/(node.y+1)) - ((double)node.x/node.y))
                > ((((double)this.x+1)/(this.y+1)) - ((double)this.x/this.y))  ? 1 : -1;
    }
}
