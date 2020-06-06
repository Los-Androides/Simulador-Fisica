package com.androides.simfisica;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Barra {
    private int posX, posY;

    private Texture barraImg;
    private Texture reglaImg;
    private Texture baseImg;
    private Texture marcasImg;

    private boolean showRegla;
    private boolean showMarcas;

    private double width;
    private double height;

    private double rotation;

    private Bloque bloques[];

    /**
     * constructor de Barra
     * @param w ancho de la barra
     * @param h altura de la barra
     * @param x posicion de la barra en el eje x
     * @param y posicion de la barra en el eje y
     */
    public Barra(double w, double h, int x, int y) {
        // carga todas las imagenes necesarias para la barra
        this.barraImg = new Texture("tabla.png");
        this.reglaImg = new Texture("regla.png");
        this.baseImg = new Texture("base.png");
        this.marcasImg = new Texture("marca.png");

        this.bloques = new Bloque[16];

        // inicializa todos los bloques a nulos
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

    public double getTorqueIzquierdo(){return  calcularTorqueIzquierdo();}
    public double getTorqueDerecho(){return calcularTorqueDerecho();}

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

    /**
     * calcula el torque del lado izquierdo de la barra
     * @return double con el valor del torque izquierdo
     */
    public double calcularTorqueIzquierdo() {

        double torque = 0;
        for (int i = 0;  i < (bloques.length / 2); i++) {
            if (bloques[i] != null) {
                double distancia = ((bloques.length / 2) - i) / 4f;
                torque += bloques[i].getPeso() * (distancia);
            }
        }
        return torque;
    }

    /**
     * calcula el torque del lado derecho de la barra
     * @return double con el valor del torque derecho
     */
    public double calcularTorqueDerecho() {
        double torque = 0;
        for (int i = 0;  i < (bloques.length / 2); i++) {
            int pos = i + (bloques.length / 2);
            double distancia = (i + 1) / 4f;
            if (bloques[pos] != null) {
                torque += bloques[pos].getPeso() * (distancia);
            }
        }
        return torque;
    }

    /**
     * agrega un nuevo bloque a la barra
     * @param bloque    el bloque que se quiere agregar
     * @param pos       posicion en la barra a la cual se quiere agregar el bloque
     * @return          boolean que especifica si se pudo agregar el bloque
     */
    public boolean addBloque(Bloque bloque, int pos) {
        if (this.bloques[pos] == null) {
            this.bloques[pos] = bloque;
            return true;
        }
        return false;
    }

    /**
     * quita un bloque de la barra
     * @param pos   posición de la cual borra el bloque
     * @return      boolean que especifica si se pudo quitar el bloque
     */
    public boolean quitarBloque(int pos) {
        if (this.bloques[pos] != null) {
            this.bloques[pos] = null;
            return true;
        }
        return false;
    }

    /**
     * dibuja las marcas de la barra
     * @param batch Spritebatch para poder dibujar las marcas
     */
    private void dibujarMarcas(SpriteBatch batch) {
        double wb = (this.width / 2) * .9f;
        double w = wb / 8;

        double marcaWidth = Gdx.graphics.getWidth() * .025f;
        double marcaHeight = Gdx.graphics.getHeight() * .1f;

        double originX;
        double originY = (this.height / 2);

        int x;
        int y = (int) (this.posY - marcaHeight);

        double val;
        double half;
        double extra;

        for (int i = 0; i < 16; i++) {
            // checa si la marca se encuentra del lado derecho o del izquierdo
            // si está del lado izquierdo, calculara su posición a partir del extremo izquierdo de la barra
            // si está del lado derecho, calculara su posición a partir del extremo derecho de la barra
            originX = (this.width / 2);
            half = (marcaWidth / 2);
            if (i < 8) {
                val = ((i + 1) * (int)(w));
                extra = 0;

            } else {
                int posicion = (16 - i);
                val = -(posicion * w);
                half *= -1;
                extra = this.width;
            }

            // val es para guardar la posicion en la cual se debe dibujar la marca
            // half es la mitad del ancho de la marca, esto para centrarla
            // extra sirve para poder dibujar las marcas del lado derecho a partir del extremo derecho de la barra
            x = (int) (this.posX + val + half + extra);

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
    }

    /**
     * dibuja la barra
     * @param batch Spritebatch para poder dibujar todas las imagenes de la barra
     */
    public void render(SpriteBatch batch) {

        int originX = ((int) this.width / 2);
        int originY = ((int) this.height / 2);

        float scaleX = 1;
        float scaleY = 1;

        double widthBase = Gdx.graphics.getWidth() * .17f;
        double heightBase = Gdx.graphics.getHeight() * .34f;

        batch.draw(this.baseImg, (int) (this.posX + originX - (widthBase / 2)), (int) (this.posY + originY - (widthBase * .9f)), (int) (widthBase ), (int) (heightBase));

        // checa que se debe dibujar
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

        // dibuja todos los bloques en la barra
        for (int i = 0;  i < this.bloques.length; i++) {
            if (this.bloques[i] != null) {
                this.bloques[i].render(batch, this, i);
            }
        }
    }

    /**
     * libera la memoria de la imagen
     */
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
