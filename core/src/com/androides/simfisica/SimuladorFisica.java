package com.androides.simfisica;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;

public class SimuladorFisica extends  ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Barra barra;
//	private ShapeRenderer sr;

	int screenWidth, screenHeight;

	@Override
	public void create () {
		batch = new SpriteBatch();
//		sr = new ShapeRenderer();
		background = new Texture("background.png");

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		double width = screenWidth * .70f;
		double height = screenHeight * .05f;

		double x = (screenWidth - width) / 2;
		double y = (screenHeight * .365f) ;

//		Gdx.graphics.getHeight() * .34f

		barra = new Barra("tabla.png", "regla.png", "base.png", "base.png", width, height, (int) (x), (int) (y));

		System.out.println(barra.getWidth());
		System.out.println((barra.getWidth() / 16));
		System.out.println(height);

        for (int i = 0; i < 16; i++) {
        	double wb = (barra.getWidth() / 2) * .9f;

//			double w = (barra.getWidth() / 2);//* .7f;
			double w = wb / 8;//* .7f;
            double h = screenHeight * .05f;
            Bloque bloque = new Bloque("kg5.png", w, h,5 / 9.8);
            barra.addBloque(bloque, i);
        }
	}

	@Override
	public void render () {
//		Gdx.gl.glClearColor(1, 1, 0, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();

		batch.draw(background, 0, 0, screenWidth, screenHeight);

		barra.render(batch);

		batch.end();
//		System.out.println("hola");
	}

	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		barra.dispose();
//		sr.dispose();
	}

//	private OrthographicCamera cam;
//	private ShapeRenderer sr;
//	private Vector3 pos;
//
//	public void create() {
//		sr = new ShapeRenderer();
//		cam = new OrthographicCamera();
//		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
//
//		pos = new Vector3(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
//	}
//
//	public void render() {
//
//		// Logic
//
//		cam.update();
//
//		if (Gdx.input.isTouched()) {
//			pos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
//			cam.unproject(pos);
//		}
//
//		// Drawing
//
//		Gdx.gl.glClearColor(1, 1, 1, 1);
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//
//		sr.begin(ShapeRenderer.ShapeType.Filled);
//		sr.setColor(Color.GREEN);
//		sr.circle(pos.x, pos.y, 64);
//		sr.end();
//
//	}
//
//	public void dispose() {
//		sr.dispose();
//	}

//    int fps = 50;
//    // time for each tick in nano segs
//    double timeTick = 1000000000 / fps;
//    // initializing delta
//    double delta = 0;
//    // define now to use inside the loop
//    long now;
//    // initializing last time to the computer time in nanosecs
//    long lastTime = System.nanoTime();
//        while (running) {
//        // setting the time now to the actual time
//        now = System.nanoTime();
//        // acumulating to delta the difference between times in timeTick units
//        delta += (now - lastTime) / timeTick;
//        // updating the last time
//        lastTime = now;
//
//        // if delta is positive we tick the game
//        if (delta >= 1) {
//            try {
//                tick();
//                render();
//                delta--;
//            } catch (SQLException ex) {
//                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }

}
