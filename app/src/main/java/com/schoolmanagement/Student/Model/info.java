package com.schoolmanagement.Student.Model;

import android.graphics.drawable.Drawable;

public class info {
    private String name;
    private Drawable image;
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public Drawable getImage() {
        return image;
    }
    public void setImage(Drawable image) {
        this.image = image;
    }
    public info(String name, Drawable image) {
        this.name = name;
        this.image = image;
    }
}
