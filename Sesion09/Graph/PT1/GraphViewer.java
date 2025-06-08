package Sesion09.Graph.PT1;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class GraphViewer<E> extends JFrame {

    private GraphLink<E> grafo;
    private Map<E, Point> posiciones;
    private final int RADIO_NODO = 25;

    public GraphViewer(GraphLink<E> grafo) {
        this.grafo = grafo;
        this.posiciones = new HashMap<>();

        setTitle("Grafo");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        calcularPosiciones();

        add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                setBackground(Color.WHITE);
                dibujar(g);
            }
        });
    }

    private void calcularPosiciones() {
        int numNodos = grafo.listVertex.size();
        if (numNodos == 0) return;

        int centroX = 300;
        int centroY = 250;
        int radio = 150;

        for (int i = 0; i < numNodos; i++) {
            double angulo = 2 * Math.PI * i / numNodos;
            int x = centroX + (int)(radio * Math.cos(angulo));
            int y = centroY + (int)(radio * Math.sin(angulo));

            E nodo = grafo.listVertex.get(i).getData();
            posiciones.put(nodo, new Point(x, y));
        }
    }

    private void dibujar(Graphics g) {
        // Dibujar aristas
        g.setColor(Color.BLACK);
        for (int i = 0; i < grafo.listVertex.size(); i++) {
            Vertex<E> vertice = grafo.listVertex.get(i);
            E origen = vertice.getData();
            Point posOrigen = posiciones.get(origen);

            for (int j = 0; j < vertice.listAdj.size(); j++) {
                E destino = vertice.listAdj.get(j).getRefDest().getData();
                Point posDestino = posiciones.get(destino);
                g.drawLine(posOrigen.x, posOrigen.y, posDestino.x, posDestino.y);
            }
        }

        // Dibujar nodos
        for (E nodo : posiciones.keySet()) {
            Point pos = posiciones.get(nodo);

            // Círculo azul
            g.setColor(Color.CYAN);
            g.fillOval(pos.x - RADIO_NODO/2, pos.y - RADIO_NODO/2, RADIO_NODO, RADIO_NODO);

            // Borde negro
            g.setColor(Color.BLACK);
            g.drawOval(pos.x - RADIO_NODO/2, pos.y - RADIO_NODO/2, RADIO_NODO, RADIO_NODO);

            // Texto del nodo
            String texto = nodo.toString();
            FontMetrics fm = g.getFontMetrics();
            int ancho = fm.stringWidth(texto);
            g.drawString(texto, pos.x - ancho/2, pos.y + 5);
        }
    }

    public void mostrar() {
        setVisible(true);
    }

    public static <E> void visualizar(GraphLink<E> grafo) {
        new GraphViewer<>(grafo).mostrar();
    }
}