package de.frauas.dronesimulation.app.main;

import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;
import java.io.IOException;
import java.util.List;
import de.frauas.dronesimulation.app.apiconnection.ApiHandler; // Import the apihandler class
import de.frauas.dronesimulation.app.dronedynamics.DroneDynamics;
import de.frauas.dronesimulation.app.dronelist.DroneList;

/**
 * Helper class provides utility methods for drone dynamics
 */
public class Helper {

	private static final Logger LOG = Logger.getGlobal();

	/**
	 * Static block for setting up logger handlers.
	 */
	static {
		Handler fileHandler;
		try {
			// File handler for logging to a file
			fileHandler = new FileHandler("./HelperLogFile.log");
			LOG.addHandler(fileHandler);
			Formatter xmlFormat = new XMLFormatter();
			fileHandler.setFormatter(xmlFormat);
			fileHandler.setLevel(Level.ALL);
		} catch (IOException e) {
			LOG.severe("An error occurred while setting up the logger handlers: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Fetches drone dynamics based on a specified offset.
	 * If an exception occurs during this process, it logs a warning message.
	 *
	 * @param droneApiHandler              This is an instance of ApiHandler class
	 * @param listOfDrones                 This is a list that contains all drones
	 * @param minutesBefore                This represents the offset value
	 * @param listOfDronesDynamicTimeStamp This is a list that contains first and
	 *                                     last drone dynamics info from API
	 */
	public static void getDroneDynamics(ApiHandler droneApiHandler, List<DroneList> listOfDrones, int minutesBefore,
			List<DroneDynamics> listOfDronesDynamicTimeStamp) {
		try {
			int offset = (minutesBefore * 25);
			if (offset >= 0) {
				droneApiHandler.fetchDroneDynamics(listOfDrones, offset, listOfDronesDynamicTimeStamp);
				listOfDronesDynamicTimeStamp.add(listOfDrones.get(0).getDroneDynamics());
			} else {
				System.out.println("Invalid minutesBefore value.");
			}
		} catch (Exception e) {
			LOG.warning("An error occurred while fetching dynamics info:  " + e.getMessage());
			e.printStackTrace();
		}
	}

}