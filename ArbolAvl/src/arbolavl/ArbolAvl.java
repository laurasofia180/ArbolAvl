/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolavl;

import javax.swing.JOptionPane;

/**
 *
 * @author sofia
 */
public class ArbolAvl {

    private Nodo raiz;
    private Integer tamaño;
    public Nodo nodo;

    // Costructor
    public ArbolAvl() {
//        nodo = new Nodo(dato);
//        this.nodo = Nodo;
        tamaño = 0;
    }

    //Isertar Dato
    public boolean add(Integer dato) {
        if (esVacio()) {
            raiz = new Nodo(dato, null, null, null);
            tamaño++;
            System.out.println("Hola");
            return true;
        } else {
            boolean agregar = add(dato);
            balanceo(raiz);
            return agregar;
        }
    }

    public boolean add(Integer dato, Nodo actual) {
        if (actual.getDato() == dato) {
            JOptionPane.showMessageDialog(null, "Numero Repetido", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (actual.getDato() < dato) {
            actual.setIzq(new Nodo(dato, null, actual, null));
            tamaño++;
            return true;
        } else {
            actual.setDere(new Nodo(dato, actual, null, null));
            tamaño++;
            return true;
        }
        return false;
    }
    
        // busca el dato dentro del arbol
   public boolean buscar(Nodo raiz, Nodo nodo){
       if (raiz == null){
           JOptionPane.showMessageDialog(null,"No se encuentra");
       } else {
           if (nodo.getDato() == raiz.getDato()){
               int dat = raiz.getDato();
               JOptionPane.showMessageDialog(null,"valor" + dat + " se encontro en el dato");
           } else {
               if (nodo.getDato() < raiz.getDato()){ // lado izquierdo
                   return buscar (raiz.izq(), nodo);
               } else {
                   return buscar (raiz.dere(),nodo);
               }
           }
       }
       return false;
   }
    
// este metodo balancea el nodo segun el peso que tenga.
    private void balanceo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        balanceo(nodo.dere());
        balanceo(nodo.izq());
        Integer izqPeso = nodo.izqPeso();
        Integer derePeso = nodo.derePeso();
        boolean nesecitaBalanceo = Math.abs(izqPeso - derePeso) > 1;
        if (nesecitaBalanceo) {
            if (derePeso > izqPeso) {
                rotarIzq(nodo);
            } else if (izqPeso > derePeso) {
                rotarDere(nodo);
            }
        }

    }

    private void rotarIzq(Nodo ViejoNodo) {
        Nodo newNodo, padre = ViejoNodo.padre();
        if (ViejoNodo.dere().dere() != null) {
            newNodo = soltarHijosDerechos(ViejoNodo);
            if (newNodo.izq() != null) {
                ViejoNodo.setDere(newNodo.izq());
            }
            newNodo.setIzq(ViejoNodo);
        } else {
            newNodo = soltarHijosIzquierdos(ViejoNodo.dere());
            newNodo.setDere(soltarHijosDerechos(ViejoNodo));
            newNodo.setIzq(ViejoNodo);
        }

        setNewHijoNodo(padre, ViejoNodo, newNodo);
    }

    private void rotarDere(Nodo ViejoNodo) {
        Nodo newNodo, padre = ViejoNodo.padre();
        if (ViejoNodo.dere().dere() != null) {
            newNodo = soltarHijosIzquierdos(ViejoNodo);
            if (newNodo.dere() != null) {
                ViejoNodo.setIzq(newNodo.dere());
            }
            newNodo.setDere(ViejoNodo);
        } else {
            newNodo = soltarHijosDerechos(ViejoNodo.izq());
            newNodo.setIzq(soltarHijosIzquierdos(ViejoNodo));
            newNodo.setDere(ViejoNodo);
        }

        setNewHijoNodo(padre, ViejoNodo, newNodo);
    }

    public Integer tamaño() {
        return tamaño;
    }

    public Nodo raiz() {
        return nodo.raiz();
    }

    public void imprimeEnOrden() {
        nodo.imprimeEnOrden();
    }

    public boolean esVacio() {
        if (tamaño != 0) {
            return true;
        }
        return false;
    }

    private void setNewHijoNodo(Nodo raiz, Nodo antHijo, Nodo newHijo) {
        if (raiz == null) {
            newHijo.setRaiz(null);
            raiz = newHijo;
        } else {
            raiz.reemplazar(antHijo).con(newHijo);
        }
    }

    public Nodo soltarHijosDerechos(Nodo nodoHijo) {
        Nodo hijoDerecho = nodo.dere;
        nodo.setDere(null);
        return hijoDerecho;
    }

    public Nodo soltarHijosIzquierdos(Nodo nodoHijo) {
        Nodo hijoIzquierdo = nodo.izq;
        nodo.setIzq(null);
        return hijoIzquierdo;
    }
    
    // Metodo eliminar
    public boolean remover(Integer dato) {
        final boolean removidos = contiene(dato);
        balanceo(raiz); 
        return removidos;
    }

    public void clear() {
        nodo.setDato(null);
        nodo.setDere(null);
        nodo.setIzq(null);
        nodo.setPadre(null);
        nodo.setRaiz(null);
    }

    public boolean contiene(Integer data) {
        if (esVacio()) {
            return true;
        }
        return false;
    }
}
