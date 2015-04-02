/**
 * Created by Stefan Fleck on 11/19/14.
 * <p/>
 * Fehlerhaft weil Input ja in radiant ist und ich das nie umwandel
 */

public class Ex2_funktionstabelle {
    public static void main(String[] args) {
        // Winkeleinheit, Startwinkel, Endwinkel, Schrittweite
        int angle_unit = 0; // hier kein default Wert da verpflichtende Angabe.
        double angle_start = 0.;
        double angle_end = 360.;
        double angle_steps = 10.;

        // Error Messages
        final String err1 = "Bitte command line arguments angeben. (Winkeleinheit, Startwinkel, Endwinkel, Schrittweite)";
        final String err2 = "Endwinkel muss größer als Startwinkel sein.";

        // Check and parse input parameters

        if (args.length == 0) {
            System.out.println(err1);
            System.exit(1);
        } else if (args.length > 4) {
            System.out.println(err1);
            System.exit(1);
        } else {
            try {
                // Define default values for variables
                angle_unit = Integer.parseInt(args[0]);

                // angle_end default value depends on angle_unit
                if (angle_unit == 400) angle_end = 400.;
                if (angle_unit != 360 && angle_unit != 400) {
                    angle_end = Math.PI;
                    angle_steps = Math.PI / 6;
                }

                if (args.length > 1) angle_start = Double.parseDouble(args[1]);
                if (args.length > 2) angle_end = Double.parseDouble(args[2]);
                if (args.length > 3) angle_steps = Double.parseDouble(args[3]);

                if (angle_end <= angle_start) {
                    System.out.println(err2);
                    System.exit(1);
                }

            } catch (NumberFormatException e) {
                System.err.println(err1);
                System.exit(1);
            }
        }

        String angle_unit_text = "Altgrad";
        if (angle_unit == 400) angle_unit_text = "Neugrad";
        if (angle_unit == 360) angle_unit_text = "Bogenmaß";

        double angle = angle_start;

        System.out.println("\n Winkelfunktionen (" + angle_unit_text + ")");
        System.out.println("+----------+----------+----------+----------+");
        System.out.println("|     x    | sin(x)   | cos(x)   | tan(x)   |");
        System.out.println("+----------+----------+----------+----------+");

        while (angle <= angle_end) {

            String angle_str = String.format("%8.2f", angle);
            String angle_sin = String.format("%8.4f", Math.sin(angle));
            String angle_cos = String.format("%8.4f", Math.cos(angle));
            String angle_tan = String.format("%8.4f", Math.tan(angle));

            if (Math.abs(Math.tan(angle)) > 100) angle_tan = "********";
            if (Math.abs(angle) > 100000) angle_str = "********";

            System.out.println(
                    "| " + angle_str +
                            " | " + angle_sin +
                            " | " + angle_cos +
                            " | " + angle_tan + " |");

            angle = angle + angle_steps;
        }
        System.out.println("+----------+----------+----------+----------+\n");

    }

}



