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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import de.frauas.dronesimulation.app.apiconnection.ApiHandler;
import de.frauas.dronesimulation.app.dronedynamics.DroneDynamics;
import de.frauas.dronesimulation.app.dronelist.DroneList;
import de.frauas.dronesimulation.app.dronetype.DroneType;
import de.frauas.dronesimulation.app.ui.uiHandler;

public class main {
	private static final Logger LOG = Logger.getGlobal();
	static {
		Handler fileHandler;
		Handler consoleHandler;
		try {
			fileHandler = new FileHandler("./Logs/mainLogFile.log");
			LOG.addHandler(fileHandler);
			Formatter xmlFormat = new XMLFormatter();
			fileHandler.setFormatter(xmlFormat);
			fileHandler.setLevel(Level.ALL);
		} catch (IOException e) {
			// Exception handling
		}
		consoleHandler = new ConsoleHandler();
		LOG.addHandler(consoleHandler);
		consoleHandler.setLevel(Level.WARNING);
		Formatter consoleFormat = new SimpleFormatter();
		consoleHandler.setFormatter(consoleFormat);
	}

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

			// int minutesBefore = 250; // 1440 means current time and 0 means 24 hours
			// before
			int minutesBefore = 1440; // 1440 means current time and 0 means 24 hours before

			Helper.getDroneDynamics(droneApiHandler, listOfDrones, minutesBefore, listOfDronesDynamicTimeStamp);
			// for (DroneList drone : listOfDrones) {
			// System.out.println("Drone " + i++ + ":");
			// drone.printStatus();
			// drone.getDroneType().printStatus();
			// drone.getDroneDynamics().printStatus();
			// System.out.println("###");
			// }

			// refreshData(droneApiHandler, listOfDrones, listOfDroneTypes, minutesBefore);
			// call to refresh the data
			// System.out.println("size of listOfDronesDynamicTimeStamp list: " +
			// listOfDronesDynamicTimeStamp.size());
			// System.out.println("size of listOfDrones list: " +
			// listOfDronesDynamicTimeStamp.get(0).getTimestamp());
			// System.out.println("size of listOfDrones list: " +
			// listOfDronesDynamicTimeStamp.get(1).getTimestamp());

			// Swap the dates if needed

			uiHandler droneUI = new uiHandler(listOfDrones, listOfDroneTypes, droneApiHandler, minutesBefore,
					listOfDronesDynamicTimeStamp);
			droneUI.setVisible(true);
			LOG.info("Size of drone list: " + listOfDrones.size());
			LOG.info("Size of drone type list: " + listOfDroneTypes.size());

			LOG.info("Done.");
		} catch (Exception e) {
			LOG.severe("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void populateDroneList(ApiHandler droneApiHandler, List<DroneList> listOfDrones) {
		try {
			droneApiHandler.fetchDroneList(0, 30, listOfDrones);
		} catch (Exception e) {
			LOG.severe("An error occurred while Calling and creating the drone list: " + e.getMessage());
			e.printStackTrace();
			System.exit(1); // stop the application
		}
	}

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