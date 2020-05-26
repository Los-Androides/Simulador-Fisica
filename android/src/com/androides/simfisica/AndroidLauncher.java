package com.androides.simfisica;

import android.os.Build;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

public class AndroidLauncher extends AppCompatActivity {//AndroidApplication {
	@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
	protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_layout); //aquí le cambio por layout

        GameFragment libgdxFragment = new GameFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();

        //lo cambio por el framelayout que cree en layout
        fragmentManager.beginTransaction().add(R.id.content_framelayout, libgdxFragment).addToBackStack(null).commit();
    }
}
