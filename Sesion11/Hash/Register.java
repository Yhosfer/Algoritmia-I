package Sesion11.Hash;
public class Register<T> {
    private int clave;
    private T valor;

    public Register(int clave, T valor) {
        this.clave = clave;
        this.valor = valor;
    }
    public int getClave() {
        return clave;
    }
    public T getValor() {
        return valor;
    }
    public void setValor(T nuevoValor) {
        this.valor = nuevoValor;
    }
    @Override
    public String toString() {
        return "(" + clave + ", " + valor + ")";
    }
}
