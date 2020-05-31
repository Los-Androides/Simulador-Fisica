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
    Texture marcaImg;
    Texture soporteImg;

    boolean showBarra;
    boolean showRegla;
    boolean showMarca;
    int j = 0;

    double width;
    double height;

    double rotation;

    Bloque bloques[];

    public Barra(String barraPath, String reglaPath, String marcaPath, String soportePath, double w, double h, int x, int y) {
        this.barraImg = new Texture(barraPath);
        this.reglaImg = new Texture(reglaPath);
        this.marcaImg = new Texture(marcaPath);
        this.soporteImg = new Texture(soportePath);

        this.bloques = new Bloque[16];

        this.showBarra = true;
        this.showRegla = false;
        this.showMarca = false;

        this.width = w;
        this.height = h;

        this.posX = x;
        this.posY = y;

        this.rotation = 0;
    }

    // getters

    public Texture getBarraImg() { return barraImg; }
    public Texture getReglaImg() { return reglaImg; }
    public Texture getMarcaImg() { return marcaImg; }
    public Texture getSoporteImg() { return soporteImg; }

    public int getPosX() { return posX; }
    public int getPosY() { return posY; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }
    public double getRotation() { return rotation; }

    public boolean isShowBarra() { return showBarra;}
    public boolean isShowRegla() { return showRegla;}
    public boolean isShowMarca() { return showMarca;}

    // setters

    public void setBarraImg(String path) { barraImg = new Texture(path); }
    public void setReglaImg(String path) { reglaImg = new Texture(path); }
    public void setMarcaImg(String path) { marcaImg = new Texture(path); }
    public void setSoporteImg(String path) { soporteImg = new Texture(path); }

    public void setPosX(int val) { posX = val; }
    public void setPosY(int val) { posY = val; }
    public void setWidth(double val) { width = val; }
    public void setHeight(double val) { height = val; }
    public void setRotation(double val) { rotation = val; }

    public void setShowBarra(boolean val) { showBarra = val; }
    public void setShowRegla(boolean val) { showRegla = val; }
    public void setShowMarca(boolean val) { showMarca = val; }

    // methods

    public double calcularTorqueIzquierdo() {

        double torque = 0;
        for (int i = 0;  i < bloques.length / 2; i++) {
            torque += bloques[i].getPeso() * (((bloques.length / 2) - i) / 2);
        }
        return torque;
    }

    public double calcularTorqueDerecho() {
        double torque = 0;
        for (int i = 0;  i < bloques.length / 2; i++) {
            torque += bloques[i + (bloques.length / 2)].getPeso() * ((i + 1) / 2);
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

    public void render(SpriteBatch batch) {

        int originX = ((int) width / 2);
        int originY = ((int) height / 2);

        float scaleX = 1;
        float scaleY = 1;

        double widthBase = Gdx.graphics.getWidth() * .17f;
        double heightBase = Gdx.graphics.getHeight() * .34f;

        batch.draw(soporteImg, (int) (posX + originX - (widthBase / 2)), (int) (posY + originY - (widthBase * .9f)), (int) (widthBase ), (int) (heightBase));

        batch.draw(barraImg,
                posX, posY,
                originX, originY,
                (int) width, (int) height,
                scaleX, scaleY,
                (float) rotation,
                0, 0,
                barraImg.getWidth(), barraImg.getHeight(),
                false, false);


        for (int i = 0;  i < bloques.length; i++) {
            bloques[i].render(batch, this, i);
        }

        rotation += 100 * Gdx.graphics.getDeltaTime();
    }

    public void dispose() {
        barraImg.dispose();
        reglaImg.dispose();
        marcaImg.dispose();
        for (int i = 0;  i < bloques.length; i++) {
            bloques[i].dispose();
        }
    }
}
