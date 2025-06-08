package Sesion09.Graph.PT1;

public class Prueba {
    public static void main(String[] args) {
        GraphLink<String> grafo = new GraphLink<>();

        grafo.insertEdge("A", "B");
        grafo.insertEdge("A", "C");
        grafo.insertEdge("B", "D");
        grafo.insertEdge("C", "D");
        grafo.insertEdge("D", "E");

        GraphViewer.visualizar(grafo);


    }
}
