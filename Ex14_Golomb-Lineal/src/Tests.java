/**
 * Created by Stefan Fleck on 29.01.15.
 * 0440930
 */
public class Tests {
    public static void main(String[] args) {

        Lineal beispiel = new Lineal(0, 3, 4, 9, 11);
        Lineal normal = new Lineal(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Lineal golomb = new Lineal(0, 1, 8, 11, 13, 17);
        Lineal perfekt = new Lineal(0, 1, 3);

        System.out.println(beispiel.toString());
        System.out.println(normal.toString());
        System.out.println(golomb.toString());
        System.out.println(perfekt.toString());

    }
}
