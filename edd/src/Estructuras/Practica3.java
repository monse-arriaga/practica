package edd.src.Estructuras;
import java.util.Iterator;
import java.util.Comparator;

public class Practica3 {

    static Lista <String> listaCadena = new Lista<>();


    
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
      
	//permutacionesCadena("star");

        Lista <Integer> prueba = new Lista<Integer>();
	Lista <Integer> prueba2 = new Lista<Integer>();	
	
	prueba.add(3);
	prueba.add(1);
	prueba.add(99);
	prueba.add(55);
	prueba.add(20);
	prueba.add(40);
	prueba.add(22);
	prueba.add(43);
	prueba.add(15);
	prueba.add(5);

	prueba2.add(2);
	prueba2.add(5);
	prueba2.add(10);
	prueba2.add(15);
	prueba2.add(50);
	prueba2.add(100);
	prueba2.add(120);
	
	ArbolBinarioCompleto<Integer> a1 = new ArbolBinarioCompleto<Integer>(prueba2);
	ArbolBinarioOrdenado<Integer> a2 = new ArbolBinarioOrdenado<Integer>(prueba2, true);
	

	
	a2.add(3);

	System.out.println(a1);
	System.out.println(a1.size());
	System.out.println(a1.altura());

	
	System.out.println();

	System.out.println(a2);
	System.out.println(a2.size());
	System.out.println(a2.altura());
	System.out.println(a2.raiz());

	System.out.println("Buscar 5: " + a2.search(5));
	System.out.println("Buscar 50: " + a2.search(50));
	System.out.println("Buscar 120: " + a2.search(120));
    	System.out.println("Buscar 3: " + a2.search(3));
	System.out.println("Buscar 57: " + a2.search(57));

	a2.insert(57);
	System.out.println("Buscar 57: " + a2.search(57));
	a2.imprimirPreOrder();
	System.out.println();

	ArbolBinarioOrdenado<Integer> a3 = a2.convertBST(a1);
	
	System.out.println(a3);

	ArbolBinarioOrdenado<Integer> a4 = new ArbolBinarioOrdenado<Integer>();

	a4.insert(2);
	a4.insert(5);
	a4.insert(10);
	a4.insert(15);
	a4.insert(50);
	a4.insert(100);
	a4.insert(120);

	System.out.println("a4: \n" + a4);

	a4 = a4.balance();

	System.out.println("a4: \n" + a4);

	System.out.println("Buscar 2: " + a4.search(2));
	System.out.println("Buscar 5: " + a4.search(5));
	System.out.println("Buscar 10: " + a4.search(10));
    	System.out.println("Buscar 15: " + a4.search(15));
	System.out.println("Buscar 57: " + a4.search(57));

	a4.insert(57);

	System.out.println("Buscar 57: " + a4.search(57));

	a4.delete(2);

	System.out.println(a4);

	System.out.println("Buscar 2: " + a4.search(2));

	a4.delete(15);

	System.out.println(a4);
	
	System.out.println("Buscar 15: " + a4.search(15));

	a4.delete(100);

	System.out.println(a4);
	
	System.out.println("Buscar 100: " + a4.search(100));

	a4.delete(50);

	System.out.println(a4);
	
	System.out.println("Buscar 50: " + a4.search(50));
	
	a4.delete(57);

	System.out.println(a4);
	
	System.out.println("Buscar 57: " + a4.search(57));
	System.out.println("Altura: " + a4.altura());

	System.out.println("\nBalancear: \n");
	a4 = a4.balance();

	System.out.println(a4);

	System.out.println("Altura: " + a4.altura());
	
	a4.delete(10);

	System.out.println(a4);
	
	System.out.println("Buscar 10: " + a4.search(10));
	System.out.println("Altura: " + a4.altura());

	a4.delete(120);
	a4.delete(100000);

	System.out.println(a4);
	
	System.out.println("Buscar 120: " + a4.search(120));
	System.out.println("Altura: " + a4.altura());

	a4.delete(5);
	System.out.println(a4);

	System.out.println("Es vacio?: " + a4.isEmpty());
	System.out.println("Altura: " + a4.altura());
	
        //sumaCercana(prueba, 130);
	//sumaCercana(prueba2, 4);
    }

}
