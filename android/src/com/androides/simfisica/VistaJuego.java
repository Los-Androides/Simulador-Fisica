package com.androides.simfisica;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

public class VistaJuego extends AndroidApplication {

    View vistaJuego;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // return the GLSurfaceView on which libgdx is drawing game stuff
        vistaJuego = initializeForView(new SimuladorFisica());
        return vistaJuego;
    }

    public View getVistaJuego() {
        return vistaJuego;
    }

}
