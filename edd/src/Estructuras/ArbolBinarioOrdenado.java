package edd.src.Estructuras;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

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
            buildSorted();
        }
        else{
            buildUnsorted();
        }

    }

    private void buildUnsorted() {
    }

    private void buildSorted() {
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
        // TODO Auto-generated method stub
        
    }

    @Override
    public boolean delete(T elemento) {
    
        return false;
        
    }

    @Override
    public T pop() {
        // TODO Auto-generated method stub
        return null;
    }

}
