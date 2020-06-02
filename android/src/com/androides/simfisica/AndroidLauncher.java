package com.androides.simfisica;


import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

public class AndroidLauncher extends FragmentActivity implements AndroidFragmentApplication.Callbacks, MenuFragment.OnFragmentInteractionListener,Juego.OnFragmentInteractionListener {

    //La opción 3 significa Ninguno en el menú "mostrar" de Juego
    private int mRadioButtonChoice = 3;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
	protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        displayMenuFragment();

    }

    public void displayMenuFragment(){
	    MenuFragment menuFragment = new MenuFragment();
	    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, menuFragment).commit();
    }


    @Override
    public void onButtonClick(String option) {
        Toast.makeText(this, "choice is" + option,Toast.LENGTH_SHORT).show();
        if (option == "Juego"){
            displayJuego();
        }
        if (option == "Quiz"){
            displayQuizFragment();
        }
    }
    public void displayJuego(){

        VistaJuego vistaJuego = new VistaJuego();
       // getSupportFragmentManager().beginTransaction().add(R.id.game_container, vistaJuego).addToBackStack(null).commit();

        Juego juegoFragment = Juego.newInstance(mRadioButtonChoice);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,juegoFragment);
        transaction.addToBackStack(null);
        transaction.commit();



    }

    public void displayQuizFragment(){
        QuizFragment questionFragment = new QuizFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container,questionFragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    @Override
    public void onRadioButtonChoice(int choice) {

    }

    @Override
    public void exit() {

    }
}
