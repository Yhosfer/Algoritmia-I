package Sesion04.Actividad02;

import javax.swing.*;
import java.awt.*;

public class ArbolPitagoras extends JFrame {

    public ArbolPitagoras(int profundidad) {
        setTitle("Árbol de Pitágoras - " + profundidad + " niveles");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new PanelArbol(profundidad));
    }

    public static void main(String[] args) {
        // Árboles de 6, 8 y 10 niveles
        new ArbolPitagoras(6).setVisible(true);
        new ArbolPitagoras(8).setVisible(true);
        new ArbolPitagoras(10).setVisible(true);
    }

    static class PanelArbol extends JPanel {
        private int profundidad;

        public PanelArbol(int profundidad) {
            this.profundidad = profundidad;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            setBackground(Color.BLACK);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(Color.GREEN);
            int x = getWidth() / 2;
            int y = getHeight() - 50;
            int lado = 100;
            double angulo = -90; // hacia arriba
            trazaArbol(g2d, x, y, lado, angulo, profundidad);
        }

        private void trazaArbol(Graphics2D g, int x, int y, int lado, double angulo, int nivel) {
            if (nivel == 0 || lado < 2) return;

            int x2 = x + (int) (lado * Math.cos(Math.toRadians(angulo)));
            int y2 = y + (int) (lado * Math.sin(Math.toRadians(angulo)));

            g.drawLine(x, y, x2, y2);

            int nuevoLado = (int) (lado * 0.7);
            trazaArbol(g, x2, y2, nuevoLado, angulo - 45, nivel - 1);
            trazaArbol(g, x2, y2, nuevoLado, angulo + 45, nivel - 1);
        }
    }
}
