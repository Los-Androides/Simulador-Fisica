package com.androides.simfisica;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.androides.simfisica.SimuladorFisica;

import android.support.v4.app.FragmentActivity;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

import java.util.ArrayList;

public class AndroidLauncher extends FragmentActivity implements  AndroidFragmentApplication.Callbacks {//AndroidApplication {
	@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

//        AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
//
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        setContentView(R.layout.main_layout);

        getSupportFragmentManager().beginTransaction().add(R.id.content_framelayout, new GameFragment()).commit();
	}

    @Override
    public void exit() {

    }
}
