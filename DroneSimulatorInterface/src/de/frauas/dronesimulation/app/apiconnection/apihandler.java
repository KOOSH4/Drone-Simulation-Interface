package de.frauas.dronesimulation.app.apiconnection;

// Import necessary libraries
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class apihandler {

    // Define constants for the user agent, endpoint URL, and token
    private static final String USER_AGENT = "MOzilla FIrefox Awesome version";
    private static final String ENDPOINT_URL_DRONE_LIST = "https://dronesim.facets-labs.com/api/drones/?format=json";
    private static final String ENDPOINT_URL_DRONE_DYNAMICS = "https://dronesim.facets-labs.com/api/dronedynamics/?format=json";

    private static final String TOKEN = "Token 2ace84830f9ad2a039c6a6dda7b529bac48a71cd";

    // Main method
    // Main method1
    public static void main(String[] args) {

        System.out.println("parseJsonResponse started...");

        callDroneListAPI(10, 20); // offset , Limit

        String droneTypeApi = "http://dronesim.facets-labs.com/api/dronetypes/60/"; // "http://dronesim.facets-labs.com/api/dronetypes/60/"
        callDroneTypeAPI(droneTypeApi);

        String droneDynamicsUri = "http://dronesim.facets-labs.com/api/drones/67/"; // http://dronesim.facets-labs.com/api/drones/67/
        callDroneDynamics(droneDynamicsUri);

        System.out.println("Done.");

    }

    public static void callDroneListAPI(int offset, int limit) {
        URL url;
        try {
            // Create a URL object with the endpoint URL
            url = new URL(ENDPOINT_URL_DRONE_LIST + "&offset=" + offset + "&limit=" + limit);
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
                parseJsonResponse(response.toString(), "droneList", null);
            } else {
                System.out.println("GET request not worked");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void callDroneTypeAPI(String apiUri) {
        URL url;
        try {
            url = new URL(apiUri + "?format=json");
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

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // System.out.println(response.toString());
                parseJsonResponse(response.toString(), "droneType", null);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void callDroneDynamics(String DroneUri) {
        URL url;
        try {
            // Create a URL object with the endpoint URL
            url = new URL(ENDPOINT_URL_DRONE_DYNAMICS + "&limit=" + 20);
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
                parseJsonResponse(response.toString(), "droneDynamics", DroneUri);
            } else {
                System.out.println("GET request not worked");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    // Method to process the response
    public static void parseJsonResponse(String input, String apiType, String DroneUri) { // 1- droneList, 2-droneType,
                                                                                          // 3-DroneDynamics
        // Create a JSONObject with the response
        JSONObject wholeFile = new JSONObject(input);
        // Get the "results" array from the JSONObject
        switch (apiType) {
            case "droneList":
                JSONArray jsonFile = wholeFile.getJSONArray("results");
                // Loop through the array
                for (int i = 0; i < jsonFile.length(); i++) {
                    // Get each object in the array
                    JSONObject o = jsonFile.getJSONObject(i);
                    // If the object has "carriage_type" and "carriage_weight"
                    if (o.has("carriage_type") && o.has("carriage_weight")) {
                        // Get the values of "carriage_type" and "carriage_weight"
                        String carriageType = o.getString("carriage_type");
                        int carriageWeight = o.getInt("carriage_weight");
                        int id = o.getInt("id");
                        String dateCreated = o.getString("created");
                        String serialNumber = o.getString("serialnumber");
                        String droneType = o.getString("dronetype");

                        // Print the values
                        System.out.println("\n" + "Drone " + id + ": carriage type " + carriageType + " serial number: "
                                + serialNumber + " " + "created: " + dateCreated + " (carriage weight: "
                                + carriageWeight + "g)" + " drone type: " + droneType + "\n");
                    }
                }
                break;

            case "droneType":
                JSONObject o = new JSONObject(input);
                int id = o.getInt("id");
                String manufacturer = o.getString("manufacturer");
                String typename = o.getString("typename");
                int weight = o.getInt("weight");
                int max_speed = o.getInt("max_speed");
                int battery_capacity = o.getInt("battery_capacity");
                int control_range = o.getInt("control_range");
                int max_carriage = o.getInt("max_carriage");

                System.out.println(
                        "\n" + "Drone " + id + ": Manufacturer " + manufacturer + " Type name: " + typename + " "
                                + "Weight: " + weight + " Max speed: " + max_speed + "Km/h)" + " Battery capacity: "
                                + battery_capacity + " Control range: " + control_range + "Max carriage:" + max_carriage
                                + "g" + "\n");

                break;
            case "droneDynamics":
                JSONArray jsonFileDynamic = wholeFile.getJSONArray("results");
                // Loop through the array
                for (int i = 0; i < jsonFileDynamic.length(); i++) {
                    // Get each object in the array
                    JSONObject object = jsonFileDynamic.getJSONObject(i);
                    // If the object has "carriage_type" and "carriage_weight"
                    if (object.has("speed") && object.getString("drone").equals(DroneUri + "?format=json")) {
                        // Get the values of "carriage_type" and "carriage_weight"

                        String droneLink = object.getString("drone");
                        String timestamp = object.getString("timestamp");
                        int speed = object.getInt("speed");
                        String alignRoll = object.getString("align_roll");
                        String alignPitch = object.getString("align_pitch");
                        String alignYaw = object.getString("align_roll");
                        String longitude = object.getString("longitude");
                        String latitude = object.getString("latitude");
                        int batteryStatus = object.getInt("battery_status");
                        String lastSeen = object.getString("last_seen");
                        String status = object.getString("status");

                        // Print the values
                        System.out.println("Drone Dynamics: ");
                        System.out.println(
                                "\n" + status + " " + droneLink + " " + timestamp + " " + speed + " " + alignRoll
                                        + " " + alignPitch + " " + alignYaw + " " + longitude + " " + latitude + " "
                                        + batteryStatus + " " + lastSeen + "\n");
                    }

                }
        }

    }

    // Method to format a JSON string
    public static String formatJson(String input) {
        final int indentSpaces = 4;
        // Create a JSONTokener with the input string
        Object json = new JSONTokener(input).nextValue();

        // If the object is a JSONObject
        if (json instanceof JSONObject) {
            JSONObject o = (JSONObject) json;

            // Return the string representation of the JSONObject
            return o.toString(indentSpaces);
            // If the object is a JSONArray
        } else if (json instanceof JSONArray) {
            // Return the string representation of the JSONArray
            return ((JSONArray) json).toString(indentSpaces);
            // If the object is neither a JSONObject nor a JSONArray
        } else {
            // Throw an IllegalArgumentException
            throw new IllegalArgumentException("Input string is not a valid JSON");
        }
    }

}
