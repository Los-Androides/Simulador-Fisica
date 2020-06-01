package com.androides.simfisica;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class MenuFragment extends Fragment {
    OnFragmentInteractionListener mListener;


    public static MenuFragment newInstance(){
        MenuFragment fragment = new MenuFragment();
        return fragment;
    }

    interface OnFragmentInteractionListener{
        void onButtonClick(String option);
    }

    public MenuFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.pantalla_principal, container, false);
        final Button buttonA = rootView.findViewById(R.id.btn_juego);
        final Button buttonB = rootView.findViewById(R.id.btn_examen);

        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClick("Juego");
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onButtonClick("Quiz");
            }
        });


        return rootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof OnFragmentInteractionListener){
            mListener = (OnFragmentInteractionListener) context;
        }else{
            throw new ClassCastException(context.toString() + "must implement OnFragmentInteractionListener");
        }
    }

}
