package com.androides.simfisica;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.androides.simfisica.SimuladorFisica;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//        setContentView(R.layout.main_layout);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
//		initialize(new SimuladorFisica(), config);

        // Create the layout
        RelativeLayout layout = new RelativeLayout(this);

        // Create the libgdx View
        View gameView = initializeForView(new SimuladorFisica(), config);

        // Do the stuff that initialize() would do for you
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);

        // Create and setup the TextView
        TextView helloText = new TextView(this);
        helloText.setText("Buenas");

        // Add the libgdx view
        layout.addView(gameView);

        // Add the TextView
        RelativeLayout.LayoutParams textViewParams =
                new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT);
        textViewParams.addRule(RelativeLayout.ALIGN_PARENT_TOP);
        textViewParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);

        layout.addView(helloText, textViewParams);

        // Hook it all up
        setContentView(layout);
	}
}
