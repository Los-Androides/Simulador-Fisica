package com.androides.simfisica;


public class Preguntas {
    private String Preguntas[];
    private int Respuestas[];
    public Preguntas(){
      Preguntas = new String[]{"Cual es la masa de la maceta?",
              "Que pasara al remover los soportes? Ingresa 1 para centrado, 2 para inclinacion izquierda, 3 para derecha",
              "Que pasara al remover los soportes? Ingresa 1 para centrado, 2 para inclinacion izquierda, 3 para derecha",
              "Cual es la masa de la llanta?",
              "Que pasara al remover los soportes? Ingresa 1 para centrado, 2 para inclinacion izquierda, 3 para derecha",
              "Cual es la masa de la television?",
              "Que pasara al remover los soportes? Ingresa 1 para centrado, 2 para inclinacion izquierda, 3 para derecha"
      };
      Respuestas = new int[]{5,2,2,15,3,10,2};
    };

    public String getPregunta(int a){
        return Preguntas[a];
    }

    public double getRespuesta(int a){
        return Respuestas[a];
    }

    public int getSize(){return Preguntas.length;}

}
