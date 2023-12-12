package de.frauas.dronesimulation.app.dronelist;

import java.time.ZonedDateTime;

public class DroneList {
    private int id;
    private String dronetype;
    private ZonedDateTime created;
    private String serialnumber;
    private int carriage_weight;
    private String carriage_type;

    // Getters and setters for each field

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDronetype() {
        return dronetype;
    }

    public void setDronetype(String dronetype) {
        this.dronetype = dronetype;
    }

    public ZonedDateTime getCreated() {
        return created;
    }

    public void setCreated(ZonedDateTime created) {
        this.created = created;
    }

    public String getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(String serialnumber) {
        this.serialnumber = serialnumber;
    }

    public int getCarriage_weight() {
        return carriage_weight;
    }

    public void setCarriage_weight(int carriage_weight) {
        this.carriage_weight = carriage_weight;
    }

    public String getCarriage_type() {
        return carriage_type;
    }

    public void setCarriage_type(String carriage_type) {
        this.carriage_type = carriage_type;
    }
}
