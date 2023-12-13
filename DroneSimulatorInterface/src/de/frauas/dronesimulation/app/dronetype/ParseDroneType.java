package de.frauas.dronesimulation.app.dronetype;

import org.json.JSONObject;

import de.frauas.dronesimulation.app.dronelist.DroneList;

public class ParseDroneType {
    public static void parseJsonResponse(String input, DroneList drone) {
        JSONObject o = new JSONObject(input);
        int typeId = o.getInt("id");
        String manufacturer = o.getString("manufacturer");
        String typeName = o.getString("typename");
        int weight = o.getInt("weight");
        int maxSpeed = o.getInt("max_speed");
        int batteryCapacity = o.getInt("battery_capacity");
        int controlRange = o.getInt("control_range");
        int maxCarriage = o.getInt("max_carriage");

        // System.out.println(
        // "\n" + "Drone " + typeId + ": Manufacturer " + manufacturer + " Type name: "
        // + typeName + " "
        // + "Weight: " + weight + " Max speed: " + maxSpeed + "Km/h)" + " Battery
        // capacity: "
        // + batteryCapacity + " Control range: " + controlRange + "Max carriage:" +
        // maxCarriage
        // + "g" + "\n");
        DroneType dronetype = new DroneType(typeId, manufacturer, typeName, weight, maxSpeed,
                batteryCapacity, controlRange, maxCarriage);
        drone.setDroneType(dronetype);
    }
}