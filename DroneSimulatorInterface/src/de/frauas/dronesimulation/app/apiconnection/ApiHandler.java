package de.frauas.dronesimulation.app.apiconnection;

import de.frauas.dronesimulation.app.dronedynamics.DroneDynamics;
import de.frauas.dronesimulation.app.dronedynamics.ParseDroneDynamics;
import de.frauas.dronesimulation.app.dronelist.DroneList;
import de.frauas.dronesimulation.app.dronelist.ParseDroneList;
import de.frauas.dronesimulation.app.dronetype.ParseDroneType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.XMLFormatter;

/**
 * This class handles the API connection.
 * It contains methods for fetching the drone list, drone type, and drone
 * dynamics
 * from the API.
 * It also contains a static block for setting up logger handlers.
 */
public class ApiHandler {

    private static final Logger LOG = Logger.getLogger(ApiHandler.class.getName());
    private static final String USER_AGENT = "MOzilla FIrefox Awesome version";
    private static final String ENDPOINT_URL_DRONE_LIST = "https://dronesim.facets-labs.com/api/drones/?format=json";
    private static final String ENDPOINT_URL_DRONE_DYNAMICS = "https://dronesim.facets-labs.com/api/dronedynamics/?format=json";
    private static final String TOKEN = "Token 2ace84830f9ad2a039c6a6dda7b529bac48a71cd";

    /**
     * Static block for setting up logger handlers.
     * It sets up two handlers: one for logging to a file and another for logging to
     * the console.
     * The file handler logs all levels of messages and uses an XML formatter.
     * The console handler only logs warning and higher level messages and uses a
     * simple formatter.
     * If an exception occurs while setting up the file handler, it logs the error
     * message.
     * this code only needs to be done once, no matter how many instances of the
     * class are
     * created. thats why a static block is used
     */
    static {
        Handler fileHandler;
        Handler consoleHandler;
        try {
            // File handler for logging to a file
            fileHandler = new FileHandler("./APILogFile.log");
            // Add the file handler to the logger
            LOG.addHandler(fileHandler);
            // Set the formatter for the file handler to XML
            Formatter xmlFormat = new XMLFormatter();
            fileHandler.setFormatter(xmlFormat);
            // Set the level of the file handler to ALL
            fileHandler.setLevel(Level.ALL);
        } catch (IOException e) {
            // Handle any IO exceptions
        }

        // Console handler for logging to the console
        consoleHandler = new ConsoleHandler();
        // Add the console handler to the logger
        LOG.addHandler(consoleHandler);
        // Set the level of the console handler to WARNING
        consoleHandler.setLevel(Level.WARNING);
        // Set the formatter for the console handler to Simple
        Formatter consoleFormat = new SimpleFormatter();
        consoleHandler.setFormatter(consoleFormat);
    }

    /**
     * This method fetches the drone list from the API.
     * It takes an offset and limit as parameters.
     * It also takes an empty list of drnes as a parameter.
     * It creates a URL object with the API endpoint and the specified limit and
     * offset.
     * It then opens a connection to the URL and sets the request properties.
     * after that it gets the response.
     * If the response code is OK, it creates a BufferedReader to read the response
     * and a StringBuffer to store the response.
     * It then reads the response line by line and appends it to the StringBuffer.
     * It then closes the BufferedReader and parses the JSON response.
     * If the response code is not OK, it logs a warning.
     * If an exception occurs during this process, it logs the error message and
     * stops the application.
     *
     * @param offset       the offset
     * @param limit        the limit
     * @param listOfDrones the list of drones
     */
    public static void fetchDroneList(int offset, int limit, List<DroneList> listOfDrones) {
        try {
            // Create a URL object with the API endpoint
            URL url = new URL(ENDPOINT_URL_DRONE_LIST + "&offset=" + offset + "&limit=" + limit);
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Set the request properties
            connection.setRequestProperty("Authorization", TOKEN);
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            // Log the response code
            LOG.log(Level.INFO, "Response Code : " + responseCode);

            // If the response code is OK
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // This line creates a BufferedReader that reads from the InputStream of the
                // connection. This InputStream contains the data returned from the server.
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                // We Declare a String variable inputLine to hold each line of the
                // response, and a StringBuffer response to hold the entire response.
                String inputLine;
                StringBuffer response = new StringBuffer();
                /**
                 * 
                 * This loop reads each line from the `BufferedReader` and appends it to
                 * the `response`. When `readLine()` returns `null`, that means it has reached
                 * the end of the stream, and the loop exits.
                 */
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                /*
                 * The line of code `in.close();` is used to close the `BufferedReader` after
                 * it's no longer needed. in.close();
                 * Parse the JSON response
                 */
                ParseDroneList.parseJsonResponse(response.toString(), listOfDrones);
            } else {
                // Log a warning if the GET request did not work
                LOG.log(Level.WARNING, "GET request not worked");
            }
        } catch (Exception e) {
            // Log any exceptions that occur
            LOG.log(Level.WARNING, "An error occurred while fetching the drone list: " + e.getMessage(), e);
            // Stop the application
            System.exit(1);
        }
    }

    /**
     * This method fetches the drone type from the API.
     * It takes a single drone object as a parameter.
     * It creates a URL object with the drone type URI that is stored in drone obj
     * as string.
     * It then opens a connection to the URL and sets the request properties.
     * after that it gets the response.
     * If the response code is OK, it creates a BufferedReader to read the response
     * and a StringBuffer to store the response.
     * It then reads the response line by line and appends it to the StringBuffer.
     * It then closes the BufferedReader and call parser methde to the JSON
     * response.
     * If an exception occurs during this process, it logs the error message and
     * stops the application.
     *
     * @param drone the drone object
     */
    public void fetchDroneType(DroneList drone) {
        try {
            // Create a URL object with the drone type URI
            URL url = new URL(drone.getDronetypeUri());
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Set the request properties
            connection.setRequestProperty("Authorization", TOKEN);
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            // Log the response code
            LOG.log(Level.INFO, "Response Code : " + responseCode);

            // If the response code is OK
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Create a BufferedReader to read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                // Read the response line by line
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                // Close the BufferedReader
                in.close();

                // Parse the JSON response
                ParseDroneType.parseJsonResponse(response.toString(), drone);
            }
        } catch (Exception e) {
            // Log any exceptions that occur
            LOG.log(Level.WARNING, "An error occurred while fetching the drone type: " + e.getMessage(), e);
            // Stop the application
            System.exit(1);
        }
    }

    /**
     * This method fetches the drone dynamics from the API.
     * It takes an offset as a parameter.
     * It also takes a list of drones and an empty list of drone dynamics timestampt
     * as
     * parameters.
     * It creates a URL object with the API endpoint and the specified limit and
     * offset.
     * the offset is calculated in helper class within getDroneDynamics method and
     * calls only wanted drone dynamic info based of its offset
     * it reduces the amount of data to be fetched from the API and reduces the load
     * time of the application
     * It then opens a connection to the URL and sets the request properties.
     * after that it gets the response.
     * If the response code is OK, it creates a BufferedReader to read the response
     * and a StringBuffer to store the response.
     * It then reads the response line by line and appends it to the StringBuffer.
     * It then closes the BufferedReader and parses the JSON response.
     * If the response code is not OK, it logs a warning.
     * If an exception occurs during this process, it logs the error message and
     * stops the application.
     * 
     * note: we call the parser and api two times and store objects in
     * listOfDroneDnamicTimestamp because we need to get the very
     * first timestamp of the drone dynamics and very last timestamp of the drone
     * dynamics to calculate the offset between the two timestamps and then call the
     * api again with the offset to get the drone dynamics
     *
     * @param listOfDrones                 the list of drones
     * @param offset                       the offset
     * @param listOfDronesDynamicTimeStamp the list of drone dynamics
     *                                     timestamp
     */
    public void fetchDroneDynamics(List<DroneList> listOfDrones, int offset,
            List<DroneDynamics> listOfDronesDynamicTimeStamp) {
        try {
            // Create a URL object with the API endpoint and the specified limit and offset
            URL url = new URL(ENDPOINT_URL_DRONE_DYNAMICS + "&limit=" + 25 + "&offset=" + offset);
            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            // Set the request properties
            connection.setRequestProperty("Authorization", TOKEN);
            connection.setRequestProperty("User-Agent", USER_AGENT);
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();
            // Log the response code
            LOG.log(Level.INFO, "Response Code : " + responseCode);

            // If the response code is OK
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Create a BufferedReader to read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                // Read the response line by line
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                // Close the BufferedReader
                in.close();

                // Parse the JSON response
                ParseDroneDynamics.parseJsonResponse(response.toString(), listOfDrones, listOfDronesDynamicTimeStamp);
            } else {
                // Log a warning if the GET request did not work
                LOG.log(Level.WARNING, "GET request not worked");
            }

            // Create a new URL object with the API endpoint and a limit of 25 and an offset
            // of 0
            url = new URL(ENDPOINT_URL_DRONE_DYNAMICS + "&limit=" + 25 + "&offset=" + 0);
            // Open a new connection to the URL
            HttpURLConnection connection1 = (HttpURLConnection) url.openConnection();
            // Set the request properties
            connection1.setRequestProperty("Authorization", TOKEN);
            connection1.setRequestProperty("User-Agent", USER_AGENT);
            connection1.setRequestMethod("GET");

            // Get the response code
            responseCode = connection1.getResponseCode();

            // If the response code is OK
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Create a BufferedReader to read the response
                BufferedReader in = new BufferedReader(new InputStreamReader(connection1.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();
                // Read the response line by line
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                // Close the BufferedReader
                in.close();

                // Parse the JSON response for the date
                ParseDroneDynamics.parseJsonResponseForDate(response.toString(), listOfDronesDynamicTimeStamp);
            } else {
                // Log a warning if the GET request did not work
                LOG.log(Level.WARNING, "GET request not worked");
            }
        } catch (Exception e) {
            // Log any exceptions that occur
            LOG.log(Level.WARNING, "An error occurred while fetching the drone dynamics: " + e.getMessage(), e);
            // Stop the application
            System.exit(1);
        }
    }
}