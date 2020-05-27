package com.androides.simfisica;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

    public void render(SpriteBatch batch, Barra barra) {
        int originX = ((int) barra.width / 2);
        int originY = -((int) barra.height / 2);
        batch.draw(bloqueImg, barra.posX, barra.posY + (int) barra.height, (int) width, (int) height);
        batch.draw(bloqueImg,
                barra.posX, barra.posY + (int) barra.height,
                originX, originY,
                (int) width, (int) height,
                1, 1,
                (float) barra.rotation,
                0, 0,
                bloqueImg.getWidth(), bloqueImg.getHeight(),
                false, false);
    }

}
