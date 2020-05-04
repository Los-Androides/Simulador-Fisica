package com.androides.simfisica;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Widget;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import static com.badlogic.gdx.files.FileHandle.*;

public class Quiz extends ApplicationAdapter {
    private Stage stage;
    private Skin skin;
    private TextField question;
    private Texture placeholder;


    @Override
    public void create () {
        placeholder = new Texture(Gdx.files.internal("Placeholder.jpg"));
        Image image1 = new Image(placeholder);
        image1.setSize(Gdx.graphics.getWidth(),Gdx.graphics.getHeight()/1.5f);
        image1.setPosition(10,300);
        skin = new Skin(Gdx.files.internal("Skin.json"));
        stage = new Stage(new ScreenViewport());
        question = new TextField("La barra se va a inclinar a la izquierda, derecha o quedar centrada?", skin);
        question.setDisabled(true);
        final TextButton siguiente = new TextButton("Siguiente", skin);
        final TextButton button = new TextButton("Centrado", skin);
        final TextButton button2 = new TextButton("Izquierda", skin);
        final TextButton button3 = new TextButton("Derecha", skin);
        final Dialog answerRight = new Dialog("Correcto",skin);
        final Dialog answerWrong = new Dialog("Incorrecto",skin);
        siguiente.setPosition(Gdx.graphics.getWidth() - 200,0);
        Table answerTable = new Table();
        answerTable.setWidth(stage.getWidth());
        answerTable.align(Align.center|Align.bottom);
        answerTable.setDebug(true);
        answerTable.add(question).width(750).pad(10);
        answerTable.row();
        answerTable.add(button2).pad(10);
        answerTable.row();
        answerTable.add(button).pad(10);
        answerTable.row();
        answerTable.add(button3).pad(10);
        answerTable.setFillParent(true);
        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                answerRight.show(stage);
                Timer.schedule(new Timer.Task(){
                    @Override
                    public void run(){
                        answerRight.hide();
                    }
                },2);
            }
        });
        stage.addActor(siguiente);
        stage.addActor(image1);
        stage.addActor(answerTable);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render () {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.act();
        stage.draw();

    }

    @Override
    public void dispose () {

    }
}
