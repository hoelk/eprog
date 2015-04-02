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

        Matrix test1 = Matrix.parseMatrixFile("/home/hoelk/Dropbox/workspace/java/eprog/Ex22_MatrixRank/src/test_1");
        Matrix test2 = Matrix.parseMatrixFile("/home/hoelk/Dropbox/workspace/java/eprog/Ex22_MatrixRank/src/test_2");
        Matrix test3 = Matrix.parseMatrixFile("/home/hoelk/Dropbox/workspace/java/eprog/Ex22_MatrixRank/src/test_3");
        Matrix test4 = Matrix.parseMatrixFile("/home/hoelk/Dropbox/workspace/java/eprog/Ex22_MatrixRank/src/test_4");

        System.out.println("-- test1");
        System.out.println(test1.toString());
        System.out.println("Max Row: " + test1.getPosRowWithMax());
        System.out.println("Max Col: " + test1.getPosColWithMax());
        System.out.println("");
        System.out.println("Row Max: " + test1.getRowMax(0) + " at " + test1.getPosRowMax(0));
        System.out.println("Row Max: " + test1.getRowMax(1) + " at " + test1.getPosRowMax(1));
        System.out.println("Row Max: " + test1.getRowMax(2) + " at " + test1.getPosRowMax(2));
        System.out.println("Row Max: " + test1.getRowMax(3) + " at " + test1.getPosRowMax(3));
        System.out.println("");
        System.out.println("Col Max: " + test1.getColMax(0) + " at " + test1.getPosColMax(0));
        System.out.println("Col Max: " + test1.getColMax(1) + " at " + test1.getPosColMax(1));
        System.out.println("Col Max: " + test1.getColMax(2) + " at " + test1.getPosColMax(2));
        System.out.println("Col Max: " + test1.getColMax(3) + " at " + test1.getPosColMax(3));
        System.out.println("Col Max: " + test1.getColMax(4) + " at " + test1.getPosColMax(4));

        System.out.println("-- test2");
        System.out.println(test2.toString());
        System.out.println("Max Row: " + test2.getPosRowWithMax());
        System.out.println("Max Col: " + test2.getPosColWithMax());
        System.out.println("");
        System.out.println("Row Max: " + test2.getRowMax(0) + " at " + test2.getPosRowMax(0));
        System.out.println("Row Max: " + test2.getRowMax(2) + " at " + test2.getPosRowMax(2));
        System.out.println("Row Max: " + test2.getRowMax(2) + " at " + test2.getPosRowMax(2));
        System.out.println("");
        System.out.println("Col Max: " + test2.getColMax(0) + " at " + test2.getPosColMax(0));
        System.out.println("Col Max: " + test2.getColMax(2) + " at " + test2.getPosColMax(2));
        System.out.println("Col Max: " + test2.getColMax(2) + " at " + test2.getPosColMax(2));

        System.out.println("-- test3");
        System.out.println(test3.toString());
        System.out.println("Max Row: " + test3.getPosRowWithMax());
        System.out.println("Max Col: " + test3.getPosColWithMax());
        System.out.println("");
        System.out.println("Row Max: " + test3.getRowMax(0) + " at " + test3.getPosRowMax(0));
        System.out.println("Row Max: " + test3.getRowMax(1) + " at " + test3.getPosRowMax(1));
        System.out.println("Row Max: " + test3.getRowMax(2) + " at " + test3.getPosRowMax(2));
        System.out.println("");
        System.out.println("Col Max: " + test3.getColMax(0) + " at " + test3.getPosColMax(0));
        System.out.println("Col Max: " + test3.getColMax(1) + " at " + test3.getPosColMax(1));
        System.out.println("Col Max: " + test3.getColMax(2) + " at " + test3.getPosColMax(2));
        System.out.println("Col Max: " + test3.getColMax(3) + " at " + test3.getPosColMax(3));


        System.out.println("-- test4");
        System.out.println(test4.toString());
        System.out.println("Max Row: " + test4.getPosRowWithMax());
        System.out.println("Max Col: " + test4.getPosColWithMax());
        System.out.println("");
        System.out.println("Row Max: " + test4.getRowMax(0) + " at " + test4.getPosRowMax(0));
        System.out.println("Row Max: " + test4.getRowMax(1) + " at " + test4.getPosRowMax(1));
        System.out.println("Row Max: " + test4.getRowMax(2) + " at " + test4.getPosRowMax(2));
        System.out.println("");
        System.out.println("Col Max: " + test4.getColMax(0) + " at " + test4.getPosColMax(0));
        System.out.println("Col Max: " + test4.getColMax(1) + " at " + test4.getPosColMax(1));
        System.out.println("Col Max: " + test4.getColMax(2) + " at " + test4.getPosColMax(2));
        System.out.println("Col Max: " + test4.getColMax(3) + " at " + test4.getPosColMax(3));



        System.out.println(test2.toString());

        System.out.println(test1.toString());
        test1.rowSubtract(0, 3);
        System.out.println(test1.toString());

        test1.rowTimesScalar(1, 2.5);
        System.out.println(test1.toString());

        test1.rowTimesScalar(3, 40);
        System.out.println(test1.toString());



    }
}
