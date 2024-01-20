package de.frauas.dronesimulation.app.main;

import java.util.List;
import de.frauas.dronesimulation.app.apiconnection.ApiHandler; // Import the apihandler class
import de.frauas.dronesimulation.app.dronelist.DroneList;

public class Helper {

	public static void getDroneDynamics(ApiHandler droneApiHandler, DroneList droneInstance, int minutesBefore) {
		int offset = 28800 - (minutesBefore * 20);
		if (offset >= 0) {
			droneApiHandler.fetchDroneDynamics(droneInstance, offset);
		} else {
			System.out.println("Invalid minutesBefore value. It should be less than or equal to 1440 (24 hours).");
		}
	}

	public static void printDroneDynamicsStatus(List<DroneList> listOfDrones, int selectedDroneIndex) {
		listOfDrones.get(selectedDroneIndex).getDroneDynamicsList().get(0).printStatus();
	}

	public static int calculatePayloadPercentage(DroneList droneInstance) {
		int payloadPercentage = (droneInstance.getCarriageWeight() * 100)
				/ droneInstance.getDroneType().getMaxCarriage();
		System.out.println("PAYLOAD PERCENTAGE: " + payloadPercentage);
		return payloadPercentage;
	}
}
