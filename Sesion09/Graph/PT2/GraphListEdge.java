package Sesion09.Graph.PT2;
import Sesion09.TDA.ListLinked;

import java.util.ArrayList;

public class GraphListEdge<V, E> {
    ArrayList<VertexObj<V, E>> secVertex;
    ArrayList<EdgeObj<V, E>> secEdge;

    public GraphListEdge() {
        this.secVertex = new ArrayList<>();
        this.secEdge = new ArrayList<>();
    }

    public void insertVertex(V info) {
        if (searchVertex(info) == null) {
            VertexObj<V, E> newVertex = new VertexObj<>(info, secVertex.size());
            secVertex.add(newVertex);
        }
    }

    public void insertEdge(V info1, V info2, E peso) {
        VertexObj<V, E> v1 = searchVertex(info1);
        VertexObj<V, E> v2 = searchVertex(info2);
        if (v1 == null || v2 == null) return;

        if (!searchEdge(info1, info2)) {
            EdgeObj<V, E> edge = new EdgeObj<>(v1, v2, peso, secEdge.size());
            secEdge.add(edge);
        }
    }

    public VertexObj<V, E> searchVertex(V info) {
        for (VertexObj<V, E> v : secVertex) {
            if (v.getInfo().equals(info)) return v;
        }
        return null;
    }
    public VertexObj<V, E> getVertex(V info) {
        for (VertexObj<V, E> v : secVertex) {
            if (v.info.equals(info)) return v;
        }
        return null;
    }

    public EdgeObj<V, E> getEdge(V info1, V info2) {
        for (EdgeObj<V, E> e : secEdge) {
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
            secEdge.remove(edge.getPosition());
            updateEdgePositions();
        }
    }

    public void removeVertex(V info) {
        VertexObj<V, E> vertex = searchVertex(info);
        if (vertex != null) {
            secVertex.remove(vertex.getPosition());
            secEdge.removeIf(e -> e.getEndVertex1().equals(vertex) || e.getEndVertex2().equals(vertex));
            updateVertexPositions();
            updateEdgePositions();
        }
    }

    private void updateVertexPositions() {
        for (int i = 0; i < secVertex.size(); i++) {
            secVertex.get(i).setPosition(i);
        }
    }

    private void updateEdgePositions() {
        for (int i = 0; i < secEdge.size(); i++) {
            secEdge.get(i).setPosition(i);
        }
    }

    public void dfs(V start) {
        VertexObj<V, E> startVertex = searchVertex(start);
        if (startVertex == null) return;

        ArrayList<VertexObj<V, E>> visited = new ArrayList<>();
        dfsRecursive(startVertex, visited);
    }

    private void dfsRecursive(VertexObj<V, E> current, ArrayList<VertexObj<V, E>> visited) {
        System.out.println(current.getInfo());
        visited.add(current);

        for (EdgeObj<V, E> edge : secEdge) {
            VertexObj<V, E> neighbor = null;
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
    // BFS sencillo (no ponderado) usando tu ListLinked
    public void bfs(V start) {
        VertexObj<V,E> s = searchVertex(start);
        if (s == null) return;
        ListLinked<VertexObj<V,E>> cola = new ListLinked<>();
        ListLinked<VertexObj<V,E>> visit = new ListLinked<>();

        cola.addLast(s); visit.addLast(s);
        while (!cola.isEmpty()) {
            VertexObj<V,E> v = cola.removeFirst();
            System.out.println(v.getInfo());

            for (EdgeObj<V,E> e : secEdge) {
                VertexObj<V,E> w = null;
                if (e.getEndVertex1().equals(v)) w = e.getEndVertex2();
                else if (e.getEndVertex2().equals(v)) w = e.getEndVertex1();
                if (w != null && !visit.contains(w)) {
                    cola.addLast(w); visit.addLast(w);
                }
            }
        }
    }

    // Wrappers booleanos
    public boolean searchVertexBool(V info){ return searchVertex(info)!=null; }
    public boolean searchEdgeBool(V v, V z){ return getEdge(v,z)!=null; }

    // Sobrecarga sin peso
    public void insertEdge(V v, V z){ insertEdge(v,z,null); }

    //ejer 5
    public int grado(V info) {
        VertexObj<V, E> v = searchVertex(info);
        if (v == null) return -1;

        int count = 0;
        for (EdgeObj<V, E> e : secEdge) {
            if (e.getEndVertex1().equals(v) || e.getEndVertex2().equals(v)) {
                count++;
            }
        }
        return count;
    }
    public boolean esCamino() {
        int grado1 = 0;
        int grado2 = 0;

        for (VertexObj<V, E> v : secVertex) {
            int g = grado(v.getInfo());
            if (g == 1) grado1++;
            else if (g == 2) grado2++;
            else return false;
        }
        return (grado1 == 2 && grado2 == secVertex.size() - 2);
    }

    public boolean esCiclo() {
        for (VertexObj<V, E> v : secVertex) {
            if (grado(v.getInfo()) != 2) return false;
        }
        return true;
    }

    public boolean esRueda() {
        int centro = 0;
        int periferia = 0;
        int n = secVertex.size();

        for (VertexObj<V, E> v : secVertex) {
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
    //ejer 6
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
        for (VertexObj<V, E> v : secVertex) {
            System.out.print(v.getInfo() + " → ");
            for (EdgeObj<V, E> e : secEdge) {
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
        int n = secVertex.size();
        int[][] matriz = new int[n][n];

        // Llenar matriz con 0 o 1 según conexiones
        for (EdgeObj<V, E> e : secEdge) {
            int i = e.getEndVertex1().getPosition();
            int j = e.getEndVertex2().getPosition();
            matriz[i][j] = 1;
            matriz[j][i] = 1; // porque es no dirigido
        }

        // Imprimir encabezado
        System.out.print("   ");
        for (VertexObj<V, E> v : secVertex) {
            System.out.print(v.getInfo() + " ");
        }
        System.out.println();

        // Imprimir matriz
        for (int i = 0; i < n; i++) {
            System.out.print(secVertex.get(i).getInfo() + " ");
            for (int j = 0; j < n; j++) {
                System.out.print(" " + matriz[i][j]);
            }
            System.out.println();
        }
    }
    //ejer 6
    public int gradoEntrada(V info) {
        VertexObj<V, E> v = searchVertex(info);
        if (v == null) return -1;

        int count = 0;
        for (EdgeObj<V, E> e : secEdge) {
            if (e.getEndVertex2().equals(v)) {
                count++;
            }
        }
        return count;
    }

    public int gradoSalida(V info) {
        VertexObj<V, E> v = searchVertex(info);
        if (v == null) return -1;

        int count = 0;
        for (EdgeObj<V, E> e : secEdge) {
            if (e.getEndVertex1().equals(v)) {
                count++;
            }
        }
        return count;
    }
    public boolean esCaminoDirigido() {
        int inicio = 0, fin = 0, intermedios = 0;

        for (VertexObj<V, E> v : secVertex) {
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
        for (VertexObj<V, E> v : secVertex) {
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

        for (VertexObj<V, E> v : secVertex) {
            int in = gradoEntrada(v.getInfo());
            int out = gradoSalida(v.getInfo());

            if (out == n - 1 && in == 0) centro++;
            else if (in == 1 && out == 1) periferia++;
            else return false;
        }

        return (centro == 1 && periferia == n - 1);
    }
//ejer 8

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
        for (VertexObj<V, E> v : secVertex) {
            System.out.print(v.getInfo() + " → ");
            for (EdgeObj<V, E> e : secEdge) {
                if (e.getEndVertex1().equals(v)) {
                    System.out.print(e.getEndVertex2().getInfo() + " ");
                }
            }
            System.out.println();
        }
    }
    public void mostrarMatrizAdyacenciaDirigido() {
        int n = secVertex.size();
        int[][] matriz = new int[n][n];

        for (EdgeObj<V, E> e : secEdge) {
            int from = e.getEndVertex1().getPosition();
            int to = e.getEndVertex2().getPosition();
            matriz[from][to] = 1;  // Solo esta dirección, porque es dirigido
        }

        // Encabezado
        System.out.print("   ");
        for (VertexObj<V, E> v : secVertex) {
            System.out.print(v.getInfo() + " ");
        }
        System.out.println();

        // Filas
        for (int i = 0; i < n; i++) {
            System.out.print(secVertex.get(i).getInfo() + " ");
            for (int j = 0; j < n; j++) {
                System.out.print(" " + matriz[i][j]);
            }
            System.out.println();
        }
    }
    //ejer 9
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

        // Para grafo dirigido simple: condición básica para planitud
        return e <= (2 * v - 4);
    }
    public boolean esConexo() {
        for (VertexObj<V, E> v : secVertex) {
            if (!alcanzaATodos(v)) return false;
        }
        return true;
    }


    private boolean alcanzaATodos(VertexObj<V,E> origen) {
        ListLinked<VertexObj<V,E>> cola     = new ListLinked<>();
        ListLinked<VertexObj<V,E>> visitados = new ListLinked<>();

        cola.addLast(origen);
        visitados.addLast(origen);

        while (!cola.isEmpty()) {
            VertexObj<V,E> actual = cola.removeFirst();

            // recorre aristas salientes
            for (EdgeObj<V,E> e : secEdge) {
                if (e.getEndVertex1().equals(actual)) {
                    VertexObj<V,E> destino = e.getEndVertex2();
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

        for (VertexObj<V,E> v : secVertex) {
            complemento.insertVertex(v.getInfo());
        }

        for (VertexObj<V,E> vi : secVertex) {
            for (VertexObj<V,E> vj : secVertex) {
                if (!vi.equals(vj) && searchEdge(vi.getInfo(), vj.getInfo())) {
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
