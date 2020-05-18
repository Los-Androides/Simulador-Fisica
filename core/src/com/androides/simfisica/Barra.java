package com.androides.simfisica;

import com.badlogic.gdx.graphics.Texture;

public class Barra {
    Texture barraImg;
    Texture reglaImg;
    Texture marcaImg;

    double width;
    double height;

    double rotation;

    public Barra(String barraPath, String reglaPath, String marcaPath, double w, double h) {
        this.barraImg = new Texture(barraPath);
        this.reglaImg = new Texture(reglaPath);
        this.marcaImg = new Texture(marcaPath);

        this.width = w;
        this.height = h;

        this.rotation = 0;
    }

    // getters

    public Texture getBarraImg() { return barraImg; }

    public Texture getReglaImg() { return reglaImg; }

    public Texture getMarcaImg() { return marcaImg; }

    public double getWidth() { return width; }

    public double getHeight() { return height; }

    public double getRotation() { return rotation; }

    // setters

    public void setBarraImg(String path) { barraImg = new Texture(path); }

    public void setReglaImg(String path) { reglaImg = new Texture(path); }

    public void setMarcaImg(String path) { marcaImg = new Texture(path); }

    public void setWidth(double val) { width = val; }

    public void setHeight(double val) { height = val; }

    public void setRotation(double val) { rotation = val; }

    // methods

    public double calcularTorqueIzquierdo() {
        return 0;
    }

    public double calcularTorqueDerecho() {
        return 0;
    }

    public boolean addBloque() {
        return true;
    }
}
