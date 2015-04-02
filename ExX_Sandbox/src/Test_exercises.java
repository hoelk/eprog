/**
 * Created by hoelk on 11/30/14.
 */

    /*
    Schreiben Sie ein Java-Programm, welches zwei Koordinaten x und y
    eines Punktes als Kommandozeilenparameter einliest, dann pruft,
    ob der eingegebene Punkt innerhalb der in nebenstehender Zeichnung
    gegebenen Grenzen liegt (grauer Bereich), und das Ergebnis dieser
    Prufung
    ̈
    am Bildschirm ausgibt.
    */

public class Test_exercises {

    public static void main(String[] args) {

        // Bereichsprüfung

        double x = Double.parseDouble(args[0]);
        double y = Double.parseDouble(args[1]);
        String result = "Ausserhalb";

        boolean imKreis = Math.hypot(x - 1, y) < 1;
        boolean unterGerade1 = y < x;
        boolean uberGerade2 = y > x - 2;

        if (imKreis && unterGerade1 && uberGerade2) {
            result = "Innerhalb";
        }

        System.out.println(result);


        // Zahlenfolge

        final int n = Integer.parseInt(args[2]);
        int pn = 1;

        final int p0 = 3,
                p1 = 0,
                p2 = 2;

        int pn1 = p0,
                pn2 = p1,
                pn3 = p2;

        System.out.print(p0 + ", " + p1 + ", " + p2);

        for (int i = 0; i < n - 3; i++) {
            pn = pn2 + pn1;
            pn1 = pn2;
            pn2 = pn3;
            pn3 = pn;

            System.out.print(", " + pn);
        }

        System.out.println(" ");
    }
}




/*
        Kreis Mittelpunkt: 1
        radius: 1

        radius = sqrt(x2 + y2)

        1. Beidnung

        x2 + y2 < Math.sqrt(2)

        2. Bedinung

        wenn x < 1, y < x
        wenn x > 1, y > 1 - x


 */




