package com.androides.simfisica;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
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
    private Button reset;
    private Button next;
    private Button mostrar;
    private int tries = 0;
    private TextView puntuacion;
    private TextView nivel;
    private TextView reto;
    private int numPreguntas;
    private EditText respuesta;



    public QuizFragment() {
        // Required empty public constructor
        Preguntas = new Preguntas();
        numPreguntas = Preguntas.getSize();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        final View rootView = inflater.inflate(R.layout.activity_quiz,container,false);
        textView = rootView.findViewById(R.id.Pregunta);
        respuesta = rootView.findViewById(R.id.respuesta);
        mostrar = rootView.findViewById(R.id.button4);
        textView.setText(Preguntas.getPregunta(numPregunta));
        reto = rootView.findViewById(R.id.Reto);
        reto.setText("Reto " + Integer.toString(numPregunta + 1) + " de " + Integer.toString(numPreguntas));
        puntuacion = rootView.findViewById(R.id.Puntuacion);
        puntuacion.setText("Puntuacion: " + Integer.toString(score));
        ImageView Imagen = rootView.findViewById(R.id.imageView);
        Imagen.setImageBitmap(Preguntas.getImage(numPregunta));
        mostrar.setEnabled(false);
        mostrar.setVisibility(rootView.INVISIBLE);
        next = rootView.findViewById(R.id.Next);
        check = rootView.findViewById(R.id.checkAns);
        next.setEnabled(false);
        reset = rootView.findViewById(R.id.reset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetQuiz(rootView);
            }
        });
        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String answer = respuesta.getText().toString().trim();
                if(answer == Preguntas.getRespuesta(numPregunta).trim()){
                    Toast.makeText(rootView.getContext(), "CORRECTO!",Toast.LENGTH_SHORT).show();
                    score = score + 2;
                    puntuacion.setText("Puntuacion: " + Integer.toString(score));
                    next.setEnabled(true);
                }else{
                    Toast.makeText(rootView.getContext(), "Intentalo de nuevo!",Toast.LENGTH_SHORT).show();
                    tries++;
                    if(tries == 3){
                        mostrar.setVisibility(rootView.VISIBLE);
                        mostrar.setEnabled(true);
                        mostrar.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(rootView.getContext(), "La respuesta es: " +
                                        Preguntas.getRespuesta(numPregunta),Toast.LENGTH_SHORT).show();
                                score--;
                                puntuacion.setText("Puntuacion: " + Integer.toString(score));
                                next.setEnabled(true);
                            }
                        });
                    }
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numPregunta++;
                textView.setText(Preguntas.getPregunta(numPregunta));
                puntuacion.setText("Puntuacion: " + Integer.toString(score));
                sigPregunta(rootView);
            }
        });

        return rootView;
    }




    private void sigPregunta(View rootView){
        numPregunta++;
        textView.setText(Preguntas.getPregunta(numPregunta));
        reto.setText("Reto " + Integer.toString(numPregunta + 1) + " de " + Integer.toString(numPreguntas));
        next.setEnabled(false);
        mostrar.setEnabled(false);
        mostrar.setVisibility(rootView.INVISIBLE);
        respuesta.setText("");
    }

    private void resetQuiz(View rootView){
        numPregunta = 0;
        score = 0;
        textView.setText(Preguntas.getPregunta(numPregunta));
        puntuacion.setText("Puntuacion: " + Integer.toString(score));
        next.setEnabled(false);
        respuesta.setText("");
        mostrar.setEnabled(false);
        mostrar.setVisibility(rootView.INVISIBLE);
        reto.setText("Reto " + Integer.toString(numPregunta + 1) + " de " + Integer.toString(numPreguntas));
    }
}


