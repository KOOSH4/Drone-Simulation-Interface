package de.frauas.dronesimulation.app.main;

import java.util.ArrayList;
import java.util.List;
import de.frauas.dronesimulation.app.apiconnection.ApiHandler;
import de.frauas.dronesimulation.app.dronelist.DroneList;
import de.frauas.dronesimulation.app.dronetype.DroneType;

// Main class for the drone simulation application
public class main {
	// Main method of the application
	public static void main(String[] args) {
		// Create an API handler for drone
		ApiHandler droneApiHandler = new ApiHandler();
		// List to store the drones
		List<DroneList> listOfDrones = new ArrayList<>();
		// List to store the drone types
		List<DroneType> listOfDroneTypes = new ArrayList<>();

		// Populate the drone list
		populateDroneList(droneApiHandler, listOfDrones);

		// Create drone type objects
		createDroneTypeObj(droneApiHandler, listOfDrones, listOfDroneTypes);

		// Select a drone by index for test purposes
		int selectedDroneIndex = 1;

		// TEST: Get and write drone dynamics for the selected drone
		// Helper.getDroneDynamics(droneApiHandler,
		// listOfDrones.get(selectedDroneIndex), 0);

		// TEST: Print the drone dynamics status for the selected drone
		// Helper.printDroneDynamicsStatus(listOfDrones, selectedDroneIndex);

		// TEST: Calculate the payload percentage of the selected drone
		// Helper.calculatePayloadPercentage(listOfDrones.get(selectedDroneIndex));

		// Print the size of the drone list
		System.out.println("Size of drone list: " + listOfDrones.size());

		// Print the size of the drone type list
		System.out.println("Size of drone type list: " + listOfDroneTypes.size());

		// Print a completion message
		System.out.println("Done.");
	}

	// Method to populate the drone list Onjects
	private static void populateDroneList(ApiHandler droneApiHandler, List<DroneList> listOfDrones) {
		// Call the drone list API
		droneApiHandler.callDroneListAPI(0, 30, listOfDrones);
	}

	// Method to create drone type objects and add them to the drone type list
	private static void createDroneTypeObj(ApiHandler droneApiHandler, List<DroneList> listOfDrones,
			List<DroneType> listOfDroneTypes) {
		// Iterate over the drone list. reason: to avoid calling the drone type API for
		// existing drone types.
		for (DroneList drone : listOfDrones) {
			boolean matchFound = false;

			// Iterate over the drone type list
			for (DroneType droneType : listOfDroneTypes) {
				// If the drone type URI matches, set the drone type and break the loop
				if (drone.getDronetypeUri().equals(droneType.getDroneTypeUri())) {
					drone.setDroneType(droneType);
					matchFound = true;
					break;
				}
			}

			// If no match was found, call the drone type API and add the drone type to the
			// list
			if (!matchFound) {
				droneApiHandler.callDroneTypeAPI(drone);
				listOfDroneTypes.add(drone.getDroneType());
			}
		}
	}
}