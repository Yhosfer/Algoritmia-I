package Sesion09.Graph.PT2;
import Sesion09.TDA.ListLinked;

public class GraphListEdge<V, E> {
    ListLinked<VertexObj<V>> secVertex;
    ListLinked<EdgeObj<V, E>> secEdge;

    public GraphListEdge() {
        this.secVertex = new ListLinked<>();
        this.secEdge = new ListLinked<>();
    }

    public void insertVertex(V info) {
        if (searchVertex(info) == null) {
            VertexObj<V> newVertex = new VertexObj<>(info, secVertex.size());
            secVertex.addLast(newVertex);
        }
    }


    public void insertEdge(V info1, V info2, E peso) {
        VertexObj<V> v1 = searchVertex(info1);
        VertexObj<V> v2 = searchVertex(info2);
        if (v1 == null || v2 == null) return;

        if (!searchEdge(info1, info2)) {
            EdgeObj<V, E> edge = new EdgeObj<>(v1, v2, peso, secEdge.size());
            secEdge.addLast(edge);
        }
    }

    public VertexObj<V> searchVertex(V info) {
        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V> v = secVertex.get(i);
            if (v.getInfo().equals(info)) return v;
        }
        return null;
    }

    public EdgeObj<V, E> getEdge(V info1, V info2) {
        for (int i = 0; i < secEdge.size(); i++) {
            EdgeObj<V, E> e = secEdge.get(i);
            V v1 = e.getEndVertex1().getInfo();
            V v2 = e.getEndVertex2().getInfo();
            if ((v1.equals(info1) && v2.equals(info2)) || (v1.equals(info2) && v2.equals(info1))) {
                return e;
            }
        }
        return null;
    }

    public boolean searchEdge(V info1, V info2) {
        return getEdge(info1, info2) != null;
    }

    public void removeEdge(V info1, V info2) {
        EdgeObj<V, E> edge = getEdge(info1, info2);
        if (edge != null) {
            secEdge.remove(edge);
        }
    }

    public void removeVertex(V info) {
        VertexObj<V> vertex = searchVertex(info);
        if (vertex != null) {
            secVertex.remove(vertex);
            ListLinked<EdgeObj<V, E>> edgesToRemove = new ListLinked<>();
            for (int i = 0; i < secEdge.size(); i++) {
                EdgeObj<V, E> e = secEdge.get(i);
                if (e.getEndVertex1().equals(vertex) || e.getEndVertex2().equals(vertex)) {
                    edgesToRemove.addLast(e);
                }
            }
            for (int i = 0; i < edgesToRemove.size(); i++) {
                secEdge.remove(edgesToRemove.get(i));
            }
        }
    }


    private void updateVertexPositions() {
        for (int i = 0; i < secVertex.size(); i++) {
            secVertex.get(i).setPosition(i);
        }
    }

    public void dfs(V start) {
        VertexObj<V> startVertex = searchVertex(start);
        if (startVertex == null) return;

        ListLinked<VertexObj<V>> visited = new ListLinked<>();
        dfsRecursive(startVertex, visited);
    }

    private void dfsRecursive(VertexObj<V> current, ListLinked<VertexObj<V>> visited) {
        System.out.println(current.getInfo());
        visited.addLast(current);

        for (int i = 0; i < secEdge.size(); i++) {
            EdgeObj<V, E> edge = secEdge.get(i);
            VertexObj<V> neighbor = null;
            if (edge.getEndVertex1().equals(current)) {
                neighbor = edge.getEndVertex2();
            } else if (edge.getEndVertex2().equals(current)) {
                neighbor = edge.getEndVertex1();
            }

            if (neighbor != null && !visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited);
            }
        }
    }

    public void bfs(V start) {
        VertexObj<V> s = searchVertex(start);
        if (s == null) return;
        ListLinked<VertexObj<V>> cola = new ListLinked<>();
        ListLinked<VertexObj<V>> visit = new ListLinked<>();

        cola.addLast(s);
        visit.addLast(s);
        while (!cola.isEmpty()) {
            VertexObj<V> v = cola.removeFirst();
            System.out.println(v.getInfo());

            for (int i = 0; i < secEdge.size(); i++) {
                EdgeObj<V,E> e = secEdge.get(i);
                VertexObj<V> w = null;
                if (e.getEndVertex1().equals(v)){
                    w = e.getEndVertex2();
                }
                else if (e.getEndVertex2().equals(v)){
                    w = e.getEndVertex1();
                }
                if (w != null && !visit.contains(w)) {
                    cola.addLast(w);
                    visit.addLast(w);
                }
            }
        }
    }

    public boolean searchVertexBool(V info){ return searchVertex(info)!=null; }

    public boolean searchEdgeBool(V v, V z){ return getEdge(v,z)!=null; }

    public void insertEdge(V v, V z){ insertEdge(v,z,null); }

    public int grado(V info) {
        VertexObj<V> v = searchVertex(info);
        if (v == null) return -1;

        int count = 0;
        for (int i = 0; i < secEdge.size(); i++) {
            EdgeObj<V, E> e = secEdge.get(i);
            if (e.getEndVertex1().equals(v) || e.getEndVertex2().equals(v)) {
                count++;
            }
        }
        return count;
    }

    public boolean esCamino() {
        int grado1 = 0;
        int grado2 = 0;

        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V> v = secVertex.get(i);
            int g = grado(v.getInfo());
            if (g == 1) grado1++;
            else if (g == 2) grado2++;
            else return false;
        }
        return (grado1 == 2 && grado2 == secVertex.size() - 2);
    }

    public boolean esCiclo() {
        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V> v = secVertex.get(i);
            if (grado(v.getInfo()) != 2) return false;
        }
        return true;
    }

    public boolean esRueda() {
        int centro = 0;
        int periferia = 0;
        int n = secVertex.size();

        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V> v = secVertex.get(i);
            int g = grado(v.getInfo());
            if (g == n - 1) centro++;
            else if (g == 3) periferia++;
            else return false;
        }
        return (centro == 1 && periferia == n - 1);
    }

    public boolean esCompleto() {
        int n = secVertex.size();
        int expectedEdges = n * (n - 1) / 2;
        return secEdge.size() == expectedEdges;
    }

    public void mostrarFormal() {
        System.out.print("V = {");
        for (int i = 0; i < secVertex.size(); i++) {
            System.out.print(secVertex.get(i).getInfo());
            if (i < secVertex.size() - 1) System.out.print(", ");
        }
        System.out.println("}");

        System.out.print("E = {");
        for (int i = 0; i < secEdge.size(); i++) {
            EdgeObj<V, E> e = secEdge.get(i);
            System.out.print("(" + e.getEndVertex1().getInfo() + "," + e.getEndVertex2().getInfo() + ")");
            if (i < secEdge.size() - 1) System.out.print(", ");
        }
        System.out.println("}");
    }

    public void mostrarListaAdyacencia() {
        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V> v = secVertex.get(i);
            System.out.print(v.getInfo() + " → ");
            for (int j = 0; j < secEdge.size(); j++) {
                EdgeObj<V, E> e = secEdge.get(j);
                if (e.getEndVertex1().equals(v)) {
                    System.out.print(e.getEndVertex2().getInfo() + " ");
                } else if (e.getEndVertex2().equals(v)) {
                    System.out.print(e.getEndVertex1().getInfo() + " ");
                }
            }
            System.out.println();
        }
    }

    public void mostrarMatrizAdyacencia() {
        updateVertexPositions();
        int n = secVertex.size();
        int[][] matriz = new int[n][n];

        for (int i = 0; i < secEdge.size(); i++) {
            EdgeObj<V, E> e = secEdge.get(i);
            int idx1 = e.getEndVertex1().getPosition();
            int idx2 = e.getEndVertex2().getPosition();
            matriz[idx1][idx2] = 1;
            matriz[idx2][idx1] = 1;
        }

        System.out.print("   ");
        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V> v = secVertex.get(i);
            System.out.print(v.getInfo() + " ");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print(secVertex.get(i).getInfo() + " ");
            for (int j = 0; j < n; j++) {
                System.out.print(" " + matriz[i][j]);
            }
            System.out.println();
        }
    }

    public int gradoEntrada(V info) {
        VertexObj<V> v = searchVertex(info);
        if (v == null) return -1;

        int count = 0;
        for (int i = 0; i < secEdge.size(); i++) {
            EdgeObj<V, E> e = secEdge.get(i);
            if (e.getEndVertex2().equals(v)) {
                count++;
            }
        }
        return count;
    }

    public int gradoSalida(V info) {
        VertexObj<V> v = searchVertex(info);
        if (v == null) return -1;

        int count = 0;
        for (int i = 0; i < secEdge.size(); i++) {
            EdgeObj<V, E> e = secEdge.get(i);
            if (e.getEndVertex1().equals(v)) {
                count++;
            }
        }
        return count;
    }

    public boolean esCaminoDirigido() {
        int inicio = 0, fin = 0, intermedios = 0;

        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V> v = secVertex.get(i);
            int in = gradoEntrada(v.getInfo());
            int out = gradoSalida(v.getInfo());

            if (in == 0 && out == 1) inicio++;
            else if (in == 1 && out == 0) fin++;
            else if (in == 1 && out == 1) intermedios++;
            else return false;
        }
        return (inicio == 1 && fin == 1 && intermedios == secVertex.size() - 2);
    }

    public boolean esCicloDirigido() {
        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V> v = secVertex.get(i);
            if (gradoEntrada(v.getInfo()) != 1 || gradoSalida(v.getInfo()) != 1) {
                return false;
            }
        }
        return true;
    }

    public boolean esRuedaDirigida() {
        int centro = 0;
        int periferia = 0;
        int n = secVertex.size();

        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V> v = secVertex.get(i);
            int in = gradoEntrada(v.getInfo());
            int out = gradoSalida(v.getInfo());

            if (out == n - 1 && in == 0) centro++;
            else if (in == 1 && out == 1) periferia++;
            else return false;
        }

        return (centro == 1 && periferia == n - 1);
    }

    public void mostrarFormalDirigido() {
        System.out.print("V = {");
        for (int i = 0; i < secVertex.size(); i++) {
            System.out.print(secVertex.get(i).getInfo());
            if (i < secVertex.size() - 1) System.out.print(", ");
        }
        System.out.println("}");

        System.out.print("E = {");
        for (int i = 0; i < secEdge.size(); i++) {
            EdgeObj<V, E> e = secEdge.get(i);
            System.out.print("(" + e.getEndVertex1().getInfo() + "→" + e.getEndVertex2().getInfo() + ")");
            if (i < secEdge.size() - 1) System.out.print(", ");
        }
        System.out.println("}");
    }

    public void mostrarListaAdyacenciaDirigido() {
        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V> v = secVertex.get(i);
            System.out.print(v.getInfo() + " → ");
            for (int j = 0; j < secEdge.size(); j++) {
                EdgeObj<V, E> e = secEdge.get(j);
                if (e.getEndVertex1().equals(v)) {
                    System.out.print(e.getEndVertex2().getInfo() + " ");
                }
            }
            System.out.println();
        }
    }

    public void mostrarMatrizAdyacenciaDirigido() {
        updateVertexPositions();
        int n = secVertex.size();
        int[][] matriz = new int[n][n];

        for (int i = 0; i < secEdge.size(); i++) {
            EdgeObj<V, E> e = secEdge.get(i);
            int from = e.getEndVertex1().getPosition();
            int to = e.getEndVertex2().getPosition();
            matriz[from][to] = 1;
        }

        System.out.print("   ");
        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V> v = secVertex.get(i);
            System.out.print(v.getInfo() + " ");
        }
        System.out.println();

        for (int i = 0; i < n; i++) {
            System.out.print(secVertex.get(i).getInfo() + " ");
            for (int j = 0; j < n; j++) {
                System.out.print(" " + matriz[i][j]);
            }
            System.out.println();
        }
    }

    public boolean esIsomorfo(GraphListEdge<V,E> otro) {
        if (this.secVertex.size() != otro.secVertex.size()) return false;
        if (this.secEdge.size() != otro.secEdge.size()) return false;

        for (int i = 0; i < secVertex.size(); i++) {
            int in1 = gradoEntrada(secVertex.get(i).getInfo());
            int out1 = gradoSalida(secVertex.get(i).getInfo());

            int in2 = gradoEntrada(otro.secVertex.get(i).getInfo());
            int out2 = gradoSalida(otro.secVertex.get(i).getInfo());

            if (in1 != in2 || out1 != out2) return false;
        }
        return true;
    }

    public boolean esPlano() {
        int v = secVertex.size();
        int e = secEdge.size();
        return e <= (2 * v - 4);
    }

    public boolean esConexo() {
        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V> v = secVertex.get(i);
            if (!alcanzaATodos(v)) return false;
        }
        return true;
    }

    private boolean alcanzaATodos(VertexObj<V> origen) {
        ListLinked<VertexObj<V>> cola     = new ListLinked<>();
        ListLinked<VertexObj<V>> visitados = new ListLinked<>();

        cola.addLast(origen);
        visitados.addLast(origen);

        while (!cola.isEmpty()) {
            VertexObj<V> actual = cola.removeFirst();

            for (int i = 0; i < secEdge.size(); i++) {
                EdgeObj<V,E> e = secEdge.get(i);
                if (e.getEndVertex1().equals(actual)) {
                    VertexObj<V> destino = e.getEndVertex2();
                    if (!visitados.contains(destino)) {
                        visitados.addLast(destino);
                        cola.addLast(destino);
                    }
                }
            }
        }
        return visitados.size() == secVertex.size();
    }

    public GraphListEdge<V,E> obtenerComplemento() {
        GraphListEdge<V,E> complemento = new GraphListEdge<>();

        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V> v = secVertex.get(i);
            complemento.insertVertex(v.getInfo());
        }

        for (int i = 0; i < secVertex.size(); i++) {
            for (int j = 0; j < secVertex.size(); j++) {
                VertexObj<V> vi = secVertex.get(i);
                VertexObj<V> vj = secVertex.get(j);
                if (!vi.equals(vj) && !searchEdge(vi.getInfo(), vj.getInfo())) {
                    complemento.insertEdge(vi.getInfo(), vj.getInfo(), null);
                }
            }
        }
        return complemento;
    }

    public boolean esAutoComplementario() {
        GraphListEdge<V,E> comp = obtenerComplemento();
        return this.esIsomorfo(comp);
    }
}