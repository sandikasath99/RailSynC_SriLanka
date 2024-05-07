package uk.ac.wlv.railsyncsrilanka.model;

public class TicketPriceModel {
    private String id;
    private String station_from;
    private String station_to;
    private String normal_3rd;
    private String normal_2d;
    private String season_private_3rd;
    private String season_private_2nd;
    private String season_gov_3rd;
    private String season_gov_2nd;
    private String season_gov3_3rd;
    private String season_gov3_2nd;

    public TicketPriceModel(String id, String station_from, String station_to, String normal_3rd, String normal_2d, String season_private_3rd, String season_private_2nd, String season_gov_3rd, String season_gov_2nd, String season_gov3_3rd, String season_gov3_2nd) {
        this.id = id;
        this.station_from = station_from;
        this.station_to = station_to;
        this.normal_3rd = normal_3rd;
        this.normal_2d = normal_2d;
        this.season_private_3rd = season_private_3rd;
        this.season_private_2nd = season_private_2nd;
        this.season_gov_3rd = season_gov_3rd;
        this.season_gov_2nd = season_gov_2nd;
        this.season_gov3_3rd = season_gov3_3rd;
        this.season_gov3_2nd = season_gov3_2nd;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStation_from() {
        return station_from;
    }

    public void setStation_from(String station_from) {
        this.station_from = station_from;
    }

    public String getStation_to() {
        return station_to;
    }

    public void setStation_to(String station_to) {
        this.station_to = station_to;
    }

    public String getNormal_3rd() {
        return normal_3rd;
    }

    public void setNormal_3rd(String normal_3rd) {
        this.normal_3rd = normal_3rd;
    }

    public String getNormal_2d() {
        return normal_2d;
    }

    public void setNormal_2d(String normal_2d) {
        this.normal_2d = normal_2d;
    }

    public String getSeason_private_3rd() {
        return season_private_3rd;
    }

    public void setSeason_private_3rd(String season_private_3rd) {
        this.season_private_3rd = season_private_3rd;
    }

    public String getSeason_private_2nd() {
        return season_private_2nd;
    }

    public void setSeason_private_2nd(String season_private_2nd) {
        this.season_private_2nd = season_private_2nd;
    }

    public String getSeason_gov_3rd() {
        return season_gov_3rd;
    }

    public void setSeason_gov_3rd(String season_gov_3rd) {
        this.season_gov_3rd = season_gov_3rd;
    }

    public String getSeason_gov_2nd() {
        return season_gov_2nd;
    }

    public void setSeason_gov_2nd(String season_gov_2nd) {
        this.season_gov_2nd = season_gov_2nd;
    }

    public String getSeason_gov3_3rd() {
        return season_gov3_3rd;
    }

    public void setSeason_gov3_3rd(String season_gov3_3rd) {
        this.season_gov3_3rd = season_gov3_3rd;
    }

    public String getSeason_gov3_2nd() {
        return season_gov3_2nd;
    }

    public void setSeason_gov3_2nd(String season_gov3_2nd) {
        this.season_gov3_2nd = season_gov3_2nd;
    }
}
