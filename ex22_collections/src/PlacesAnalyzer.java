import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by hoelk on 29.06.15.
 */
public class PlacesAnalyzer {

    HashMap<String, String> countries;
    HashMap<String, TreeSet<Place>> places;

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


    public void biggestPlaces(String countryCode, int n) {
        TreeSet<Place> places_select = places.get(countryCode.toLowerCase());
        Iterator it = places_select.iterator();
        int i = 0;

        while (it.hasNext()) {
            if (++i > n) break;
            System.out.println(it.next());
        }
    }

    public void homonymPlaces(String cityName) {
        HashSet<String> res = new HashSet<String>();
        cityName = cityName.toLowerCase();

        for (HashMap.Entry<String, TreeSet<Place>> country : places.entrySet()) {
            String countryCode = country.getValue().first().countryCode;
            String countryName = countries.get(countryCode);

            for (Place e : country.getValue()) {
                if (e.cityNameASCII.toLowerCase().equals(cityName) || e.cityName.toLowerCase().equals(cityName)) {
                    res.add(countryName);
                    break;
                }
            }
        }

        Iterator it = res.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }


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

            String countryCode = elem[0].toLowerCase();
            String countryName = elem[1];

            Countries.put(countryCode, countryName);

        }

        input.close();
        return Countries;

    }


    public static HashMap<String, TreeSet<Place>> readCsvPlaces(String path) throws IOException {
        HashMap<String, TreeSet<Place>> places = new HashMap<String, TreeSet<Place>>();

        FileInputStream istream = new FileInputStream(path);
        InputStreamReader reader = new InputStreamReader(istream);
        BufferedReader input = new BufferedReader(reader);

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

            if (places.containsKey(countryCode)) {
                places.get(countryCode).add(temp);
            } else {
                TreeSet<Place> temp_set = new TreeSet<Place>();
                temp_set.add(temp);
                places.put(countryCode, temp_set);
            }

        }

        input.close();
        return places;

    }


}
