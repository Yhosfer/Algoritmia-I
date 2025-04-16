import java.util.ArrayList;

public class App {
    
    public static <T extends Comparable<T>> ArrayList<T> mergeSort(ArrayList<T> lista) {
        // Caso base: si la lista tiene 0 o 1 elementos, ya está ordenada
        if (lista.size() <= 1) {
            return lista;
        }
        
        // Dividir la lista en dos mitades
        int medio = lista.size() / 2;
        
        // Crear listas vacías
        ArrayList<T> izquierda = new ArrayList<>();
        ArrayList<T> derecha = new ArrayList<>();

        // Llenar la lista izquierda (desde 0 hasta medio - 1)
        for (int i = 0; i < medio; i++) {
            izquierda.add(lista.get(i));
        }

        // Llenar la lista derecha (desde medio hasta el final)
        for (int i = medio; i < lista.size(); i++) {
            derecha.add(lista.get(i));
        }
        // Ordenar recursivamente cada mitad
        izquierda = mergeSort(izquierda);
        derecha = mergeSort(derecha);
        
        // Combinar las mitades ordenadas
        return merge(izquierda, derecha);
    }
    
    private static <T extends Comparable<T>> ArrayList<T> merge(ArrayList<T> izquierda, ArrayList<T> derecha) {
        ArrayList<T> resultado = new ArrayList<>();
        int i = 0; // Índice para la lista izquierda
        int j = 0; // Índice para la lista derecha
        
        // Comparar elementos de ambas listas y añadir el menor al resultado
        while (i < izquierda.size() && j < derecha.size()) {
            if (izquierda.get(i).compareTo(derecha.get(j)) <= 0) {
                resultado.add(izquierda.get(i));
                i++;
            } else {
                resultado.add(derecha.get(j));
                j++;
            }
        }
        
        // Añadir elementos restantes de la lista izquierda
        while (i < izquierda.size()) {
            resultado.add(izquierda.get(i));
            i++;
        }
        
        // Añadir elementos restantes de la lista derecha
        while (j < derecha.size()) {
            resultado.add(derecha.get(j));
            j++;
        }
        
        return resultado;
    }
    
    public static void main(String[] args) {
        // Ejemplo de uso
        ArrayList<Integer> lista = new ArrayList<>();
        lista.add(38);
        lista.add(27);
        lista.add(43);
        lista.add(3);
        lista.add(9);
        lista.add(82);
        lista.add(10);
        
        ArrayList<Integer> listaOrdenada = mergeSort(lista);
        
        System.out.println("Lista original: " + lista);
        System.out.println("Lista ordenada: " + listaOrdenada);
    }
}
