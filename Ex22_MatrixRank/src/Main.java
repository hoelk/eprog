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

/*
        Matrix input = Matrix.parseMatrixFile(args[0]);
        Matrix inputTriangular = new Matrix(input);
        inputTriangular.triangularise();

        System.out.println(input);

        System.out.printf("Dreiecks Form der Matrix: %n%n");
        System.out.println(inputTriangular);

        System.out.printf("Rang der Matrix: ");
        System.out.println(inputTriangular.getRank());

        System.out.printf("%n%n");


        System.out.println("Test submatrix (Streiche Reihe 1, zeile 4)");
        System.out.println(input);

        try {
            Matrix inputSub = input.subMatrix(0,3);
            System.out.println(inputSub);
        } catch(MatrixIndexException e){
            System.out.println('b');
        }
*/

/*

        String[] testMatrices = {"mat3x2.dat", "mat2x2.dat", "mat4x4.dat",
                "mat5x5.dat", "mat6x6.dat", "mat7x7.dat", "max7x5.dat"};

*/

        String[] testMatrices = {"mat4x4.dat"};

        // {{1 , 4, 1 , 3}, {0, -1, 3, -1}, {3,1, 0, 2}, {1,-2,5,1}
        // det der 4x4 matrix sollte 88 sein

        for (String i: testMatrices) {
            System.out.println(i);
            String path = "/home/hoelk/Dropbox/workspace/java/eprog/Ex22_MatrixRank/src/" + i;
            Matrix test = Matrix.parseMatrixFile(path);

            Matrix testTriangular = new Matrix(test);
            testTriangular.triangularise();

/*            System.out.println(test);

            System.out.printf("Dreiecks Form der Matrix: %n%n");
            System.out.println(testTriangular);

            System.out.printf("Rang der Matrix: ");
            System.out.println(test.getRank());*/
            System.out.println(test);
            System.out.printf("Determinante der Matrix: ");
            System.out.println(test.getDet());

            System.out.printf("%n%n");
        }









    }
}
