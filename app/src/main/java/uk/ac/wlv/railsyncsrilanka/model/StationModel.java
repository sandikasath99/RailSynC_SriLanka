package uk.ac.wlv.railsyncsrilanka.model;

public class StationModel {
    private String id;
    private String name;
    private String train_line_id;

    public StationModel() {
    }

    public StationModel(String id, String name, String train_line_id) {
        this.id = id;
        this.name = name;
        this.train_line_id = train_line_id;
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

    public String getTrain_line_id() {
        return train_line_id;
    }

    public void setTrain_line_id(String train_line_id) {
        this.train_line_id = train_line_id;
    }
}
