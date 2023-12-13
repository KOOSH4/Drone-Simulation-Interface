package de.frauas.dronesimulation.app.dronedynamics;

import java.time.ZonedDateTime;

public class DroneDynamics {
    private String drone;

    private ZonedDateTime timestamp;
    private int speed;
    private String alignRoll;
    private String alignPitch;
    private String alignYaw;
    private String longitude;
    private String latitude;
    private int batteryStatus;
    private ZonedDateTime lastSeen;
    private String status;

    public DroneDynamics(String drone, ZonedDateTime timestamp, int speed, String alignRoll, String alignPitch,
            String alignYaw, String longitude, String latitude, int batteryStatus, ZonedDateTime lastSeen,
            String status) {
        super();
        this.drone = drone;
        this.timestamp = timestamp;
        this.speed = speed;
        this.alignRoll = alignRoll;
        this.alignPitch = alignPitch;
        this.alignYaw = alignYaw;
        this.longitude = longitude;
        this.latitude = latitude;
        this.batteryStatus = batteryStatus;
        this.lastSeen = lastSeen;
        this.status = status;
    }

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

    public String getAlignRoll() {
        return alignRoll;
    }

    public void setAlignRoll(String alignRoll) {
        this.alignRoll = alignRoll;
    }

    public String getAlignPitch() {
        return alignPitch;
    }

    public void setAlignPitch(String alignPitch) {
        this.alignPitch = alignPitch;
    }

    public String getAlignYaw() {
        return alignYaw;
    }

    public void setAlignYaw(String alignYaw) {
        this.alignYaw = alignYaw;
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

    public int getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(int batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public ZonedDateTime getLastSeen() {
        return lastSeen;
    }

    public void setLastSeen(ZonedDateTime lastSeen) {
        this.lastSeen = lastSeen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
