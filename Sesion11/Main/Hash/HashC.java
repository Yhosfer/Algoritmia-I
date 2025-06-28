package Sesion11.Main.Hash;

import Sesion11.Hash.Register;

public class HashC<T> {
    private final Elemento[] tabla;
    private final int tamaño;

    private class Elemento<T> {
        Register<T> registro;
        int estado;

        Elemento() {
            this.registro = null;
            this.estado = 0;
        }
    }

    public HashC(int tamaño) {
        this.tamaño = tamaño;
        tabla = new Elemento[tamaño];
        for (int i = 0; i < tamaño; i++) {
            tabla[i] = new Elemento();
        }
    }

    private int funcionHash(int clave) {
        return clave % tamaño;
    }

    public int insertar(Register<T> reg) {
        int clave = reg.getClave();
        int pos = funcionHash(clave);
        int intentos = 0;

        while (intentos < tamaño) {
            if (tabla[pos].estado == 0 || tabla[pos].estado == -1) {
                tabla[pos].registro = reg;
                tabla[pos].estado = 1;
                return pos;
            } else if (tabla[pos].estado == 1 && tabla[pos].registro.getClave() == clave) {
                tabla[pos].registro.setValor(reg.getValor()); // actualiza
                return pos;
            }

            pos = (pos + 1) % tamaño;
            intentos++;
        }

        return -1;
    }

    public Register<T> buscar(int clave) {
        int pos = funcionHash(clave);
        int intentos = 0;

        while (intentos < tamaño) {
            if (tabla[pos].estado == 0) return null;
            if (tabla[pos].estado == 1 && tabla[pos].registro.getClave() == clave) {
                return tabla[pos].registro;
            }

            pos = (pos + 1) % tamaño;
            intentos++;
        }

        return null;
    }


    public boolean eliminar(int clave) {
        int pos = funcionHash(clave);
        int intentos = 0;

        while (intentos < tamaño) {
            if (tabla[pos].estado == 0) return false;
            if (tabla[pos].estado == 1 && tabla[pos].registro.getClave() == clave) {
                tabla[pos].registro = null;
                tabla[pos].estado = -1;
                return true;
            }

            pos = (pos + 1) % tamaño;
            intentos++;
        }

        return false;
    }


    public void mostrar() {
        System.out.println("TABLA HASH CERRADA:");
        for (int i = 0; i < tamaño; i++) {
            System.out.print(i + " → ");
            switch (tabla[i].estado) {
                case 0 -> System.out.println("VACÍO");
                case -1 -> System.out.println("BORRADO");
                case 1 -> System.out.println(tabla[i].registro);
            }
        }
    }
}
