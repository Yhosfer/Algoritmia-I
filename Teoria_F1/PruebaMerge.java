package Teoria_F1;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class PruebaMerge {
    public static ArrayList mergeSort(ArrayList<Integer> lista){
        // caso base: si la lista tiene 0 o 1 elementos, ya está ordenada
        if(lista.size() <=1){
            return lista;
        }

        // Dividir la lista en dos mitades
        int medio = lista.size()/2;

        // crear listas vacias
        ArrayList<Integer> izquierda = new ArrayList();
        ArrayList<Integer> derecha = new ArrayList();
        // Llenar la lista iz de 0 hasta medio-1
        for( int i = 0 ; i < medio ; i++){
            izquierda.add(lista.get(i));
        }
        // llenar la lista derecha ( desde medio hasta final
        for (int i = medio; i < lista.size(); i++){
            derecha.add(lista.get(i));
        }
        // ordenar recursivamente cada mitas
        izquierda = mergeSort(izquierda);
        derecha = mergeSort(derecha);

        return merge(izquierda,derecha);
    }

    private static ArrayList merge(ArrayList<Integer> izquierda, ArrayList<Integer> derecha){
        ArrayList<Integer> result = new ArrayList();
        int i = 0; // indice para la lista izquierda
        int j= 0; // indice para lissta derecha
        // Compara elemntos de ambas listas y añnadir el menos al resultado
        while (i < izquierda.size() && j< derecha.size()){
            if(izquierda.get(i).compareTo(derecha.get(j)) <= 0){
                result.add(izquierda.get(i));
                i++;
            }else {
                result.add(derecha.get(j));
                j++;
            }
        }
        // añadir elementos restantes de la lista izquierda
        while (i < izquierda.size()){
            result.add(izquierda.get(i));
            i++;
        }
        while (j < derecha.size()){
            result.add(derecha.get(j));
            j++;
        }
        return result;
    }
    public static void main(String[] args) {
        ArrayList<Integer> listaPrueba = new ArrayList<>();
        listaPrueba.add(38);
        listaPrueba.add(27);
        listaPrueba.add(43);
        listaPrueba.add(3);
        listaPrueba.add(9);
        listaPrueba.add(82);
        listaPrueba.add(10);
        ArrayList<Integer> listaOrdenada = mergeSort(listaPrueba);

        System.out.println("Lista ordenada:" + listaOrdenada);


    }
}
