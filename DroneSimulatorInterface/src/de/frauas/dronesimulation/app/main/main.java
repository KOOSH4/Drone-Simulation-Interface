package de.frauas.dronesimulation.app.main;

import de.frauas.dronesimulation.app.apiconnection.apihandler; // Import the apihandler class
import java.util.List; // Add this line at the top of your file

public class main {
	public static void main(String[] args) {
		// Create an instance of apihandler
		apihandler handler = new apihandler();

		// Call the Drone Dynamics API with specific id which is same id as Drone List
		// instance id and uri.
		apihandler.callDroneDynamics("http://dronesim.facets-labs.com/api/drones/55/");

		// Call the Drone List API to get a list of drones, retrieving the first 20
		// drones
		apihandler.callDroneListAPI(0, 20);

		// Call the Drone Type API for the drone type with spicific id
		apihandler.callDroneTypeAPI("http://dronesim.facets-labs.com/api/dronetypes/65/");

	}
}
