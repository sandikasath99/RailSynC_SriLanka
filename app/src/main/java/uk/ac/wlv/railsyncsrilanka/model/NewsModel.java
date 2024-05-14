package uk.ac.wlv.railsyncsrilanka.model;

public class NewsModel {

    private String id;
    private String title;
    private String description;
    private String img_path;

    public NewsModel(String id, String title, String description, String img_path) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.img_path = img_path;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImg_path() {
        return img_path;
    }

    public void setImg_path(String img_path) {
        this.img_path = img_path;
    }
}
