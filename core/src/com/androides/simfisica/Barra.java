package com.androides.simfisica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Barra {
    int posX, posY;

    Texture barraImg;
    Texture reglaImg;
    Texture baseImg;
    Texture marcasImg;

    boolean showRegla;
    boolean showMarcas;

    double width;
    double height;

    double rotation;

    Bloque bloques[];

    public interface SimJuegoInterface {
        public void mostrarRegla(boolean val);
        public void mostrarMarca(boolean val);
        public void mostrarFuerzas(boolean val);
    }

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

    public int getPosX() { return posX; }
    public int getPosY() { return posY; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public double getRotation() { return rotation; }

    public boolean isShowRegla() { return showRegla;}
    public boolean isShowMarcas () { return showMarcas;}

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
        if (bloques[pos] == null) {
            bloques[pos] = bloque;
            return true;
        }
        return false;
    }

    private void dibujarMarcas(SpriteBatch batch) {
        batch.draw(marcasImg, posX, posY);

    }

    public void render(SpriteBatch batch) {

        int originX = ((int) width / 2);
        int originY = ((int) height / 2);

        float scaleX = 1;
        float scaleY = 1;

        double widthBase = Gdx.graphics.getWidth() * .17f;
        double heightBase = Gdx.graphics.getHeight() * .34f;

        batch.draw(baseImg, (int) (posX + originX - (widthBase / 2)), (int) (posY + originY - (widthBase * .9f)), (int) (widthBase ), (int) (heightBase));

        if (showRegla) {

            double ratio = 4.5f;
            double changeInHeight = (height * (ratio - 1));

            batch.draw(reglaImg,
                    posX, (int) (posY - changeInHeight),
                    originX, (int) (originY + changeInHeight),
                    (int) width, (int) (height * ratio),
                    scaleX, scaleY,
                    (float) rotation,
                    0, 0,
                    reglaImg.getWidth(), reglaImg.getHeight(),
                    false, false);
        } else {
            if (showMarcas) {
                dibujarMarcas(batch);
            }
            batch.draw(barraImg,
                    posX, posY,
                    originX, originY,
                    (int) width, (int) height,
                    scaleX, scaleY,
                    (float) rotation,
                    0, 0,
                    barraImg.getWidth(), barraImg.getHeight(),
                    false, false);
        }

        for (int i = 0;  i < bloques.length; i++) {
            if (bloques[i] != null) {
                bloques[i].render(batch, this, i);
            }
        }
    }

    public void dispose() {
        barraImg.dispose();
        reglaImg.dispose();
        baseImg.dispose();
        marcasImg.dispose();
        for (int i = 0;  i < bloques.length; i++) {
            if (bloques[i] != null) {
                bloques[i].dispose();
            }
        }
    }
}
