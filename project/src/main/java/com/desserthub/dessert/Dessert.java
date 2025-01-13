package com.desserthub.dessert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dessert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int d_index;
    private String d_name;
    private String d_image;
    private String d_chara;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
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
