package de.frauas.dronesimulation.app.dronetype;

import org.json.JSONObject;
import de.frauas.dronesimulation.app.dronelist.DroneList;
import java.util.logging.*;

/**
 * This class is used to parse the drone type from a JSON response.
 */
public class ParseDroneType {
    // Logger for this class
    private static final Logger LOG = Logger.getLogger(ParseDroneType.class.getName());

    /**
     * Static block for setting up logger handlers.
     * It sets up two handlers: one for logging to a file and another for logging to
     * the console.
     * The file handler logs all levels of messages and uses an XML formatter.
     */
    static {
        Handler fileHandler;
        Handler consoleHandler;
        try {
            // File handler for logging to a file
            fileHandler = new FileHandler("./ParseDroneTypeLogFile.log");
            LOG.addHandler(fileHandler);
            Formatter xmlFormat = new XMLFormatter();
            fileHandler.setFormatter(xmlFormat);
            fileHandler.setLevel(Level.ALL);
        } catch (Exception e) {
            LOG.log(Level.WARNING, "Error occur in FileHandler.", e);
        }

        // Console handler for logging to the console
        consoleHandler = new ConsoleHandler();
        LOG.addHandler(consoleHandler);
        consoleHandler.setLevel(Level.WARNING);
        Formatter consoleFormat = new SimpleFormatter();
        consoleHandler.setFormatter(consoleFormat);
    }

    /**
     * Parses a JSON response to extract drone type information and sets it to a
     * drone instance.
     * If an exception occurs during this process, it logs a warning message.
     *
     * @param input This is the response from the API call
     * @param drone This is the drone instance to which the drone type information
     *              is set to after parsing
     */
    public static void parseJsonResponse(String input, DroneList drone) {
        try {
            // Create a JSONObject from the input string
            JSONObject o = new JSONObject(input);
            // Extract the drone type information from the JSONObject
            int typeId = o.getInt("id");
            String manufacturer = o.getString("manufacturer");
            String typeName = o.getString("typename");
            int weight = o.getInt("weight");
            int maxSpeed = o.getInt("max_speed");
            int batteryCapacity = o.getInt("battery_capacity");
            int controlRange = o.getInt("control_range");
            int maxCarriage = o.getInt("max_carriage");

            // Create a new DroneType object with the extracted information
            DroneType dronetype = new DroneType(typeId, manufacturer, typeName, weight, maxSpeed,
                    batteryCapacity, controlRange, maxCarriage);
            // Set the drone type of the drone
            drone.setDroneType(dronetype);
        } catch (Exception e) {
            // Log any exceptions that occur while parsing the drone type
            LOG.log(Level.WARNING, "An error occurred while parsing the drone type: " + e.getMessage(), e);
        }
    }
}