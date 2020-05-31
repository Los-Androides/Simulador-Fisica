package com.androides.simfisica;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class Juego extends Fragment {

    OnFragmentInteractionListener mListener;
    private static final String OPCION_MOSTRAR = "opcion_mostrar";
    private static final int REGLA = 0;
    private static final int MARCA = 1 ;
    private static final int NINGUNO = 2;
    public int mRadioButtonChoice = NINGUNO;

    public Juego() {
        // Required empty public constructor
    }

    public static Juego newInstance() {
        Juego fragment = new Juego();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    interface OnFragmentInteractionListener{
        void onRadioButtonChoice(int choice);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the pantalla_juego for this fragment
        final View rootView = inflater.inflate(R.layout.pantalla_juego, container, false);

        final RadioGroup radioGroup= rootView.findViewById(R.id.radio_group);


        if(getArguments().containsKey(OPCION_MOSTRAR)){
            mRadioButtonChoice=getArguments().getInt(OPCION_MOSTRAR);

            if(mRadioButtonChoice != NINGUNO){
                radioGroup.check(radioGroup.getChildAt(mRadioButtonChoice).getId());
            }
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                View radioButton = radioGroup.findViewById(checkedId);
                int index = radioGroup.indexOfChild(radioButton);


                switch (index){

                    case REGLA:
                        //Agrega método que dibuje la regla
                        mRadioButtonChoice = REGLA;
                        mListener.onRadioButtonChoice(REGLA);
                        break;

                    case MARCA:
                        //Agrega método que dibuje la MARCA
                        mRadioButtonChoice = MARCA;
                        mListener.onRadioButtonChoice(MARCA);
                        break;

                    default:

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
