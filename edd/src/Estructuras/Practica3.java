package edd.src.Estructuras;

import java.util.Iterator;

public class Practica3 {

    static Lista <String> listaCadena = new Lista<>();


    
    public static void sumaCercana(Lista lista, int N){

        IteradorLista<Integer> iterador = lista.iteradorLista();
        
        int aux= 0;

        while(iterador.hasNext()){
            aux= iterador.next();
        }


    }

    public static String reverseString(char[] s) {
        int len = s.length;
    
    
        for (int i=0; i < (len/2); i++)
        {
            char l = s[i];
            s[i] = s[len-i-1];
            s[len-i-1] = l;
        }
    
        String nuevoString = new String(s);
        return nuevoString;
    }


    public static String swap(String a, int i, int j) {
        char temp;
        char[] charArray = a.toCharArray();
        temp = charArray[i] ;
        charArray[i] = charArray[j];
        charArray[j] = temp;
        return String.valueOf(charArray);
    }

    public static void permute(String str, int l, int r)
    {
        if (l == r)
            System.out.println(str);
        else
        {
            for (int i = l; i <= r; i++)
            {
                str = swap(str,l,i);
                permute(str, l+1, r);
                str = swap(str,l,i);
            }
        }
    }

    public static void permutacionesCadena(String cadena){
    int r= cadena.length();
     permute(cadena, 0, r-1);
    }



    public static void primosQueSuman(int S, int P, int N){

    }
    public static void N_Reinas(int N){

    }

    public static void main(String[] args) {
        //Puedes hacer tus pruebas aqui
       // Lista <Integer> prueba = new Lista<>();
        permutacionesCadena("star");



    }

}
