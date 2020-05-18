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
	SpriteBatch batch;
	Texture img;

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1 	, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(img, 0, 0);
		batch.end();
		System.out.println("hola");
	}

	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
