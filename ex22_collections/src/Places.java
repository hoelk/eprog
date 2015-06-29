import java.util.ArrayList;
import java.io.*;
import java.util.TreeSet;

/**
 * Created by hoelk on 25.06.15.
 */
public class Places {

    TreeSet<Place> Places = new TreeSet<Place>();

    Places(String path) {

        try {
            Places = readCsvPlaces(path);
        } catch (IOException e) {
            System.err.println("\nWarning: IOException: " + e.getMessage());
        }
    }


    public static TreeSet<Place> readCsvPlaces(String path) throws IOException {

        FileInputStream istream = new FileInputStream(path);
        InputStreamReader reader = new InputStreamReader(istream);
        BufferedReader input = new BufferedReader(reader);
        TreeSet<Place> Places = new TreeSet<Place>();

        String line = input.readLine(); // reads first line (header) which is not processed


        while ((line = input.readLine()) != null ) {

            String[] elem = line.split(",");

            String countryCode = elem[0];
            String cityNameASCII = elem[1];
            String cityName = elem[2];
            String region = elem[3];
            int population = -1;

            try {
                population = Integer.parseInt(elem[4]);
            } catch (NumberFormatException e) {
                //System.err.println("\nWARNING: NumberFormatException when parsing population: " + e.getMessage());
            }
            double lat = Double.parseDouble(elem[5]);
            double lon = Double.parseDouble(elem[6]);

            Place temp = new Place(countryCode, cityNameASCII, cityName, region, population, lat, lon);
            Places.add(temp);

        }

        input.close();
        return Places;

    }


}


