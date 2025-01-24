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

    private String dessertName; //이름
    private String dessertImage; //이미지
    private String dessertChara; //이모지

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getDessertName() {
        return dessertName;
    }
    public void setDessertName(String dessertName) {
        this.dessertName = dessertName;
    }
    public String getDessertImage() {
        return dessertImage;
    }
    public void setDessertImage(String dessertImage) {
        this.dessertImage = dessertImage;
    }
    public String getDessertChara() {
        return dessertChara;
    }
    public void setDessertChara(String dessertChara) {
        this.dessertChara = dessertChara;
    }
}