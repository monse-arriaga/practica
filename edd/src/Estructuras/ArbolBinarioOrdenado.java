package edd.src.Estructuras;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;

public class ArbolBinarioOrdenado<T extends Comparable<T>> extends ArbolBinario<T> {
    private class Iterador implements Iterator<T>{
        private Pila<Vertice> pila;
        public Iterador(){
            pila = new Pila<Vertice>();
            Vertice p = raiz;
            while (p!= null) {
                pila.push(p);
                p = p.izquierdo;    
            }
        }
        // falta hasNext
        public T next(){
            if(pila.isEmpty()){
                throw new NoSuchElementException("vacio");
            }
            Vertice v = pila.pop();
            if(v.derecho != null){
                Vertice u = v.derecho;
                while (u!=null) {
                    pila.push(u);
                    u=u.izquierdo;
                }
            }

            return v.elemento;
        }
        @Override
        public boolean hasNext() {
            // TODO Auto-generated method stub
            return false;
        }
    }

    public ArbolBinarioOrdenado(Lista<T> lista, boolean isSorted ){
        if (isSorted) {
            buildSorted(lista);
        }
        else{
            buildUnsorted(lista);
        }

    }

    private void buildUnsorted(Lista<T> lista) {
	
	Lista<T> lista2 = lista.mergeSort(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
	});

	buildSorted(lista2);
       
    }

    private void buildSorted(Lista<T> lista) {

	
	ArrayList<T> lista2 = new ArrayList<>(lista.size());
	
	for(T elemento: lista){
	    lista2.add(elemento);
	 }

	int ultimo = lista2.size()-1;
	build(lista2,0,ultimo, null);
    }

    private Vertice build(ArrayList<T> lista, int inicio, int ultimo, Vertice p){
	
	if(ultimo < inicio){
	    return null;
	}
	
	int mitad = (inicio+ultimo)/2;
	
	Vertice v1 = new Vertice(lista.get(mitad));

	if(raiz == null){
	    raiz = v1;
	}
	else{
	    v1.padre = p;
	}

	elementos++;
	
	v1.izquierdo = build(lista, inicio, mitad-1, v1);
	v1.derecho = build(lista, mitad +1 , ultimo, v1);
	
	return v1;

   }
    
    /**
     * Regresa un iterador para iterar el árbol. El árbol se itera en orden.
     * 
     * @return un iterador para iterar el árbol.
     */
    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }

    @Override
    public void add(T elemento) {
        if(elemento == null){
	    throw new IllegalArgumentException();
	}

	Vertice a = new Vertice(elemento);
	elementos++;

	if(isEmpty()){
	    raiz = a;
	}
	else{
	    Vertice b = raiz;
	    while(2>1){
		if(a.elemento.compareTo(b.elemento) >= 0){ //si a es más grande que b
		    if(!b.hayIzquierdo()){
			b.izquierdo = a;
			a.padre = b;
			return;
		    }
		    else{
			b = b.izquierdo;
		    }
		}
		else if(a.elemento.compareTo(b.elemento) < 0){ //si a es más chico que b
		    if(!b.hayDerecho()){
			b.derecho = a;
			a.padre = b;
			return;
		    }
		    else{
			b = b.derecho;
		    }
		}
	    }
	}
	
    }



    /**
     * Construye un arbol binario ordenado a partir de un arbol binario
     * @param un arbol binario 
     * @return el arbol binario ordenado correspondiente
     */
    public ArbolBinarioOrdenado convertBST(ArbolBinario t1){
	Lista<T> lista = BFS(t1);
	ArbolBinarioOrdenado<T> arbol = new ArbolBinarioOrdenado<T>(lista, false);
	return arbol;
    }


    /**
     * Método auxuliar de convertBST. Recorre un árbol con BFS
     * guarda cada elemento del aŕbol en una lista y la regresa
     * @param arbol binario
     * @param lista  
     */
    private Lista<T> BFS(ArbolBinario t1){

	Lista<T> lista = new Lista<T>();
	
	if(t1.isEmpty()){
            return null;
        }
	
        Cola<Vertice> a = new Cola<Vertice>();
        a.push(t1.raiz);

	Vertice temp = t1.raiz;
	
	lista.add(temp.get());
	
        while (a.cabeza != null) {
            Vertice b = a.pop();
            if (b.hayIzquierdo()) {
                a.push(b.izquierdo);
		lista.add(b.izquierdo.elemento);
            }
            if (b.hayDerecho()) {
                a.push(b.derecho);
		lista.add(b.derecho.elemento);
            }
            if(!b.hayIzquierdo() || !b.hayDerecho()){
                
            }
        }
        return lista;

    }

    /**
     * Método DFS. Recorre el árbol en DFS in-order
     * Devuelve una lista con los elementos del árbol
     * @return Lista
     */
    public Lista<T> DFS(){
	if(this.isEmpty()){
	    return null;
	}

	Lista<T> lista = new Lista<T>();
	Pila<Vertice> pila = new Pila<Vertice>();
	Vertice actual = raiz;

	while(!pila.isEmpty() || actual != null){
	    while(actual != null){
		pila.push(actual);
		actual = actual.izquierdo;
	    }

	    actual = pila.pop();
	    
	    lista.add(actual.get());

	    actual = actual.derecho;
	}
	return lista;
     }

    /**
     * Método que busca un elemento en el árbol
     * Regresa true si lo encuentra, false si no lo encuentra
     * @param T elemento
     * @return boolean
     */
    public boolean search(T elemento){
	if(this.isEmpty()){
	    return false;
	}
	if(elemento == null){
	    return false;
	}

	Vertice aux = raiz;

	while(aux != null){
	    if(elemento.compareTo(aux.elemento) == 0){
		return true;
	    }
	    else if(elemento.compareTo(aux.elemento) > 0){
		if(aux.hayDerecho()){
		    aux = aux.derecho;
		}
		else{
		    return false;
		}
	    }
	    else{
		if(aux.hayIzquierdo()){
		    aux = aux.izquierdo;
		}
		else{
		    return false;
		}
	    }
	}

	return false;
    }

    /**
     * Método que inserta un elemento al árbol
     * @param T elemento
     */
    public void insert(T elemento){
	this.add(elemento);
    }
    
    @Override
    public boolean delete(T elemento) {
	 return  true;
        }

    @Override
    public T pop() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String toString(){
	String s;
	Lista<T> lista = new Lista<T>();
	lista = this.DFS();
	s = lista.toString();
	return s;
    }
}
