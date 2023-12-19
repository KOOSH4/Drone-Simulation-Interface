package de.frauas.dronesimulation.app.main;

import java.util.ArrayList;
import java.util.List;

import de.frauas.dronesimulation.app.apiconnection.apihandler; // Import the apihandler class
import de.frauas.dronesimulation.app.dronelist.DroneList;

import de.frauas.dronesimulation.app.main.Helper;

public class main {
	public static void main(String[] args) {
		apihandler apihandler = new apihandler();
		List<DroneList> droneInstanceList = new ArrayList<>(); // Initialize empty list for DroneList
		Helper.getDroneList(apihandler, 0, 20, droneInstanceList); // get and write drone list
		// apihandler.callDroneListAPI(0, 20, droneInstanceList); // offset , Limit

		for (DroneList droneinstance : droneInstanceList) {
			Helper.getDroneType(apihandler, droneinstance);
			// apihandler.callDroneTypeAPI(droneinstance); // get and write drone type for
			// each drone in list.
			droneinstance.printStatus(); // print drone instance status
			droneinstance.getDroneType().printStatus(); // print drone type status

			System.out.println("--------------------------");
		}
		int count = 0;

	}

}
