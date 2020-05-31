package com.androides.simfisica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Bloque {
    private Texture bloqueImg;

    private double width;
    private double height;

    private double peso;
    private int tipo;

    public Bloque(double w, double h, double p, int num) {
        this.width = w;
        this.height = h;
        this.peso = p;

        this.tipo = num;

        String path = "";

        switch (num) {
            case 1:
                path = "kg5.png";
                break;
            case 2:
                path = "kg10.png";
                break;
            case 3:
                path = "kg15.png";
                break;
            case 4:
                path = "kg20.png";
                break;
        }

        this.bloqueImg = new Texture(path);
    }

    // getters

    public Texture getBloqueImg() { return bloqueImg; }
    public double getWidth()      { return width; }
    public double getHeight()     { return height; }
    public double getPeso()       { return peso * tipo; }

    // setters

    public void setBloqueImg (String path) { bloqueImg = new Texture(path); }
    public void setWidth     (double val)  { width = val; }
    public void setHeight    (double val)  { height = val; }
    public void setMasa      (double val)  { peso = val; }

    // methods

    public void render(SpriteBatch batch, Barra barra, int pos) {

        int x;
        int y = barra.posY + (int) barra.height;

        double originX = (barra.getWidth() / 2);
        double originY = -(barra.getHeight() / 2);

        double percentage = .75f;
        double val = 0;

        double offset = 0;
        double offHelp = 0;
        offHelp = (1f - percentage) / 2;
        offset = ((offHelp * width));

        double extra;
        double half = (width / 2);
        double w = this.width * percentage;

        if (pos < 8) {
            val = ((pos) * (int)(width));
            extra = 0;

        } else {
            int posicion = (16 - pos);
            val = -(posicion * width);
            half *= -1;
            extra = barra.getWidth();
        }

        x = (int) (barra.getPosX() + half + val + offset + extra);

        originX -= x - barra.getPosX();

        batch.draw(bloqueImg,
                x, y,
                (int) originX, (int) originY,
                (int) w, (int) (height * tipo),
                1, 1,
                (float) barra.rotation,
                0, 0,
                bloqueImg.getWidth(), bloqueImg.getHeight(),
                false, false);

    }

    public void dispose() {
        bloqueImg.dispose();
    }
}
