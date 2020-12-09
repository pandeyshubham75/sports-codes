public class MirrorReflection {
    public static void main(String[] args) {
        System.out.println(mirrorReflection(2,1));
    }

    public static int mirrorReflection(int p, int q) {
        int pp=p, qq=q;
        while (qq != pp) {
            qq+=q;
            if (qq>pp) pp+=p;
        }
        if ((pp/p)%2 == 0) return 0;
        if ((qq/q)%2 == 0) return 2;
        return 1;
    }
}
