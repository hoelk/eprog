import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * Created by hoelk on 29.06.15.
 */
public class PlacesAnalyzer {

    TreeSet<Place> places;
    HashMap<String, String> countries;

    PlacesAnalyzer(String places_path, String countries_path) {
        try {
            places = readCsvPlaces(places_path);
        } catch (IOException e) {
            System.err.println("\nWarning: IOException: " + e.getMessage());
        }

        try {
            countries = readCsvCountries(countries_path);
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }

    }


    public TreeSet<Place> biggesPlaces(String countryCode, int n) {
        Collections.sort(Places);


    }

    public TreeSet<Place> homonymPlaces(String name) {

    }


    public static HashMap<String, String> readCsvCountries(String path) throws IOException {
        //System.out.println("Reading csv file");

        FileInputStream istream = new FileInputStream(path);
        InputStreamReader reader = new InputStreamReader(istream);
        BufferedReader input = new BufferedReader(reader);
        HashMap<String, String> Countries = new HashMap<String, String>();

        String line;

        while ((line = input.readLine()) != null) {
            String[] elem = line.split(",");

            String countryCode = elem[0];
            String countryName = elem[1];

            Countries.put(countryCode, countryName);

        }

        input.close();
        return Countries;

    }

    public static TreeSet<Place> readCsvPlaces(String path) throws IOException {

        FileInputStream istream = new FileInputStream(path);
        InputStreamReader reader = new InputStreamReader(istream);
        BufferedReader input = new BufferedReader(reader);
        TreeSet<Place> Places = new TreeSet<Place>();

        String line = input.readLine(); // reads first line (header) which is not processed


        while ((line = input.readLine()) != null) {

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
