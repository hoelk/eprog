import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by hoelk on 29.06.15.
 */
public class PlacesQueryTool {

    static HashMap<String, String> countries;
    static HashMap<String, TreeSet<Place>> places;
    ResultSet result;

    /**
     * Custom constructor for PlacesQueryTool
     *
     * @param places_path    Path to places csv file
     * @param countries_path Path to countries csv file
     * @throws IOException Is thrown if files cannot be accessed
     */
    PlacesQueryTool(String places_path, String countries_path) throws IOException {
        places = readCsvPlaces(places_path);
        countries = readCsvCountries(countries_path);

    }

    /**
     * Prints n biggest places of a country, and in which other countries cities of the same name exist
     *
     * @param countryCode Country Code of the country of interes
     * @param n           Number of biggest places to show
     * @throws InvalidInputException IS thrown in case of a nonexisting country code
     */
    public void queryPlaces(String countryCode, int n) throws InvalidInputException {
        countryCode = countryCode.toLowerCase();

        if (!countries.containsKey(countryCode)) {
            System.out.println(countryCode);
            throw new InvalidInputException("Country code not found");
        }

        TreeSet<Place> biggestPlaces = getBiggestPlaces(countryCode, n);


        System.out.println("=================================================================");
        System.out.printf("%s (%s)%n", lookupName(countryCode), countryCode);
        System.out.println("=================================================================");

        for (Place e : biggestPlaces) {
            ArrayList<String> countriesWithCity = getCountriesWithCity(e.cityName);

            System.out.printf("%-30s                     %9d Ew.%n", e.cityName, e.population);

            if (countriesWithCity.size() > 1) {
                System.out.println("  Länder mit gleichnamigen Städten: ");

                for (String f : countriesWithCity) {
                    if (!f.equals(lookupName(countryCode))) {
                        System.out.println("     " + f);
                    }
                }

            } else {
                System.out.println("  Es gibt keine Länder mit gleichnamigen Städten.");
            }
            System.out.println("-----------------------------------------------------------------");
        }
    }

    /**
     * Returns the n biggest places of a country
     *
     * @param countryCode Country Code of the country of interest
     * @param n           Number of biggest places to show
     * @return Tree set of the n biggest places of a country sorted by population size
     */
    public TreeSet<Place> getBiggestPlaces(String countryCode, int n) {
        TreeSet<Place> placesSelect = places.get(countryCode.toLowerCase());
        TreeSet<Place> res = new TreeSet<Place>();

        Iterator<Place> it = placesSelect.iterator();

        int i = 0;

        while (it.hasNext()) {
            if (++i > n) break;
            res.add(it.next());
        }

        return (res);
    }

    /**
     * Returns countries that contain a given place
     *
     * @param placeName Place name of interest
     * @return Array list of countries that contain a place with placeName, sorted alphabetically
     */

    public ArrayList<String> getCountriesWithCity(String placeName) {
        ArrayList<String> res = new ArrayList<String>();
        placeName = placeName.toLowerCase();

        for (HashMap.Entry<String, TreeSet<Place>> country : places.entrySet()) {
            String countryCode = country.getValue().first().countryCode;
            String countryName = lookupName(countryCode);

            for (Place e : country.getValue()) {
                if (e.cityNameASCII.toLowerCase().equals(placeName)) {
                    res.add(countryName);
                    break;
                }
            }
        }

        Collections.sort(res);
        return (res);
    }


    /**
     * Lookup country name based on countryCode
     *
     * @param countryCode country code of country of interest
     * @return String representation of the country name
     */
    public static String lookupName(String countryCode) {
        countryCode = countryCode.trim().toLowerCase();
        return (countries.get(countryCode));
    }


    /**
     * Reads the countries csv files
     *
     * @param path path of the countries csv file
     * @return HashMap containing the country code / country value pairs
     * @throws IOException
     */
    public static HashMap<String, String> readCsvCountries(String path) throws IOException {

        FileInputStream istream = new FileInputStream(path);
        InputStreamReader reader = new InputStreamReader(istream);
        BufferedReader input = new BufferedReader(reader);
        HashMap<String, String> Countries = new HashMap<String, String>();

        String line;

        while ((line = input.readLine()) != null) {
            String[] elem = line.split(",\"");

            String countryCode = elem[0].toLowerCase().trim();
            String countryName = elem[1].trim();
            countryName = countryName.substring(0, countryName.length() - 1);

            Countries.put(countryCode, countryName);
        }

        input.close();
        return Countries;

    }

    /**
     * Read the places csv file
     *
     * @param path path to the places csv file
     * @return Hash map containing a TreeSet of places for every countryCode
     * @throws IOException
     */
    public static HashMap<String, TreeSet<Place>> readCsvPlaces(String path) throws IOException {
        HashMap<String, TreeSet<Place>> places = new HashMap<String, TreeSet<Place>>();

        FileInputStream istream = new FileInputStream(path);
        InputStreamReader reader = new InputStreamReader(istream);
        BufferedReader input = new BufferedReader(reader);

        String line = input.readLine(); // reads first line (header) which is not processed


        while ((line = input.readLine()) != null) {

            String[] elem = line.split(",");

            String countryCode = elem[0].trim().toLowerCase();
            String cityNameASCII = elem[1].trim();
            String cityName = elem[2].trim();
            String region = elem[3].trim();
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
