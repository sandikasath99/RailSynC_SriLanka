package uk.ac.wlv.railsyncsrilanka.model;

public class TrainModel {
    private String id;
    private String name;
    private String arrival_time;

    public TrainModel(String id, String name, String arrival_time) {
        this.id = id;
        this.name = name;
        this.arrival_time = arrival_time;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArrivalTime() {
        return arrival_time;
    }

    public void setArrivalTime(String arrival_time) {
        this.arrival_time = arrival_time;
    }
}
