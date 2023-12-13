package de.frauas.dronesimulation.app.main;

import java.util.ArrayList;
import java.util.List;

import de.frauas.dronesimulation.app.apiconnection.apihandler; // Import the apihandler class
import de.frauas.dronesimulation.app.dronelist.DroneList;

public class main {
	public static void main(String[] args) {

		List<DroneList> droneInstanceList = new ArrayList<>(); // Initialize empty list for DroneList
		apihandler.callDroneListAPI(0, 20, droneInstanceList); // offset , Limit

		for (DroneList droneinstance : droneInstanceList) {

			apihandler.callDroneTypeAPI(droneinstance); // get and write drone type for each drone in list.
			droneinstance.printStatus(); // print drone instance status
			droneinstance.getDroneType().printStatus(); // print drone type status

			System.out.println("--------------------------");
		}

		apihandler.callDroneDynamics(droneInstanceList.get(1)); // get amd write drone dynamics for second drone in
																// list.
		droneInstanceList.get(1).getDroneDynamicsList().get(0).printStatus(); // print drone dynamics status
	}
}
