package com.desserthub.dessert;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dessert {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dessert_id;

    private String dessert_name; //이름
    private String dessert_image; //이미지
    private String dessert_chara; //특징
    
    public Long getDessert_id() {
        return dessert_id;
    }
    public void setDessert_id(Long dessert_id) {
        this.dessert_id = dessert_id;
    }
    public String getDessert_name() {
        return dessert_name;
    }
    public void setDessert_name(String dessert_name) {
        this.dessert_name = dessert_name;
    }
    public String getDessert_image() {
        return dessert_image;
    }
    public void setDessert_image(String dessert_image) {
        this.dessert_image = dessert_image;
    }
    public String getDessert_chara() {
        return dessert_chara;
    }
    public void setDessert_chara(String dessert_chara) {
        this.dessert_chara = dessert_chara;
    }
    


    
}
