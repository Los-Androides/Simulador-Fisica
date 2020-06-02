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

    SimuladorFisica juego;

    public Juego() {

    }

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

    //imagenes base
//    ImageView barra;
//    ImageView regla;
    //imagenes de los objetos

    ImageView kg5,kg10,kg15,kg20;
    //titulo de los objetos
    TextView tkg5,tkg10,tkg15,tkg20;
    //distancia de los textView
    TextView l2m,l15m,l1m,l05m;
    TextView r2m,r15m,r1m,r05m;
    CheckBox fuerza,masa,nivel;
    TextView fi,fd;

    Button limpia;


    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        juego = mListener.getJuego();

        // Inflate the pantalla_juego for this fragment
        final View rootView = inflater.inflate(R.layout.pantalla_juego, container, false);

        fi = rootView.findViewById(R.id.fi);
        fd = rootView.findViewById(R.id.fd);
        //Checkbox
        fuerza = rootView.findViewById(R.id.checkbox_fuerzaObj);
        masa = rootView.findViewById(R.id.checkbox_valorMasa);
        nivel = rootView.findViewById(R.id.checkbox_nivel);

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

        fuerza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fuerza.isChecked()){
                    fi.setText(String.valueOf(juego.torqueIzquierdo));
                    fd.setText(String.valueOf(juego.torqueDerecho));
                }
                else{

                    fi.setText("0");
                    fd.setText("0");
                }
            }
        });
        nivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nivel.isChecked()){
                    Log.d("myTag","Nivel");
                }
            }
        });


        //Checas si hay una checkbox seleccionada

        //Checa si hay un RadioButton seleccionado
        if (getArguments().containsKey(OPCION_MOSTRAR)) {
            mRadioButtonChoice = getArguments().getInt(OPCION_MOSTRAR);

            if (mRadioButtonChoice != NINGUNO) {
                radioGroup.check(radioGroup.getChildAt(mRadioButtonChoice).getId());
            }
        }
        //cuando el usuario seleciona algun checkbox


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
//                        mListener.show(MARCA);
                        break;

                    case REGLA:
                        //Agrega método que dibuje la regla

                        mRadioButtonChoice = REGLA;
                        juego.mostrarRegla();
//                        mListener.show(REGLA);
                        break;

                }
            }
        });

        return rootView;
    }

    View.OnLongClickListener longClickListener = new View.OnLongClickListener(){

        @Override
        public boolean onLongClick(View v) {
            ClipData data = ClipData.newPlainText("","");
            View.DragShadowBuilder myShadowBuilder = new View.DragShadowBuilder(v);
            v.startDrag(data,myShadowBuilder,v,0);
            return true;
        }
    };

    View.OnDragListener dragListener = new View.OnDragListener(){
        @Override
        public boolean onDrag(View v, DragEvent event) {
            int dragEvent = event.getAction();
            int id;
            final View view = (View) event.getLocalState();
            switch (dragEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    break;
                case DragEvent.ACTION_DROP:
//                    if(view.getId() == R.id.kg5) {
//                        id = getResources().getIdentifier("5kg","drawable",getPackageName());
//                        I2m.setImageResource(id);
//                    }
//                     else if(view.getId() == R.id.kg10){
//                        id = getResources().getIdentifier("10kg","drawable",getPackageName());
//                        I2m.setImageResource(id);
//                    }
                    break;
            }
            return true;
        }
    };

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
