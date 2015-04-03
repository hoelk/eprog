/**
 * Created by Stefan Fleck on 19.03.15.
 */

import java.io.*;


public class Matrix {
    public static int numberOfMatrices;
    private final int id;
    private final int diagDim;
    private double[][] A;  // [row][col]

    // Custom constructor
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

    Matrix(double[][] a) {

        id = numberOfMatrices++;
        diagDim = Math.min(a.length, a[0].length);
        A = a;

    }

    // Custom constructor for easy diagonal matrices
    Matrix(int rows, int cols, int diagEl) {

        id = numberOfMatrices++;
        diagDim = Math.min(rows, cols);

        A = new double[rows][cols];
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

    // Custom constructor for Matrices with random values
    Matrix(int rows, int cols, boolean random) {

        id = numberOfMatrices++;
        diagDim = Math.min(rows, cols);

        A = new double[rows][cols];
        for (int m = 0; m < rows; m++) {
            for (int n = 0; n < cols; n++) {
                A[m][n] = Math.random() * 10;
            }
        }
    }

    // Copy constructor
    Matrix(Matrix B) {
        id = ++numberOfMatrices;
        diagDim = B.diagDim;

        A = B.A.clone();
        for (int row = 0; row < A.length; row++) {
            A[row] = B.A[row].clone();
        }
    }


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
            elem = line.split(" ");

            for (int col = 0; col < cols; col++) {
                res.setElement(row, col, Double.parseDouble(elem[col]));
            }
        }

        input.close();

        return (res);

    }

    public void setElement(int row, int col, double value) {
        A[row][col] = value;
    }

    public void setRow(int row, double[] new_row) {

        if (row > A.length) {
            System.out.println("Zeilenindex zu groß");
        } else if (new_row.length != A[0].length) {
            System.out.println("Länge der neuen Zeile nicht gleich Länge der alten Zeile");
        } else {
            A[row] = new_row;
        }
    }

    public void setCol(int col, double[] new_col) {

        if (col > A[0].length) {
            System.out.println("Spaltenindex zu groß");
        } else if (new_col.length != A.length) {
            System.out.println("Länge der neuen Spalte nicht gleich Länge der alten Spalte");
        } else {
            for (int row = 0; row < A.length; row++) {
                A[row][col] = new_col[row];
            }
        }
    }

    public int getId() {
        return (id);
    }

    public int getRows() {
        return (A.length);
    }

    public int getCols() {
        return (A[0].length);
    }

    public double[][] getArray() {
        return (A);
    }

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

    private double getAbsColMax(int col, int fromrow) {
        double max = Math.abs(A[fromrow][col]);
        for (int row = fromrow; row < A.length; row++) {
            max = Math.max(max, Math.abs(A[row][col]));
        }
        return (max);
    }

    private double getAbsRowMax(int row, int fromcol) {
        double max = Math.abs(A[row][fromcol]);

        for (int col = fromcol; col < A[0].length; col++) {
            max = Math.max(max, Math.abs(A[row][col]));
        }
        return (max);
    }

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
            if (A[diagEl][diagEl] != 0) {
                break;
            }
        }

    }

    public void switchRows(int row1, int row2) {
        double[] temp = A[row1];

        A[row1] = A[row2];
        A[row2] = temp;
    }

    public void switchCols(int col1, int col2) {

        for (double[] row : A) {
            double temp = row[col1];

            row[col1] = row[col2];
            row[col2] = temp;
        }
    }

    public void rowSubtract(int row1, int row2) {

        for (int i = 0; i < A[0].length; i++) {
            A[row1][i] = A[row1][i] - A[row2][i];
        }
    }

    public void rowTimesScalar(int row, double scalar) {
        for (int i = 0; i < A[0].length; i++) {
            A[row][i] = A[row][i] * scalar;
        }
    }

    public void triangularise() {

        for (int diagEl = 0; diagEl < diagDim; diagEl++) {
            makeTopLeftCornerNotZero(diagEl);

            for (int row = diagEl + 1; row < A.length; row++) {

                if (A[row][diagEl] != 0) {
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

    public int getRank() {
        Matrix tempMatrix = new Matrix(this);
        tempMatrix.triangularise();

        int Rank = 0;

        for (int i = 0; i < tempMatrix.diagDim; i++) {
            if (tempMatrix.A[i][i] != 0) {
                Rank++;
            }
        }

        return (Rank);
    }

    public String toString() {

        StringBuilder matrix = new StringBuilder("");

        matrix.append(String.format("%d x %d Matrix%n%n", getRows(), getCols()));

        for (int row = 0; row < A.length; row++) {
            for (int col = 0; col < A[row].length; col++) {
                matrix.append(String.format("%7.3f  ", A[row][col]));
            }
            matrix.append(String.format("%n")); // Produces platform specific linebreak, wheres \n always produces \u000A
        }

        return (matrix.toString());
    }

}

