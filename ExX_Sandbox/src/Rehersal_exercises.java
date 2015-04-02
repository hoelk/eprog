/**
 * Created by hoelk on 11/28/14.
 */
public class Rehersal_exercises {

    public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        int c = Integer.parseInt(args[2]);

        // 1. Test

        // MEDIAN von 3 Zahlen
        int med = a;

        if ((a >= c & c >= b) | (b >= c & c >= a)) med = c;
        if ((a >= b & b >= c) | (c >= b & b >= a)) med = b;
        if ((b >= a & a >= c) | (c >= a & a >= b)) med = a;

        System.out.println("----- Median -----");
        System.out.println("Input:  a = " + a + ", b = " + b + ", c = " + c);
        System.out.println("Median: " + med + "\n");


        // Dreieck
        double seiteA = a;
        double seiteB = b;
        double gamma = Math.toRadians(c);
        double Area = (1d / 2d) * seiteA * seiteB * Math.sin(gamma);

        System.out.println("----- Dreiecks Berechnung ----- ");
        System.out.printf("Input: a = %.0f, b = %.0f, Winkel Gamma (rad / degree) = %.2f / %d%n", seiteA, seiteB, gamma, c);
        System.out.printf("Output: Fläche = %.02f Sin Gamma %f %n%n", Area, Math.sin(gamma));


        // Parallelschaltung von Widerständen
        double R1 = a;
        double R2 = b;
        double Rp = (R1 * R2) / (R1 + R2);

        System.out.println("----- Wiederstand ----- ");
        System.out.printf("R1: %.3f, R2: %.3f, Rp: %.3f%n%n", R1, R2, Rp);


        // Kreissektor und -abschnitt
        double r = Math.toRadians(a);
        double alpha = c;
        double SectorArea = (alpha * r * r) / 2d;
        double SegmentLength = r * r / 2d * (alpha - Math.sin(alpha));

        System.out.println("----- Kreissektor und Kreissegment ----- ");
        System.out.printf("Input:  Radius: %.0f, alpha (rad / deg): %.02f / %.02f%n", r, alpha, Math.toDegrees(alpha));
        System.out.printf("Output: Kreissektor Fläche: %.03f, Kreissegment Länge: %.03f%n%n", SectorArea, SegmentLength);

        // Tetraederberechnung
        double TetraderVol = (seiteA * seiteA * seiteA * Math.sqrt(2)) / 12;
        double TetraderArea = seiteA * seiteA * Math.sqrt(3);

        System.out.println("----- Tetrader Berechnung ----- ");
        System.out.printf("Input:  Seite: %.0f%n", seiteA);
        System.out.printf("Output: Tetraeder Volumen: %.03f, Tetraeder Oberfläche: %.03f%n%n", TetraderVol, TetraderArea);

        // Freier Fall
        double hoehe = a;
        double g = 9.80665; // m / s²
        double impactVelocity = Math.sqrt(2 * g * hoehe);
        double fallTime = impactVelocity / hoehe;

        System.out.println("----- Freier Fall ----- ");
        System.out.printf("Input:  Fallhöhe: %.0f%n", hoehe);
        System.out.printf("Output: Aufprallgeschwindigkeit: %.03f (m/s²), Fallzeit: %.03f (s)%n%n", impactVelocity, fallTime);

        // Fabonacci Nummern
        int n = a;
        long f0 = 0;
        long f1 = 1;

        System.out.println("----- Fabonacci nummern ----- ");
        System.out.println("Input: , n = " + n);
        System.out.print("Fabonacci nummern: ");
        if (n >= 1) System.out.print(f0 + ", ");
        if (n >= 2) System.out.print(f1 + ", ");
        if (n >= 3) {
            for (int i = 3; i <= n; i++) {
                long fp = f0 + f1;
                System.out.print(fp + ", ");
                f0 = f1;
                f1 = fp;
            }
        }
        System.out.print("\n");

        // Zweierpotenzen
        int x = a;
        int i = 0;
        double result = 0;

        while (result < x) {
            result = Math.pow(2, i);
            i++;
        }

        System.out.println("----- Zweierpotenzen ----- ");
        System.out.println("Input: x = " + x);
        System.out.println("Zweierpotenzen :" + i + " " + result + "\n");

        // römische Zahl in Dezimalzahl
        int inputArab = (a + 10 * b + 100 * c) * a * b * c;
        int arab1000 = inputArab / 1000;
        int arab100 = inputArab / 100 % 10;
        int arab10 = inputArab / 10 % 10;
        int arab1 = inputArab % 10;
        String Rom1 = "Fehler";
        String Rom10 = "Fehler";
        String Rom100 = "Fehler";
        String Rom1000 = new String(new char[arab1000]).replace("\0", "M");

        switch (arab1) {
            case 1:
                Rom1 = "I";
                break;
            case 2:
                Rom1 = "II";
                break;
            case 3:
                Rom1 = "III";
                break;
            case 4:
                Rom1 = "IV";
                break;
            case 5:
                Rom1 = "V";
                break;
            case 6:
                Rom1 = "VI";
                break;
            case 7:
                Rom1 = "VII";
                break;
            case 8:
                Rom1 = "VIII";
                break;
            case 9:
                Rom1 = "IX";
                break;
            case 0:
                Rom1 = "";
                break;
        }

        switch (arab10) {
            case 1:
                Rom10 = "X";
                break;
            case 2:
                Rom10 = "XX";
                break;
            case 3:
                Rom10 = "XXX";
                break;
            case 4:
                Rom10 = "XL";
                break;
            case 5:
                Rom10 = "L";
                break;
            case 6:
                Rom10 = "LX";
                break;
            case 7:
                Rom10 = "LXX";
                break;
            case 8:
                Rom10 = "LXXX";
                break;
            case 9:
                Rom10 = "XC";
                break;
            case 0:
                Rom10 = "";
                break;
        }

        switch (arab100) {
            case 1:
                Rom100 = "C";
                break;
            case 2:
                Rom100 = "CC";
                break;
            case 3:
                Rom100 = "CCC";
                break;
            case 4:
                Rom100 = "CD";
                break;
            case 5:
                Rom100 = "D";
                break;
            case 6:
                Rom100 = "DC";
                break;
            case 7:
                Rom100 = "DCC";
                break;
            case 8:
                Rom100 = "DCCC";
                break;
            case 9:
                Rom100 = "CM";
                break;
            case 0:
                Rom100 = "";
                break;
        }

        System.out.println("----- Römische Zahlen ----- ");
        System.out.println("Input arabisch : " + arab1000 + " + " + arab100 + " + " + arab10 + " + " + arab1 + " = " + inputArab);
        System.out.println("Output römisch :" + Rom1000 + Rom100 + Rom10 + Rom1);
        System.out.println("");


        // 2. Test

        /*
        Schreiben Sie eine Java-Funktion, welche einen String als Parameter entgegennimmt und die Länge
        der längsten ununterbrochenen Folge von Ziffern als Ergebnis zurückgibt. (ohne Regex)
        */

        System.out.println("----- String Manipulation ----- ");
        String text = ("A-Ä-Ö-Ü a-e-ö-ü hallo 12 hnjkj54 n324 ä 2n5 ö Ä Üöä23 3252l 5 235 jjnljh 5t252p 12345678 ");
        System.out.println(text);


        int sequenceLength = 0;
        int maxSequenceLength = 0;

        for (int j = 0; j < text.length(); j++) {
            if (text.charAt(j) >= '0' & text.charAt(j) <= '9') {
                sequenceLength++;
            } else {
                maxSequenceLength = Math.max(maxSequenceLength, sequenceLength);
                sequenceLength = 0;
            }
        }

        System.out.println("Länge längste zusammenhängende Ziffernfolge: " + maxSequenceLength);

        /*
        Umlaute ersetzen
        Schreiben Sie eine Java-Funktion, welche einen String als Parameter entgegennimmt und alle
        Umlaute durch "AE", "OE", "UE", "ae", "ou", "ue" ersetzt.
        */

        StringBuilder textUl = new StringBuilder(text);

        for (int k = 0; k < textUl.length(); k++) {
            if (textUl.charAt(k) == 'ä') textUl.replace(k, k + 1, "ae");
            if (textUl.charAt(k) == 'Ä') textUl.replace(k, k + 1, "Ae");
            if (textUl.charAt(k) == 'ö') textUl.replace(k, k + 1, "oe");
            if (textUl.charAt(k) == 'Ö') textUl.replace(k, k + 1, "Oe");
            if (textUl.charAt(k) == 'ü') textUl.replace(k, k + 1, "ue");
            if (textUl.charAt(k) == 'Ü') textUl.replace(k, k + 1, "Ue");
        }

        System.out.println("Umlaute ersetzt: " + textUl.toString());

        /*
        Differenzen
        Schreiben Sie ein Java-Programm, welches eine beliebige Anzahl von ganzen Zahlen als
        Kommandozeilenparameter entgegennimmt und folgende Aufgabe löst:
        • Das Programm soll alle möglichen Differenzen bilden (nur Absolutwerte) und aufsteigend
        sortiert ausgeben.
        • Verbesserung: Jede auftretende Differenz soll nur ein Mal ausgegeben werden.
        */

        System.out.println("----- Differenzen ----- "); //todo
        double[] inputNumbers = {33, 2, 9, 34, 34, 5, 32, 2, 3, 5, 6, 3, 77};
        double[] differences = new double[(inputNumbers.length) * 4];

        System.out.println("----- Emtpy Array ----- " + inputNumbers.length);

        for (double element : differences) {
            System.out.println(element);
        }
        System.out.println("----- ----- ");

        int unique_elements_count = 0;

        for (int u = 0; u < inputNumbers.length; u++) {
            for (int v = u + 1; v < inputNumbers.length; v++) {
                double diffValue = Math.abs(inputNumbers[u] - inputNumbers[v]);

                boolean firstOccurence = true;

                for (double element : differences) {
                    if (element == diffValue) {
                        firstOccurence = false;
                    }
                }

                if (firstOccurence) {
                    differences[unique_elements_count] = diffValue;
                    unique_elements_count++;
                    System.out.println("Value: " + diffValue + " Position: " + unique_elements_count);
                }
            }
        }

        System.out.println("----- Filled Array ----- ");

        for (double element : differences) {
            System.out.println(element);
        }
        System.out.println("----- ----- ");

        for (i = 0; i < unique_elements_count; i++) {
            int l = i + 1;
            while ((l > 0) && (differences[l] < differences[l - 1]) && (l < unique_elements_count)) {
                double tmp_element = differences[l];
                differences[l] = differences[l - 1];
                differences[l - 1] = tmp_element;
                l--;
            }
        }

        System.out.println("----- Sorted Array ----- ");

        for (i = 0; i < unique_elements_count; i++) {
            System.out.println(differences[i]);
        }
        System.out.println("----- ----- ");

        /*
        Geben Sie eine Methode an, welche einen String als Parameter entgegennimmt und als Ergebnis true liefert,
        wenn folgende Bedingungen erfüllt sind, sonst false.
        Bedingungen:
            1. Im String dürfen nur Ziffern enthalten sein.
            2. Jede enthaltene Ziffer darf nur ein Mal vorkommen.
        */

        System.out.println(Ziffernstring("01a"));
        System.out.println(('1' + 2));
        System.out.println(("1" + 2));

        /*
        Schreiben Sie eine statische Methode zur Umwandlung von römischen Zahlen in Dezimalzahlen.
        Die Methode soll einen String-Parameter mit der römischen Zahl erhalten und als Ergebnis die
        äquivalente arabische Zahl zurückgeben.
        */

        String roman = ("MCMLXXXIV");

        System.out.println("----- Römische Zahlen -> Dezimalzahlen ----- ");
        System.out.println("Input römisch : " + roman);
        System.out.println("Output arabisch :" + romanToArabic(roman));
        System.out.println("");

    }

    public static boolean Ziffernstring(String text) {
        boolean[] Ziffern = new boolean[10];

        for (int i = 0; i < text.length(); i++) {
            char character = text.charAt(i);

            if (character < '0' || character > '9') {
                return false;
            } else {
                int x = character - '0';
                System.out.println(x + " " + character);
                if (Ziffern[x] == true) {
                    return false;
                } else {
                    Ziffern[x] = true;
                }
            }
        }
        return true;
    }

    public static int romanToArabic(String roman) {
        int decimalValue = 0;

        for (int i = 0; i < roman.length(); i++) {
            char currentDigit = roman.charAt(i);
            char nextDigit;
            int sign = 1;

            if (i + 1 < roman.length()) {
                nextDigit = roman.charAt(i + 1);
            } else {
                nextDigit = currentDigit;  // a bit ugly but works
            }

            if (romanDigitValue(currentDigit) < romanDigitValue(nextDigit)) {
                sign = -1;
            }

            decimalValue += romanDigitValue(currentDigit) * sign;
        }
        return decimalValue;
    }

    public static int romanDigitValue(char romanDigit) {
        int digitValue = 0;

        if (romanDigit == 'I') digitValue += 1;
        if (romanDigit == 'V') digitValue += 5;
        if (romanDigit == 'X') digitValue += 10;
        if (romanDigit == 'L') digitValue += 50;
        if (romanDigit == 'C') digitValue += 100;
        if (romanDigit == 'D') digitValue += 500;
        if (romanDigit == 'M') digitValue += 1000;

        return (digitValue);
    }

}





