package de.frauas.dronesimulation.app.dronedynamics;

import java.time.ZonedDateTime;

public class DroneDynamics {
    private String drone;
    private int drone_dynamiy_Id;
    private ZonedDateTime timestamp;
    private int speed;
    private String align_roll;
    private String align_pitch;
    private String align_yaw;
    private String longitude;
    private String latitude;
    private int battery_status;
    private ZonedDateTime last_seen;
    private String status;

    // Getters and setters for each field

    public String getDrone() {
        return drone;
    }

    public void setDrone(String drone) {
        this.drone = drone;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getAlign_roll() {
        return align_roll;
    }

    public void setAlign_roll(String align_roll) {
        this.align_roll = align_roll;
    }

    public String getAlign_pitch() {
        return align_pitch;
    }

    public void setAlign_pitch(String align_pitch) {
        this.align_pitch = align_pitch;
    }

    public String getAlign_yaw() {
        return align_yaw;
    }

    public void setAlign_yaw(String align_yaw) {
        this.align_yaw = align_yaw;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getBattery_status() {
        return battery_status;
    }

    public void setBattery_status(int battery_status) {
        this.battery_status = battery_status;
    }

    public ZonedDateTime getLast_seen() {
        return last_seen;
    }

    public void setLast_seen(ZonedDateTime last_seen) {
        this.last_seen = last_seen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getDrone_dynamiy_Id() {
        return drone_dynamiy_Id;
    }

    public void setDrone_dynamiy_Id(int drone_dynamiy_Id) {
        this.drone_dynamiy_Id = drone_dynamiy_Id;
    }
}
