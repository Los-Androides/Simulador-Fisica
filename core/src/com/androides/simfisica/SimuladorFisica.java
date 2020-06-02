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

	public interface SimuladorFisicaListener {
		void actualizarTorques();
	}

	private SimuladorFisicaListener mListener;

	private SpriteBatch batch;
	private Texture background;
	private Texture bloquesImg[];
	private Barra barra;
//	ShapeRenderer sr;

	private int screenWidth, screenHeight;

	private double torqueDerecho, torqueIzquierdo, diff;
	private double rotacion;
	private double aceleracion;
	private double angulo;

	private boolean calcularTorque;
	private boolean aceleracionNegativa;

	private Bloque bloques[];

	private boolean bloqueDrag;
	private int tipoBloqueDrag;
	private int bloqueX;
	private int bloqueY;
	private double widthBloque;
	private double heightBloque;

	private Bloque crearBloque(int tipo, int x, int y, boolean enBarra) {
		double wb = (barra.getWidth() / 2) * .9f;
		double w = wb / 8;
		double h = screenHeight * .03f;
		return new Bloque(w, h, tipo, x, y, enBarra);
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
		barra.setShowRegla(false);
		barra.setShowMarcas(false);
	}

	public void mostarNivel(boolean val) {
	    barra.setShowNivel(val);
    }

	public double getBarraTorqueIzquierdo() {
		return barra.calcularTorqueIzquierdo();
	}

	public double getBarraTorqueDerecho() {
		return barra.calcularTorqueDerecho();
	}

	private void agregarBloqueBarra(int pos, int tipo) {
		barra.addBloque(crearBloque(tipo, 0, 0, true), pos);
		calcularTorque = true;
	}

	private void checarSiSeAgregaBloque() {
		double espacio = barra.getWidth() / 16;

		for (int i = 0; i < 16; i++) {
			if (barra.getPosX() + (espacio * i) <= bloqueX && bloqueX < barra.getPosX() + (espacio * (i + 1))) {
				agregarBloqueBarra(i, tipoBloqueDrag);
			}
		}

	}

	public void dibujarBloque() {
		bloqueX = Gdx.input.getX();
		bloqueY  = screenHeight - Gdx.input.getY();
		int pos = tipoBloqueDrag - 1;
		double w = widthBloque;//bloques[pos].getWidth();
		double h = heightBloque;//bloques[pos].getHeight();
		batch.draw(bloquesImg[pos], (int) (bloqueX - (w / 2)), (int) (bloqueY - (h - 2)), (int) (w), (int) (h));
	}

	private void dragAndDrop() {

		if (bloqueDrag) {
			if (Gdx.input.isTouched()) {
				dibujarBloque();
			} else {
				bloqueDrag = false;
				checarSiSeAgregaBloque();
			}
		} else {
			if (Gdx.input.isTouched()) {

				int x = Gdx.input.getX();
				int y  = screenHeight - Gdx.input.getY();
				for (int i = 0; i < 4; i++) {
					if (bloques[i].checarSiEstaSeleccionado(x, y)) {
						bloqueDrag = true;
						tipoBloqueDrag = bloques[i].getTipo();
						widthBloque = bloques[i].getWidth();
						heightBloque = bloques[i].getHeight();
						dibujarBloque();
					}
				}
			}
		}
	}

	private void calcularTorque() {
		torqueIzquierdo = barra.calcularTorqueIzquierdo();
		torqueDerecho = barra.calcularTorqueDerecho();

		diff = torqueIzquierdo - torqueDerecho;

		angulo = Math.pow(Math.abs(diff), 1.25);
		aceleracion = (angulo - barra.getRotation()) / 8;
		int signo = (diff < 0) ? -1 : 1;
		aceleracion *= signo;
		angulo *= signo;

		calcularTorque = false;
		aceleracionNegativa = (aceleracion > 0) ? false : true;

		System.out.println("Valores ----------------------------------------");
		System.out.println("Izq " + torqueIzquierdo);
		System.out.println("Der " + torqueDerecho);
		System.out.println("diff " + diff);
		System.out.println("rot " + rotacion);
		System.out.println("ang " + angulo);
		System.out.println("ace " + aceleracion);

//		mListener.actualizarTorques();
	}

	private void actualizarRotacion() {
		rotacion += aceleracion * Gdx.graphics.getDeltaTime();

		if (aceleracionNegativa) {
			if (rotacion <= angulo) {
				aceleracion = 0;
			}
		} else {
			if (rotacion >= angulo) {
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

	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("background.png");
		bloquesImg = new Texture[4];

		bloquesImg[0] = new Texture("kg5.png");
		bloquesImg[1] = new Texture("kg10.png");
		bloquesImg[2] = new Texture("kg15.png");
		bloquesImg[3] = new Texture("kg20.png");
//		sr = new ShapeRenderer();

		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		double width = screenWidth * .70f;
		double height = screenHeight * .05f;

		double x = ((screenWidth - width) / 2) - 180;
		double y = (screenHeight * .365f) ;

		barra = new Barra(width, height, (int) (x), (int) (y));

		bloques = new Bloque[4];

		for (int i = 0; i < 4; i++) {
			bloques[i] = crearBloque((i + 1), 300 * (i + 1), 750, false);
		}

        torqueDerecho = 0;
        torqueIzquierdo = 0;
        diff = 0;
        rotacion = 0;
        aceleracion = 0;
        angulo = 0;

        calcularTorque = true;
        aceleracionNegativa = false;

        bloqueDrag = false;
        tipoBloqueDrag = 0;
        bloqueX = 0;
		bloqueY = 0;
		widthBloque = 0;
		heightBloque = 0;
	}

	@Override
	public void render () {
//		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		batch.begin();

		batch.draw(background, 0, 0, screenWidth, screenHeight);

		dragAndDrop();

		if (calcularTorque) {
			calcularTorque();
		}

        if (aceleracion != 0) {
        	actualizarRotacion();
		}

		barra.render(batch);

        for (int i = 0; i < 4; i++) {
        	bloques[i].render(batch, null,0);
		}

		batch.end();

	}

	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
		barra.dispose();
		for (int i = 0; i < 4; i++) {
			bloques[i].dispose();
			bloquesImg[i].dispose();
		}
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
