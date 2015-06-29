import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

/**
 * Created by hoelk on 29.06.15.
 */
public class Countries {

    HashMap<String, String> Countries = new HashMap<String, String>();

    Countries(String path){
        try {
            readCsvCountries(path);
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
        }
    }


    public static HashMap<String, String> readCsvCountries(String path) throws IOException {
        //System.out.println("Reading csv file");

        FileInputStream istream = new FileInputStream(path);
        InputStreamReader reader = new InputStreamReader(istream);
        BufferedReader input = new BufferedReader(reader);
        HashMap<String, String> Countries = new HashMap<String, String>();

        String line;

        while ((line = input.readLine()) != null ) {
            String[] elem = line.split(",");

            String countryCode = elem[0];
            String countryName = elem[1];

            Countries.put(countryCode, countryName);
            System.out.println(countryCode + " " + countryName);

        }

        input.close();
        return Countries;

    }



}
