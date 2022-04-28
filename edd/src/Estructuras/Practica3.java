package edd.src.Estructuras;
import java.util.Iterator;
import java.util.Comparator;

public class Practica3 {
    
    public static void sumaCercana(Lista<Integer> lista, int n){
	if(lista.isEmpty()){
	    System.out.println("La lista es vacía");
	    return;
	}
	
	if(lista.size() == 1){
	    System.out.println("La lista solo tiene un elemento");
	    return;
	}
	
	Lista<Integer> l = lista.mergeSort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 - o2;
            }
	    });

	System.out.println(l);

	int c=0,f=0;
	int contador = 0;
	Iterator<Integer> iterador1 = l.iterator();

	if(!l.isEmpty()){
	    iterador1.next();
	    while(iterador1.hasNext()){
		iterador1.next();
		contador++;
	    }
	}
	
	f = contador;
	
	int diferencia = 1000000000;
	int respuesta1 = 0, respuesta2 = 0;
	int valorC = 0, valorF = 0;

	Iterator<Integer> iterador2 = l.iterator();
	IteradorLista<Integer> iterador3 = l.iteradorLista();

        
	iterador3.end();
	if(iterador3.hasPrevious()){
	    valorF = iterador3.previous();
	}
	if(iterador2.hasNext()){
	    valorC = iterador2.next();
	}
	
	while(c<f){
	    if(Math.abs((valorC + valorF) - n) < diferencia){
		respuesta1 = valorC;
		respuesta2 = valorF;
		diferencia = Math.abs((valorC + valorF) - n);
	    }

	    if((valorC + valorF) > n){
		if(iterador3.hasPrevious()){
		    valorF = iterador3.previous();
		    f--;
		}
	    }
	    if((valorC + valorF) <= n){
	        if(iterador2.hasNext()){
		    valorC = iterador2.next();
		    c++;
		}
	    }
	}	
	
	System.out.println("Los números con la suma más cercana son: " + respuesta1 + ", " + respuesta2);


    }
    public static void permutacionesCadena(String cadena){

    }
    public static void primosQueSuman(int S, int P, int N){

    }
    public static void N_Reinas(int N){

    }

    public static void main(String[] args) {
        //Puedes hacer tus pruebas aqui
        Lista <Integer> prueba = new Lista<Integer>();
	Lista <Integer> prueba2 = new Lista<Integer>();

	prueba2.add(4);

	
	prueba.add(3);
	prueba.add(1);
	prueba.add(99);
	prueba.add(55);
	prueba.add(20);
	prueba.add(40);
	prueba.add(43);
	prueba.add(15);
	prueba.add(5);
	
        sumaCercana(prueba, 130);
	sumaCercana(prueba2, 4);
    }

}
