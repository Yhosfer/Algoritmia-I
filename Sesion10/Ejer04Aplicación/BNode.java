package Sesion10.Ejer04Aplicación;

import java.util.ArrayList;

public class BNode {

    protected int idNodo;
    protected int idPadre;
    protected ArrayList<RegistroEstudiante> keys;
    protected ArrayList<BNode> childs;
    protected int count;

    public BNode(int n) {
        this.idNodo = -1;   // se asignará desde Btree
        this.idPadre = -1;
        this.keys = new ArrayList<>(n);
        this.childs = new ArrayList<>(n + 1);
        this.count = 0;

        for (int i = 0; i < n; i++) {
            this.keys.add(null);
            this.childs.add(null);
        }
        this.childs.add(null);
    }

    public boolean nodeFull(int i) {
        return count == keys.size();
    }

    public boolean nodeEmpty() {
        return count == 0;
    }

    public boolean searchNode(RegistroEstudiante key, int[] pos) {
        int i = 0;
        while (i < count && key.compareTo(keys.get(i)) > 0) {
            i++;
        }
        pos[0] = i;
        return (i < count && key.compareTo(keys.get(i)) == 0);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < count; i++) {
            sb.append(keys.get(i));
            if (i < count - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
