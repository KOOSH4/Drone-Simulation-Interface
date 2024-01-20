package de.frauas.dronesimulation.app.apiconnection;

import de.frauas.dronesimulation.app.dronedynamics.ParseDroneDynamics;
import de.frauas.dronesimulation.app.dronelist.DroneList;
import de.frauas.dronesimulation.app.dronelist.ParseDroneList;
import de.frauas.dronesimulation.app.dronetype.ParseDroneType;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class ApiHandler {

    private static final String USER_AGENT = "MOzilla FIrefox Awesome version";
    private static final String ENDPOINT_URL_DRONE_LIST = "https://dronesim.facets-labs.com/api/drones/?format=json";
    private static final String ENDPOINT_URL_DRONE_DYNAMICS = "https://dronesim.facets-labs.com/api/dronedynamics/?format=json";
    private static final String TOKEN = "Token 2ace84830f9ad2a039c6a6dda7b529bac48a71cd";

    public static void fetchDroneList(int offset, int limit, List<DroneList> listOfDrones) {
        try {
            URL url = new URL(ENDPOINT_URL_DRONE_LIST + "&offset=" + offset + "&limit=" + limit);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", TOKEN);
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            // System.out.println("Response Code : " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                ParseDroneList.parseJsonResponse(response.toString(), listOfDrones);
            } else {
                System.out.println("GET request not worked");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while fetching the drone list: " + e.getMessage());
            e.printStackTrace();
            System.exit(1); // stop the application

        }
    }

    public static void fetchDroneType(DroneList drone) {
        try {
            URL url = new URL(drone.getDronetypeUri());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", TOKEN);
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            // System.out.println("Response Code : " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                ParseDroneType.parseJsonResponse(response.toString(), drone);
            }
        } catch (Exception e) {
            System.out.println("An error occurred while fetching the drone type: " + e.getMessage());
            e.printStackTrace();
            System.exit(1); // stop the application

        }
    }

    public void fetchDroneDynamics(DroneList drone, int offset) {
        try {
            URL url = new URL(ENDPOINT_URL_DRONE_DYNAMICS + "&limit=" + 25 + "&offset=" + offset);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Authorization", TOKEN);
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            // System.out.println("Response Code : " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                ParseDroneDynamics.parseJsonResponse(response.toString(), drone);
            } else {
                System.out.println("GET request not worked");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while fetching the drone dynamics: " + e.getMessage());
            e.printStackTrace();
            System.exit(1); // stop the application

        }
    }
}