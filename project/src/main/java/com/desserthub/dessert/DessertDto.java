package com.desserthub.dessert;

public class DessertDto {
    private int d_index;
    private String d_name; //이름
    private String d_image; //이미지
    private String d_chara; //특징
    
    public int getD_index() {
        return d_index;
    }
    public void setD_index(int d_index) {
        this.d_index = d_index;
    }
    public String getD_name() {
        return d_name;
    }
    public void setD_name(String d_name) {
        this.d_name = d_name;
    }
    public String getD_image() {
        return d_image;
    }
    public void setD_image(String d_image) {
        this.d_image = d_image;
    }
    public String getD_chara() {
        return d_chara;
    }
    public void setD_chara(String d_chara) {
        this.d_chara = d_chara;
    }
}
