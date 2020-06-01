package com.androides.simfisica;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import static android.icu.lang.UCharacter.NumericType.NONE;

public class QuizFragment extends Fragment {
    private static Preguntas Preguntas;
    private int numPregunta = 0;
    private int score = 0;
    public int mRadioButtonChoice;
    private TextView textView;
    private Button check;
    private Button prev;
    private Button next;



    public QuizFragment() {
        // Required empty public constructor
        Preguntas = new Preguntas();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View rootView = inflater.inflate(R.layout.activity_quiz,container,false);
        textView = rootView.findViewById(R.id.Pregunta);
        textView.setText(Preguntas.getPregunta(numPregunta));
        ImageView Imagen = rootView.findViewById(R.id.imageView);
        Imagen.setImageBitmap(Preguntas.getImage(numPregunta));
        //prev = rootView.findViewById(R.id.Prev);
        next = rootView.findViewById(R.id.Next);
        check = rootView.findViewById(R.id.checkAns);
        check.setEnabled(false);


        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sigPregunta(rootView);
            }
        });

        //prev.setOnClickListener(new View.OnClickListener() {
          //  @Override
            //public void onClick(View v) {
              //  prevPregunta();
            //}
        //});






        return rootView;
    }

    private void sigPregunta(View rootView){
        numPregunta++;
        textView.setText(Preguntas.getPregunta(numPregunta));
        prev.setEnabled(true);
        check.setVisibility(rootView.GONE);
        check.setEnabled(false);
    }

    private void prevPregunta(){
        numPregunta--;
        textView.setText(Preguntas.getPregunta(numPregunta));
        if(numPregunta == 0){
            prev.setEnabled(false);
        }
    }
}


