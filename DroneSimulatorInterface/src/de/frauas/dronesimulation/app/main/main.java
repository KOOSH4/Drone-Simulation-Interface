package de.frauas.dronesimulation.app.main;

import java.util.ArrayList;
import java.util.List;
import de.frauas.dronesimulation.app.apiconnection.ApiHandler;
import de.frauas.dronesimulation.app.dronelist.DroneList;
import de.frauas.dronesimulation.app.dronetype.DroneType;

public class main {
	public static void main(String[] args) {
		try {
			ApiHandler droneApiHandler = new ApiHandler();
			List<DroneList> listOfDrones = new ArrayList<>();
			List<DroneType> listOfDroneTypes = new ArrayList<>();

			populateDroneList(droneApiHandler, listOfDrones);
			createDroneTypeObj(droneApiHandler, listOfDrones, listOfDroneTypes);

			int selectedDroneIndex = 1;

			System.out.println("Size of drone list: " + listOfDrones.size());
			System.out.println("Size of drone type list: " + listOfDroneTypes.size());
			System.out.println("Done.");
		} catch (Exception e) {
			System.out.println("An error occurred: " + e.getMessage());
			e.printStackTrace();
		}
	}

	private static void populateDroneList(ApiHandler droneApiHandler, List<DroneList> listOfDrones) {
		try {
			droneApiHandler.callDroneListAPI(0, 30, listOfDrones);
		} catch (Exception e) {
			System.out.println("An error occurred while Calling and creating the drone list: " + e.getMessage());
			e.printStackTrace();
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
					droneApiHandler.callDroneTypeAPI(drone);
					listOfDroneTypes.add(drone.getDroneType());
				}
			}
		} catch (Exception e) {
			System.out.println("An error occurred while creating drone type objects: " + e.getMessage());
			e.printStackTrace();
		}
	}
}