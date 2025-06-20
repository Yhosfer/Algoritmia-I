package Sesion10.LecturaTxt;

import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class BNode<E extends Comparable<E>> {
    protected int idNodo;
    protected int idPadre;
    protected ArrayList<E> keys;
    protected ArrayList<BNode<E>> childs;
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

    public boolean nodeFull() {
        return count == keys.size();
    }

    public boolean nodeEmpty() {
        return count == 0;
    }

    public boolean searchNode(E key, int[] pos) {
        int i = 0;

        while (i < count && key.compareTo(keys.get(i)) > 0) {
            i++;
        }

        //Retornamos por referencia la posición de donde se posiciona el nodo
        pos[0] = i;

        return (i < count && key.compareTo(keys.get(i)) == 0);
    }

    public boolean nodeFull(int maxKeys) {
        return count == maxKeys;
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

    // Debes importar:
// import java.io.*;
// import java.util.*;

    public static Btree<Integer> building_Btree(String filename) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;

        // 1️⃣ Leer orden
        int orden = Integer.parseInt(br.readLine().trim());
        Btree<Integer> tree = new Btree<>(orden);

        // 2️⃣ Estructuras para reconstruir nodos
        Map<Integer, List<BNode<Integer>>> levels = new HashMap<>();
        Map<Integer, BNode<Integer>> idMap = new HashMap<>();

        // 3️⃣ Leer nodos
        while ((line = br.readLine()) != null) {
            String[] parts = line.trim().split(",");
            int nivel = Integer.parseInt(parts[0]);
            int idNodo = Integer.parseInt(parts[1]);

            BNode<Integer> node = new BNode<>(orden - 1);
            node.idNodo = idNodo;
            node.count = parts.length - 2;
            for (int i = 2; i < parts.length; i++) {
                node.keys.set(i - 2, Integer.parseInt(parts[i].trim()));
            }

            levels.putIfAbsent(nivel, new ArrayList<>());
            levels.get(nivel).add(node);
            idMap.put(idNodo, node);
        }
        br.close();

        // 4️⃣ Construir relaciones padre-hijo
        for (int nivel = 0; levels.containsKey(nivel); nivel++) {
            List<BNode<Integer>> nodes = levels.get(nivel);
            for (BNode<Integer> node : nodes) {
                if (nivel == 0) {
                    tree.root = node; // raíz
                    continue;
                }
                // Buscar padre en nivel anterior
                List<BNode<Integer>> padres = levels.get(nivel - 1);
                boolean enlazado = false;
                for (BNode<Integer> padre : padres) {
                    for (int i = 0; i <= padre.count; i++) {
                        if (padre.childs.get(i) == null) {
                            padre.childs.set(i, node);
                            node.idPadre = padre.idNodo;
                            enlazado = true;
                            break;
                        }
                    }
                    if (enlazado) break;
                }
                if (!enlazado) {
                    throw new Exception("No se pudo enlazar el nodo id=" + node.idNodo + " a un padre válido");
                }
            }
        }

        // 5️⃣ Verificar propiedades B-Tree
        int minKeys = (int) Math.ceil(orden / 2.0) - 1;
        int leafLevel = -1;
        Queue<BNode<Integer>> queue = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();
        queue.add(tree.root);
        levelQueue.add(0);
        while (!queue.isEmpty()) {
            BNode<Integer> current = queue.poll();
            int lvl = levelQueue.poll();

            if (current != tree.root && current.count < minKeys) {
                throw new Exception("Nodo id=" + current.idNodo + " viola minKeys: tiene " + current.count);
            }
            if (current.count > orden - 1) {
                throw new Exception("Nodo id=" + current.idNodo + " excede maxKeys");
            }

            boolean isLeaf = true;
            for (BNode<Integer> child : current.childs) {
                if (child != null) {
                    queue.add(child);
                    levelQueue.add(lvl + 1);
                    isLeaf = false;
                }
            }
            if (isLeaf) {
                if (leafLevel == -1) leafLevel = lvl;
                else if (leafLevel != lvl) {
                    throw new Exception("Nodos hoja en distintos niveles");
                }
            }
        }

        return tree;
    }

}
