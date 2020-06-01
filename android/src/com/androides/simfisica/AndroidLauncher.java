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

public class AndroidLauncher extends FragmentActivity implements MenuFragment.OnFragmentInteractionListener,Juego.OnFragmentInteractionListener {
	@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
	protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.pantalla_principal);
        displayMenuFragment();

    }

    public void displayMenuFragment(){
	    MenuFragment menuFragment = new MenuFragment();
	    getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, menuFragment).commit();
    }


    @Override
    public void onButtonClick(String option) {
        Toast.makeText(this, "choice is" + option,Toast.LENGTH_SHORT).show();
        if (option == "Quiz"){
            displayQuizFragment();
        }
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
}
