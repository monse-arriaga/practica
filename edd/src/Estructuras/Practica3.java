package edd.src.Estructuras;
import java.util.Iterator;
import java.util.Arrays;
import java.util.Comparator;

public class Practica3 {

    static Lista <String> listaCadena = new Lista<>();
    static Lista <String> xd = new Lista<>();


    
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



    public static String swap(String a, int i, int j) {
        char temp;
        char[] arregloDeCaracteres = a.toCharArray();
        temp = arregloDeCaracteres[i] ;
        arregloDeCaracteres[i] = arregloDeCaracteres[j];
        arregloDeCaracteres[j] = temp;
        return String.valueOf(arregloDeCaracteres);
    }

    public static Lista<String> permutacionesCadenaLaPrecuela(String str, int l, int r){

        if (l == r)
        {
            if(!xd.contains(str)){
                xd.add(str);
            }
        } else
        {
            for (int i = l; i <= r; i++)
            {
                str = swap(str,l,i);
                permutacionesCadenaLaPrecuela(str, l+1, r);
                str = swap(str,l,i);
            }
        }
        return xd;
    }

    public static void permutacionesCadena(String cadena){
    int r= cadena.length();
     System.out.println(permutacionesCadenaLaPrecuela(cadena, 0, r-1));
    }


    public static int[] criba(int modulo, int S, int [] arreglo){


        if(modulo<S){
            for(int i=0;i<=S-1;i++){
                if(arreglo[i]%modulo==0&&arreglo[i]!=modulo){
                    arreglo[i]=0;
                }   
        }
        criba(modulo+1, S, arreglo);
        }
        arreglo[S-1]=0;
        arreglo[0]=0;
        return arreglo;
       
      
    }

  

    public static int[] arregloSinCeros(int P, int S){
       
        int [] arreglo = new int[S];
        int contador=0;


        for(int i=0;i<=S-1;i++){
            contador++;
            arreglo[i]=contador;                 
       }

         criba(2,S,arreglo);

         if(P!=2){
             int i =0;
             while(i < P ){
                 arreglo[i]=0;
                 i++;
             }
        }

        int[] arregloSinCeros = Arrays.stream(arreglo).filter(num -> num != 0).toArray();
        return arregloSinCeros;        

    }

    public static void primosQueSumanLaSecuela(int [] arreglo, Lista <Integer> sol, int ultimoPrimo , int suma, int S, int N){
        

            if(sol.size()==N&&suma==S){
               System.out.println(sol);
                
            }else if(sol.size()==N)
                return;

                for(int i=ultimoPrimo+1;i<arreglo.length;i++){

                    //System.out.println(i+" " );               
                    if(suma+arreglo[i]<=S){

                        sol.add(arreglo[i]);
                        primosQueSumanLaSecuela(arreglo, sol, i, suma+arreglo[i], S, N);
                        //System.out.print(sol+" ");
                        //System.out.print(i+" n " );

                        if(sol.size()==1){
                            sol.empty();
                            primosQueSumanLaSecuela(arreglo, sol, i, suma, S, N);
                        }
                        
                        if(!sol.isEmpty()){
                            sol.pop();
                        } else return;
                        
                    }
                   
                   
                }
        }

          
        
        

    

    public static void primosQueSuman(int S, int P, int N){
        Lista <Integer> sol = new Lista<>();

       primosQueSumanLaSecuela(arregloSinCeros(P, S), sol, -1, 0, S, N);


    }

    static void imprimirSolucion(int tablero[][], int N)
    {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++)
                System.out.print(" " + tablero[i][j]
                        + " ");
            System.out.println();
        }
    }
 
    static boolean esSeguro(int tablero[][], int row, int col, int N)
    {
        int i, j;
        for (i = 0; i < col; i++)
            if (tablero[row][i] == 1)
                return false;
 
        
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (tablero[i][j] == 1)
                return false;
             
        for (i = row, j = col; j >= 0 && i < N; i++, j--)
            if (tablero[i][j] == 1)
                return false;
 
        return true;
    }


    public static boolean NReinasLaPrecuela(int tablero[][], int col, int N)
    {
        if (col >= N)
            return true;
 
        for (int i = 0; i < N; i++) {
            //Si es seguro poner a la reina en la posición i, ponla
            if (esSeguro(tablero, i, col, N)) {
                tablero[i][col] = 1;
 
                if (NReinasLaPrecuela(tablero, col + 1, N))
                    return true;
 
                //backtrack si la condición es falsa
                tablero[i][col] = 0;
            }
        }
        return false;
    }

    public static void N_Reinas(int N){
        int[][] tablero = new int[N][N];

       /* for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(tablero[i][j] + " ");
            }
  
            System.out.println();}*/

            
        if (!NReinasLaPrecuela(tablero, 0, N)) {
            System.out.print("La solución no existe!");
            return;
        }
 
        imprimirSolucion(tablero, N);
}

    static float raizCuadrada(double numero, int precision)
    {
        double inicio = 0;
        double mid;
        double finall = numero;
        double res = 0.0;

        
        while (inicio <= finall) {
            mid = (inicio + finall) / 2;

            if (mid * mid == numero) {
                res = mid;
                break;
            }


            if (mid * mid < numero) {
                inicio = mid + 1;
                res = mid;
            }


            else {
                finall = mid - 1;
            }
        }


        double incremento = 0.1;
        for (int i = 0; i < precision; i++) {
            while (res * res <= numero) {
                res += incremento;
            }

            res = res - incremento;
            incremento = incremento / 10;
        }
        return (float)res;
    }

    public static void sqrtBusqBin(double raiz){
    System.out.println(raizCuadrada(raiz, 5));
    }

    public static void main(String[] args) {
        //Puedes hacer tus pruebas aqui
        N_Reinas(8);
        primosQueSuman(100, 7, 4);
        permutacionesCadena("oso");
        sqrtBusqBin(2.884);

 
    }

}
