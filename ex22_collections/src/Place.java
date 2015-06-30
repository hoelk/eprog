/**
 * Created by hoelk on 23.06.15.
 */

public class Place implements Comparable<Place> {
    String countryCode;
    String cityNameASCII;
    String cityName;
    String region;
    int population;
    double lat;
    double lon;


    Place(String countryCode, String cityNameASCII, String cityName, String region, int population, double lat, double lon) {

        try {
            checkPlace(countryCode, cityNameASCII, cityName, region, population, lat, lon);
        } catch (InvalidInputException e) {
            // System.err.println("\nWARNING: Caught InvalidInputException: " + e.getMessage);
        }

        this.countryCode = countryCode.toLowerCase().trim();
        this.cityNameASCII = cityNameASCII.trim();
        this.cityName = cityName.trim();
        this.region = region.trim();
        this.population = population;
        this.lat = lat;
        this.lon = lon;

    }

    void checkPlace(String countryCode, String cityNameASCII, String cityName, String region, int population, double lat, double lon) throws InvalidInputException {

        if (countryCode.length() != 2) throw new InvalidInputException("Country Code not valid: " + countryCode);
        if (cityNameASCII.length() > 100) throw new InvalidInputException("Ascii City Name not valid: " + cityNameASCII);
        if (cityName.length() > 255) throw new InvalidInputException("City Name not valid"+ cityName);
        if ((region.length() != 2)  && (region.length() != 0)) throw new InvalidInputException("region not valid: " + region);
        if (population < -1) throw new InvalidInputException("population not valid");
        if (lat > 90 || lat < -90) throw new InvalidInputException("lat not valid");
        if (lon > 180 || lon < -180) throw new InvalidInputException("lon not valid");

    }


    public int compareTo(Place other) {
        if (other.population > this.population) {
            return 1;
        } else {
            return -1; // Bitte diesen Hack verzeihen... Notwendig damit sich das ganze halbwegs effizient als TreeSet realisieren laesst
        }
    }


    public boolean equals(Place other) {
        return (other.countryCode.equals(countryCode) &&
                other.cityNameASCII.equals(cityNameASCII) &&
                other.cityName.equals(cityName) &&
                other.region.equals(region) &&
                other.population == population &&
                other.lat == lat &&
                other.lon == lon);
    }


    @Override
    public String toString() {
        return String.format("%s %30s %30s %5s %10d %10.3f %10.3f", countryCode, cityNameASCII, cityName, region, population, lat, lon);
    }




}
