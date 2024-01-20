package de.frauas.dronesimulation.app.main;

import java.util.List;
import de.frauas.dronesimulation.app.apiconnection.ApiHandler; // Import the apihandler class
import de.frauas.dronesimulation.app.dronelist.DroneList;

public class Helper {

	public static void getDroneDynamics(ApiHandler droneApiHandler, List<DroneList> listOfDrones, int minutesBefore) {
		try {
			int offset = 28800 - (minutesBefore * 20);
			if (offset >= 0) {
				droneApiHandler.fetchDroneDynamics(listOfDrones, offset);
			} else {
				System.out.println("Invalid minutesBefore value. It should be less than or equal to 1440 (24 hours).");
			}
		} catch (Exception e) {
			System.out.println("An error occurred while getting drone dynamics: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static void printDroneDynamicsStatus(List<DroneList> listOfDrones, int selectedDroneIndex) {
		try {
			listOfDrones.get(selectedDroneIndex).getDroneDynamicsList().get(0).printStatus();
		} catch (Exception e) {
			System.out.println("An error occurred while printing drone dynamics status: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public static int calculatePayloadPercentage(DroneList droneInstance) {
		try {
			int payloadPercentage = (droneInstance.getCarriageWeight() * 100)
					/ droneInstance.getDroneType().getMaxCarriage();
			System.out.println("PAYLOAD PERCENTAGE: " + payloadPercentage);
			return payloadPercentage;
		} catch (Exception e) {
			System.out.println("An error occurred while calculating payload percentage: " + e.getMessage());
			e.printStackTrace();
			return 0;
		}
	}
}