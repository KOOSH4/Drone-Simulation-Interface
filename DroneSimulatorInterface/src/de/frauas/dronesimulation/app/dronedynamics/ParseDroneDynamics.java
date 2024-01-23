package de.frauas.dronesimulation.app.dronedynamics;

import org.json.JSONObject;
import de.frauas.dronesimulation.app.dronelist.DroneList;
import org.json.JSONArray;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.List;

public class ParseDroneDynamics {
    public static void parseJsonResponse(String input, List<DroneList> listOfDrones,
            List<DroneDynamics> listOfDronesDynamicTimeStamp) {
        try {
            JSONObject wholeFile = new JSONObject(input);
            for (DroneList drone : listOfDrones) {
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
                        String timestamp = object.getString("timestamp");
                        int speed = object.getInt("speed");
                        String alignRoll = object.getString("align_roll");
                        String alignPitch = object.getString("align_pitch");
                        String alignYaw = object.getString("align_roll");
                        String longitude = object.getString("longitude");
                        String latitude = object.getString("latitude");
                        int batteryStatus = object.getInt("battery_status");
                        String lastSeen = object.getString("last_seen");
                        String status = object.getString("status");

                        int batteryPercentage = (batteryStatus * 100) / drone.getDroneType().getBatterycapacity();
                        // int batteryPercentage = 100;
                        // System.out.println("BATTERY PERCENTAGE: " + batteryPercentage);
                        DroneDynamics droneDynamics = new DroneDynamics(droneLink, timestamp, speed, alignRoll,
                                alignPitch,
                                alignYaw, longitude, latitude, batteryStatus, lastSeen, status, batteryPercentage);
                        // drone.addDroneDynamics(droneDynamics);
                        drone.setDroneDynamics(droneDynamics);
                        // System.out.println("The DroneDynamics object is created.");
                    }
                }
            }

        } catch (Exception e) {
            System.out.println("An error occurred while parsing the drone dynamics: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void parseJsonResponseForDate(String input, List<DroneDynamics> listOfDronesDynamicTimeStamp) {
        try {

            JSONObject wholeFile = new JSONObject(input);

            JSONArray jsonFileDynamic = wholeFile.getJSONArray("results");
            // Loop through the arra
            JSONObject object = jsonFileDynamic.getJSONObject(0);

            // If the object has "speed" to make sure its DroneDynamics api payload
            // and the drone uri is same as the drone uri in the drone list

            String droneLink = object.getString("drone");
            String timestamp = object.getString("timestamp");
            int speed = object.getInt("speed");
            String alignRoll = object.getString("align_roll");
            String alignPitch = object.getString("align_pitch");
            String alignYaw = object.getString("align_roll");
            String longitude = object.getString("longitude");
            String latitude = object.getString("latitude");
            int batteryStatus = object.getInt("battery_status");
            String lastSeen = object.getString("last_seen");
            String status = object.getString("status");

            // int batteryPercentage = 100;
            // System.out.println("BATTERY PERCENTAGE: " + batteryPercentage);
            DroneDynamics droneDynamics = new DroneDynamics(droneLink, timestamp, speed, alignRoll,
                    alignPitch,
                    alignYaw, longitude, latitude, batteryStatus, lastSeen, status, 0);
            listOfDronesDynamicTimeStamp.add(droneDynamics);
            // drone.addDroneDynamics(droneDynamics);
            // System.out.println("The DroneDynamics object is created.");

        } catch (

        Exception e) {
            System.out.println("An error occurred while parsing the drone dynamics: " + e.getMessage());
            e.printStackTrace();
        }
    }
}