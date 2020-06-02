package com.androides.simfisica;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class SimuladorFisica extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;
	Barra barra;
//	ShapeRenderer sr;

	int screenWidth, screenHeight;

	double torqueDerecho, torqueIzquierdo, diff;
	double rotacion;
	double aceleracion;

	boolean calcularTorque;
	boolean aceleracionNegativa;

	private Bloque crearBloque(int peso, int tipo) {
		double wb = (barra.getWidth() / 2) * .9f;

		double w = wb / 8;
		double h = screenHeight * .03f;
		return new Bloque(w, h, peso, tipo);
	}

	public void mostrarRegla() {
		barra.setShowRegla(true);
		barra.setShowMarcas(false);
	}

	public void mostrarMarcas() {
		barra.setShowMarcas(true);
		barra.setShowRegla(false);
	}

	public void mostrarNiguno() {
		barra.setShowRegla(true);
		barra.setShowMarcas(false);
	}

	public double getBarraTorqueIzquierdo() {
		return barra.calcularTorqueIzquierdo();
	}

	public double getBarraTorqueDerecho() {
		return barra.calcularTorqueDerecho();
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("background.png");
//		sr = new ShapeRenderer();

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		double width = screenWidth * .70f;
		double height = screenHeight * .05f;

		double x = (screenWidth - width) / 2;
		double y = (screenHeight * .365f) ;

		barra = new Barra(width, height, (int) (x), (int) (y));

//		System.out.println(barra.getWidth());
//		System.out.println((barra.getWidth() / 16));
//		System.out.println(height);

//        for (int i = 0; i < 16; i++) {
//        	double wb = (barra.getWidth() / 2) * .9f;
//
//			double w = wb / 8;
//            double h = screenHeight * .03f;
//            Bloque bloque = new Bloque(w, h,5, (i % 4) + 1);
//            barra.addBloque(bloque, i);
//        }

        barra.addBloque(crearBloque(5, 2), 7);

        torqueDerecho = 0;
        torqueIzquierdo = 0;
        diff = 0;
        rotacion = 0;
        aceleracion = 0;

        calcularTorque = true;
        aceleracionNegativa = false;
	}

	@Override
	public void render () {
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();

		batch.draw(background, 0, 0, screenWidth, screenHeight);

		barra.render(batch);

		if (calcularTorque) {
			torqueIzquierdo = barra.calcularTorqueIzquierdo();
			torqueDerecho = barra.calcularTorqueDerecho();

			diff = torqueIzquierdo - torqueDerecho;

			System.out.println("Valores ----------------------------------------");
			System.out.println("Izq " + torqueIzquierdo);
			System.out.println("Der " + torqueDerecho);
			System.out.println("diff " + diff);
			System.out.println("rot " + rotacion);

			aceleracion = Math.pow(diff, 0.9);

			calcularTorque = false;
			aceleracionNegativa = (aceleracion > 0) ? false : true;
		}

        if (aceleracion != 0) {
			rotacion += aceleracion * Gdx.graphics.getDeltaTime();

			double val = 0.5 * Gdx.graphics.getDeltaTime();
			if (aceleracionNegativa) {
				aceleracion += val;
				if (aceleracion > 0) {
					aceleracion = 0;
				}
			} else {
				aceleracion -= val;
				if (aceleracion < 0) {
					aceleracion = 0;
				}
			}


			if (rotacion > 22) { // limite izquierd
				rotacion = 22;
				aceleracion = 0;
			} else if (rotacion < -22) { // limite derecho
				rotacion = -22;
                aceleracion = 0;
			}

			barra.setRotation(rotacion);

		}

		batch.end();

	}

	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		barra.dispose();
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
