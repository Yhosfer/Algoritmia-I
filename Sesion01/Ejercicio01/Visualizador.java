package Sesion01.Ejercicio01;

import javax.swing.*;
import java.awt.*;

//JFrame = clase que declara esta clase como una ventana gráfica
public class Visualizador extends JFrame {
    private Rectangulo r1, r2;

    public Visualizador(Rectangulo r1, Rectangulo r2) {
        this.r1 = r1;
        this.r2 = r2;

        setTitle("Rectángulos");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);
                dibujar(g, r1);
                g.setColor(Color.RED);
                dibujar(g, r2);
            }
        });

        setVisible(true);
    }

    private void dibujar(Graphics g, Rectangulo r) {

        int x = (int) r.getEsquina1().getX() * 50;
        int y = (int) r.getEsquina1().getY() * 50;
        int ancho = (int) (r.getEsquina2().getX() - r.getEsquina1().getX()) * 50;
        int alto = (int) (r.getEsquina2().getY() - r.getEsquina1().getY()) * 50;
        g.drawRect(x, y, ancho, alto);

    }
}
