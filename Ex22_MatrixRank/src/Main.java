import java.io.IOException;

/**
 * Created by hoelk on 02.04.15.
 */
public class Main {
    public static void main(String[] args) throws IOException{

        Matrix test1 = Matrix.parseMatrixFile("/home/hoelk/Dropbox/workspace/java/eprog/Ex22_MatrixRank/src/test_1");
        Matrix test2 = Matrix.parseMatrixFile("/home/hoelk/Dropbox/workspace/java/eprog/Ex22_MatrixRank/src/test_2");
        Matrix test3 = Matrix.parseMatrixFile("/home/hoelk/Dropbox/workspace/java/eprog/Ex22_MatrixRank/src/test_3");
        Matrix test4 = Matrix.parseMatrixFile("/home/hoelk/Dropbox/workspace/java/eprog/Ex22_MatrixRank/src/test_4");

        System.out.println("-- Make Triangular");

        Matrix randrix = new Matrix(10, 10, true);
        System.out.println(randrix.toString());
        randrix.makeTriangular();


        test3.makeTriangular();

    }
}
