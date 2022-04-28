package edd.src.Estructuras;

import java.util.Iterator;

public class Practica3 {
    
    public static void sumaCercana(Lista lista, int N){

        IteradorLista<Integer> iterador = lista.iteradorLista();
        
        int aux= 0;

        while(iterador.hasNext()){
            aux= iterador.next();
        }


    }
    public static void permutacionesCadena(String cadena){

    }
    public static void primosQueSuman(int S, int P, int N){

    }
    public static void N_Reinas(int N){

    }

    public static void main(String[] args) {
        //Puedes hacer tus pruebas aqui
        Lista <Integer> prueba = new Lista<>();

        sumaCercana(prueba, 20);
    }

}
