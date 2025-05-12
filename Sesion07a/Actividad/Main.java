package Sesion07a.Actividad;

import Sesion07a.Actividad.Queue.ExceptionIsEmpty;
import Sesion07a.Exceptions.ItemDuplicated;
import Sesion07a.Exceptions.ItemNoFound;

public class Main {
    public static void main(String[] args) {
        try {
            LinkedBST<Integer> bst = new LinkedBST<>();

            // Insertar elementos
            bst.insert(50);
            bst.insert(60);
            bst.insert(70);
            bst.insert(80);
            bst.insert(90);
            bst.insert(120);
            bst.insert(180);

            // Mostrar recorridos
            System.out.println("InOrder: " + bst.inOrder());
            System.out.println("PreOrder: " + bst.preOrder());
            System.out.println("PostOrder: " + bst.postOrder());

            // Dibujar árbol
            System.out.println("Árbol dibujado:");
            bst.drawBST();

            // Buscar un nodo
            System.out.println("Buscar 40: " + bst.search(40));

            // Altura de un nodo
            System.out.println("Altura de 30: " + bst.height(30));

            // Amplitud en nivel 2
            System.out.println("Amplitud en nivel 2: " + bst.amplitude(2));

            // Contar nodos con hijos
            System.out.println("Nodos con hijos: " + bst.countNodes());

            // Contar todos los nodos
            System.out.println("Todos los nodos: " + bst.countAllNodes());

            // Área del árbol
            System.out.println("Área del árbol: " + bst.areaBST());

            // Representación con paréntesis
            System.out.println("Parenthesize:");
            System.out.println(bst.parenthesize());

            // Eliminar nodo
            bst.delete(30);
            System.out.println("InOrder después de eliminar 30: " + bst.inOrder());

            // Destruir árbol
            bst.destroyNodes();
            System.out.println("¿Árbol vacío? " + bst.isEmpty());

        } catch (ItemDuplicated e) {
            System.out.println("Error de duplicado: " + e.getMessage());
        } catch (ItemNoFound e) {
            System.out.println("Error de búsqueda/eliminación: " + e.getMessage());
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error de operación: " + e.getMessage());
        } catch (Sesion06.Actividad02.ExceptionIsEmpty e) {
            throw new RuntimeException(e);
        }
    }
}