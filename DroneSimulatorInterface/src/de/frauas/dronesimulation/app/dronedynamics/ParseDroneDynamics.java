package de.frauas.dronesimulation.app.dronedynamics;

import org.json.JSONObject;

import de.frauas.dronesimulation.app.dronelist.DroneList;

import org.json.JSONArray;
import java.time.ZonedDateTime;

public class ParseDroneDynamics {
    public static void parseJsonResponse(String input, DroneList drone) {
        JSONObject wholeFile = new JSONObject(input);
        int DroneId = drone.getId();
        String DroneUri = "http://dronesim.facets-labs.com/api/drones/" + DroneId + "/?format=json";
        JSONArray jsonFileDynamic = wholeFile.getJSONArray("results");
        // Loop through the array
        for (int i = 0; i < jsonFileDynamic.length(); i++) {
            // Get each object in the array
            JSONObject object = jsonFileDynamic.getJSONObject(i);

            // If the object has "speed" to make sure its DroneDynamics api payload
            // and the drone uri is same as the drone uri in the drone list
            if (object.has("speed") && object.getString("drone").equals(DroneUri)) {

                String droneLink = object.getString("drone");
                ZonedDateTime timestamp = ZonedDateTime.parse(object.getString("timestamp"));
                int speed = object.getInt("speed");
                String alignRoll = object.getString("align_roll");
                String alignPitch = object.getString("align_pitch");
                String alignYaw = object.getString("align_roll");
                String longitude = object.getString("longitude");
                String latitude = object.getString("latitude");
                int batteryStatus = object.getInt("battery_status");
                ZonedDateTime lastSeen = ZonedDateTime.parse(object.getString("last_seen"));
                String status = object.getString("status");

                // Print the values
                System.out.println("Drone Dynamics: ");
                System.out.println(
                        "\n" + status + " " + droneLink + " " + timestamp + " " + speed + " " + alignRoll
                                + " " + alignPitch + " " + alignYaw + " " + longitude + " " + latitude + " "
                                + batteryStatus + " " + lastSeen + "\n");
                DroneDynamics droneDynamics = new DroneDynamics(droneLink, timestamp, speed, alignRoll, alignPitch,
                        alignYaw, longitude, latitude, batteryStatus, lastSeen, status);
                drone.addDroneDynamics(droneDynamics);
                System.out.println("The DroneDynamics object is created.");
            }
        }
    }
}