import java.io.IOException;

/**
 * Created by hoelk on 02.04.15.
 */
public class Main {
    public static void main(String[] args) throws IOException{
        Matrix null10x10 = new Matrix(10, 10);
        Matrix unit5x5 = new Matrix(5, 5, 1);

        System.out.println(null10x10.toString());
        System.out.println(unit5x5.toString());
        System.out.println(unit5x5.toString(0));

        unit5x5.switchRows(1,2);
        System.out.println(unit5x5.toString(0));

        unit5x5.switchCols(2, 3);
        System.out.println(unit5x5.toString(0));

        double[] replace5 = {1, 2, 3, 4, 5};
        double[] replace3 = {9, 8, 7};

        System.out.println(unit5x5.toString(0));

        Matrix unit3x5 = new Matrix(3,5,1);

        System.out.println(unit3x5.toString(0));

        unit3x5.setRow(2, replace5);
        unit3x5.setCol(4, replace3);

        System.out.println(unit3x5.toString(0));

        Matrix parse1 = Matrix.parseMatrixFile("/home/hoelk/Dropbox/workspace/java/eprog/Ex22_MatrixRank/src/test_1");
        Matrix parse2 = Matrix.parseMatrixFile("/home/hoelk/Dropbox/workspace/java/eprog/Ex22_MatrixRank/src/test_2");

        System.out.println(parse1.toString());
        System.out.println(parse2.toString());


    }
}
