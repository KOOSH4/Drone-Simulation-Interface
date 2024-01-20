package de.frauas.dronesimulation.app.apiconnection;

import de.frauas.dronesimulation.app.dronedynamics.ParseDroneDynamics;
import de.frauas.dronesimulation.app.dronelist.DroneList;
import de.frauas.dronesimulation.app.dronelist.ParseDroneList;
import de.frauas.dronesimulation.app.dronetype.DroneType;
import de.frauas.dronesimulation.app.dronetype.ParseDroneType;

// Import necessary libraries
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ApiHandler {

    // Define constants for the user agent, endpoint URL, and token
    private static final String USER_AGENT = "MOzilla FIrefox Awesome version";
    private static final String ENDPOINT_URL_DRONE_LIST = "https://dronesim.facets-labs.com/api/drones/?format=json";
    private static final String ENDPOINT_URL_DRONE_DYNAMICS = "https://dronesim.facets-labs.com/api/dronedynamics/?format=json";

    private static final String TOKEN = "Token 2ace84830f9ad2a039c6a6dda7b529bac48a71cd";

    // Main method
    // Main method1
    public static void main(String[] args) {
        System.out.println("parseJsonResponse started...");

        // callDroneListAPI(10, 20, droneInstanceList); // offset , Limit

        // String droneTypeApi = "http://dronesim.facets-labs.com/api/dronetypes/60/";
        // // "http://dronesim.facets-labs.com/api/dronetypes/60/"
        // callDroneTypeAPI(droneTypeApi);

        // String droneDynamicsUri = "http://dronesim.facets-labs.com/api/drones/67/";
        // // http://dronesim.facets-labs.com/api/drones/67/
        // callDroneDynamics(droneDynamicsUri);

        System.out.println("Done.");

    }

    public static void callDroneListAPI(int offset, int limit, List<DroneList> droneInstanceList) {
        URL url;
        try {
            // Create a URL object with the endpoint URL, offset and limit as query
            // parameters
            url = new URL(ENDPOINT_URL_DRONE_LIST + "&offset=" + offset + "&limit=" + limit);
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Set the request properties
            connection.setRequestProperty("Authorization", TOKEN); // Set the Authorization header with the TOKEN
            connection.setRequestProperty("User-Agent", USER_AGENT); // Set the User-Agent header
            connection.setRequestMethod("GET"); // Set the request method to GET

            int responseCode = connection.getResponseCode(); // Get the response code
            System.out.println("Response Code : " + responseCode); // Print the response code

            if (responseCode == HttpURLConnection.HTTP_OK) { // If the response code is HTTP_OK (200)
                // Create a BufferedReader to read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                // Read the response line by line
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine); // Append each line to the response StringBuffer
                }
                in.close(); // Close the BufferedReader

                // Pass the <responseParse the JSON response and update the droneInstanceList
                ParseDroneList.parseJsonResponse(response.toString(), droneInstanceList);
            } else {
                System.out.println("GET request not worked"); // Print an error message if the response code is not
                                                              // HTTP_OK
            }
        } catch (Exception e) {
            e.printStackTrace(); // Print the stack trace if an exception is thrown
        }
    }

    public static void callDroneTypeAPI(DroneList drone) {
        URL url;
        String apiUri = drone.getDronetypeUri();
        try {
            url = new URL(apiUri);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", TOKEN);
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) { // this code reads all the data from an input stream and
                                                              // stores it in a StringBuilder object.
                    response.append(inputLine);
                }
                in.close();

                // System.out.println(response.toString());
                ParseDroneType.parseJsonResponse(response.toString(), drone);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void callDroneDynamics(DroneList drone, int offset) {
        URL url;
        try {
            // Create a URL object with the endpoint URL

            url = new URL(ENDPOINT_URL_DRONE_DYNAMICS + "&limit=" + 25 + "&offset=" + offset);
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Set the request properties
            connection.setRequestProperty("Authorization", TOKEN);
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            System.out.println("Response Code : " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                // System.out.println(response.toString());

                // Call the parseJsonResponse method with the response as argument
                ParseDroneDynamics.parseJsonResponse(response.toString(), drone);
            } else {
                System.out.println("GET request not worked");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
