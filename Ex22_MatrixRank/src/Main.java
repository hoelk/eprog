import java.io.IOException;

/** Main program for the first home exercise of the lecture 122.425.
 *
 * Loads a matrix from a text file that is supplied as a command line
 * argument, displays the matrix, displays the triangulised version
 * of the matrix and also its rank.
 *
 * @author Stefan Fleck
 * @version 0.0.1
 */

public class Main {
    public static void main(String[] args) throws IOException{

        Matrix input = Matrix.parseMatrixFile(args[0]);
        Matrix inputTriangular = new Matrix(input);
        inputTriangular.triangularise();

        System.out.println(input);

        System.out.printf("Dreiecks Form der Matrix: %n%n");
        System.out.println(inputTriangular);

        System.out.printf("Rang der Matrix: ");
        System.out.println(inputTriangular.getRank());

        System.out.printf("%n%n");

        String[] testMatrices = {"mat2x2.dat", "mat2x2.dat", "mat4x4.dat",
                "mat5x5.dat", "mat6x6.dat", "mat7x7.dat"};

        for (String i: testMatrices) {
            String path = "/home/hoelk/Dropbox/workspace/java/eprog/Ex22_MatrixRank/src/" + i;
            Matrix test = Matrix.parseMatrixFile(path);

            Matrix testTriangular = new Matrix(test);
            testTriangular.triangularise();

            System.out.println(test);

            System.out.printf("Dreiecks Form der Matrix: %n%n");
            System.out.println(testTriangular);

            System.out.printf("Rang der Matrix: ");
            System.out.println(test.getRank());
            System.out.println(test);
            System.out.printf("Determinante der Matrix: ");
            System.out.println(test.getDet());

            System.out.printf("%n%n");
        }


    }
}
