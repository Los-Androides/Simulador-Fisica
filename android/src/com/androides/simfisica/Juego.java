package com.androides.simfisica;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

import androidx.fragment.app.Fragment;

public class Juego extends Fragment {

    OnFragmentInteractionListener mListener;
    private static final String OPCION_MOSTRAR = "opcion_mostrar";
    private static final int REGLA = 1;
    private static final int MARCA = 2 ;
    private static final int NINGUNO = 3;
    public int mRadioButtonChoice = NINGUNO;


    public Juego() {

    }

    public static Juego newInstance(int choice){

        Juego fragment = new Juego();
        Bundle arguments = new Bundle();
        arguments.putInt(OPCION_MOSTRAR,choice);
        fragment.setArguments(arguments);

        return fragment;
    }


    interface OnFragmentInteractionListener{
        void onRadioButtonChoice(int choice);
        void onButtonClick(String option);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the pantalla_juego for this fragment
        final View rootView = inflater.inflate(R.layout.pantalla_juego, container, false);


        final RadioGroup radioGroup= rootView.findViewById(R.id.radio_group);
        final CheckBox checkFuerzaObj = rootView.findViewById(R.id.checkbox_fuerzaObj);
        final CheckBox checkMasa = rootView.findViewById(R.id.checkbox_valorMasa);
        final CheckBox checkNivel = rootView.findViewById(R.id.checkbox_nivel);


        if(checkFuerzaObj.isChecked()){
            Log.d("myTag","Guerza");
        }
        if(checkMasa.isChecked()){
            Log.d("myTag","Masa");
        }
        if(checkNivel.isChecked()){
            Log.d("myTag","Nivel");
        }


        //Checas si hay una checkbox seleccionada
        //onCheckboxClicked(rootView);

        //Checa si hay un RadioButton seleccionado
       if(getArguments().containsKey(OPCION_MOSTRAR)){
            mRadioButtonChoice=getArguments().getInt(OPCION_MOSTRAR);

            if(mRadioButtonChoice != NINGUNO){
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

                    case REGLA:
                        //Agrega método que dibuje la regla
                        Log.d("myTag","Regla");

                        mRadioButtonChoice = REGLA;
                        mListener.onRadioButtonChoice(REGLA);
                        break;

                    case MARCA:
                        //Agrega método que dibuje la MARCA
                        Log.d("myTag","Marca");
                        mRadioButtonChoice = MARCA;
                        mListener.onRadioButtonChoice(MARCA);
                        break;

                    default:

                        //Agrega método que dibuje nada
                        Log.d("myTag","Nada");
                        mRadioButtonChoice = NINGUNO;
                        mListener.onRadioButtonChoice(NINGUNO);
                        break;

                }

            }
        });

                return rootView;
    }
    @Override
    public void onAttach(Context context){

        super.onAttach(context);
        if(context instanceof  OnFragmentInteractionListener){
            mListener =(OnFragmentInteractionListener) context;
        }
        else {
            throw new ClassCastException(context.toString() + getResources().getString(R.string.exception_message));
        }
    }

}
