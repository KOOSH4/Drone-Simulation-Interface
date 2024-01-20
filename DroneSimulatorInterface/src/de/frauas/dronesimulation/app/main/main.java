package de.frauas.dronesimulation.app.main;

import java.util.ArrayList;
import java.util.List;

import de.frauas.dronesimulation.app.apiconnection.ApiHandler; // Import the apihandler class
import de.frauas.dronesimulation.app.dronelist.DroneList;

import de.frauas.dronesimulation.app.main.Helper;

public class main {
	public static void main(String[] args) {
		ApiHandler apihandler = new ApiHandler();
		List<DroneList> droneInstanceList = new ArrayList<>(); // Initialize empty list for DroneList
		apihandler.callDroneListAPI(0, 30, droneInstanceList);

		for (DroneList droneinstance : droneInstanceList) {
			apihandler.callDroneTypeAPI(droneinstance); // get and write drone type for
			// each drone in list.
			droneinstance.printStatus(); // print drone instance status
			droneinstance.getDroneType().printStatus(); // print drone type status

			System.out.println("--------------------------");
		}
		int count = 1;

		Helper.getDroneDynammics(apihandler, droneInstanceList.get(count), 0); // 1-1441 get amd write drone dynamics
																				// for
																				// drone
																				// dynamics for
																				// second drone
																				// in
		// apihandler.callDroneDynamics(droneInstanceList.get(count), 12); // get amd
		// write drone dynamics for second drone
		// in
		// list.
		Helper.printDroneDynamicsStatus(droneInstanceList, count);
		Helper.payloadPercentage(droneInstanceList.get(count));
		// droneInstanceList.get(count).getDroneDynamicsList().get(0).printStatus(); //
		// print drone dynamics status
	}

}
