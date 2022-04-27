package edd.src.Estructuras;

public class Cola<T> extends PushPop<T> {

    //Agregar al final
    public void push(T elemento){
	if(elemento == null){
	    throw new IllegalArgumentException("");
	}


	Nodo aux = new Nodo(elemento);
	
	if(isEmpty()){
	   this.cabeza = ultimo = aux;
	   longi++;
	}
	else{
	    ultimo.siguiente = aux;
	    ultimo = aux;
	    longi++;
	}
	
    }

    public Cola<T> clone(){
	Cola<T> a = new Cola<T>();

	if (this.isEmpty()) {
		return a;
	}
	
	a.push(this.cabeza.elemento);
	Nodo n = this.cabeza;
	while (n.siguiente != null) {
	   a.push(n.siguiente.elemento);
	   n = n.siguiente;
	}
	return a;
    }

    public String toString(){
	
	if(isEmpty()){
	    return "";
	}
	else{
	    Nodo nodo = cabeza;
	    String s = String.valueOf(nodo.elemento);
	    nodo = cabeza.siguiente;
	    while(nodo != null){
		s += " -> " + nodo.elemento;
		nodo = nodo.siguiente;
	    }
	    
	    return s;
	}
    }
}