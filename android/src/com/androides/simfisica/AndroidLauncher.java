package com.androides.simfisica;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

public class AndroidLauncher extends FragmentActivity implements AndroidFragmentApplication.Callbacks, MenuFragment.OnFragmentInteractionListener, Juego.JuegoListener {

    //La opción 3 significa Ninguno en el menú "mostrar" de Juego
    private int mRadioButtonChoice = 3;

    VistaJuego libgdxFragment;

    @Override
	protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        libgdxFragment = new VistaJuego();

        displayMenuFragment();
    }

    // **************************************************
    // Public methods
    // **************************************************

    /**
     * Hace el cambio de hacia el fragmento Menu.
     *
     *
     */
    public void displayMenuFragment(){
	    MenuFragment menuFragment = new MenuFragment();
	    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, menuFragment).commit();
    }

    /**
     * Al seleccionar el boton llama a la funcion que despliega el quiz o juego.
     *
     *
     */
    @Override
    public void onButtonClick(String option) {
        if (option == "Juego"){
            displayJuego();
        }
        if (option == "Quiz"){
            displayQuizFragment();
        }
    }
    /**
     * Hace el cambio de hacia el fragmento quiz.
     *
     *
     */
    public void displayJuego(){
        if (getSupportFragmentManager().findFragmentById(R.id.game_container) == null){
            getSupportFragmentManager().beginTransaction().add(R.id.game_container, libgdxFragment).commit();
        }

        Juego juegoFragment = Juego.newInstance(mRadioButtonChoice);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,juegoFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * Hace el cambio de hacia el fragmento quiz.
     *
     *
     */
    public void displayQuizFragment(){
        QuizFragment questionFragment = new QuizFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,questionFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * Crea el objeto simulador y lo regresa en un fragmento libgdx.
     *
     *
     */
    @Override
    public SimuladorFisica getJuego() {
        return libgdxFragment.getJuego();
    }

    @Override
    public void exit() {}
}
