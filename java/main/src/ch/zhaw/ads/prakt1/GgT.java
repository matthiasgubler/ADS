package ch.zhaw.ads.prakt1;

public class GgT {
    public static int ggt(int a, int b) {
        if (a > b) {
            return ggt(a - b, b);
        } else if (a < b) {
            return ggt(a, b - a);
        } else return a;
    }
}
