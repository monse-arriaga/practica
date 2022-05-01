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
            if(pila.isEmpty()){
		return false;
	    }
	    else{
		return true;
	    }
        }
    }

    /**
     * Constructor sin parametros
     */
    public ArbolBinarioOrdenado(){
	super();
    }

    /**
     * Constructor que recibe una lista y un boolean que representa
     * si la lista está ordenada o no
     */
    public ArbolBinarioOrdenado(Lista<T> lista, boolean isSorted ){
        if (isSorted) {
            buildSorted(lista);
        }
        else{
            buildUnsorted(lista);
        }

    }


    /**
     * Método auxiliar del constructor cuando la lista no está ordenada
     * se ordena usando merge sort y se llama a buildSorted
     * @param lista
     */
    private void buildUnsorted(Lista<T> lista){
	Lista<T> lista2 = lista.mergeSort(new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                return o1.compareTo(o2);
            }
	});

	buildSorted(lista2);
    }


    /**
     * Método auxiliar del constructor cuando la lista está ordenada
     * @param lista
     */
    private void buildSorted(Lista<T> lista){
	ArrayList<T> lista2 = new ArrayList<>(lista.size());
	for(T elemento: lista){
	    lista2.add(elemento);
	 }
	
	this.elementos = lista2.size();

	int ultimo = lista2.size()-1;
	
	this.raiz = build(lista2, 0, ultimo, null);
    }


    /**
     * Método auxiliar de buildSorted que construye el árbol recursivamente
     * se toma el valor de la mitad en cada iteración y se agrega al aŕbol
     * @param arraylist
     * @param int indice izquierdo
     * @param int indice derecho
     * @param vértice padre
     */
    private Vertice build(ArrayList<T> lista2, int primero, int ultimo, Vertice v1){
	if(ultimo < primero){
	    return null;
	}

	int mitad = (primero+ultimo)/2;
	Vertice v = new Vertice(lista2.get(mitad));	
	v.padre = v1;
	v.izquierdo = build(lista2, primero, mitad -1, v);
	v.derecho = build(lista2, mitad +1, ultimo, v);
	return v;		
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


    /**
     * Añade un elemento al árbol
     * @param elemento
     */
    @Override
    public void add(T elemento) {
	if(elemento == null){
	    return;
	}

	Vertice v = new Vertice(elemento);

	elementos++;
	
	if(this.isEmpty()){
	    this.raiz = v;
	}
	else{
	    Vertice b = raiz;

	    while(true){
		if(v.get().compareTo(b.get())  >= 0){ //si v es más grande o igual al nodo actual
		    if(b.hayDerecho()){
			b = b.derecho;
		    }
		    else{
			b.derecho = v;
			v.padre = b;
			return;
		    }
		}
		else{ //si v es más chico que el nodo actual
		    if(b.hayIzquierdo()){
			b = b.izquierdo;
		    }
		    else{
			b.izquierdo = v;
			v.padre = b;
			return;
		    }
		}
	    }
	    
	}
    }

    /**
     * Método que busca un elemento en el árbol
     * Regresa el vértice si lo encuentra, regresa null en otro caso
     * @param T elemento
     * @return el vértice con el elemento buscado
     */
    public Vertice search(T elemento){
	if(elemento == null || this.isEmpty()){
	    return null;
	}

	return searchAux(this.raiz, elemento);
	
    }

    /**
     * Método auxiliar recursivo para encontrar un elemento en el árbol
     * en caso de encontrarlo regresa al vértice con el elemento, de otra
     * forma regresa null
     * @param elemento
     * @param vertice a compararse
     * @return el vertice buscado
     */
    private Vertice searchAux(Vertice v, T elemento){
	if(v == null){
	    return null;
	}
	if(v.get().compareTo(elemento) == 0){
	    return v;
	}
	else if(v.get().compareTo(elemento) > 0){
	    return searchAux(v.izquierdo, elemento);
	}
	else{
	    return searchAux(v.derecho, elemento);
	}
	
    }

   

    /**
     * Método que convierte un árbol en un árbol binario ordenado
     * Regresa el árbol binario ordenado correspondiente
     * @param arbol binario
     * @return arbol binario ordenado
     */
    public ArbolBinarioOrdenado convertBST(ArbolBinario t1){
	Lista<T> lista = convertAux(t1);
	ArbolBinarioOrdenado<T> arbol = new ArbolBinarioOrdenado<T>(lista, false);
	return arbol;
    }

    /**
     * Método que auxiliar de convertBST, recorre el árbol 
     * y mete los elementos a una lista
     * @param arbol binario
     * @return lista
     */
    private Lista<T> convertAux(ArbolBinario t1){
	
	if(t1.isEmpty()){
	    return null;
	}
       
	Lista<T> lista = new Lista<T>();

	@SuppressWarnings("unchecked")
	Iterator<T> it = t1.iterator();

	T elem = it.next();
	lista.add(elem);
	
	while(it.hasNext()){
	    elem = it.next();
	    lista.add(elem);
	}
	return lista;
    }

    /**
     * Método que imprime el árbol en preorden
     */
    public void imprimirPreOrder(){
	preOrder(this.raiz);
    }

    /**
     * Método recursivo para imprimir el árbol en preorden
     */
    private void preOrder(Vertice v){
	if(v == null){
	    return;
	}

	System.out.print(v.get() + ", ");
	preOrder(v.izquierdo);
	preOrder(v.derecho);
    }

    /**
     * Método auxiliar recursivo que recorre el árbol
     * en inOrder y agrega los elementos a una lista
     * @param vertice
     * @param lista
     */
    private void inOrder(Vertice v, Lista<T> lista){
	if(v == null){
	    return;
	}
	inOrder(v.izquierdo, lista);
	lista.add(v.get());
	inOrder(v.derecho, lista);	
    }

    /**
     * Método que balancea el árbol binario
     * regresa el árbol binario balanceado
     * @return arbol
     */
    public ArbolBinarioOrdenado balance(){
	Lista<T> lista = new Lista<T>();
	inOrder(this.raiz, lista);
	ArbolBinarioOrdenado<T> arbol = new ArbolBinarioOrdenado<T>(lista,true);
	return arbol;
    }
    
    /**
     * Método que inserta un elemento al árbol
     * @param T elemento
     */
    public void insert(T elemento){
	this.add(elemento);
    }

    /**
     * Método que elimina un vertice en el árbol
     * Regresa true si se eliminó el elemento, false si
     * el elemento no fue encontrado en el árbol
     * @param elemento a eliminar
     * @return boolean
     */
    @Override
    public boolean delete(T elemento) {
	
        Vertice v1 = search(elemento);
	if(v1 == null){
	    return false;
	}

	elementos--;
	
	if(v1.hayIzquierdo() && v1.hayDerecho()){
	    Vertice v2 = intercambiaEliminable(v1);
	    eliminarVertice(v2);
	    return true;
	}
	else{
	    eliminarVertice(v1);
	    return true;
	}
       
     }

    /**
     * Método que intercambia el elemento del parámetro
     * con el elemento mínimo del subárbol derecho del parámetro
     * @param el vértice a intercambiar
     * @return vértice con elemento mínimo
     */
    private Vertice intercambiaEliminable(Vertice v){
	Vertice v2 = minimoEnSubArbol(v.derecho);
	v.elemento = v2.elemento;
	return v2;
    }

    /**
     * Método que regresa el vértice con el elemento mínimo
     * de un árbol o subárbol
     * @param vértice raíz del subárbol
     * @param elemento mínimo del subárbol
     */
    private Vertice minimoEnSubArbol(Vertice v){
	if(!v.hayIzquierdo()){
	    return v;
	}
	return minimoEnSubArbol(v.izquierdo);
    }

    /**
     * Método que elimina un vértice que tiene 
     * a lo máximo un hijo
     * @param el vértice a eliminar
     */
    private void eliminarVertice(Vertice v){

	Vertice u;
	
	if(v.hayIzquierdo()){
	    u = v.izquierdo;
	}
	else if(v.hayDerecho()){
	    u = v.derecho;
	}
	else{
	    u = null;
	}
	
	if(v.padre != null){
	    if(v.get().compareTo(v.padre.get()) >= 0){
		v.padre.derecho = u;
		if(u != null){
		    u.padre = v.padre;
		}
	    }
	    else{
		v.padre.izquierdo = u;
		if(u != null){
		    u.padre = v.padre;
		}
	    }
	    
	}
	else{
	    this.raiz = u;
	    if(u != null){
		u.padre = null;
	    }
	}
	
    }
    
    @Override
    public T pop() {
        
        return null;
    }

    
    /**
     * Método que convierte el árbol a una cadena
     * Se representa el árbol en inOrder
     */
    @Override
    public String toString(){
	String s;
	Lista<T> lista = new Lista<T>();
	inOrder(this.raiz, lista);
	s = lista.toString();
	return s;
    }
}
