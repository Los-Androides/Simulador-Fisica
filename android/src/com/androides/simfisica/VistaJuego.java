package com.androides.simfisica;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

public class VistaJuego extends AndroidFragmentApplication {

    SimuladorFisica juego;

    /**
     * Constructor de VistaJuego. Aquí se crea el simulador que se utilizará en la aplicación
     */
    public VistaJuego() {
        juego = new SimuladorFisica();
    }

//    @Override

    /**
     * regresa una vista con el simulador en ella
     * @param inflater              cosas de Android
     * @param container             cosas de Android
     * @param savedInstanceState    cosas de Android
     * @return
     */
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // return the GLSurfaceView on which libgdx is drawing game stuff
        return initializeForView(juego);
    }

    /**
     * regresa el simulador que se creó en el constructor
     * @return SimuladorFisica del simulador que se está utilizando
     */
    public SimuladorFisica getJuego() {
        return juego;
    }

}
