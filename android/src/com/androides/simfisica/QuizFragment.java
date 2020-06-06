package com.androides.simfisica;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class QuizFragment extends Fragment {
    // **************************************************
    // Campos
    // **************************************************
    private static Preguntas Preguntas;
    private int numPregunta = 0;
    private int score = 0;
    private TextView textView;
    private Button check;
    private Button reset;
    private Button next;
    private Button mostrar;
    private int tries = 0;
    private TextView puntuacion;
    private TextView reto;
    private int numPreguntas;
    private EditText respuesta;
    private ImageView imagen;


    // **************************************************
    // Constructor
    // **************************************************
    public QuizFragment() {
        Preguntas = new Preguntas();
        numPreguntas = Preguntas.getSize();
    }
    // **************************************************
    // Metodos Publicos
    // **************************************************
    /**
     *  Crea la vista del archivo xml activity_quiz en el fragmento contenedor.
     *  @return regresa la vista actual.
     */
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
        imagen = rootView.findViewById(R.id.imageView);
        imagen.setImageResource(R.drawable.pregunta1);
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
                if(Integer.parseInt(answer) == Preguntas.getRespuesta(numPregunta)){
                    Toast.makeText(rootView.getContext(), "CORRECTO!",Toast.LENGTH_SHORT).show();
                    score = score + 2;
                    auxImage();
                    puntuacion.setText("Puntuacion: " + Integer.toString(score));
                    if(numPregunta < numPreguntas-1){
                        next.setEnabled(true);
                    }else{
                        next.setEnabled(false);
                    }
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
                                if(numPregunta < numPreguntas-1){
                                    next.setEnabled(true);
                                }else{
                                    next.setEnabled(false);
                                }

                            }
                        });
                    }
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sigPregunta(rootView);
            }
        });

        return rootView;
    }

    // **************************************************
    // Metodos Privados
    // **************************************************
    /**
     * Cambia la Imagen del image View.
     */

    private void cambiaImagen(){
        switch (numPregunta){
            case 0:
                imagen.setImageResource(R.drawable.pregunta1);
                break;
            case 1:
                imagen.setImageResource(R.drawable.pregunta2);
                break;
            case 2:
                imagen.setImageResource(R.drawable.pregunta3);
                break;
            case 3:
                imagen.setImageResource(R.drawable.pregunta4);
                break;
            case 4:
                imagen.setImageResource(R.drawable.pregunta5);
                break;
            case 5:
                imagen.setImageResource(R.drawable.pregunta6);
                break;
            case 6:
                imagen.setImageResource(R.drawable.pregunta7);
                break;
            default:
                break;
        }
    }
    /**
     * Cambia la Imagen de casos particulares para mostrar otra imagen.
     */

    private void auxImage(){
        switch (numPregunta){
            case 1:
                imagen.setImageResource(R.drawable.auxpregunta2);
                break;
            case 2:
                imagen.setImageResource(R.drawable.auxpregunta3);
                break;
            case 4:
                imagen.setImageResource(R.drawable.auxpregunta5);
                break;
            case 6:
                imagen.setImageResource(R.drawable.auxpregunta7);
                break;
            default:
                break;
        }
    }

    /**
     * Actualiza la vista con el contenido de la nueva pregunta.
     * @param rootView Vista actual.
     */

    private void sigPregunta(View rootView){
        numPregunta++;
        textView.setText(Preguntas.getPregunta(numPregunta));
        puntuacion.setText("Puntuacion: " + Integer.toString(score));
        reto.setText("Reto " + Integer.toString(numPregunta + 1) + " de " + Integer.toString(numPreguntas));
        next.setEnabled(false);
        mostrar.setEnabled(false);
        mostrar.setVisibility(rootView.INVISIBLE);
        cambiaImagen();
        respuesta.setText("");
    }

    /**
     * Regresa el quiz a su estado original
     * @param  rootView result of the current value added to the default value.
     */

    private void resetQuiz(View rootView){
        numPregunta = 0;
        score = 0;
        cambiaImagen();
        textView.setText(Preguntas.getPregunta(numPregunta));
        puntuacion.setText("Puntuacion: " + Integer.toString(score));
        next.setEnabled(false);
        respuesta.setText("");
        mostrar.setEnabled(false);
        mostrar.setVisibility(rootView.INVISIBLE);
        reto.setText("Reto " + Integer.toString(numPregunta + 1) + " de " + Integer.toString(numPreguntas));
    }
}


