import java.util.ArrayList;
import java.io.*;
import java.util.HashSet;

/**
 * Created by hoelk on 25.06.15.
 */
public class Places {

    HashSet<Place> Places = new HashSet<Place>();

    Places(String path) {

        try {
            Places = readCsvPlaces(path);
        } catch (IOException e) {
            System.err.println("\nWarning: IOException: " + e.getMessage());
        }
    }


    public static HashSet<Place> readCsvPlaces(String path) throws IOException {
        System.out.println("Reading csv file");

        FileInputStream istream = new FileInputStream(path);
        InputStreamReader reader = new InputStreamReader(istream);
        BufferedReader input = new BufferedReader(reader);
        HashSet<Place> Places = new HashSet<Place>();

        for (int row = 1; row < 1000; row++) {
            String line = input.readLine();
            String[] elem = line.split(",");

            String CountryCode = elem[0];
            String CityNameASCII = elem[1];
            String CityName = elem[2];
            String Region = elem[3];
            int Population = -1;

            try {
                Population = Integer.parseInt(elem[4]);
            } catch (NumberFormatException e) {
                //  System.err.println("\nWarning: NumberFormatException when parsing population: " + e.getMessage());
            }
            double Latitude = Double.parseDouble(elem[5]);
            double Longitude = Double.parseDouble(elem[6]);

            Place temp = new Place(CountryCode, CityNameASCII, CityName, Region, Population, Latitude, Longitude);
            Places.add(temp);

            System.out.println(temp.toString());
        }

        input.close();
        return Places;

    }
}


