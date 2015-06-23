import java.io.*;

/**
 * A real-valued Matrix.
 *
 * Supplies various constructors for creating
 * matrices as well as a method to load a matrix
 * from a text file.
 * Also provides methods for manipulating matrices after
 * they are created.
 *
 * @author Stefan Fleck
 * @version 0.1.0
 */

public class Matrix {
    /**
     * Total number of matrices
     */
    public static int numberOfMatrices;
    /**
     * ID code for each matrix
     */
    private final int id;
    /**
     * Number of diagonal elements
     */
    private final int diagDim;
    /**
     * Elements of the matrix
     */
    private double[][] A;  // [row][col]

    /**
     * Custom constructor for creating a null matrix.
     *
     * @param rows number of rows.
     * @param cols number of columns.
     */


    Matrix(int rows, int cols) {

        id = numberOfMatrices++;
        diagDim = Math.min(rows, cols);

        A = new double[rows][cols];
        for (int m = 0; m < rows; m++) {
            for (int n = 0; n < cols; n++) {
                A[m][n] = 0;
            }
        }
    }


    /**
     * Custom constructor for creating a matrix filled with random values.
     *
     * If random == true, this constructor creates a Matrix filled with
     * random double values between -1 and 1.
     *
     * @param rows   The number of rows.
     * @param cols   The number of columns.
     * @param random Whether the matrix should be filled with random values or not.
     */
    Matrix(int rows, int cols, boolean random) {
        this(rows, cols);

        if (random) {
            A = new double[rows][cols];
            for (int m = 0; m < rows; m++) {
                for (int n = 0; n < cols; n++) {
                    A[m][n] = Math.random();
                }
            }
        }
    }


    /**
     * Custom constructor for creating a matrix with a diagonal element.
     *
     * Useful for creating unit-matrices.
     *
     * @param rows   The number of rows.
     * @param cols   The number of columns.
     * @param diagEl The diagonal element of the matrix.
     */
    Matrix(int rows, int cols, int diagEl) {
        this(rows, cols);

        for (int m = 0; m < rows; m++) {
            for (int n = 0; n < cols; n++) {

                if (m == n) {
                    A[m][n] = diagEl;
                } else {
                    A[m][n] = 0;
                }
            }
        }
    }


    /**
     * Custom constructor for creating a matrix from a 2 dimensional array.
     *
     * @param array A 2d array.
     */
    Matrix(double[][] array) {

        id = numberOfMatrices++;
        diagDim = Math.min(array.length, array[0].length);
        A = array;

    }

    /**
     * Copy Constructor.
     *
     * Creates a deep copy of a matrix object.
     *
     * @param B A The matrix to be copied.
     */
    Matrix(Matrix B) {
        id = ++numberOfMatrices;
        diagDim = B.diagDim;

        A = B.A.clone();
        for (int row = 0; row < A.length; row++) {
            A[row] = B.A[row].clone();
        }
    }

    /**
     * Loads a matrix from a text file.
     *
     * The
     * first row of the text file contains
     * the dimensions of the matrix (space separated).
     * The reminder of the text files contains the values
     * of the matrix, also space separated. Example:
     * <p>
     * 2 3 <br>
     * 1 2 3 <br>
     * 9 8 7 <br>
     * </p>
     *
     * @param path The path of the Matrix file.
     * @return A matrix.
     * @throws IOException Entering an invalid path will throw an IOException.
     */
    public static Matrix parseMatrixFile(String path) throws IOException {

        FileInputStream istream = new FileInputStream(path);
        InputStreamReader reader = new InputStreamReader(istream);
        BufferedReader input = new BufferedReader(reader);


        // Erste Zeile lesen (enthält Zeilen- und Spaltenzahl)
        String line = input.readLine();
        String[] elem = line.split(" ");
        int rows = Integer.parseInt(elem[0]);
        int cols = Integer.parseInt(elem[1]);

        // Restliche Zeilen lesen
        Matrix res = new Matrix(rows, cols);

        for (int row = 0; row < rows; row++) {
            line = input.readLine();
            elem = line.trim().split("\\s+");

            for (int col = 0; col < cols; col++) {
                res.setElement(row, col, Double.parseDouble(elem[col]));
            }
        }

        input.close();

        return (res);

    }

    /**
     * Set matrix element to a specified value.
     *
     * @param row   The row of the element to be modified.
     * @param col   The column of the element to be modified.
     * @param value The new value for the element.
     */

    public void setElement(int row, int col, double value) {
        A[row][col] = value;
    }

    /**
     * Get ID-value of a matrix.
     *
     * @return The id of the matrix.
     */
    public int getId() {
        return (id);
    }

    /**
     * Return the number of rows of a matrix.
     *
     * @return The number of rows.
     */
    public int getRows() {
        return (A.length);
    }

    /**
     * Return the number of columns of a matrix.
     *
     * @return The number of columns.
     */
    public int getCols() {
        return (A[0].length);
    }

    /**
     * Get the array index of the absolute maximum value of a row.
     *
     * @param row     The row of which the absolute maximum is required.
     * @param fromcol The first column to consider for evaluating the maximum. The first fromcol - 1 columns are ignored.
     * @return The Array index of the absolute row maximum.
     */

    private int getPosAbsRowMax(int row, int fromcol) {
        double max = Math.abs(A[row][fromcol]);
        int maxpos = fromcol;

        for (int col = fromcol; col < A[0].length; col++) {
            double newmax = Math.max(max, Math.abs(A[row][col]));

            if (newmax > max) {
                maxpos = col;
                max = newmax;
            }
        }
        return (maxpos);
    }

    /**
     * Get the array index of the absolute maximum value of a column.
     *
     * @param col     The column of which the array index of the absolute maximum is required.
     * @param fromrow The first row to consider for evaluating the maximum. The first fromrow - 1 rows are ignored.
     * @return The Array index of the absolute column maximum.
     */
    private int getPosAbsColMax(int col, int fromrow) {
        double max = Math.abs(A[fromrow][col]);
        int maxpos = fromrow;

        for (int row = fromrow; row < A.length; row++) {
            double newmax = Math.max(max, Math.abs(A[row][col]));

            if (newmax > max) {
                maxpos = row;
                max = newmax;
            }
        }
        return (maxpos);
    }

    /**
     * Get the absolute maximum of a column.
     *
     * @param col     The column of which the absolute maximum is required.
     * @param fromrow The first row to consider for evaluating the maximum. The first fromrow - 1 rows are ignored.
     * @return The absolute maximum value of a column.
     */
    private double getAbsColMax(int col, int fromrow) {
        double max = Math.abs(A[fromrow][col]);
        for (int row = fromrow; row < A.length; row++) {
            max = Math.max(max, Math.abs(A[row][col]));
        }
        return (max);
    }

    /**
     * Get the absolute maximum of a row.
     *
     * @param row     The row of which the absolute maximum is required.
     * @param fromcol The first column to consider for evaluating the maximum. The first fromcol - 1 columns are ignored.
     * @return The absolute maximum value of a row.
     */
    private double getAbsRowMax(int row, int fromcol) {
        double max = Math.abs(A[row][fromcol]);

        for (int col = fromcol; col < A[0].length; col++) {
            max = Math.max(max, Math.abs(A[row][col]));
        }
        return (max);
    }

    /**
     * Ensures that the top left corner of a matrix, or submatrix thereof
     * is not zero
     *
     * @param diagEl The diagonal element which is considered the top left corner of the submatrix. If diagEl = 0 the whole
     *               matrix is processed, if diagEl = 1 the submatrix not containing the first row and first column
     *               is processed, and so forth..
     */
    private void makeTopLeftCornerNotZero(int diagEl) {

        if (diagEl == diagDim) {
            switchCols(diagEl, getPosAbsRowMax(diagEl, diagEl));
            return;
        }

        for (int i = diagEl; i < A[0].length; i++) {

            if (getAbsColMax(i, diagEl) > 0) {

                switchRows(diagEl, getPosAbsColMax(i, diagEl));
                switchCols(diagEl, getPosAbsRowMax(diagEl, diagEl));
            }
            if (A[diagEl][diagEl] > 0.0001 || A[diagEl][diagEl] < -0.0001) {
                break;
            }
        }

    }

    /**
     * Switches two rows.
     *
     * @param row1 First row to be switched.
     * @param row2 Second row to be switched.
     */
    public void switchRows(int row1, int row2) {
        double[] temp = A[row1];

        A[row1] = A[row2];
        A[row2] = temp;
    }

    /**
     * Switches two columns.
     *
     * @param col1 First column to be switched.
     * @param col2 Second column to be switched.
     */
    public void switchCols(int col1, int col2) {

        for (double[] row : A) {
            double temp = row[col1];

            row[col1] = row[col2];
            row[col2] = temp;
        }
    }

    /**
     * Subtracts one row from another.
     *
     * @param row1 Row that is subtracted from.
     * @param row2 Row to be subtracted.
     */
    public void rowSubtract(int row1, int row2) {

        for (int i = 0; i < A[0].length; i++) {
            A[row1][i] = A[row1][i] - A[row2][i];
        }
    }

    /**
     * Multiply each element of a row with a scalar.
     *
     * @param row    Row to be multiplied.
     * @param scalar Scaler to multiply the row by.
     */
    public void rowTimesScalar(int row, double scalar) {
        for (int i = 0; i < A[0].length; i++) {
            A[row][i] = A[row][i] * scalar;
        }
    }

    /**
     * Reduce a matrix to triangular form.
     */
    public void triangularise() {

        for (int diagEl = 0; diagEl < diagDim; diagEl++) {
            makeTopLeftCornerNotZero(diagEl);

            for (int row = diagEl + 1; row < A.length; row++) {

                if (A[row][diagEl] > 0.0001 || A[row][diagEl] < -0.0001) {
                    double a = A[diagEl][diagEl];
                    double b = A[row][diagEl];
                    rowTimesScalar(row, a);
                    rowTimesScalar(diagEl, b);

                    double c = A[row][diagEl]; // Damit die Zahlen nicht zu gross werden

                    rowTimesScalar(row, 1 / c);
                    rowTimesScalar(diagEl, 1 / c);

                    rowSubtract(row, diagEl);


                }
            }
        }
    }

    /**
     * Get the rank of a matrix.
     *
     * @return Rank of the matrix.
     */
    public int getRank() {
        Matrix tempMatrix = new Matrix(this);
        tempMatrix.triangularise();

        int Rank = 0;

        for (int i = 0; i < tempMatrix.diagDim; i++) {
            if (tempMatrix.A[i][i] > 0.0001 || tempMatrix.A[i][i] < -0.0001) {
                Rank++;
            }
        }

        return Rank;
    }


    /**
     * Get the determinant of a (square) matrix.
     *
     * @return determinant of a square matrix
     */

    public double getDet() {
        double res = 0;

        try {
            if (getRows() != getCols()) {
                throw new MatrixFormatException("Matrix not square");
            } else if (getRows() == 1) {
                res = A[0][0];
            } else if (getRows() == 2) {
                res = A[0][0] * A[1][1] - A[1][0] * A[0][1];
            } else {
                // Entwickeln nach erster Zeile, aufpassen mit index!
                for (int i = 0; i < getRows(); i++) {
                    double ik = Math.pow(-1.0, (i));
                    res += A[i][0] * ik * subMatrix(i, 0).getDet();
                }
            }
        } catch (MatrixFormatException e) {
            System.err.println("Caught MatrixFormatException: " + e.getMessage());
        } catch (MatrixIndexException e) {
            System.err.println("Caught MatrixIndexException: " + e.getMessage());
        }

        return res;
    }


    /**
     * Create a submatrix be removing one row and one column.
     *
     * @param i Row to remove.
     * @param j column to remove.
     * @return Submatrix of input matrix after removing row i and column j.
     * @throws MatrixIndexException Trying to access a non-existing matrix element will throw a MatrixIndexException.
     */
    public Matrix subMatrix(int i, int j) throws MatrixIndexException {

        if (i > getRows() || j > getCols()) {
            throw new MatrixIndexException("Matrix Index out of Range");
        }

        double[][] sub = new double[getRows() - 1][getCols() - 1];

        for (int rowIdx = 0; rowIdx < getRows() - 1; rowIdx++) {
            int rowSkip = 0;
            if (rowIdx >= i) rowSkip = 1;

            for (int colIdx = 0; colIdx < getCols() - 1; colIdx++) {
                int colSkip = 0;
                if (colIdx >= j) colSkip = 1;

                sub[rowIdx][colIdx] = A[rowIdx + rowSkip][colIdx + colSkip];

            }
        }

        return new Matrix(sub);

    }


    /**
     * Return a string describing the matrix.
     *
     * @return String representation of the matrix.
     */

    public String toString() {

        StringBuilder matrix = new StringBuilder("");

        matrix.append(String.format("%d x %d Matrix %n%n", getRows(), getCols()));

        for (int row = 0; row < A.length; row++) {
            for (int col = 0; col < A[row].length; col++) {
                matrix.append(String.format("%7.3f  ", A[row][col]));
            }
            matrix.append(String.format("%n")); // Produces platform specific linebreak, wheres \n always produces \u000A
        }

        return (matrix.toString());
    }

}

