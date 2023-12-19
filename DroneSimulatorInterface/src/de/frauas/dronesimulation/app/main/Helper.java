package de.frauas.dronesimulation.app.main;

import java.util.List;
import de.frauas.dronesimulation.app.apiconnection.apihandler; // Import the apihandler class
import de.frauas.dronesimulation.app.dronelist.DroneList;

public class Helper {

	public static void printDroneDynamicsStatus(List<DroneList> droneInstanceList, int count) {
		droneInstanceList.get(count).getDroneDynamicsList().get(0).printStatus();
	}

	public static void getDroneDynammicData(apihandler apiHandler, DroneList droneInstance, int minutesBefore) {
		int offset = 28800 - (minutesBefore * 20);
		if (offset >= 0) {
			apiHandler.callDroneDynamics(droneInstance, offset);
		} else {
			System.out.println("Invalid minutesBefore value. It should be less than or equal to 1440 (24 hours).");
		}
	}
}
