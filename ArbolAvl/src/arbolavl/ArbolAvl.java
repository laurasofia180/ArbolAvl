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
public class ArbolAvl {
    private Nodo raiz;
    private int tamaño;
    
    public ArbolAvl(){
        tamaño = 0;
    }
     
     public boolean add(int dato){
         if (esVacio()){
             raiz = new Nodo (dato,null,null,null);
             tamaño++;
             return true;
         } else {
             boolean agregar = add(dato);
             balanceo (raiz);
             return agregar;
         }
     }
     
     private boolean add(int dato, Nodo actual){
         if (actual.getDato() == dato) {
             Nodo izq = actual.izq();
                 if(izq != null){
                     return add (dato,izq);
                 } else {
                     actual.setIzq(new Nodo (dato,null,actual,null));
                     tamaño++;
                     return true;
                 }
         }else {
            Nodo dere = actual.dere();
            if (dere != null){
                return add (dato, dere); 
            } else {
                actual.setDere(new Nodo (dato,actual,null,null));
                tamaño++;
                return true;
            }
         }
     }

    private void balanceo (Nodo nodo) {
        if (nodo == null){
            return;
        }
       balanceo(nodo.izq());
       balanceo (nodo.dere());
       
       int izqPeso = nodo.izqPeso();
       int derePeso = nodo.derePeso();
       boolean nesecitaBalanceo = Math.abs(izqPeso - derePeso) > 1;
       
       if(nesecitaBalanceo){
           if (derePeso > izqPeso){
               rotarIzq(nodo);
           } else if (izqPeso > derePeso){
               rotarDere(nodo);
           }
       }
  
    }
 private void rotarIzq(Nodo anteriorNodo) {
   
    }

    private void rotateRight(Nodo oldNodo) {
        
    }

    public int tamaño(){
         return tamaño;
     }
     
     public boolean esVacio(){
         if (tamaño == 0) {
             return true;
         }
         return false;
     }
   
}
