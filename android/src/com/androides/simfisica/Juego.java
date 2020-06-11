package com.androides.simfisica;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.ClipDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;

import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import androidx.fragment.app.Fragment;

public class Juego extends Fragment {

    JuegoListener mListener;
    private static final String OPCION_MOSTRAR = "opcion_mostrar";
    private static final int REGLA = 1;
    private static final int MARCA = 2;
    private static final int NINGUNO = 3;
    public int mRadioButtonChoice = NINGUNO;

    //trae a la clase juego una variable para poder aceder a Simulador fisica
    SimuladorFisica juego;

    public Juego() {

    }

    Thread myThread;
    Runnable myRunnable;

    public static Juego newInstance(int choice) {

        Juego fragment = new Juego();
        Bundle arguments = new Bundle();
        arguments.putInt(OPCION_MOSTRAR, choice);
        fragment.setArguments(arguments);

        return fragment;
    }

    interface JuegoListener{
        SimuladorFisica getJuego();
    }

    //titulo de los objetos
    TextView tkg5,tkg10,tkg15,tkg20;
    //Checkbox de fuerza masa y nivel
    CheckBox fuerza,masa,nivel;
    /*valores que tomaran la torca de la barra
    @valor fi representa la torca del lado izquierdo
    @valor fi representa la torca del lado izquierdo
     */
    TextView fi,fd;
    //botton que servira para quitar las barras del nivel
    Button limpia;

    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        juego = mListener.getJuego();

        myRunnable = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000); // Waits for 1 second (1000 milliseconds)
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    fi.post(new Runnable() {
                        @Override
                        public void run() {
                            fi.setText(String.valueOf(juego.getBarraTorqueIzquierdo()));
                            fd.setText(String.valueOf(juego.getBarraTorqueDerecho()));
                        }});
                }
            }
        };

        myThread = new Thread(myRunnable);
        myThread.start();

        // infla la pantalla de juego en el fragment
        final View rootView = inflater.inflate(R.layout.pantalla_juego, container, false);

        //asigna la variable limpia al boton
        limpia = rootView.findViewById(R.id.reset);
        //asigna los valores a los textView
        fi = rootView.findViewById(R.id.fi);
        fd = rootView.findViewById(R.id.fd);
        //Checkbox
        fuerza = rootView.findViewById(R.id.checkbox_fuerzaObj);
        masa = rootView.findViewById(R.id.checkbox_valorMasa);
        nivel = rootView.findViewById(R.id.checkbox_nivel);

        //asigna las variables de los titulos a los TextView
        tkg5 = rootView.findViewById(R.id.tkg5);
        tkg10 = rootView.findViewById(R.id.tkg10);
        tkg15 = rootView.findViewById(R.id.tkg15);
        tkg20 = rootView.findViewById(R.id.tkg20);

        //imagen sobre la barra
        final RadioGroup radioGroup = rootView.findViewById(R.id.radio_group);

        final CheckBox checkFuerzaObj = rootView.findViewById(R.id.checkbox_fuerzaObj);
        final CheckBox checkMasa = rootView.findViewById(R.id.checkbox_valorMasa);
        final CheckBox checkNivel = rootView.findViewById(R.id.checkbox_nivel);

        masa.setOnClickListener(new View.OnClickListener() {
            @Override
            //permite la visualizacion de los titulos de los pesos
            public void onClick(View v) {
                if(masa.isChecked()){
                    tkg5.setVisibility(View.VISIBLE);
                    tkg10.setVisibility(View.VISIBLE);
                    tkg15.setVisibility(View.VISIBLE);
                    tkg20.setVisibility(View.VISIBLE);
                } else{
                    tkg5.setVisibility(View.INVISIBLE);
                    tkg10.setVisibility(View.INVISIBLE);
                    tkg15.setVisibility(View.INVISIBLE);
                    tkg20.setVisibility(View.INVISIBLE);
                }
            }
        });

        //funcion que permite ver y actualizar el torque
        fuerza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            if(fuerza.isChecked()){
                fi.setText(String.valueOf(juego.getBarraTorqueIzquierdo()));
                fd.setText(String.valueOf(juego.getBarraTorqueDerecho()));
                fi.setVisibility(View.VISIBLE);
                fd.setVisibility(View.VISIBLE);
            }
            else{
                fi.setVisibility(View.INVISIBLE);
                fd.setVisibility(View.INVISIBLE);
                fi.setText("0");
                fd.setText("0");
            }

            }
        });

        /* funcion a futuro del nivel
        nivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nivel.isChecked()){
                    Log.d("myTag","Nivel");
                }
            }
        });*/


        //Checa si hay un RadioButton seleccionado
        if (getArguments().containsKey(OPCION_MOSTRAR)) {
            mRadioButtonChoice = getArguments().getInt(OPCION_MOSTRAR);

            if (mRadioButtonChoice != NINGUNO) {
                radioGroup.check(radioGroup.getChildAt(mRadioButtonChoice).getId());
            }
        }

        //Cuando el usuario selecciona alguna opción del menú Mostrar
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);

                switch (index){
                    case MARCA:
                        //Agrega método que dibuje la MARCA
                        Log.d("myTag", "Marca");
                        mRadioButtonChoice = MARCA;
                        juego.mostrarMarcas();
                        break;

                    case REGLA:
                        //Agrega método que dibuje la regla
                        mRadioButtonChoice = REGLA;
                        juego.mostrarRegla();
                        break;
                }
            }
        });

        //funcion que borra los ladrillos de la barra
        rootView.findViewById(R.id.reset).setOnClickListener(new View.OnClickListener() {
            @Override
            //manda llamar la funcion BorrarBloques del la variable juego que acessa a SimuladorFisica
            public void onClick(View v) {
                juego.borrarBloques();
            }
        });

        return rootView;
    }

    @Override
    public void onAttach(Context context) {

        super.onAttach(context);
        if (context instanceof JuegoListener) {
            mListener = (JuegoListener) context;
        } else {
            throw new ClassCastException(context.toString() + getResources().getString(R.string.exception_message));

        }
    }
}
