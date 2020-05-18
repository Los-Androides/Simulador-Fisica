package com.androides.simfisica;

import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentActivity;
import com.badlogic.gdx.backends.android.AndroidFragmentApplication;

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
