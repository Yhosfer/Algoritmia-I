package Sesion09.Graph.PT1;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GraphViewer<E> extends JFrame {

    private GraphLink<E> grafo;
    private Map<E, Point> posiciones;

    public GraphViewer(GraphLink<E> grafo) {
        this.grafo = grafo;
        this.posiciones = new HashMap<>();

        setTitle("WAOS");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


        calcularPosicionesSimples();

        add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.WHITE);
                dibujar(g);
            }
        });
    }

    private void calcularPosicionesSimples() {
        int numNodos = grafo.listVertex.size();
        if (numNodos == 0) return;


        int nodosPerFila = 3;

        for (int i = 0; i < numNodos; i++) {
            int fila = i / nodosPerFila;
            int columna = i % nodosPerFila;

            int x = 150 + columna * 150;
            int y = 150 + fila * 150;

            E nodo = grafo.listVertex.get(i).getData();
            posiciones.put(nodo, new Point(x, y));
        }
    }

    private void dibujar(Graphics g) {

        g.setColor(Color.BLACK);
        for (int i = 0; i < grafo.listVertex.size(); i++) {

            Vertex<E> vertice = grafo.listVertex.get(i);
            E origen = vertice.getData();
            Point posOrigen = posiciones.get(origen);

            for (int j = 0; j < vertice.listAdj.size(); j++) {
                E destino = vertice.listAdj.get(j).getRefDest().getData();
                Point posDestino = posiciones.get(destino);
                g.drawLine(posOrigen.x, posOrigen.y, posDestino.x, posDestino.y);

                Edge<E> arista = vertice.listAdj.get(j);
                if (arista.weight > -1) {
                    int midX = (posOrigen.x + posDestino.x) / 2;
                    int midY = (posOrigen.y + posDestino.y) / 2;
                    g.setColor(Color.RED);
                    g.drawString("(" + arista.weight + ")", midX, midY);
                    g.setColor(Color.BLACK);
                }
            }
        }


        for (E nodo : posiciones.keySet()) {
            Point pos = posiciones.get(nodo);


            g.setColor(Color.BLUE);
            g.fillOval(pos.x - 20, pos.y - 20, 40, 40);


            g.setColor(Color.BLACK);
            g.drawOval(pos.x - 20, pos.y - 20, 40, 40);


            g.setColor(Color.WHITE);
            String texto = nodo.toString();
            g.drawString(texto, pos.x - 5, pos.y + 5);
        }
    }

    public void mostrar() {
        setVisible(true);
    }

    public static <E> void visualizar(GraphLink<E> grafo) {
        new GraphViewer<>(grafo).mostrar();
    }
}
