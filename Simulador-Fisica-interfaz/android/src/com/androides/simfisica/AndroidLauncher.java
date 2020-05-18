package com.androides.simfisica;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.androides.simfisica.SimuladorFisica;

public class AndroidLauncher extends AndroidApplication {
	ImageView tabla,regla;
	CheckBox  masa;
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new SimuladorFisica(), config);


		regla = (ImageView) findViewById(R.id.regla);
		tabla = (ImageView) findViewById(R.id.tabla);
		k10 = (TextView)findViewById(R.id.text10k);
        k20 = (TextView)findViewById(R.id.text20k);
        masa = (CheckBox)findViewById(R.id.checkbox_valorMasa);
	}


	public void reglaMark(View v){
		regla.setVisibility(View.VISIBLE);
		tabla.setVisibility(View.GONE);
	}
	public void tablaMark(View v){
		regla.setVisibility(View.GONE);
		tabla.setVisibility(View.VISIBLE);
	}
	 masa.setOnClickListener(new View.OnClickListener()){
	    @Override
                public void onClick(View v){
	        if(masa.isChecked()){
                k10.setVisibility(View.VISIBLE);
                k20.setVisibility(View.VISIBLE);
            }
	        else {
                k10.setVisibility(View.GONE);
                k20.setVisibility(View.GONE);
            }
        }
    }
}
