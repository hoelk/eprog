/**
 * Created by Stefan Fleck on 19.03.15.
 */

import java.io.*;


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

    // Copy constructor

    Matrix(Matrix B) {
        A = B.A;
        id = ++numberOfMatrices;
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

    public int getRows() {
        return (A.length);
    }

    public int getCols() {
        return (A[0].length);
    }

    public double getRowMax(int row) {

        double max = A[row][0];
        for (double el : A[row]) {
            max = Math.max(max, el);
        }
        return (max);
    }

    public double getColMax(int col) {

        double max = A[0][col];
        for (double[] row : A) {
            max = Math.max(max, row[col]);
        }
        return (max);
    }

    public int getPosRowMax(int row) {
        double max = A[row][0];
        int maxpos = 0;

        for (int i = 0; i < A[0].length; i++) {
            double newmax = Math.max(max, A[row][i]);

            if (newmax > max) {
                maxpos = i;
                max = newmax;
            }
        }
        return (maxpos);
    }

    public int getPosColMax(int col) {

        double max = A[0][col];
        int maxpos = 0;

        for (int i = 0; i < A.length; i++) {
            double newmax = Math.max(max, A[i][col]);

            if (newmax > max) {
                maxpos = i;
                max = newmax;
            }
        }

        return (maxpos);
    }

    public int getPosColWithMax() {

        double max = getColMax(0);
        int maxcol = 0;

        for (int col = 0; col < A[0].length; col++) {
            double newmax = Math.max(getColMax(col), max);
            if (newmax > max) {
                maxcol = col;
                max = newmax;
            }
        }
        return maxcol;
    }

    public int getPosColWithMax(int start_col) {

        double max = getColMax(start_col);
        int maxcol = 0;

        for (int col = start_col; col < A[0].length; col++) {
            double newmax = Math.max(getColMax(col), max);
            if (newmax > max) {
                maxcol = col;
                max = newmax;
            }
        }
        return maxcol;
    }

    public int getPosRowWithMax() {
        double max = getRowMax(0);
        int maxrow = 0;

        for (int row = 0; row < A.length; row++) {
            double newmax = Math.max(getRowMax(row), max);
            if (newmax > max) {
                maxrow = row;
                max = newmax;
            }
        }
        return maxrow;
    }

    public int getPosRowWithMax(int start_row) {
        double max = getRowMax(start_row);
        int maxrow = 0;

        for (int row = start_row; row < A.length; row++) {
            double newmax = Math.max(getRowMax(row), max);
            if (newmax > max) {
                maxrow = row;
                max = newmax;
            }
        }
        return maxrow;
    }

    public int getId() {
        return (id);
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

    public void makeTriangular() {
        Matrix B = this;

        B.switchRows(0, B.getPosRowWithMax());
        B.switchCols(0, B.getPosColWithMax());

        System.out.println(B);
        System.out.println(this);


    }

    public String toString() {

        StringBuilder matrix = new StringBuilder("");

        matrix.append(String.format("Matrix %d is a %d x %d Matrix%n%n", getId(), getRows(), getCols()));

        for (int row = 0; row < A.length; row++) {
            for (int col = 0; col < A[row].length; col++) {
                matrix.append(String.format("%5.3f  ", A[row][col]));
            }
            matrix.append(String.format("%n")); // Produces platform specific linebreak, wheres \n always produces \u000A
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
            matrix.append(String.format("%n"));
        }

        return (matrix.toString());
    }


}

