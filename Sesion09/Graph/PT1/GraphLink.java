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

    public boolean searchVertex(E v) {
        return getVertex(v) != null;
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

        for (int i = 0; i < listVertex.size(); i++) {
            Vertex<E> actual = listVertex.get(i);
            actual.listAdj.remove(new Edge<>(vertice));
        }

        listVertex.remove(vertice);

    }

    public void removeEdge(E v, E z) {
        Vertex<E> origen = getVertex(v);
        Vertex<E> destino = getVertex(z);

        if (origen == null || destino == null) return;

        origen.listAdj.remove(new Edge<>(destino));
    }

    private void resetVisited() {
        for (int i = 0; i < listVertex.size(); i++) {
            listVertex.get(i).setVisited(false);
        }
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
            System.out.println("Error: cola vacía.");
        }
    }


    public ListLinked<E> bfsPath(E v, E z) {
        Vertex<E> inicio = getVertex(v);
        Vertex<E> fin = getVertex(z);
        ListLinked<E> camino = new ListLinked<>();

        if (inicio == null || fin == null) {
            return camino;
        }

        resetVisited();
        Queue<Vertex<E>> cola = new Queue<>();


        ListLinked<E> vertices = new ListLinked<>();
        ListLinked<E> padres = new ListLinked<>();

        inicio.setVisited(true);
        vertices.addLast(inicio.getData());
        padres.addLast(null);

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
                        vertices.addLast(vecino.getData());
                        padres.addLast(actual.getData()); // El padre del vecino es actual
                        cola.enqueue(vecino);
                    }
                }
            }
        } catch (ExceptionIsEmpty e) {
            System.out.println("Error: cola vacía.");
        }

        E actual = fin.getData();
        ListLinked<E> caminoReverso = new ListLinked<>();

        boolean encontrado = false;
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(actual)) {
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            return camino;
        }

        while (actual != null) {
            caminoReverso.addLast(actual);

            E padre = null;
            for (int i = 0; i < vertices.size(); i++) {
                if (vertices.get(i).equals(actual)) {
                    padre = padres.get(i);
                    break;
                }
            }
            actual = padre;
        }


        for (int i = caminoReverso.size() - 1; i >= 0; i--) {
            camino.addLast(caminoReverso.get(i));
        }

        return camino;
    }

    public ListLinked<E> shortPath(E v, E z) {
        return bfsPath(v, z);
    }

    public boolean isConexo() {
        if (listVertex.isEmpty()) return false;

        Vertex<E> inicio = listVertex.get(0);
        resetVisited();

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

        for (int i = 0; i < listVertex.size(); i++) {
            if (!listVertex.get(i).isVisited()) {
                return false;
            }
        }
        return true;
    }

    public Stack<E> dijkstra(E v, E w) {
        Vertex<E> inicio = getVertex(v);
        Vertex<E> fin = getVertex(w);
        Stack<E> ruta = new Stack<>();

        if (inicio == null || fin == null) {
            return ruta;
        }


        ListLinked<E> vertices = new ListLinked<>();
        ListLinked<Integer> distancias = new ListLinked<>();
        ListLinked<E> padres = new ListLinked<>();

        PriorityQueue<E> pq = new PriorityQueue<>();


        vertices.addLast(v);
        distancias.addLast(0);
        padres.addLast(null);
        pq.add(v, 0);

        while (!pq.isEmpty()) {
            E actual = pq.poll();
            Vertex<E> verticeActual = getVertex(actual);


            if (actual.equals(w)) {
                break;
            }

            int distActual = 0;
            for (int i = 0; i < vertices.size(); i++) {
                if (vertices.get(i).equals(actual)) {
                    distActual = distancias.get(i);
                    break;
                }
            }

            for (int i = 0; i < verticeActual.listAdj.size(); i++) {
                Edge<E> arista = verticeActual.listAdj.get(i);
                E vecino = arista.getRefDest().getData();
                int peso = (arista.weight > -1) ? arista.weight : 1;
                int nuevaDist = distActual + peso;

                int indicVecino = -1;
                for (int j = 0; j < vertices.size(); j++) {
                    if (vertices.get(j).equals(vecino)) {
                        indicVecino = j;
                        break;
                    }
                }

                if (indicVecino == -1 || nuevaDist < distancias.get(indicVecino)) {
                    if (indicVecino == -1) {

                        vertices.addLast(vecino);
                        distancias.addLast(nuevaDist);
                        padres.addLast(actual);
                    } else {

                        distancias.remove(distancias.get(indicVecino));
                        padres.remove(padres.get(indicVecino));
                        distancias.addLast(nuevaDist);
                        padres.addLast(actual);
                    }
                    pq.add(vecino, nuevaDist);
                }
            }
        }


        E actual = w;
        ListLinked<E> caminoTemp = new ListLinked<>();


        boolean llegamos = false;
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).equals(w)) {
                llegamos = true;
                break;
            }
        }

        if (!llegamos) {
            return ruta;
        }


        while (actual != null) {
            caminoTemp.addLast(actual);

            E padre = null;
            for (int i = 0; i < vertices.size(); i++) {
                if (vertices.get(i).equals(actual)) {
                    padre = padres.get(i);
                    break;
                }
            }
            actual = padre;
        }

        for (int i = caminoTemp.size() - 1; i >= 0; i--) {
            ruta.push(caminoTemp.get(i));
        }

        return ruta;
    }

    public String toString() {
        return this.listVertex.toString();
    }
}