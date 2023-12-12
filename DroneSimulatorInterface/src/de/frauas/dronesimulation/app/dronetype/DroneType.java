package de.frauas.dronesimulation.app.dronetype;

public class DroneType {
    private int typeid;
    private String manufacturer;
    private String typename;
    private int weight;
    private int max_speed;
    private int battery_capacity;
    private int control_range;
    private int max_carriage;

    // Getters and setters for each field

    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getTypename() {
        return typename;
    }

    public void setTypename(String typename) {
        this.typename = typename;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMax_speed() {
        return max_speed;
    }

    public void setMax_speed(int max_speed) {
        this.max_speed = max_speed;
    }

    public int getBattery_capacity() {
        return battery_capacity;
    }

    public void setBattery_capacity(int battery_capacity) {
        this.battery_capacity = battery_capacity;
    }

    public int getControl_range() {
        return control_range;
    }

    public void setControl_range(int control_range) {
        this.control_range = control_range;
    }

    public int getMax_carriage() {
        return max_carriage;
    }

    public void setMax_carriage(int max_carriage) {
        this.max_carriage = max_carriage;
    }
}
