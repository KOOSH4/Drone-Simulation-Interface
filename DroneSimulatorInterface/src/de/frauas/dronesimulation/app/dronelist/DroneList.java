package de.frauas.dronesimulation.app.dronelist;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import de.frauas.dronesimulation.app.dronedynamics.DroneDynamics;
import de.frauas.dronesimulation.app.dronetype.DroneType;

public class DroneList {
    private int id;
    private DroneType droneType; // instance of DroneType class
    private String droneTypeUri; // we can use this to call the DroneType API
    private ZonedDateTime created;
    private String serialNumber;
    private int carriageWeight;
    private String carriageType;
    private List<DroneDynamics> droneDynamicsList; // To hold many DroneDynamics
    private DroneDynamics droneDynamics; // To hold one DroneDynamics

    // Constructor
    public DroneList(int id /* , DroneType droneType */, String droneTypeUri, ZonedDateTime created,
            String serialNumber,
            int carriageWeight, String carriageType) {
        this.id = id;
        // this.droneType = droneType;
        this.droneTypeUri = droneTypeUri;
        this.created = created;
        this.serialNumber = serialNumber;
        this.carriageWeight = carriageWeight;
        this.carriageType = carriageType;
        this.droneDynamicsList = new ArrayList<>(); // Initialize empty list for DroneDynamics
    }

    public DroneList() {
    }

    // Add a DroneDynamics instance to the list
    public void addDroneDynamics(DroneDynamics dynamics) {
        droneDynamicsList.add(dynamics);
    }

    // Getters and setters for each field

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDronetypeUri() {
        return droneTypeUri;
    }

    public void setDronetype(String droneTypeUri) {
        this.droneTypeUri = droneTypeUri;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public String getSerialnumber() {
        return serialNumber;
    }

    public void setSerialnumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public int getCarriageWeight() {
        return carriageWeight;
    }

    public void setCarriageWeight(int carriageWeight) {
        this.carriageWeight = carriageWeight;
    }

    public String getCarriageType() {
        return carriageType;
    }

    public void setCarriageType(String carriageType) {
        this.carriageType = carriageType;
    }

    public DroneType getDroneType() {
        return droneType;
    }

    public void setDroneType(DroneType droneType) {
        this.droneType = droneType;
    }

    public List<DroneDynamics> getDroneDynamicsList() {
        return droneDynamicsList;
    }

    public void setDroneDynamicsList(List<DroneDynamics> droneDynamicsList) {
        this.droneDynamicsList = droneDynamicsList;
    }

    public DroneDynamics getDroneDynamics() {
        return droneDynamics;
    }

    public void setDroneDynamics(DroneDynamics droneDynamics) {
        this.droneDynamics = droneDynamics;
    }

    public void printStatus() {
        System.out.println("Drone Instance from List: ");
        System.out.println("\n" + id + "\t" + carriageType + "\t" + serialNumber + "\t" + created + "\t"
                + carriageWeight + "g)" + "\t" + droneTypeUri + "\n");
    }

}
