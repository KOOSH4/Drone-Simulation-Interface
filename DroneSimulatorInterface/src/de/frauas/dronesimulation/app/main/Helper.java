package de.frauas.dronesimulation.app.main;

import java.util.List;
import de.frauas.dronesimulation.app.apiconnection.apihandler; // Import the apihandler class
import de.frauas.dronesimulation.app.dronelist.DroneList;

public class Helper {

	public static void getDroneList(apihandler apiHandler, int offset, int limit, List<DroneList> droneInstanceList) {
		apiHandler.callDroneListAPI(offset, limit, droneInstanceList);
	}

	public static void getDroneType(apihandler apiHandler, DroneList droneInstance) {
		apiHandler.callDroneTypeAPI(droneInstance);
	}

	public static void getDroneDynammics(apihandler apiHandler, DroneList droneInstance, int minutesBefore) {
		int offset = 28800 - (minutesBefore * 20);
		if (offset >= 0) {
			apiHandler.callDroneDynamics(droneInstance, offset);
		} else {
			System.out.println("Invalid minutesBefore value. It should be less than or equal to 1440 (24 hours).");
		}
	}

	public static void printDroneDynamicsStatus(List<DroneList> droneInstanceList, int count) {
		droneInstanceList.get(count).getDroneDynamicsList().get(0).printStatus();
	}
}
