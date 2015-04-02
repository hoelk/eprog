/**
 * Created by hoelk on 02.04.15.
 */
public class Main {
    public static void main(String[] args) {
        Matrix null10x10 = new Matrix(10, 10);
        Matrix unit10x10 = new Matrix(10, 10, 1);

        System.out.println(null10x10.toString());
        System.out.println(unit10x10.toString());
        System.out.println(unit10x10.toString(0));

    }

}
