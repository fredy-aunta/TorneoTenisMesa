package modelo;

import java.util.ArrayList;
import services.Nodo;

/**
 * @author Sebasti�n
 * @version 1.0
 * @created 10-abr.-2017 11:34:17 p. m.
 */
public class Arbol extends Estructura {

	public Arbol(){
            this.setIdEstructura(FactoriaEstructura.ESTRUCTURA_ARBOL);
	}

    @Override
    public services.Arbol crearEstructura(int cantidadJugadores) {
        return crearArbol(cantidadJugadores/2);
    }
    
    private services.Arbol crearArbol(int cantidadPartidosInicio) {
        ArrayList<Nodo> nodos, newNodos;
        nodos = new ArrayList<Nodo>();
        newNodos = new ArrayList<Nodo>();
        int numeroNodos = 1;
        Nodo nodo,raiz;
        for (int i = 0; i < cantidadPartidosInicio; i++) {
            nodo = new Nodo(numeroNodos);
            numeroNodos++;
            nodos.add(nodo);
        }
        raiz = construirArbol(nodos, numeroNodos);
        services.Arbol arbol = new services.Arbol(raiz);
        return arbol;
    }
    private Nodo construirArbol(ArrayList<Nodo> nodos, int numeroNodos) {
        Nodo n = null;
        if (nodos.size() >= 2) {
            ArrayList<Nodo> newNodos = new ArrayList<Nodo>();
            for (int i = 0; i < nodos.size(); i=i+2) {
                Nodo nodo = new Nodo(numeroNodos);
                nodo.setIzq(nodos.get(i));
                nodo.setDer(nodos.get(i+1));
                numeroNodos++;
                newNodos.add(nodo);
            }
            nodos = newNodos;
            n = construirArbol(nodos, numeroNodos);
        } else {
            n = nodos.get(0);
        }
        return n;
    }
}//end Arbol