package uk.ac.wlv.railsyncsrilanka.model;

public class TrainScheduleModel {
    private String train_id;
    private String train_name;
    private String train_start_date;
    private String train_start_station;
    private String train_end_station;
    private String start_arival_time;
    private String end_arival_time;


    public TrainScheduleModel() {
    }

    public TrainScheduleModel(String train_id, String train_name, String train_start_date, String train_start_station, String train_end_station, String start_arival_time, String end_arival_time) {
        this.train_id = train_id;
        this.train_name = train_name;
        this.train_start_date = train_start_date;
        this.train_start_station = train_start_station;
        this.train_end_station = train_end_station;
        this.start_arival_time = start_arival_time;
        this.end_arival_time = end_arival_time;
    }

    public String getTrain_id() {
        return train_id;
    }

    public void setTrain_id(String train_id) {
        this.train_id = train_id;
    }

    public String getTrain_name() {
        return train_name;
    }

    public void setTrain_name(String train_name) {
        this.train_name = train_name;
    }

    public String getTrain_start_date() {
        return train_start_date;
    }

    public void setTrain_start_date(String train_start_date) {
        this.train_start_date = train_start_date;
    }

    public String getStart_arival_time() {
        return start_arival_time;
    }

    public void setStart_arival_time(String start_arival_time) {
        this.start_arival_time = start_arival_time;
    }

    public String getEnd_arival_time() {
        return end_arival_time;
    }

    public void setEnd_arival_time(String end_arival_time) {
        this.end_arival_time = end_arival_time;
    }

    public String getTrain_start_station() {
        return train_start_station;
    }

    public void setTrain_start_station(String train_start_station) {
        this.train_start_station = train_start_station;
    }

    public String getTrain_end_station() {
        return train_end_station;
    }

    public void setTrain_end_station(String train_end_station) {
        this.train_end_station = train_end_station;
    }
}
