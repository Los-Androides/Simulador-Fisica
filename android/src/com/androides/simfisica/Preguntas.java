package com.androides.simfisica;

/**
 * Clase de preguntas para el quiz.
 *
 */
public class Preguntas {
    // **************************************************
    // Campos
    // **************************************************
    private String Preguntas[];
    private int Respuestas[];
    // **************************************************
    // Constructor
    // **************************************************
    /**
     * Constructor Predeterminado.

     * Preguntantas y Respuestas fijas.
     */
    public Preguntas(){
      Preguntas = new String[]{"¿Cuál es la masa de la maceta?",
              "¿Qué pasará al remover los soportes? Ingresa 1 para centrado, 2 para inclinación izquierda, 3 para derecha",
              "¿Qué pasará  al remover los soportes? Ingresa 1 para centrado, 2 para inclinación izquierda, 3 para derecha",
              "¿Cuál es la masa de la llanta?",
              "¿Qué pasará  al remover los soportes? Ingresa 1 para centrado, 2 para inclinación izquierda, 3 para derecha",
              "¿Cuál es la masa de la televisión?",
              "¿Qué pasará al remover los soportes? Ingresa 1 para centrado, 2 para inclinación izquierda, 3 para derecha"
      };
      Respuestas = new int[]{5,2,2,15,3,10,2};
    };

    // **************************************************
    // Metodos Publicos
    // **************************************************
    /**
     * Regresa el String de la pregunta.
     * @param a Numero de pregunta
     * @return la pregunta indicada por el numero en el arreglo.
     */

    public String getPregunta(int a){
        return Preguntas[a];
    }

    /**
     * Regresa la respuesta.
     * @param a Numero de pregunta
     * @return la respuesta de la pregunta indicada .
     */
    public int getRespuesta(int a){
        return Respuestas[a];
    }

    /**
     * Regresa el numero de preguntas
     * @return Entero que es el numero de respuestas
     */
    public int getSize(){return Preguntas.length;}

}
