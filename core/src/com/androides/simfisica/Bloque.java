package com.androides.simfisica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.loader.G3dModelLoader;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Bloque {
    Texture bloqueImg;

    private double width;
    private double height;

    private double peso;
    private int tipo;

    private int posX;
    private int posY;

    private boolean estaEnBarra;

    public Bloque(double w, double h, int num, int x, int y, boolean enBarra) {
        this.width = w;
        this.height = h;

        this.tipo = num;

        this.posX = x;
        this.posY = y;

        this.estaEnBarra = enBarra;

        String path = "";

        switch (num) {
            case 1:
                path = "kg5.png";
                this.peso = 5;
                break;
            case 2:
                path = "kg10.png";
                this.peso = 10;
                break;
            case 3:
                path = "kg15.png";
                this.peso = 15;
                break;
            case 4:
                path = "kg20.png";
                this.peso = 20;
                break;
        }

        this.bloqueImg = new Texture(path);
    }

    // getters

    public Texture getBloqueImg() { return this.bloqueImg; }
    public double getWidth()      { return this.width; }
    public double getHeight()     { return this.height * this.tipo; }
    public double getPeso()       { return this.peso * this.tipo; }
    public int getTipo()          { return this.tipo; }

    // setters

    public void setBloqueImg (String path) { this.bloqueImg = new Texture(path); }
    public void setWidth     (double val)  { this.width = val; }
    public void setHeight    (double val)  { this.height = val; }
    public void setMasa      (double val)  { this.peso = val; }

    // methods

    public boolean checarSiEstaSeleccionado(int pointerX, int pointerY) {

        if (this.posX < pointerX && pointerX < (this.posX + this.width) && this.posY < pointerY && pointerY < (this.posY + this.height * this.tipo)) {
            return true;
        }
        return false;
    }

    public void render(SpriteBatch batch, Barra barra, int pos) {

        if (this.estaEnBarra) {
            this.posY = barra.getPosY() + (int) barra.getHeight();

            double originX = (barra.getWidth() / 2);
            double originY = -(barra.getHeight() / 2);

            double percentage = .75f;
            double val = 0;

            double offset = 0;
            double offHelp = 0;
            offHelp = (1f - percentage) / 2;
            offset = ((offHelp * this.width));

            double extra;
            double half = (this.width / 2);
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

            this.posX = (int) (barra.getPosX() + half + val + offset + extra);

            originX -= this.posX - barra.getPosX();

            batch.draw(bloqueImg,
                    this.posX, this.posY,
                    (int) originX, (int) originY,
                    (int) w, (int) (this.height * this.tipo),
                    1, 1,
                    (float) barra.getRotation(),
                    0, 0,
                    this.bloqueImg.getWidth(), this.bloqueImg.getHeight(),
                    false, false);
        } else {
            batch.draw(this.bloqueImg, this.posX, this.posY, (int) this.width, (int) (this.height * this.tipo));
        }

    }

    public void dispose() {
        this.bloqueImg.dispose();
    }
}
