package com.androides.simfisica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Barra {
    Texture barraImg;
    Texture reglaImg;
    Texture marcaImg;

    boolean showBarra;
    boolean showRegla;
    boolean showMarca;

    double width;
    double height;

    double rotation;

    public Barra(String barraPath, String reglaPath, String marcaPath, double w, double h) {
        this.barraImg = new Texture(barraPath);
        this.reglaImg = new Texture(reglaPath);
        this.marcaImg = new Texture(marcaPath);

        this.showBarra = true;
        this.showRegla = false;
        this.showMarca = false;

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

    public boolean isShowBarra() { return showBarra;}
    public boolean isShowRegla() { return showRegla;}
    public boolean isShowMarca() { return showMarca;}

    // setters

    public void setBarraImg(String path) { barraImg = new Texture(path); }
    public void setReglaImg(String path) { reglaImg = new Texture(path); }
    public void setMarcaImg(String path) { marcaImg = new Texture(path); }

    public void setWidth(double val) { width = val; }
    public void setHeight(double val) { height = val; }
    public void setRotation(double val) { rotation = val; }

    public void setShowBarra(boolean val) { showBarra = val; }
    public void setShowRegla(boolean val) { showRegla = val; }
    public void setShowMarca(boolean val) { showMarca = val; }

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

    public void render(SpriteBatch batch) {

        int x = (Gdx.graphics.getWidth() / 2) - ((int) width / 2);
        int y = (Gdx.graphics.getHeight() / 2) - ((int) height / 2);

        int originX = ((int) width / 2);
        int originY = ((int) height / 2);

//        int x = originX + ((int) width / 2);
//        int y = originY + ((int) height / 2);

        float scaleX = 1;
        float scaleY = 1;

        batch.begin();

        batch.draw(barraImg,
                x, y,
                originX, originY,
                (int) width, (int) height,
                scaleX, scaleY,
                (float) rotation,
                0, 0,
                barraImg.getWidth(), barraImg.getHeight(),
                false, false);

        batch.end();
    }

    public void dispose() {
        barraImg.dispose();
        reglaImg.dispose();
        marcaImg.dispose();
    }
}
