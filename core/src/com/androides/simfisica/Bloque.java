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

//    double peso;
//    double posicion;
//    double fuerza;

    private double peso;

    public Bloque(String bloquePath, double w, double h, double p) {
        this.bloqueImg = new Texture(bloquePath);
        this.width = w;
        this.height = h;
        this.peso = p;
    }

    // getters

    public Texture getBloqueImg() { return bloqueImg; }
    public double getWidth()      { return width; }
    public double getHeight()     { return height; }
    public double getPeso()       { return peso; }

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
        offset = ((offHelp * width));// * (pos + 1));



        double w = this.width * percentage;

        if (pos < 8) {
            val = ((pos) * (int)(width));
            x = (int) (barra.getPosX() + (width / 2) + val + offset);//  + offset);
//            x = (int) (barra.getPosX() + val + (width / 2) + offset);
//            x = (int) (barra.getPosX() + val + (width / 2) + offset);

        } else {
            int posicion = (16 - pos);
            val = (posicion * width);
            x = (int) (barra.getPosX() + barra.getWidth() - (width / 2) + offset - val);
//            x = (int) (barra.getPosX() + barra.getWidth() - (width * 8) + val + offset);
        }

//        System.out.println("\nBloque " + pos);
//        System.out.println("Screen width: " + Gdx.graphics.getWidth() / 2);
//        System.out.println("Screen height: " + Gdx.graphics.getHeight() / 2);
//        System.out.println("OriginX: " + (barra.getPosX() + originX));
//        System.out.println("OriginY: " + (barra.getPosY() - originY));

        originX -= x - barra.getPosX();

        batch.draw(bloqueImg,
                x, y,
                (int) originX, (int) originY,
                (int) w, (int) height,
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
