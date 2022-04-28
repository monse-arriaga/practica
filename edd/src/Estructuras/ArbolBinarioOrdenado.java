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
        
        Nodo current = root;
        Node parent = root;
        boolean isLeft = false;
        boolean isRight = false;
        // Encuentra el hijo izquierdo o derecho del nodo que se va a eliminar
        while(current.val != value) {
            parent = current;
            isLeft = false;
            isRight = false;
            if(value < current.val) {
                current = current.leftChild;
                isLeft = true;
            }
            else {
                current = current.rightChild;
                isRight = true;
            }
        }
        // El valor no existe
        if(current == null) {
            return false;
        }
        // Es un nodo hoja, no hay nodo hijo
        if((current.leftChild == null) 
            && (current.rightChild == null)) {
            System.out.println("Es un nodo hoja, no hay nodos secundarios");
            if(isLeft) {
                // Si es un nodo secundario izquierdo, establezca el nodo secundario izquierdo del nodo primario en nulo
                parent.leftChild = null;
            }
            else if(isRight) {
                // Si es un nodo secundario derecho, establezca el nodo secundario derecho del nodo primario en nulo
                parent.rightChild = null;
            }
            return  true;
        }
        // Hay un nodo hijo izquierdo
        else if((current.leftChild != null) 
                && (current.rightChild == null)) {
                System.out.println("No es un nodo hoja, hay un nodo hijo izquierdo");

                if(isLeft) {
                    parent.leftChild = current.leftChild;
                }
                else if(isRight) {
                    parent.rightChild = current.leftChild;
                }
                current = null;
                return  true;
        }

        // Hay un nodo secundario derecho
        else if((current.leftChild == null) 
                && (current.rightChild != null)) {
                System.out.println("No es un nodo hoja, hay un nodo hijo derecho");                
                if(isLeft) {
                    parent.leftChild = current.rightChild; 

                }
                else if(isRight) {
                    parent.rightChild = current.rightChild; 
                }
                current = null;
                return  true;
        }
        // Existen los nodos secundarios izquierdo y derecho
        else {
            System.out.println("No es un nodo hoja, hay nodos secundarios izquierdo y derecho");

            if(isLeft) {
                parent.leftChild = current.rightChild; 
                Node currentLeft = current.rightChild;
                Node parentLeft = currentLeft;
                while(currentLeft != null) {
                    parentLeft = currentLeft;
                    currentLeft = currentLeft.leftChild;
                }
                parentLeft.leftChild = current.leftChild;
                current = null;

            }
            else if(isRight) {
                parent.rightChild = current.rightChild; 

                Node currentLeft = current.rightChild;
                Node parentLeft = currentLeft;
                while(currentLeft != null) {
                    parentLeft = currentLeft;
                    currentLeft = currentLeft.leftChild;
                }
                parentLeft.leftChild = current.leftChild;
                current = null;
            }

            return  true;
        }
    }

    @Override
    public T pop() {
        // TODO Auto-generated method stub
        return null;
    }

}
