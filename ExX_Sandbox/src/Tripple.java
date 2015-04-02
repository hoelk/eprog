/**
 * Created by hastur on 18.01.15.
 */
public class Tripple {
    private int a;
    private int b;
    private int c;

    Tripple(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;

    }

    public static void main(String[] args) {
        Tripple t1 = new Tripple(1, 9, 8);
        Tripple t2 = new Tripple(9, 1, 8);
        Tripple t3 = new Tripple(8, 1, 9);
        Tripple t4 = new Tripple(8, 1, 99);

        System.out.println(t1.equals(t1) + " " + t1.equals(t2) + " " + t1.equals(t3) + " " + t1.equals(t4));

    }

    public boolean equals(Tripple trip) {
        if (a == trip.a && b == trip.b && c == trip.c) return true;
        if (a == trip.a && b == trip.c && c == trip.b) return true;
        if (a == trip.b && b == trip.a && c == trip.c) return true;
        if (a == trip.b && b == trip.c && c == trip.a) return true;
        if (a == trip.c && b == trip.b && c == trip.a) return true;
        if (a == trip.c && b == trip.a && c == trip.b) return true;

        return false;
    }
}
