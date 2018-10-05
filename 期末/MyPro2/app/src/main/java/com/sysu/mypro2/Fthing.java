package com.sysu.mypro2;

/**
 * Created by Gavin on 2018/1/1.
 */

public class Fthing {
    private String name;
    private  String pic;

    public Fthing(String name, String pic) {
        this.name = name;
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
