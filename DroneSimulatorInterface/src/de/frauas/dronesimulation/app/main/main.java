package de.frauas.dronesimulation.app.main;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.XMLFormatter;
import java.io.IOException;

import de.frauas.dronesimulation.app.apiconnection.ApiHandler;
import de.frauas.dronesimulation.app.dronedynamics.DroneDynamics;
import de.frauas.dronesimulation.app.dronelist.DroneList;
import de.frauas.dronesimulation.app.dronetype.DroneType;
import de.frauas.dronesimulation.app.ui.uiHandler;

/**
 * This is the main class of the application.
 * It contains the main method, which is the entry point of the application.
 * It also contains a static block for setting up logger handlers.
 * It also contains two methods: one for populating the list of drones and
 * another for creating DroneType objects and associating them with the
 * corresponding DroneList objects.
 * The main method initializes the ApiHandler, lists of drones, drone types, and
 * drone dynamics.
 * It then populates the drone list, creates drone type objects, and gets the
 * drone dynamics.
 * It also initializes the UI handler and sets it to visible.
 * If an exception occurs during this process, it logs the error message.
 */
public class Main {

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
	private static final Logger LOG = Logger.getGlobal();
	public static final String ROOT_FOLDER = "/icons/";

	// Static block for setting up logger handlers
	static {
		Handler fileHandler;
		Handler consoleHandler;
		try {
			// File handler for logging to a file
			fileHandler = new FileHandler("./mainLogFile.log");
			LOG.addHandler(fileHandler);
			Formatter xmlFormat = new XMLFormatter();
			fileHandler.setFormatter(xmlFormat);
			fileHandler.setLevel(Level.ALL);
		} catch (IOException e) {
			LOG.severe("An error occurred while setting up the logger handlers: " + e.getMessage());
			e.printStackTrace();
		}

		// Console handler for logging to the console
		consoleHandler = new ConsoleHandler();
		LOG.addHandler(consoleHandler);
		consoleHandler.setLevel(Level.WARNING);
		Formatter consoleFormat = new SimpleFormatter();
		consoleHandler.setFormatter(consoleFormat);
	}

	/**
	 * This is the main method.
	 * It initializes the ApiHandler, lists of drones, drone types, and drone
	 * dynamics.
	 * It then populates the drone list, creates drone type objects, and gets the
	 * drone dynamics.
	 * It also initializes the UI handler and sets it to visible.
	 * If an exception occurs during this process, it logs the error message.
	 *
	 * @param args this is a standard parameter passed into the main method of a
	 *             Java
	 *             program. This is the standard application entry point in Java.
	 */
	public static void main(String[] args) {
		try {
			ApiHandler droneApiHandler = new ApiHandler();
			List<DroneList> listOfDrones = new ArrayList<>();
			List<DroneType> listOfDroneTypes = new ArrayList<>();
			List<DroneDynamics> listOfDronesDynamicTimeStamp = new ArrayList<>();
			populateDroneList(droneApiHandler, listOfDrones);
			System.out.println("size of drone list: " + listOfDrones.size());
			createDroneTypeObj(droneApiHandler, listOfDrones, listOfDroneTypes);
			System.out.println("size of drone type list: " + listOfDroneTypes.size());

			int minutesBefore = 1440; // 0 means current time and 1440 means last offset

			Helper.getDroneDynamics(droneApiHandler, listOfDrones, minutesBefore, listOfDronesDynamicTimeStamp);
			listOfDrones.get(0).getDroneDynamics().printStatus();
			uiHandler droneUI = new uiHandler(listOfDrones, listOfDroneTypes,
					droneApiHandler, minutesBefore,
					listOfDronesDynamicTimeStamp);
			droneUI.setVisible(true);
			// Dashboard dashboard = new Dashboard(droneApiHandler, listOfDrones,
			// listOfDroneTypes,
			// listOfDronesDynamicTimeStamp, minutesBefore);
			// dashboard.setVisible(true);
			// LOG.info("Size of drone list: " + listOfDrones.size());
			// LOG.info("Size of drone type list: " + listOfDroneTypes.size());

			LOG.info("Done.");
		} catch (Exception e) {
			LOG.severe("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to populate the list of drones.
	 * It fetches the drone list from the API and adds it to the provided list.
	 * If an exception occurs during this process, it logs the error message and
	 * stops the application.
	 *
	 * @param droneApiHandler this is an instance of DroneApiHandler
	 * @param listOfDrones    this is an empty list that will store the drone
	 *                        instances
	 */
	private static void populateDroneList(ApiHandler droneApiHandler, List<DroneList> listOfDrones) {
		try {
			ApiHandler.fetchDroneList(0, 30, listOfDrones);
		} catch (Exception e) {
			LOG.severe("An error occurred while Calling and creating the drone list: " + e.getMessage());
			e.printStackTrace();
			System.exit(1); // stop the application
		}
	}

	/**
	 * This method is used to create DroneType objects and associate them with the
	 * corresponding DroneList objects.
	 * It iterates over the list of drones, and for each drone, it checks if a
	 * matching DroneType already exists.
	 * If a match is found, it sets the DroneType for the drone. If no match is
	 * found, it fetches the DroneType from the API and adds it to the list.
	 *
	 * @param droneApiHandler  this is an instance of DroneApiHandler
	 * @param listOfDrones     this is a list of all drones
	 * @param listOfDroneTypes this is an empty list of all drone types
	 */
	private static void createDroneTypeObj(ApiHandler droneApiHandler, List<DroneList> listOfDrones,
			List<DroneType> listOfDroneTypes) {
		try {
			for (DroneList drone : listOfDrones) {
				boolean matchFound = false;

				for (DroneType droneType : listOfDroneTypes) {
					if (drone.getDronetypeUri().equals(droneType.getDroneTypeUri())) {
						drone.setDroneType(droneType);
						matchFound = true;
						break;
					}
				}

				if (!matchFound) {
					droneApiHandler.fetchDroneType(drone);
					listOfDroneTypes.add(drone.getDroneType());
				}
			}
		} catch (Exception e) {
			LOG.severe("An error occurred while creating drone type objects: " + e.getMessage());
			e.printStackTrace();
			System.exit(1); // stop the application
		}
	}

	/**
	 * This method is used to refresh the data of drones and their types.
	 * It first clears the existing lists of drones and drone types, then fetches
	 * and creates the objects again.
	 * It also gets the drone dynamics based on a specified timestamp.
	 *
	 * @param droneApiHandler              This is an instance of DroneApiHandler
	 * @param listOfDrones                 This is a list of DroneList objects
	 * @param listOfDroneTypes             This is a list of DroneType objects
	 * @param minutesBefore                This is represents the time offset
	 * @param listOfDronesDynamicTimeStamp This is a list of DroneDynamics objects
	 * @return Nothing.
	 */
	public static void refreshData(ApiHandler droneApiHandler, List<DroneList> listOfDrones,
			List<DroneType> listOfDroneTypes, int minutesBefore, List<DroneDynamics> listOfDronesDynamicTimeStamp) {
		// Clear the existing lists
		listOfDrones.clear();
		listOfDroneTypes.clear();

		// Fetch and create the objects again
		populateDroneList(droneApiHandler, listOfDrones);
		createDroneTypeObj(droneApiHandler, listOfDrones, listOfDroneTypes);
		Helper.getDroneDynamics(droneApiHandler, listOfDrones, minutesBefore, listOfDronesDynamicTimeStamp);
		System.out.println("Refreshed the data!");
	}

}