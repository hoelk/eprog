import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Main program for the first home exercise of the lecture 122.425.
 *
 * Loads a matrix from a text file that is supplied as a command line
 * argument, displays the matrix, displays the triangulised version
 * of the matrix and also its rank.
 *
 * @author Stefan Fleck
 * @version 0.0.1
 */

public class Ex2_main {
    public static void main(String[] args) {

        try {
            Matrix input = Matrix.parseMatrixFile(args[0]);

            Matrix inputTriangular = new Matrix(input);
            inputTriangular.triangularise();

            System.out.println(input);

            System.out.printf("Dreiecks Form der Matrix: %n%n");
            System.out.println(inputTriangular);
            System.out.printf("Rang der Matrix: %d%n", inputTriangular.getRank());
            if (input.getCols() == input.getRows()) {
                System.out.printf("Determinante der Matrix: %.3f%n", input.getDet());
            }

        } catch (NullPointerException e) {
            System.err.println("Caught NullPointerException: " + e.getMessage());
        } catch (FileNotFoundException e) {
            System.err.println("\nCaught FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("\nCaught IOException: " + e.getMessage());
        }


    }
}
