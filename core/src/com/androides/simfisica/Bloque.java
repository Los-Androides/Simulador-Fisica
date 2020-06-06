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

    /**
     * constructor de Bloque
     * @param w         ancho del bloque
     * @param h         altura del bloque
     * @param type      el tipo del bloque (1 == 5 kg, 2 == 10 kg, 3 == 15 kg, 4 == 20 kg)
     * @param x         posicion del bloque en el eje x
     * @param y         posicion del bloque en el eje y
     * @param enBarra   especifica si se encuentra en la barra
     */
    public Bloque(double w, double h, int type, int x, int y, boolean enBarra) {
        this.width = w;
        this.height = h;

        this.tipo = type;

        this.posX = x;
        this.posY = y;

        this.estaEnBarra = enBarra;

        String path = "";

        // dependiendo del tipo del bloque se decide su peso y la imagen que se debe dibujar
        switch (type) {
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

    /**
     * checa si el usuario está presionando al bloque
     * @param pointerX  posición en el eje x donde el usuario está presionando
     * @param pointerY  posición en el eje y donde el usuario está presionando
     * @return
     */
    public boolean checarSiEstaSeleccionado(int pointerX, int pointerY) {

        if (this.posX < pointerX && pointerX < (this.posX + this.width) && this.posY < pointerY && pointerY < (this.posY + this.height * this.tipo)) {
            return true;
        }
        return false;
    }

    /**
     * dibuja al bloque
     * @param batch Spritebatch donde se va a dibujar la imagen
     * @param barra la barra del simulador
     * @param pos   la posición en la que se encuentra dentro de la barra
     */
    public void render(SpriteBatch batch, Barra barra, int pos) {

        // checa si está en la barra
        // si sí está, dibuja el bloque de acuerdo a la barra
        // si no, lo dibuja de acuerdo a su posición
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

            // checa si el bloque se encuentra del lado derecho o del izquierdo
            // si está del lado izquierdo, calculara su posición a partir del extremo izquierdo de la barra
            // si está del lado derecho, calculara su posición a partir del extremo derecho de la barra
            if (pos < 8) {
                val = ((pos) * (int)(width));
                extra = 0;
            } else {
                int posicion = (16 - pos);
                val = -(posicion * width);
                half *= -1;
                extra = barra.getWidth();
            }

            // val es para guardar la posicion en la cual se debe dibujar el bloque
            // half es la mitad del ancho del bloque, esto para centrarlo
            // offset es para poder centrar correctamente el bloque ya que mide un poco menos que cada división de la barra
            // extra sirve para poder dibujar los bloques del lado derecho a partir del extremo derecho de la barra
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

    // libera la memoria de la imagen
    public void dispose() {
        this.bloqueImg.dispose();
    }
}
