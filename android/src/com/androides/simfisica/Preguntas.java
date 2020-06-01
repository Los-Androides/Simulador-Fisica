package com.androides.simfisica;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Preguntas {
    private String Preguntas[];
    private String Respuestas[];
    private String Imagenes[];
    public Preguntas(){
      Preguntas = new String[]{"Distancia que tiene que ir la roca para balancear la regla?","Cual es la masa?","Se centrara la barra o se inclinara a la izquierda o derecha?"};
      Respuestas = new String[]{"1.5","20",};
      Imagenes = new String[]{"Pregunta.png"};
    };

    public String getPregunta(int a){
        return Preguntas[a];
    }

    public String getRespuesta(int a){
        return Respuestas[a];
    }
    public Bitmap getImage(int a){
        Bitmap bm = BitmapFactory.decodeFile(Imagenes[a]);
        return bm;
    }

}
