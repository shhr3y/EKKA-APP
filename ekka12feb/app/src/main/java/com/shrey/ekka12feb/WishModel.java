

package com.shrey.ekka12feb;

public class WishModel {
    private String date;
    private String style_name;
    private String style_image;
    private String time;

    public WishModel() {
    }

    public WishModel(String date, String style_name, String style_image, String time) {
        this.date = date;
        this.style_name = style_name;
        this.style_image = style_image;
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStyle_name() {
        return style_name;
    }

    public void setStyle_name(String style_name) {
        this.style_name = style_name;
    }

    public String getStyle_image() {
        return style_image;
    }

    public void setStyle_image(String style_image) {
        this.style_image = style_image;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
