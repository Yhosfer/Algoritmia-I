package Sesion09.GraphND;
import Sesion09.Exceptions.*;
import Sesion09.TDA.ListLinked;
import Sesion09.TDA.Queue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GraphLink<E> {
    protected ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new ListLinked<Vertex<E>>();
    }

    public void insertVertex(E data) {
        Vertex<E> nuevoVertice = new Vertex<>(data);
        if (!listVertex.contains(nuevoVertice)) {
            listVertex.addLast(nuevoVertice);
        }
    }

    private Vertex<E> getVertex(E data) {
        for (int i = 0; i < listVertex.size(); i++) {
            Vertex<E> actual = listVertex.get(i);
            if (actual.getData().equals(data)) {
                return actual;
            }
        }
        return null;
    }

    public void insertEdge(E verOri, E verDes) {
        Vertex<E> origen = getVertex(verOri);
        Vertex<E> destino = getVertex(verDes);

        if (origen == null) {
            origen = new Vertex<>(verOri);
            listVertex.addLast(origen);
        }

        if (destino == null) {
            destino = new Vertex<>(verDes);
            listVertex.addLast(destino);
        }

        Edge<E> arista = new Edge<>(destino);
        if (!origen.listAdj.contains(arista)) {
            origen.listAdj.addLast(arista);
        }
    }

    public void insertEdge(E verOri, E verDes, int weight) {
        Vertex<E> origen = getVertex(verOri);
        Vertex<E> destino = getVertex(verDes);

        if (origen == null) {
            origen = new Vertex<>(verOri);
            listVertex.addLast(origen);
        }

        if (destino == null) {
            destino = new Vertex<>(verDes);
            listVertex.addLast(destino);
        }

        Edge<E> arista = new Edge<>(destino, weight);
        if (!origen.listAdj.contains(arista)) {
            origen.listAdj.addLast(arista);
        }
    }

    public boolean searchVertex(E v) {
        for (int i = 0; i < listVertex.size(); i++) {
            Vertex<E> actual = listVertex.get(i);
            if (actual.getData().equals(v)) {
                return true;
            }
        }
        return false;
    }

    public boolean searchEdge(E v, E z) {
        Vertex<E> origen = getVertex(v);
        Vertex<E> destino = getVertex(z);

        if (origen == null || destino == null) {
            return false;
        }

        Edge<E> arista = new Edge<>(destino);
        return origen.listAdj.contains(arista);
    }

    public void removeVertex(E v) {
        Vertex<E> vertice = getVertex(v);
        if (vertice == null) return;

        // Eliminar aristas que apuntan al vértice desde otros vértices
        for (int i = 0; i < listVertex.size(); i++) {
            Vertex<E> actual = listVertex.get(i);
            actual.listAdj.remove(new Edge<>(vertice));
        }

        // Eliminar el vértice de la lista de vértices
        listVertex.remove(vertice);
    }

    public void removeEdge(E v, E z) {
        Vertex<E> origen = getVertex(v);
        Vertex<E> destino = getVertex(z);

        if (origen == null || destino == null) return;

        origen.listAdj.remove(new Edge<>(destino));
    }

    public void dfs(E v) {
        Vertex<E> inicio = getVertex(v);
        if (inicio == null) {
            return;
        }

        resetVisited(); // Importante para reiniciar el estado visitado
        dfsRecursive(inicio);
    }

    private void dfsRecursive(Vertex<E> vertice) {
        vertice.setVisited(true);
        System.out.println(vertice.getData());

        for (int i = 0; i < vertice.listAdj.size(); i++) {
            Edge<E> arista = vertice.listAdj.get(i);
            Vertex<E> vecino = arista.getRefDest();
            if (!vecino.isVisited()) {
                dfsRecursive(vecino);
            }
        }
    }

    private void resetVisited() {
        for (int i = 0; i < listVertex.size(); i++) {
            listVertex.get(i).setVisited(false);
        }
    }

    public String toString() {
        return this.listVertex.toString();
    }

    public void bfs(E v) {
        Vertex<E> inicio = getVertex(v);
        if (inicio == null) {
            System.out.println("Vértice no encontrado.");
            return;
        }

        resetVisited();

        Queue<Vertex<E>> cola = new Queue<>();
        inicio.setVisited(true);
        try {
            cola.enqueue(inicio);

            while (!cola.isEmpty()) {
                Vertex<E> actual = cola.dequeue();
                System.out.println(actual.getData());

                for (int i = 0; i < actual.listAdj.size(); i++) {
                    Vertex<E> vecino = actual.listAdj.get(i).getRefDest();
                    if (!vecino.isVisited()) {
                        vecino.setVisited(true);
                        cola.enqueue(vecino);
                    }
                }
            }
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error inesperado: cola vacía.");
        }

    }

    public ArrayList<E> bfsPath(E v, E z) {
        Vertex<E> inicio = getVertex(v);
        Vertex<E> fin = getVertex(z);

        ArrayList<E> path = new ArrayList<>();
        if (inicio == null || fin == null) {
            return path;
        }

        resetVisited();
        Map<Vertex<E>, Vertex<E>> padre = new HashMap<>();
        Queue<Vertex<E>> cola = new Queue<>();

        inicio.setVisited(true);
        padre.put(inicio, null);

        try {
            cola.enqueue(inicio);

            while (!cola.isEmpty()) {
                Vertex<E> actual = cola.dequeue();

                if (actual.equals(fin)) {
                    break;
                }

                for (int i = 0; i < actual.listAdj.size(); i++) {
                    Vertex<E> vecino = actual.listAdj.get(i).getRefDest();
                    if (!vecino.isVisited()) {
                        vecino.setVisited(true);
                        padre.put(vecino, actual);
                        cola.enqueue(vecino);
                    }
                }
            }
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error inesperado: cola vacía.");
        }

        if (!padre.containsKey(fin)) {
            return path; // No hay camino
        }

        for (Vertex<E> at = fin; at != null; at = padre.get(at)) {
            path.add(0, at.getData());
        }

        return path;

    }
}