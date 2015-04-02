import java.util.Arrays;

/**
 * Created by Stefan Fleck on 29.01.15.
 * 0440930
 */

public class Lineal {
    int[] ticks;

    Lineal(int... args) {
        ticks = args;
        Arrays.sort(ticks);
    }

    public static void main(String[] args) {
        int[] argsInteger = new int[args.length];
        int i = 0;

        for (String element : args) {
            argsInteger[i] = Integer.parseInt(element);
            i++;
        }

        Lineal x = new Lineal(argsInteger);
        System.out.println(x.toString());

    }

    public int[] getMeasurableLengths() {
        int[] measurableLengths = new int[ticks.length * (ticks.length - 1) / 2];
        int w = 0;

        for (int u = 0; u < ticks.length; u++) {
            for (int v = u + 1; v < ticks.length; v++) {
                measurableLengths[w] = Math.abs(ticks[u] - ticks[v]);
                w++;
            }
        }

        Arrays.sort(measurableLengths);
        return (measurableLengths);
    }

    public int[] getNonMeasurableLengths() {
        int[] missingLengths = new int[ticks[ticks.length - 1]];
        int numMissingLengths = 0;
        int k = 0;

        for (int i = 1; i < ticks[ticks.length - 1]; i++) {
            if (i != getUniqueMeasurableLengths()[k]) {
                missingLengths[numMissingLengths] = i;
                numMissingLengths++;
            } else {
                k++;
            }
        }

        missingLengths = Arrays.copyOf(missingLengths, numMissingLengths);
        Arrays.sort(missingLengths);
        return (missingLengths);
    }

    public int[] getUniqueMeasurableLengths() {
        int[] measurableLengths = new int[ticks.length * (ticks.length - 1) / 2];
        int unique_elements_count = 0;

        for (int u = 0; u < ticks.length; u++) {
            for (int v = u + 1; v < ticks.length; v++) {
                int diffValue = Math.abs(ticks[u] - ticks[v]);
                boolean firstOccurrence = true;

                for (double element : measurableLengths) {
                    if (element == diffValue) firstOccurrence = false;
                }

                if (firstOccurrence) {
                    measurableLengths[unique_elements_count] = diffValue;
                    unique_elements_count++;
                }
            }
        }

        measurableLengths = Arrays.copyOf(measurableLengths, unique_elements_count);
        Arrays.sort(measurableLengths);
        return (measurableLengths);
    }

    public boolean isGolomb() {
        return (Arrays.equals(getMeasurableLengths(), getUniqueMeasurableLengths()));
    }

    public boolean isPerfectGolomb() {
        boolean golomb = isGolomb();
        boolean perfekt = getMeasurableLengths().length == ticks[ticks.length - 1]; //Funktioniert weil wir das Array bereits im ctor geordnet haben
        return (golomb && perfekt);
    }

    public String toString() {
        StringBuilder result = new StringBuilder();

        String linealTyp = " kein ";

        if (isGolomb()) linealTyp = " ein ";
        if (isPerfectGolomb()) linealTyp = " ein perfektes ";

        result.append("----------------------------------\nDas Lineal ist" + linealTyp + "Golomb Lineal \n");
        result.append("Markierungen: \t\t");

        for (int tick : ticks) {
            result.append(tick + " ");
        }

        result.append("\nMessbare Längen: \t");

        for (int element : getUniqueMeasurableLengths()) {
            result.append(element + " ");
        }

        if (getNonMeasurableLengths().length > 0) {
            result.append("\nNicht Messbare Längen: \t");
            for (int element : getNonMeasurableLengths()) {
                result.append(element + " ");
            }
        }

        return result.toString();
    }


}
