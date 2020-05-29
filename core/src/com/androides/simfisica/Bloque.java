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
        double originX = (barra.getWidth() / 2);
        double originY = -(barra.getHeight() / 2);

        double num = originX;

//        ShapeRenderer sr = new ShapeRenderer();
//
//        sr.begin(ShapeRenderer.ShapeType.Filled);
//		sr.setColor(Color.GREEN);
//		sr.circle((int) (barra.getPosX() + originX), (int) (barra.getPosY() - originY), 32);
//		sr.end();

        double percentage = .75f;
        double extra = (barra.getWidth() / 2) * .1f;

        int x; //= barra.getPosX();
        int y = barra.posY + (int) barra.height;

        double w = width * percentage;

        double val = 0;
        double offset = 0;
        double offHelp = 0;

//        x = (int) (barra.getPosX() + (width / 2));// + val + offset);


        if (pos < 8) {
//            double val = ((pos) * width);
            val = ((pos) * (int)(width));
            offHelp = (1f - percentage) / 2;
            offset = ((offHelp * width) * (pos + 1));
            x = (int) (barra.getPosX() + val + (width / 2) + offset);
//            originX -= val;
//            x = (int) (barra.getPosX() + val + offset);
            //originX -= x - barra.getPosX();//(offset);//(val);// + offset);//((1f - percentage) / 2));
//            originX = 0;
//            originX -= x;
//            originX += (barra.getWidth() / 2);

//            originX -= offset;

//            originX = (-x + (barra.getWidth() / 2));


//            sr.begin(ShapeRenderer.ShapeType.Filled);
//            sr.setColor(Color.BLACK);
//            sr.circle((int) (x), (int) (y), 40);
//            sr.setColor(Color.RED);
//            sr.circle((int) (x + originX), (int) (barra.getPosY() - originY), 50);
//            sr.setColor(Color.BLUE);
//            sr.circle((int) (barra.getPosX()), (int) (barra.getPosY()), 50);
//            sr.end();
        } else {
//            double val = (pos * width);
//            originX -= val + x;
//            x -= 2 * x;
//            x += barra.getPosX() + barra.getWidth() - val;

            val = ((pos - 8) * (int)(width));
            x = (int) (barra.getPosX() + barra.getWidth() - (width * 8) + val);
//            originX -= x;
//            originX -= x;//(offset);//(val);// + offset);//((1f - percentage) / 2));
//            originX = 0;
//            originX -= x;
//            originX += (barra.getWidth() / 2);

//            sr.begin(ShapeRenderer.ShapeType.Filled);
//            sr.setColor(Color.CYAN);
//            sr.circle((int) (x + originX), (int)    (barra.getPosY() - originY), 32);
//            sr.end();
        }

//        originX -= x;//(offset);//(val);// + offset);//((1f - percentage) / 2));
//
//        sr.begin(ShapeRenderer.ShapeType.Filled);
//        sr.setColor(Color.CYAN);
//        sr.circle((int) (x + originX), 400, 32);
//        sr.end();

//        sr.dispose();

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
