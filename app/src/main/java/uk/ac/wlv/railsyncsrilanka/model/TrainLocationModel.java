package uk.ac.wlv.railsyncsrilanka.model;

public class TrainLocationModel {
    private String latitude;
    private String longitude;
    private String name;
    private String speed;

    public TrainLocationModel() {
    }

    public TrainLocationModel(String latitude, String longitude, String name, String speed) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
        this.speed = speed;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }
}
