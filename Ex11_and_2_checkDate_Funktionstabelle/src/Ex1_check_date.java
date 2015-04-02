/*
    Stefan Fleck, H0440930

    Dieses Programm überprüft, ob ein im Format DDMMYYYY eingegebenes Datum gültig ist.
    Hierzu werden 4 Methoden definiert:
        main: Das Hauptprogramm. Überprüft ob commandline parameter richtig eingegeben wurde.
            Falls dem so ist wird die unten beschrieben Methode check_date ausgeführt
            und das Ergebnis ausgegeben.
        check_date: Methode, welche überprüft ob ein Datum gültig
            ist. Das Ergebnis wird als string zurück geliefert.
        leap: Überprüft ob ein Jahr ein Schaltjahr ist oder nicht.
            Das Ergebniss wird als boolean true/false Wert geliefert.
        mdays: Gibt die Anzahl der Tage eines bestimmten Monats aus, unter Berücksichtigung
            von Schaltjahren.
 */

public class Ex1_check_date {
    public static void main(String[] args) {
        final String err1 = "Bitte das Datum im Format ddmmyyyy angeben.";

        if (args.length != 1) {
            // Überprüfe ob commandline parameter übergeben wurde
            System.err.println(err1);
        } else {
            int date_raw = 0;

            // Überprüfen ob der commandline parameter ein Integer ist
            try {
                date_raw = Integer.parseInt(args[0]);
            } catch (NumberFormatException e) {
                System.err.println(err1);
                System.exit(1);
            }
            // Überprüfe ob die eingebene Zahl überhaupt ein Datum sein kann
            // (lässt 1 und 2 stellige Tagesangaben zu).
            if (date_raw < 1000000 || date_raw > 99000000) {
                System.err.println(err1);
            } else {
                // Zerlege das Datum in seine Bestandteile
                final int year = date_raw % 10000;
                final int month = date_raw / 10000 % 100;
                final int day = date_raw / 1000000;

                // Überprüfen ob das Datum korrekt ist mit der Methode check_date
                String result = check_date(year, month, day);

                // Ausgabe des Resultates
                System.out.println(result);
            }
        }
    }

    // Überprüfe ob Datum korrekt ist
    public static String check_date(int year, int month, int day) {
        if (day > mdays(month, year)) {
            return "Tagesangabe zu groß";
        } else if (day == 0) {
            return "Tagesangabe darf nicht 0 sein";
        } else if (month > 12) {
            return "Monatsangabe zu groß";
        } else {
            return "Dieses Datum ist gültig: " + day + "." + month + "." + year;
        }
    }

    // Überprüfe ob ein Jahr ein Schaltjahr ist
    public static boolean leap(int year) {
        // Durch 4 teilbar, aber nicht nurch 100 teilbar, ausser es ist durch 400 teilbar.
        if (year % 4 == 0 && ((year % 100 != 0) || (year % 400 == 0))) {
            return true;
        } else {
            return false;
        }
    }

    // Berechnen wiviele Tage ein Monat hat
    public static int mdays(int month, int year) {
        int mdays_out;
        switch (month) {
            case 1:
                mdays_out = 31;
                break;
            case 2:
                mdays_out = 28;
                break;
            case 3:
                mdays_out = 31;
                break;
            case 4:
                mdays_out = 30;
                break;
            case 5:
                mdays_out = 31;
                break;
            case 6:
                mdays_out = 30;
                break;
            case 7:
                mdays_out = 31;
                break;
            case 8:
                mdays_out = 31;
                break;
            case 9:
                mdays_out = 30;
                break;
            case 10:
                mdays_out = 31;
                break;
            case 11:
                mdays_out = 30;
                break;
            case 12:
                mdays_out = 31;
                break;
            default:
                mdays_out = 30;
                break;
        }

        // Falls Schaltjahr hat Februar 29 Tage
        if (leap(year) && month == 2) {
            mdays_out = 29;
        }

        return mdays_out;
    }
}

