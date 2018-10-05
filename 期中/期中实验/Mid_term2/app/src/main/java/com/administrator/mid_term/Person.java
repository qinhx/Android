package com.administrator.mid_term;

/**
 * Created by Gavin on 2017/11/7.
 */

public class Person {
    private String name;
    private String place;
    private String story;
    private String master;
    private  int picId;
    private  int contry;
    public Person(String name, String place, String master, String story, int picId,int contry){
        this.master=master;
        this.name=name;
        this.picId=picId;
        this.place=place;
        this.story=story;
        this.contry=contry;
    }

    public int getContry() {
        return contry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public int getPicId() {
        return picId;
    }

    public void setPicId(int picId) {
        this.picId = picId;
    }
}
