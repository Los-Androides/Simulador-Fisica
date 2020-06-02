package com.androides.simfisica;

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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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

    ImageView barra;
    ImageView regla;
    ImageView kg5,kg10,kg15,kg20;
    TextView tkg5,tkg10,tkg15,tkg20;
    TextView l2m,l15m,l1m,l05m;
    TextView r2m,r15m,r1m,r05m;
    ImageView il5;
    CheckBox fuerza,masa,nivel;
    TextView p;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the pantalla_juego for this fragment
        final View rootView = inflater.inflate(R.layout.pantalla_juego, container, false);
        p = rootView.findViewById(R.id.prueba);
        //Checkbox
        fuerza = rootView.findViewById(R.id.checkbox_fuerzaObj);
        masa = rootView.findViewById(R.id.checkbox_valorMasa);
        nivel = rootView.findViewById(R.id.checkbox_nivel);
        //obtencio de las imagenes de las barras
        barra = rootView.findViewById(R.id.Barra);
        regla = rootView.findViewById(R.id.Regla);
        //textos de los pesos
        tkg5 = rootView.findViewById(R.id.tkg5);
        tkg10 = rootView.findViewById(R.id.tkg10);
        tkg15 = rootView.findViewById(R.id.tkg15);
        tkg20 = rootView.findViewById(R.id.tkg20);
        //imagenes de los pesos
        kg5 = rootView.findViewById(R.id.kg5);
        kg10 = rootView.findViewById(R.id.kg10);
        kg15 = rootView.findViewById(R.id.kg15);
        kg20 = rootView.findViewById(R.id.kg20);
        //listener para el drag and drop
        kg5.setOnLongClickListener(longClickListener);
        kg10.setOnLongClickListener(longClickListener);
        kg15.setOnLongClickListener(longClickListener);
        kg20.setOnLongClickListener(longClickListener);
        //imagen sobre la barra
        p.setOnDragListener(dragListener);
        final RadioGroup radioGroup= rootView.findViewById(R.id.radio_group);
        final CheckBox checkFuerzaObj = rootView.findViewById(R.id.checkbox_fuerzaObj);
        final CheckBox checkMasa = rootView.findViewById(R.id.checkbox_valorMasa);
        final CheckBox checkNivel = rootView.findViewById(R.id.checkbox_nivel);


        if(checkFuerzaObj.isChecked()){
            Log.d("myTag","Fuerza");
        }
        if(checkMasa.isChecked()){
            Log.d("myTag","Masa");
            tkg5.setVisibility(View.VISIBLE);
            tkg10.setVisibility(View.VISIBLE);
            tkg15.setVisibility(View.VISIBLE);
            tkg20.setVisibility(View.VISIBLE);
        }
        if(checkMasa.isChecked() == false){
            tkg5.setVisibility(View.INVISIBLE);
            tkg10.setVisibility(View.INVISIBLE);
            tkg15.setVisibility(View.INVISIBLE);
            tkg20.setVisibility(View.INVISIBLE);
        }

        if(checkNivel.isChecked()){
            Log.d("myTag","Nivel");
        }
        //Checas si hay una checkbox seleccionada

        //Checa si hay un RadioButton seleccionado
       if(getArguments().containsKey(OPCION_MOSTRAR)){
            mRadioButtonChoice=getArguments().getInt(OPCION_MOSTRAR);

            if(mRadioButtonChoice != NINGUNO){
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
                        Log.d("myTag","Marca");
                        mRadioButtonChoice = MARCA;
                        mListener.onRadioButtonChoice(MARCA);
                        barra.setVisibility(View.VISIBLE);
                        regla.setVisibility(View.GONE);
                        break;

                    case REGLA:
                        //Agrega método que dibuje la regla

                        Log.d("myTag","Regla");
                        mRadioButtonChoice = REGLA;
                        mListener.onRadioButtonChoice(REGLA);
                        regla.setVisibility(View.VISIBLE);
                        barra.setVisibility(View.GONE);
                        break;

                }

            }
        });

                return rootView;
    }
    public void masas(View view){

    };
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
            final View view = (View) event.getLocalState();
            switch (dragEvent){
                case DragEvent.ACTION_DRAG_ENTERED:
                    if(view.getId() == R.id.prueba){
                        p.setText("5kg");
                    }
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    if(view.getId() == R.id.prueba){
                        p.setText("0kg");
                    }
                    break;
                case DragEvent.ACTION_DROP:
                    break;
            }
            return true;
        }
    };
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
