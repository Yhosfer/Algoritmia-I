package Sesion11.Main;

import Sesion11.Hash.HashCVisual;

import javax.swing.*;
import java.util.Scanner;

public class TestHashVisual {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Ingresa la cantidad de espacios de la tabla Hash: ");
        int tamaño = -5;

        while (tamaño <= 0) {
            tamaño = sc.nextInt();
        }

        int finalTamaño = tamaño;

        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            HashCVisual<String> visualizador = new HashCVisual<>(finalTamaño);
            visualizador.setVisible(true);
        });

    }

}
