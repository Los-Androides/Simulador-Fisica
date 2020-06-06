package com.androides.simfisica;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SimuladorFisica extends ApplicationAdapter {

	public interface SimuladorFisicaListener {
		void actualizarTorques();
	}

	private SimuladorFisicaListener mListener;

	private SpriteBatch batch;
	private Texture background;
	private Texture cuadroBlanco;
	private Texture bloquesImg[];
	private Barra barra;

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

    /**
     * crear un nuevo bloque bloque dentro del simulador
     * @param tipo      el tipo de bloque que se quiere crear
     * @param x         posicion en el eje x
     * @param y         posicion en el eje y
     * @param enBarra   bandera con la que puedes saber si el bloque se encuentra en la barra
     * @return
     */
	private Bloque crearBloque(int tipo, int x, int y, boolean enBarra) {
	    // la barra tiene 16 divisiones
        // primero se calcula la mitad de cada barra ya que en medio tiene un espacio que no es del mismo tamaño que de las otras divisiones
        // luego calcula el ancho de cada división
		double wb = (barra.getWidth() / 2) * .9f;
		double w = wb / 8;
		double h = screenHeight * .03f;
		return new Bloque(w, h, tipo, x, y, enBarra);
	}

    /**
     *  muestra la imagen de la barra
     */
	public void mostrarRegla() {
		barra.setShowRegla(true);
		barra.setShowMarcas(false);
	}

    /**
     * muestra la imagen de las marcas
     */
	public void mostrarMarcas() {
		barra.setShowMarcas(true);
		barra.setShowRegla(false);
	}

    /**
     * muestra la imagen normal de la barra
     */
	public void mostrarNiguno() {
		barra.setShowRegla(false);
		barra.setShowMarcas(false);
	}

    /**
     * borra todos los bloques que estén en la barra
     */
	public void borrarBloques() {
		for (int i = 0; i < 16; i++) {
			barra.quitarBloque(i);
		}
		calcularTorque();
	}

    /**
     * determinar si muestra las marcas para poder ver si la barra está balanceada
     * @param val   determina si se muestran las marcas o no
     */
	public void mostarNivel(boolean val) {
	    barra.setShowNivel(val);
    }

    /**
     * regresa el valor del torque izquierdo de la barra
     * @return valor del torque izquierdo de la barra
     */
	public double getBarraTorqueIzquierdo() {
		return barra.calcularTorqueIzquierdo();
	}

    /**
     * regresa el valor del torque derecho de la barra
     * @return valor del torque derecho de la barra
     */
	public double getBarraTorqueDerecho() {
		return barra.calcularTorqueDerecho();
	}

    /**
     * agrega un bloque a la barra
     * @param pos   posicion del nuevo bloque en la barra
     * @param tipo  tipo de bloque
     */
	private void agregarBloqueBarra(int pos, int tipo) {
		barra.addBloque(crearBloque(tipo, 0, 0, true), pos);
		calcularTorque = true;
	}

    /**
     * checa si, cuando se está arrastrando un bloque para agregarlo a la barra, se encuentra en alguna posición válida para agregarlo a la barra
     */
	private void checarSiSeAgregaBloque() {
		double espacio = barra.getWidth() / 16;

		for (int i = 0; i < 16; i++) {
			if (barra.getPosX() + (espacio * i) <= bloqueX && bloqueX < barra.getPosX() + (espacio * (i + 1))) {
				agregarBloqueBarra(i, tipoBloqueDrag);
			}
		}
	}

    /**
     * dibuja un bloque si es que se está arrastrando para agregarlo a la barra
     */
	public void dibujarBloque() {
	    // consigue la posicion de donde se esté presionando en la pantalla
        // en la posicion y, el input considera y = 0 en la esquina superior izquierda,
        // mientras que para dibujar se considera la esquina superior derecha, por eso es la resta
		bloqueX = Gdx.input.getX();
		bloqueY  = screenHeight - Gdx.input.getY();

		// con el tipo del bloque consigue la imagen que se debe dibujar
		int pos = tipoBloqueDrag - 1;
		double w = widthBloque;
		double h = heightBloque;
		batch.draw(bloquesImg[pos], (int) (bloqueX - (w / 2)), (int) (bloqueY - (h - 2)), (int) (w), (int) (h));

		// aquí se dibuja un cuadro blanco para señalarle al usuario donde se colocaría el bloque si deja de presionar la pantalla
		double espacio = barra.getWidth() / 16;
		for (int i = 0; i < 16; i++) {
			if (barra.getPosX() + (espacio * i) <= bloqueX && bloqueX < barra.getPosX() + (espacio * (i + 1))) {

				double wb = (barra.getWidth() / 2) * .9f;
				double cuadroWidth = wb / 8;
				double cuadroHeight = barra.getHeight();

				int x;
				int y = barra.getPosY();

				double originX = (barra.getWidth() / 2);
				double originY = barra.getHeight() / 2;

				double val, extra, half = (cuadroWidth / 2);

				// checa si el bloque se colocaría del lado derecho o del izquierdo
				// si está del lado izquierdo, calculara su posición a partir del extremo izquierdo de la barra
				// si está del lado derecho, calculara su posición a partir del extremo derecho de la barra
				if (i < 8) {
					val = ((i) * (int)(cuadroWidth));
					extra = 0;
				} else {
					int posicion = (16 - i);
					val = -(posicion * cuadroWidth);
					half *= -1;
					extra = barra.getWidth();
				}

				// val es para guardar la posicion en la cual se debe dibujar el cuadro
				// half es la mitad del ancho del cuadro, esto para centrarlo
				// extra sirve para poder dibujar los bloques del lado derecho a partir del extremo derecho de la barra
				x = (int) (barra.getPosX() + half + val + extra);

				originX -= x - barra.getPosX();

				batch.draw(cuadroBlanco,
						x, y,
						(int) originX, (int) originY,
						(int) (cuadroWidth), (int) (cuadroHeight),
						1, 1,
						(float) barra.getRotation(),
						0, 0,
						cuadroBlanco.getWidth(), cuadroBlanco.getHeight(),
						false, false);
			}
		}
	}

	/**
	 * se encarga de lo relacionado con arrastar los bloques
	 */
	private void dragAndDrop() {

		// checa si ya hay un bloque arrastrandose
		if (bloqueDrag) {
			// checa si se sigue arrastrando
			// si sí, se dibuja
			// si no, se actualiza que no se esta arrastrando y checa si se debería agregar un nuevo bloque
			if (Gdx.input.isTouched()) {
				dibujarBloque();
			} else {
				bloqueDrag = false;
				checarSiSeAgregaBloque();
			}
		} else {
			// checa si la pantalla está siendo presionada
			// si sí, checa si está siendo presionada sobre algún bloque
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

	/**
	 * calcula el torque de la barra, que tanto tiene que rotar y la velocidad a la cual tiene que rotar
	 */
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
	}

	/**
	 * actualiza la rotación de la barra
	 */
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

		// checa si la barra ya llegó a su máxima rotación, que es cuando topa con el piso
		if (rotacion > 22) { // limite izquierd
			rotacion = 22;
			aceleracion = 0;
		} else if (rotacion < -22) { // limite derecho
			rotacion = -22;
			aceleracion = 0;
		}

		barra.setRotation(rotacion);
	}

	/**
	 * tipo constructor de LibGDX
	 */
	@Override
	public void create () {
		// se crea un SpriteBatch para poder dibujar y se cargan todas las imagenes
		batch = new SpriteBatch();
		background = new Texture("background.png");
		cuadroBlanco = new Texture("cuadro_blanco.png");

		bloquesImg = new Texture[4];
		bloquesImg[0] = new Texture("kg5.png");
		bloquesImg[1] = new Texture("kg10.png");
		bloquesImg[2] = new Texture("kg15.png");
		bloquesImg[3] = new Texture("kg20.png");

		// se consigué el ancho y alto de la pantalla
		screenWidth = Gdx.graphics.getWidth();
		screenHeight = Gdx.graphics.getHeight();

		// se calcula el ancho y alto de la barra, su posición en la pantalla y se crea
		double width = screenWidth * .70f;
		double height = screenHeight * .05f;
		double x = ((screenWidth - width) / 2) - 180;
		double y = (screenHeight * .365f) ;
		barra = new Barra(width, height, (int) (x), (int) (y));

		// se crean 4 bloques, uno de cada tipo, para que el usuario pueda seleccionarlos y agregarlos a la barra
		bloques = new Bloque[4];
		for (int i = 0; i < 4; i++) {
		    double w = screenWidth * .13f;
		    double h = screenHeight * .23f;
			bloques[i] = crearBloque((i + 1), (int) (w * (i + 1)), (int) (screenHeight - h), false);
		}

		// valores relacionados con la rotación de la barra
        torqueDerecho = 0;
        torqueIzquierdo = 0;
        diff = 0;
        rotacion = 0;
        aceleracion = 0;
        angulo = 0;
        calcularTorque = true;
        aceleracionNegativa = false;

        // valores relacionados con el arrastrar los bloques
        bloqueDrag = false;
        tipoBloqueDrag = 0;
        bloqueX = 0;
		bloqueY = 0;
		widthBloque = 0;
		heightBloque = 0;
	}

	/**
	 * se encarga de la lógica dle simulador y de dibujar las cosas en pantalla
	 */
	@Override
	public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        // inicializa el Spritebatch para poder empezar a dibujar
		batch.begin();

		// dibuja fondo
		batch.draw(background, 0, 0, screenWidth, screenHeight);

		// dibuja la barra y todos sus bloques
		barra.render(batch);

		dragAndDrop();

		if (calcularTorque) {
			calcularTorque();
		}

        if (aceleracion != 0) {
        	actualizarRotacion();
		}

        for (int i = 0; i < 4; i++) {
        	bloques[i].render(batch, null,0);
		}

        // termina el Spritebatch
		batch.end();

	}

	/**
	 * se encarga de liberar memoria gráfica
	 */
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
}
