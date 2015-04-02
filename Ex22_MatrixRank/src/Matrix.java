/**
 * Created by hoelk on 19.03.15.
 */
public class Matrix {
    public static int numberOfMatrices;
    private final int id;
    private double[][] A;  // [row][col]


    // Custom constructor
    Matrix(int rows, int cols) {

        id = numberOfMatrices++;

        A = new double[rows][cols];
        for (int m = 0; m < rows; m++) {
            for (int n = 0; n < cols; n++) {
                A[m][n] = 0;
            }
        }
    }

    Matrix(int rows, int cols, int diagonalElement) {

        id = numberOfMatrices++;

        A = new double[rows][cols];
        for (int m = 0; m < rows; m++) {
            for (int n = 0; n < cols; n++) {

                if (m == n) {
                    A[m][n] = diagonalElement;
                } else {
                    A[m][n] = 0;
                }
            }
        }
    }


    private int getRows() {
        return (A.length);

    }

    private int getCols() {
        return (A[0].length);
    }

    private int getId() {
        return (id);
    }

    public void switchRows(int row1, int row2){
        double[] temp1 = A[row1];
        double[] temp2 = A[row2];

        A[row1] = temp2;
        A[row2] = temp1;
    }

    public void switchCols(int col1, int col2) {

        for(double[] row: A){
            double temp1 = row[col1];
            double temp2 = row[col2];

            row[col1] = temp2;
            row[col2] = temp1;

        }



    }

    public Matrix toTriangularMatrix(Matrix A) {
        return (A);

    }

    public String toString() {

        StringBuilder matrix = new StringBuilder("");

        matrix.append(String.format("Matrix %d is a %d x %d Matrix%n%n", getId(), getRows(), getCols()));

        for (int row = 0; row < A.length; row++) {
            for (int col = 0; col < A[row].length; col++) {
                matrix.append(String.format("%5.3f  ", A[row][col]));
            }
            matrix.append(String.format("%n")); // Supplies platform specific linebreak, wheres \n always produces \u000A
        }

        return (matrix.toString());
    }

    public String toString(int decimals) {

        StringBuilder matrix = new StringBuilder("");
        String format = "%5." + decimals + "f  ";

        matrix.append(String.format("Matrix %d is a %d x %d Matrix%n%n", getId(), getRows(), getCols()));

        for (int row = 0; row < A.length; row++) {
            for (int col = 0; col < A[row].length; col++) {
                matrix.append(String.format(format, A[row][col]));
            }
            matrix.append(String.format("%n")); // Supplies platform specific linebreak, wheres \n always produces \u000A
        }

        return (matrix.toString());
    }


}
