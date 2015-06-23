/**
 * Main program for the third home exercise of the lecture 122.425.
 *
 * Generates random n x n matrices with increasing n and measures the
 * runtime of rank- and determinant calculating methods
 *
 * @author Stefan Fleck
 * @version 0.0.1
 */

public class Main {
    public static void main(String[] args) {

        boolean doRank = true;
        boolean doDet = true;

        System.out.println("      N      Rang       t[s]       Det        t[s]");
        System.out.println("--------------------------------------------------");


        for (int i = 1; i < 999; i++) {
            long t_start;
            long t_end ;
            int rank = 0;
            double det =0;

            System.out.println();

            double t_det = 0;
            double t_rank = 99;

            int n = i;

            if (i > 16){
                n = i*i;
            }


            Matrix A = new Matrix(n, n, true);

            if (doRank) {
                t_start = System.currentTimeMillis();
                rank = A.getRank();
                t_end = System.currentTimeMillis();
                t_rank = (double) (t_end - t_start) / 1000;
            }

            if (doDet) {
                t_start = System.currentTimeMillis();
                det = A.getDet();
                t_end = System.currentTimeMillis();
                t_det = (double) (t_end - t_start) / 1000;
            }

            if (doRank && doDet) {
                System.out.printf("     %3d      %3d %10.3f %10.3f %10.3f%n", n, rank, t_rank, det, t_det);
            } else if (doRank) {
                System.out.printf("     %3d      %3d %10.3f%n", n, rank, t_rank);
            } else if (doDet) {
                System.out.printf("     %3d                     %10.3f %10.3f%n", i, det, t_det);
            } else {
                break;
            }

            if (t_rank > 0.5) doRank = false;
            if (t_det > 0.5) doDet = false;

        }

    }
}
