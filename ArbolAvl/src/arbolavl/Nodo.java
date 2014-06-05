/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arbolavl;

/**
 *
 * @author sofia
 */
class Nodo {

    Integer dato;
    Nodo dere, izq, raiz, padre;

    public Nodo(Integer dato, Nodo dere, Nodo izq, Nodo raiz) {
        this.dato = dato;
        this.dere = dere;
        this.izq = izq;
        this.raiz = raiz;
    }

    Nodo(Integer dato) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    // Costructores de cada uno 
    
    public Integer dato(){
        return dato;
    }
    
    public Nodo raiz(){
        return raiz;
    }

    public Nodo izq(){
        return izq;
    }
    
    public Nodo dere(){
        return dere;
    }

    public Nodo padre(){
        return padre;
    }
    
    public boolean esHoja() {
        return ((izq == null) && (dere == null));
    }

    public int izqPeso() {
        if (izq == null) {
            return 0;
        } else {
            return izq.peso();
        }
    }

    public int derePeso() {
        if (dere == null) {
            return 0;
        } else {
            return dere.peso();
        }
    }

    // verifica el peso de cada lado del arbol
    public int peso() {
        return 1 + Math.max(izqPeso(), derePeso());
    }

    //  Da la profundidad del arbol 
    public int profundidad() {
        if (raiz == null) {
            return 0;
        } else {
            return 1 + raiz.profundidad();
        }
    }
    
    // Metodo de balanceo de los nodos 
    
    public boolean balanceo() {
        if (esHoja()) {
            return true;
        } else {
            boolean izqBalanceo = (izq == null || izq.balanceo());
            boolean dereBalanceo = (dere == null || dere.balanceo());
            boolean actualBalanceo = (Math.abs(derePeso() - izqPeso()) <= 1);
            return actualBalanceo && izqBalanceo && dereBalanceo;
        }
    }
    
    public void setDato(Integer dato) {
        this.dato = dato;
    }

    public int getDato() {
        return dato;
    }
    
    public void setRaiz(Nodo raiz){
        this.raiz = raiz;
        if (this.izq != null && this.izq.raiz != this){
            this.izq.setRaiz(this);
        }
        if (this.dere != null && this.dere.raiz != this){
            this.dere.setRaiz(this);
        }
    }
    
    public void setPadre(Nodo padre){
        this.padre = padre;
        if (this.izq != null && this.izq.padre != this) {
            this.izq.setPadre(this);
        }
        if (this.dere != null && this.dere.padre != this){
            this.dere.setPadre(this);
        }
    }
 // verifica la posiscion de cada nodo segun el balanceo 
    public void setDere(Nodo nodo) {
        if (nodo == null || nodo.getDato() == -1) {
            throw new RuntimeException("No se puede poner el nodo en la derecha de " + this);
        }
        this.dere = nodo;
        nodo.setRaiz(this);
    }
    
    public void setIzq(Nodo nodo) {
        if (nodo == null || nodo.getDato() == 1) {
            throw new RuntimeException("No se puede poner el nodo en la izquierda de " + this);
        }
        this.izq = nodo;
        nodo.setRaiz(this);
    }
 
    public reemplazoPequeñoNodo reemplazar(Nodo anteriorPequeño){
        return new reemplazoPequeñoNodo(anteriorPequeño);
    }
    
    public class reemplazoPequeñoNodo {
        private Nodo anteriorPequeño;
        
        public reemplazoPequeñoNodo(Nodo anteriorPequeño){
            if ((anteriorPequeño == izq || anteriorPequeño == dere)){
                this.anteriorPequeño = anteriorPequeño;
            }
        }
        
         public void con (Nodo newPequeño){
             if(anteriorPequeño == izq){
                 setIzq(newPequeño);
             } else if (anteriorPequeño == dere){
                 setDere(newPequeño);
             }
         }
    }
    
    // Este metodo se encarga de llamar al método recursivo pasando la dirección del nodo raiz.Visitar la raiz.
    //- Recorrer el subárbol izquierdo en pre-orden.
    //- Recorrer el subárbol derecho en pre-orden.
    private void ImprimePreOrden(Nodo recorre) {
        if (recorre != null) {
            System.out.print(recorre.dato + " ");
            ImprimePreOrden(recorre.izq);
            ImprimePreOrden(recorre.dere);
        }
    }

    public void ImprimePreOrden() {
        ImprimePreOrden(raiz);
        System.out.println();
    }

    private void imprimeEnOrden(Nodo recorre) {
        if (recorre != null) {
            imprimeEnOrden(recorre.izq);
            System.out.print(recorre.dato + " ");
            imprimeEnOrden(recorre.dere);
        }
    }

    public void imprimeEnOrden() {
        imprimeEnOrden(raiz);
        System.out.println();
    }

    private void imprimePostOrden(Nodo recorre) {
        if (recorre != null) {
            imprimePostOrden(recorre.izq);
            imprimePostOrden(recorre.dere);
            System.out.print(recorre.dato + " ");
        }
    }

    public void imprimePostOrden() {
        imprimePostOrden(raiz);
        System.out.println();
    }
}