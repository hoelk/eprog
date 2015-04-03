import java.io.IOException;

/**
 * Created by hoelk on 02.04.15.
 */
public class Main {
    public static void main(String[] args) throws IOException{

        Matrix input = Matrix.parseMatrixFile(args[0]);
        Matrix inputTriangular = new Matrix(input);
        inputTriangular.triangularise();

        System.out.println(input);

        System.out.printf("Dreiecks Form der Matrix: %n%n");
        System.out.println(inputTriangular);

        System.out.printf("Rang der Matrix: %n%n");
        System.out.println(inputTriangular.getRank());

    }
}
