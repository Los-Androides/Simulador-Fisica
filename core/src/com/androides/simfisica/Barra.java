package com.androides.simfisica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Barra {
    private int posX, posY;

    private Texture barraImg;
    private Texture reglaImg;
    private Texture baseImg;
    private Texture marcasImg;

    private boolean showRegla;
    private boolean showMarcas;
    private boolean showNivel;

    private double width;
    private double height;

    private double rotation;

    private Bloque bloques[];

    public Barra(double w, double h, int x, int y) {
        this.barraImg = new Texture("tabla.png");
        this.reglaImg = new Texture("regla.png");
        this.baseImg = new Texture("base.png");
        this.marcasImg = new Texture("marca.png");

        this.bloques = new Bloque[16];

        for (int i = 0; i < 16; i++) {
            bloques[i] = null;
        }

        this.showRegla = false;
        this.showMarcas = false;
        this.showNivel = false;

        this.width = w;
        this.height = h;

        this.posX = x;
        this.posY = y;

        this.rotation = 0;
    }

    // getters

    public Texture getBarraImg() { return barraImg; }
    public Texture getReglaImg() { return reglaImg; }
    public Texture getBaseImg() { return baseImg; }

    public double getTorqueIzquierdo(){return  calcularTorqueIzquierdo();}
    public double getTorqueDerecho(){return calcularTorqueDerecho();}

    public int getPosX() { return posX; }
    public int getPosY() { return posY; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public double getRotation() { return rotation; }

    public boolean isShowRegla() { return showRegla;}
    public boolean isShowMarcas () { return showMarcas;}
    public boolean isShowNivel () { return showNivel;}

    // setters

    public void setBarraImg(String path) { barraImg = new Texture(path); }
    public void setReglaImg(String path) { reglaImg = new Texture(path); }
    public void setBaseImg(String path) { baseImg = new Texture(path); }

    public void setPosX(int val) { posX = val; }
    public void setPosY(int val) { posY = val; }
    public void setWidth(double val) { width = val; }
    public void setHeight(double val) { height = val; }
    public void setRotation(double val) { rotation = val; }

    public void setShowRegla(boolean val) { showRegla = val; }
    public void setShowMarcas(boolean val) { showMarcas = val; }
    public void setShowNivel(boolean val) { showNivel = val; }

    // methods

    public double calcularTorqueIzquierdo() {

        double torque = 0;
        for (int i = 0;  i < (bloques.length / 2); i++) {
            if (bloques[i] != null) {
                torque += bloques[i].getPeso() * (((bloques.length / 2) - i) / 4f);
            }
        }
        return torque;
    }

    public double calcularTorqueDerecho() {
        double torque = 0;
        for (int i = 0;  i < (bloques.length / 2); i++) {
            int pos = i + (bloques.length / 2);
            if (bloques[pos] != null) {
                torque += bloques[pos].getPeso() * ((pos + 1) / 4);
            }
        }
        return torque;
    }

    public boolean addBloque(Bloque bloque, int pos) {
        if (this.bloques[pos] == null) {
            this.bloques[pos] = bloque;
            return true;
        }
        return false;
    }

    public boolean quitarBloque(int pos) {
        if (this.bloques[pos] != null) {
            this.bloques[pos] = null;
            return true;
        }
        return false;
    }

    private void dibujarMarcas(SpriteBatch batch) {
        double wb = (this.width / 2) * .9f;
        double w = wb / 8;

        double marcaWidth = Gdx.graphics.getWidth() * .025f;
        double marcaHeight = Gdx.graphics.getHeight() * .1f;

        double originX = (width / 2);
        double originY = (this.height / 2);

        int x;
        int y = (int) (this.posY - marcaHeight);

        double val = 0;
        double half = (marcaWidth / 2);
        double extra;

        for (int i = 0; i < 16; i++) {
            originX = (this.width / 2);
            if (i < 8) {
                val = ((i + 1) * (int)(w));
                extra = 0;

            } else {
                int posicion = (16 - i);
                val = -(posicion * w);
//                half *= -1;
                extra = this.width;
            }

            x = (int) (this.posX + val - half + extra);
//            x = (int) (posX + half + val + extra);

            originX -= x - this.posX;

            batch.draw(this.marcasImg,
                    x, y,
                    (int) originX, (int) originY,
                    (int) marcaWidth, (int) (marcaHeight),
                    1, 1,
                    (float) this.rotation,
                    0, 0,
                    this.marcasImg.getWidth(), this.marcasImg.getHeight(),
                    false, false);
        }

//        double percentage = .75f;
//        double val = 0;
//
//        double offset = 0;
//        double offHelp = 0;
//        offHelp = (1f - percentage) / 2;
//        offset = ((offHelp * width));
//
//        double extra;
//        double half = (width / 2);
//        double w = this.width * percentage;
//
//        if (pos < 8) {
//            val = ((pos) * (int)(width));
//            extra = 0;
//
//        } else {
//            int posicion = (16 - pos);
//            val = -(posicion * width);
//            half *= -1;
//            extra = barra.getWidth();
//        }
//
//        x = (int) (barra.getPosX() + half + val + offset + extra);
//
//        originX -= x - barra.getPosX();
//
//        batch.draw(bloqueImg,
//                x, y,
//                (int) originX, (int) originY,
//                (int) w, (int) (height * tipo),
//                1, 1,
//                (float) barra.rotation,
//                0, 0,
//                bloqueImg.getWidth(), bloqueImg.getHeight(),
//                false, false);
    }

    public void render(SpriteBatch batch) {

        int originX = ((int) this.width / 2);
        int originY = ((int) this.height / 2);

        float scaleX = 1;
        float scaleY = 1;

        double widthBase = Gdx.graphics.getWidth() * .17f;
        double heightBase = Gdx.graphics.getHeight() * .34f;

        batch.draw(this.baseImg, (int) (this.posX + originX - (widthBase / 2)), (int) (this.posY + originY - (widthBase * .9f)), (int) (widthBase ), (int) (heightBase));

        if (this.showRegla) {

            double ratio = 4.5f;
            double changeInHeight = (this.height * (ratio - 1));

            batch.draw(this.reglaImg,
                    this.posX, (int) (this.posY - changeInHeight),
                    originX, (int) (originY + changeInHeight),
                    (int) width, (int) (this.height * ratio),
                    scaleX, scaleY,
                    (float) this.rotation,
                    0, 0,
                    this.reglaImg.getWidth(), this.reglaImg.getHeight(),
                    false, false);
        } else {
            if (this.showMarcas) {
                dibujarMarcas(batch);
            }
            batch.draw(this.barraImg,
                    this.posX, this.posY,
                    originX, originY,
                    (int) this.width, (int) this.height,
                    scaleX, scaleY,
                    (float) this.rotation,
                    0, 0,
                    this.barraImg.getWidth(), this.barraImg.getHeight(),
                    false, false);
        }

//        System.out.println(posX);

//        rotation--;

        if (this.showNivel) {

        }

        for (int i = 0;  i < this.bloques.length; i++) {
            if (this.bloques[i] != null) {
                this.bloques[i].render(batch, this, i);
            }
        }
    }

    public void dispose() {
        this.barraImg.dispose();
        this.reglaImg.dispose();
        this.baseImg.dispose();
        this.marcasImg.dispose();
        for (int i = 0;  i < this.bloques.length; i++) {
            if (this.bloques[i] != null) {
                this.bloques[i].dispose();
            }
        }
    }
}
