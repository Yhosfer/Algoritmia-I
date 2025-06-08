package Sesion09;
import Sesion09.TDA.*;
import Sesion09.Exceptions.*;
import Sesion09.GraphND.*;
public class Prueba {
    public static void main(String[] args) {
        GraphLink<String> grafo = new GraphLink();
        grafo.insertVertex("A");
        grafo.insertVertex("B");
        grafo.insertVertex("C");
        grafo.insertVertex("D");
        grafo.insertVertex("E");

        grafo.insertEdge("A", "B",1);
        grafo.insertEdge("A", "C",1);
//        grafo.insertEdge("B", "D",2);
//        grafo.insertEdge("C", "D",6);
        grafo.insertEdge("D", "E",6);
        System.out.println(grafo);
        grafo.dfs("A");

        grafo.bfs("A");
        System.out.println("=======");
        System.out.println(grafo.bfsPath("A","E"));


    }
}
