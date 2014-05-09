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
public class Nodo {
    int dato;
    Nodo dere,izq;
    Nodo raiz;
    public void ArbolAvl(){
        raiz=null;
    }
    
    public void insertar(int dato){
        Nodo nuevo;
        nuevo = new Nodo();
        nuevo.dato = dato;
        nuevo.dere = null;
        nuevo.izq = null;
        if (raiz == null){
            raiz = nuevo;
        }else{
            Nodo ant = null,recorre;
            recorre = raiz;
            while(recorre != null){
                ant = recorre;
                if (dato < recorre.dato){
                   recorre = recorre.dere;
       
                }else {
                 recorre = recorre.izq;
                }
                
               if (dato < ant.dato){
                   ant.dere = nuevo;
 
               } else {
                   ant.izq = nuevo;
               }
 
            }

        }
    }
}
