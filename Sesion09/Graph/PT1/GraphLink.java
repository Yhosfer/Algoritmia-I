package Sesion09.Graph.PT1;
import Sesion09.Exceptions.*;
import Sesion09.TDA.ListLinked;
import Sesion09.TDA.Queue;
import Sesion09.TDA.Stack;
import Sesion09.TDA.PriorityQueue;

public class GraphLink<E> {
    protected ListLinked<Vertex<E>> listVertex;

    public GraphLink() {
        listVertex = new ListLinked<Vertex<E>>();
    }

    // Insertar un vértice
    public void insertVertex(E data) {
        Vertex<E> nuevoVertice = new Vertex<>(data);
        if (!listVertex.contains(nuevoVertice)) {
            listVertex.addLast(nuevoVertice);
        }
    }

    // Buscar un vértice por su dato
    private Vertex<E> getVertex(E data) {
        for (int i = 0; i < listVertex.size(); i++) {
            Vertex<E> actual = listVertex.get(i);
            if (actual.getData().equals(data)) {
                return actual;
            }
        }
        return null;
    }

    // Insertar arista sin peso
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

    // Insertar arista con peso
    public void insertEdgeWeight(E verOri, E verDes, int weight) {
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

    // Buscar si existe un vértice
    public boolean searchVertex(E v) {
        return getVertex(v) != null;
    }

    // Buscar si existe una arista
    public boolean searchEdge(E v, E z) {
        Vertex<E> origen = getVertex(v);
        Vertex<E> destino = getVertex(z);

        if (origen == null || destino == null) {
            return false;
        }

        Edge<E> arista = new Edge<>(destino);
        return origen.listAdj.contains(arista);
    }

    // Eliminar un vértice
    public void removeVertex(E v) {
        Vertex<E> vertice = getVertex(v);
        if (vertice == null) return;

        // Eliminar aristas que apuntan a este vértice
        for (int i = 0; i < listVertex.size(); i++) {
            Vertex<E> actual = listVertex.get(i);
            actual.listAdj.remove(new Edge<>(vertice));
        }

        // Eliminar el vértice
        listVertex.remove(vertice);
    }

    // Eliminar una arista
    public void removeEdge(E v, E z) {
        Vertex<E> origen = getVertex(v);
        Vertex<E> destino = getVertex(z);

        if (origen == null || destino == null) return;

        origen.listAdj.remove(new Edge<>(destino));
    }

    // Reiniciar estado visitado
    private void resetVisited() {
        for (int i = 0; i < listVertex.size(); i++) {
            listVertex.get(i).setVisited(false);
        }
    }

    // BFS que muestra los vértices visitados
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
                System.out.println(actual.getData()); // Mostrar vértice visitado

                // Agregar vecinos no visitados a la cola
                for (int i = 0; i < actual.listAdj.size(); i++) {
                    Vertex<E> vecino = actual.listAdj.get(i).getRefDest();
                    if (!vecino.isVisited()) {
                        vecino.setVisited(true);
                        cola.enqueue(vecino);
                    }
                }
            }
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: cola vacía.");
        }
    }

    // BFS que devuelve el camino entre dos vértices
    public ListLinked<E> bfsPath(E v, E z) {
        Vertex<E> inicio = getVertex(v);
        Vertex<E> fin = getVertex(z);
        ListLinked<E> camino = new ListLinked<>();

        if (inicio == null || fin == null) {
            return camino; // Vértices no encontrados
        }

        resetVisited();
        Queue<Vertex<E>> cola = new Queue<>();

        // Listas paralelas para guardar padres (como HashMap casero)
        ListLinked<E> vertices = new ListLinked<>();  // vértices procesados
        ListLinked<E> padres = new ListLinked<>();    // sus respectivos padres

        inicio.setVisited(true);
        vertices.addLast(inicio.getData());
        padres.addLast(null); // El inicio no tiene padre

        try {
            cola.enqueue(inicio);

            while (!cola.isEmpty()) {
                Vertex<E> actual = cola.dequeue();

                // Si encontramos el destino, paramos
                if (actual.equals(fin)) {
                    break;
                }

                // Procesar vecinos
                for (int i = 0; i < actual.listAdj.size(); i++) {
                    Vertex<E> vecino = actual.listAdj.get(i).getRefDest();
                    if (!vecino.isVisited()) {
                        vecino.setVisited(true);
                        vertices.addLast(vecino.getData());
                        padres.addLast(actual.getData()); // El padre del vecino es actual
                        cola.enqueue(vecino);
                    }
                }
            }
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: cola vacía.");
        }

        // Reconstruir el camino usando las listas paralelas
        E actual = fin.getData();
        ListLinked<E> caminoReverso = new ListLinked<>();

        // Buscar si llegamos al destino
        boolean encontrado = false;
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(actual)) {
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            return camino; // No hay camino
        }

        // Construir camino hacia atrás
        while (actual != null) {
            caminoReverso.addLast(actual);

            // Buscar el padre de actual
            E padre = null;
            for (int i = 0; i < vertices.size(); i++) {
                if (vertices.get(i).equals(actual)) {
                    padre = padres.get(i);
                    break;
                }
            }
            actual = padre;
        }

        // Invertir el camino
        for (int i = caminoReverso.size() - 1; i >= 0; i--) {
            camino.addLast(caminoReverso.get(i));
        }

        return camino;
    }

    // Camino más corto (sin pesos, igual que BFS)
    public ListLinked<E> shortPath(E v, E z) {
        return bfsPath(v, z); // BFS ya encuentra el camino más corto
    }

    // Verificar si el grafo es conexo
    public boolean isConexo() {
        if (listVertex.isEmpty()) return true;

        Vertex<E> inicio = listVertex.get(0);
        resetVisited();

        // Hacer BFS desde el primer vértice
        Queue<Vertex<E>> cola = new Queue<>();
        inicio.setVisited(true);

        try {
            cola.enqueue(inicio);

            while (!cola.isEmpty()) {
                Vertex<E> actual = cola.dequeue();

                for (int i = 0; i < actual.listAdj.size(); i++) {
                    Vertex<E> vecino = actual.listAdj.get(i).getRefDest();
                    if (!vecino.isVisited()) {
                        vecino.setVisited(true);
                        cola.enqueue(vecino);
                    }
                }
            }
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: cola vacía.");
        }

        // Verificar si todos fueron visitados
        for (int i = 0; i < listVertex.size(); i++) {
            if (!listVertex.get(i).isVisited()) {
                return false;
            }
        }
        return true;
    }

    // Algoritmo de Dijkstra que retorna Stack con la ruta más corta
    public Stack<E> dijkstra(E v, E w) {
        Vertex<E> inicio = getVertex(v);
        Vertex<E> fin = getVertex(w);
        Stack<E> ruta = new Stack<>();

        if (inicio == null || fin == null) {
            return ruta; // Vértices no encontrados
        }

        // Listas paralelas para distancias y padres
        ListLinked<E> vertices = new ListLinked<>();
        ListLinked<Integer> distancias = new ListLinked<>();
        ListLinked<E> padres = new ListLinked<>();

        PriorityQueue<E> pq = new PriorityQueue<>();

        // Inicializar con el vértice de inicio
        vertices.addLast(v);
        distancias.addLast(0);
        padres.addLast(null);
        pq.add(v, 0);

        while (!pq.isEmpty()) {
            E actual = pq.poll();
            Vertex<E> verticeActual = getVertex(actual);

            // Si llegamos al destino, paramos
            if (actual.equals(w)) {
                break;
            }

            // Obtener distancia actual
            int distActual = 0;
            for (int i = 0; i < vertices.size(); i++) {
                if (vertices.get(i).equals(actual)) {
                    distActual = distancias.get(i);
                    break;
                }
            }

            // Procesar vecinos
            for (int i = 0; i < verticeActual.listAdj.size(); i++) {
                Edge<E> arista = verticeActual.listAdj.get(i);
                E vecino = arista.getRefDest().getData();
                int peso = (arista.weight > -1) ? arista.weight : 1;
                int nuevaDist = distActual + peso;

                // Buscar si ya tenemos este vecino
                int indicVecino = -1;
                for (int j = 0; j < vertices.size(); j++) {
                    if (vertices.get(j).equals(vecino)) {
                        indicVecino = j;
                        break;
                    }
                }

                // Si es nuevo o encontramos mejor camino
                if (indicVecino == -1 || nuevaDist < distancias.get(indicVecino)) {
                    if (indicVecino == -1) {
                        // Agregar nuevo vértice
                        vertices.addLast(vecino);
                        distancias.addLast(nuevaDist);
                        padres.addLast(actual);
                    } else {
                        // Actualizar existente
                        distancias.remove(distancias.get(indicVecino));
                        padres.remove(padres.get(indicVecino));
                        distancias.addLast(nuevaDist);
                        padres.addLast(actual);
                    }
                    pq.add(vecino, nuevaDist);
                }
            }
        }

        // Reconstruir ruta y meterla en el stack
        E actual = w;
        ListLinked<E> caminoTemp = new ListLinked<>();

        // Verificar si llegamos al destino
        boolean llegamos = false;
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(w)) {
                llegamos = true;
                break;
            }
        }

        if (!llegamos) {
            return ruta; // No hay camino
        }

        // Construir camino hacia atrás
        while (actual != null) {
            caminoTemp.addLast(actual);

            // Buscar padre
            E padre = null;
            for (int i = 0; i < vertices.size(); i++) {
                if (vertices.get(i).equals(actual)) {
                    padre = padres.get(i);
                    break;
                }
            }
            actual = padre;
        }

        // Meter en stack (el stack naturalmente invierte el orden)
        for (int i = caminoTemp.size() - 1; i >= 0; i--) {
            ruta.push(caminoTemp.get(i));
        }

        return ruta;
    }

    public String toString() {
        return this.listVertex.toString();
    }
}