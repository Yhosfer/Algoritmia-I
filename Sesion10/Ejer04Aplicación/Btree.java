package Sesion10.Ejer04Aplicación;

public class Btree<E extends Comparable<E>> {

    public BNode<E> root;
    public  int orden; //Cantidad de hijos
    public boolean up;
    public BNode<E> nDes;
    private int idCounter = 1;

    public Btree(int orden) {
        this.orden = orden;
        this.root  = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    //inicio Inserción

    public void insert(E key) {
        up = false;
        nDes = null;

        E mediana = push(root, key);

        //En caso el nodo esté lleno y la mediana suba
        if (up) {
            BNode<E> nuevaRaiz = new BNode<>(orden);
            nuevaRaiz.count = 1;
            nuevaRaiz.keys  .set(0,mediana);
            nuevaRaiz.childs.set(0,root);
            nuevaRaiz.childs.set(1,nDes);
            root = nuevaRaiz;
            up   = false;
        }
    }

    private E push(BNode<E> current, E key) {
        //Cuando llegue a la posición deseada
        if (current == null) {
            up = true;
            nDes = null;
            return key;
        }

        int[] pos = new int[1];
        boolean found = current.searchNode(key, pos);

        if (found) {
            System.out.println("Item duplicado");
            up = false;
            return null;
        }

        E mediana = push(current.childs.get(pos[0]),key);

        if (up) {
            //En caso esté lleno
            if (current.nodeFull(orden - 1)) {
                mediana = dividedNode(current,mediana,pos[0]);
            }
            //En caso no esté lleno
            else {
                putNode(current,mediana,nDes,pos[0]);
                up = false;
            }
        }

        return mediana;
    }

    private void putNode(BNode<E> current, E key, BNode<E> rightChild, int k) {

        for (int i = current.count - 1; i >= k; i--) {
            current.keys.set(i + 1,current.keys.get(i));
            current.childs.set(i + 2,current.childs.get(i + 1));
        }

        current.keys.set(k, key);
        current.childs.set(k + 1, rightChild);
        current.count++;

    }

    //final Inserción

    private E dividedNode(BNode<E> current, E key, int k) {

        BNode<E> rightFromChild = nDes;
        int mid = orden / 2;
        nDes = new BNode<>(orden);

        for (int i = mid; i < orden - 1; i++) {
            nDes.keys.set(i - mid, current.keys.get(i));
            nDes.childs.set(i - mid + 1, current.childs.get(i + 1));
        }

        nDes.count = (orden - 1) - mid;
        current.count = mid;

        if (k <= mid) {
            putNode(current, key,rightFromChild, k);
        } else {
            putNode(nDes, key, rightFromChild, k - mid - 1);
        }

        E median = current.keys.get(current.count - 1);
        nDes.childs.set(0,current.childs.get(current.count));
        current.count--;

        return median;
    }
    // Ejerc 01
    public boolean search(E key) {
        return searchRecursivo(root, key);
    }

    private boolean searchRecursivo(BNode<E> current, E key) {
        if (current == null) return false;

        int[] pos = new int[1];
        boolean found = current.searchNode(key, pos);

        if (found) {
            System.out.println(key + " se encuentra en el nodo " + current.idNodo + " en la posición " + pos[0]);
            return true;
        } else {
            return searchRecursivo(current.childs.get(pos[0]), key);
        }
    }

    public void delete(E cl) {
        recDelete(root, cl);

        if (root != null && root.count == 0) {
            root = root.childs.get(0);
        }

    }

    private void recDelete(BNode<E> node, E cl) {
        if (node == null) return;

        int[] pos = new int[1];
        boolean found = node.searchNode(cl, pos);

        if (found) {
            if (node.childs.get(pos[0]) == null) {
                removeFromLeaf(node, pos[0]);
            } else {

                BNode<E> succNode = node.childs.get(pos[0] + 1);
                while (succNode.childs.get(0) != null) succNode = succNode.childs.get(0);
                E succKey = succNode.keys.get(0);
                node.keys.set(pos[0], succKey);
                recDelete(node.childs.get(pos[0] + 1), succKey);

            }

        } else {

            BNode<E> child = node.childs.get(pos[0]);
            if (child == null) return;

            if (child.count == (orden - 1) / 2) {
                fixChild(node, pos[0]);
            }

            recDelete(node.childs.get(pos[0]), cl);

        }

    }

    private void removeFromLeaf(BNode<E> node, int idx) {

        for (int i = idx; i < node.count - 1; i++) {
            node.keys  .set(i, node.keys  .get(i + 1));
            node.childs.set(i, node.childs.get(i + 1));
        }

        node.childs.set(node.count - 1, node.childs.get(node.count));
        node.count--;

    }

    private void fixChild(BNode<E> parent, int idxChild) {

        BNode<E> child = parent.childs.get(idxChild);

        if (idxChild > 0) {

            BNode<E> left = parent.childs.get(idxChild - 1);

            if (left.count > (orden - 1) / 2) {
                borrowRight(child, parent, left, idxChild - 1);
                return;
            }

        }

        if (idxChild < parent.count) {
            BNode<E> right = parent.childs.get(idxChild + 1);
            if (right.count > (orden - 1) / 2) {
                borrowLeft(child, parent, right, idxChild);
                return;
            }
        }

        if (idxChild > 0) {
            merge(parent, idxChild - 1);
        } else {
            merge(parent, idxChild);
        }

    }

    private void borrowRight(BNode<E> child, BNode<E> parent, BNode<E> left, int keyPosParent) {

        for (int i = child.count; i > 0; i--) {
            child.keys  .set(i, child.keys.get(i - 1));
            child.childs.set(i + 1, child.childs.get(i));
        }

        child.childs.set(1, child.childs.get(0));
        child.keys.set(0, parent.keys.get(keyPosParent));
        child.count++;

        parent.keys.set(keyPosParent, left.keys.get(left.count - 1));
        child.childs.set(0, left.childs.get(left.count));
        left.count--;

    }

    private void borrowLeft(BNode<E> child, BNode<E> parent, BNode<E> right, int keyPosParent) {

        child.keys.set(child.count, parent.keys.get(keyPosParent));
        child.childs.set(child.count + 1, right.childs.get(0));
        child.count++;

        parent.keys.set(keyPosParent, right.keys.get(0));

        for (int i = 0; i < right.count - 1; i++) {
            right.keys.set(i, right.keys  .get(i + 1));
            right.childs.set(i, right.childs.get(i + 1));
        }

        right.childs.set(right.count - 1, right.childs.get(right.count));
        right.count--;

    }

    private void merge(BNode<E> parent, int idx) {

        BNode<E> left  = parent.childs.get(idx);
        BNode<E> right = parent.childs.get(idx + 1);

        left.keys.set(left.count, parent.keys.get(idx));
        left.childs.set(left.count + 1, right.childs.get(0));
        left.count++;

        for (int i = 0; i < right.count; i++) {
            left.keys.set(left.count , right.keys.get(i));
            left.childs.set(left.count + 1, right.childs.get(i + 1));
            left.count++;
        }

        for (int i = idx; i < parent.count - 1; i++) {
            parent.keys  .set(i, parent.keys  .get(i + 1));
            parent.childs.set(i + 1, parent.childs.get(i + 2));
        }
        parent.count--;
    }

    @Override
    public String toString() {
        String s = "";
        if (isEmpty()) {
            s += "BTree is empty...";
        } else {
            // Asigna ID a cada nodo y padre:
            assignIds(root, -1);
            // Construye tabla:
            s = "| Id.Nodo | Claves Nodo | Id.Padre | Id.Hijos |\n";
            s += writeTree(root);
        }
        return s;
    }

    private void assignIds(BNode<E> current, int idPadre) {
        if (current == null) return;
        current.idNodo = idCounter++;
        current.idPadre = idPadre;
        for (BNode<E> child : current.childs) {
            if (child != null) {
                assignIds(child, current.idNodo);
            }
        }
    }

    private String writeTree(BNode<E> current) {
        if (current == null) return "";

        // Configura ancho de columnas
        int widthId = 8;
        int widthClave = (6 * (orden - 1)); // 6 = [num, ] promedio
        int widthPadre = 8;
        int widthHijos = 12;

        StringBuilder sb = new StringBuilder();

        // Formatea campos con ancho fijo
        String idNodo = String.format("%-" + widthId + "s", current.idNodo);
        String claves = String.format("%-" + widthClave + "s", current.toString());
        String padre = String.format("%-" + widthPadre + "s", current.idPadre == -1 ? "--" : current.idPadre);

        // Hijos: convierte a lista de IDs
        StringBuilder hijos = new StringBuilder("[");
        boolean first = true;
        for (BNode<E> child : current.childs) {
            if (child != null) {
                if (!first) hijos.append(", ");
                hijos.append(child.idNodo);
                first = false;
            }
        }
        hijos.append("]");
        String hijosStr = String.format("%-" + widthHijos + "s", hijos.toString());

        // Construye fila
        sb.append("| ")
                .append(idNodo).append(" | ")
                .append(claves).append(" | ")
                .append(padre).append(" | ")
                .append(hijosStr).append(" |\n");

        // Procesa hijos recursivamente
        for (BNode<E> child : current.childs) {
            if (child != null) {
                sb.append(writeTree(child));
            }
        }
        return sb.toString();
    }

    public String buscarNombre(int codigo) {
        return buscarNombreRecursivo(root, codigo);
    }

    private String buscarNombreRecursivo(BNode<E> current, int codigo) {
        if (current == null) {
            return "No encontrado";
        }

        int[] pos = new int[1];
        E buscado = (E) new RegistroEstudiante(codigo, "");

        boolean found = current.searchNode(buscado, pos);

        if (found) {
            E foundItem = current.keys.get(pos[0]);
            if (foundItem instanceof RegistroEstudiante) {
                return ((RegistroEstudiante) foundItem).getNombre();
            } else {
                throw new IllegalStateException("Tipo incompatible: el árbol no contiene RegistroEstudiante");
            }
        } else {
            return buscarNombreRecursivo(current.childs.get(pos[0]), codigo);
        }
    }

}
