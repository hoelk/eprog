/**
 * Created by hastur on 16.01.15.
 */
public class Polynomial {
    private double[] coefficients;

    Polynomial(double... coef) {
        coefficients = new double[coef.length];
        for (int i = 0; i < coef.length; i++) {
            coefficients[i] = coef[i];
        }
    }

    Polynomial(Polynomial poly) {
        coefficients = poly.coefficients.clone();
    }

    public static void main(String[] args) {
        Polynomial a = new Polynomial(13, 12, -11, 0, 1.1);
        System.out.println("test");
        System.out.println(a.toString());
    }

    public String toString() {
        String polyText = "";

        for (int i = 0; i < coefficients.length; i++) {
            String term = String.format("%.1fx", coefficients[i]);
            String operator = " + ";

            if (coefficients[i] < 0) {
                operator = " - ";
                coefficients[i] = -coefficients[i];
            }
            if (i > 0) term = operator + String.format("%.1fx^%d", coefficients[i], i);
            if (coefficients[i] == 0) term = "";

            polyText += term;
        }

        return polyText;


    }


}
