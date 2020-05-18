package com.androides.simfisica;

import com.badlogic.gdx.graphics.Texture;

public class Bloque {
    Texture bloqueImg;

    double width;
    double height;

//    double peso;
//    double posicion;
//    double fuerza;

    double masa;
    double gravedad;

    public Bloque(String bloquePath, double w, double h, double m) {
        this.bloqueImg = new Texture(bloquePath);
        this.width = w;
        this.height = h;
        this.masa = m;
        this.gravedad = 9.8;
    }

    // getters

    public Texture getBloqueImg() { return bloqueImg; }
    public double getWidth()      { return width; }
    public double getHeight()     { return height; }
    public double getMasa()       { return masa; }

    // setters

    public void setBloqueImg (String path) { bloqueImg = new Texture(path); }
    public void setWidth     (double val)  { width = val; }
    public void setHeight    (double val)  { height = val; }
    public void setMasa      (double val)  { masa = val; }

    // methods

    public double calcularPeso() {
        return masa * gravedad;
    }

}
