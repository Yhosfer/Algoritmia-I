package Sesion09.Graph.PT2;
import Sesion09.TDA.ListLinked;

public class GraphListEdge<V, E> {
    ListLinked<VertexObj<V, E>> secVertex;
    ListLinked<EdgeObj<V, E>> secEdge;

    // Constructor que inicializa las listas de vértices y aristas
    public GraphListEdge() {
        this.secVertex = new ListLinked<>();
        this.secEdge = new ListLinked<>();
    }

    // Inserta un nuevo vértice si no existe ya en el grafo
    public void insertVertex(V info) {
        if (searchVertex(info) == null) {
            VertexObj<V, E> newVertex = new VertexObj<>(info, secVertex.size());
            secVertex.addLast(newVertex);
        }
    }

    // Inserta una arista entre dos vértices con peso específico
    public void insertEdge(V info1, V info2, E peso) {
        VertexObj<V, E> v1 = searchVertex(info1);
        VertexObj<V, E> v2 = searchVertex(info2);
        if (v1 == null || v2 == null) return;

        if (!searchEdge(info1, info2)) {
            EdgeObj<V, E> edge = new EdgeObj<>(v1, v2, peso, secEdge.size());
            secEdge.addLast(edge);
        }
    }

    // Busca un vértice por su información y lo retorna
    public VertexObj<V, E> searchVertex(V info) {
        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V, E> v = secVertex.get(i);
            if (v.getInfo().equals(info)) return v;
        }
        return null;
    }

    // Obtiene un vértice por su información (método alternativo)
    public VertexObj<V, E> getVertex(V info) {
        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V, E> v = secVertex.get(i);
            if (v.info.equals(info)) return v;
        }
        return null;
    }

    // Obtiene una arista entre dos vértices especificados
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

    // Verifica si existe una arista entre dos vértices
    public boolean searchEdge(V info1, V info2) {
        return getEdge(info1, info2) != null;
    }

    // Elimina una arista entre dos vértices
    public void removeEdge(V info1, V info2) {
        EdgeObj<V, E> edge = getEdge(info1, info2);
        if (edge != null) {
            secEdge.remove(edge);
            updateEdgePositions();
        }
    }

    // Elimina un vértice y todas sus aristas asociadas
    public void removeVertex(V info) {
        VertexObj<V, E> vertex = searchVertex(info);
        if (vertex != null) {
            secVertex.remove(vertex);
            // Remover aristas asociadas
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
            updateVertexPositions();
            updateEdgePositions();
        }
    }

    // Actualiza las posiciones de todos los vértices en la lista
    private void updateVertexPositions() {
        for (int i = 0; i < secVertex.size(); i++) {
            secVertex.get(i).setPosition(i);
        }
    }

    // Actualiza las posiciones de todas las aristas en la lista
    private void updateEdgePositions() {
        for (int i = 0; i < secEdge.size(); i++) {
            secEdge.get(i).setPosition(i);
        }
    }

    // Ejecuta recorrido DFS desde un vértice inicial
    public void dfs(V start) {
        VertexObj<V, E> startVertex = searchVertex(start);
        if (startVertex == null) return;

        ListLinked<VertexObj<V, E>> visited = new ListLinked<>();
        dfsRecursive(startVertex, visited);
    }

    // Función recursiva auxiliar para DFS
    private void dfsRecursive(VertexObj<V, E> current, ListLinked<VertexObj<V, E>> visited) {
        System.out.println(current.getInfo());
        visited.addLast(current);

        for (int i = 0; i < secEdge.size(); i++) {
            EdgeObj<V, E> edge = secEdge.get(i);
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

    // Ejecuta recorrido BFS desde un vértice inicial usando ListLinked
    public void bfs(V start) {
        VertexObj<V,E> s = searchVertex(start);
        if (s == null) return;
        ListLinked<VertexObj<V,E>> cola = new ListLinked<>();
        ListLinked<VertexObj<V,E>> visit = new ListLinked<>();

        cola.addLast(s); visit.addLast(s);
        while (!cola.isEmpty()) {
            VertexObj<V,E> v = cola.removeFirst();
            System.out.println(v.getInfo());

            for (int i = 0; i < secEdge.size(); i++) {
                EdgeObj<V,E> e = secEdge.get(i);
                VertexObj<V,E> w = null;
                if (e.getEndVertex1().equals(v)) w = e.getEndVertex2();
                else if (e.getEndVertex2().equals(v)) w = e.getEndVertex1();
                if (w != null && !visit.contains(w)) {
                    cola.addLast(w); visit.addLast(w);
                }
            }
        }
    }

    // Verifica si existe un vértice (versión booleana)
    public boolean searchVertexBool(V info){ return searchVertex(info)!=null; }

    // Verifica si existe una arista (versión booleana)
    public boolean searchEdgeBool(V v, V z){ return getEdge(v,z)!=null; }

    // Inserta arista sin peso (sobrecarga)
    public void insertEdge(V v, V z){ insertEdge(v,z,null); }

    // Calcula el grado de un vértice (número de aristas conectadas)
    public int grado(V info) {
        VertexObj<V, E> v = searchVertex(info);
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

    // Verifica si el grafo es un camino simple
    public boolean esCamino() {
        int grado1 = 0;
        int grado2 = 0;

        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V, E> v = secVertex.get(i);
            int g = grado(v.getInfo());
            if (g == 1) grado1++;
            else if (g == 2) grado2++;
            else return false;
        }
        return (grado1 == 2 && grado2 == secVertex.size() - 2);
    }

    // Verifica si el grafo es un ciclo
    public boolean esCiclo() {
        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V, E> v = secVertex.get(i);
            if (grado(v.getInfo()) != 2) return false;
        }
        return true;
    }

    // Verifica si el grafo es una rueda
    public boolean esRueda() {
        int centro = 0;
        int periferia = 0;
        int n = secVertex.size();

        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V, E> v = secVertex.get(i);
            int g = grado(v.getInfo());
            if (g == n - 1) centro++;
            else if (g == 3) periferia++;
            else return false;
        }
        return (centro == 1 && periferia == n - 1);
    }

    // Verifica si el grafo es completo
    public boolean esCompleto() {
        int n = secVertex.size();
        int expectedEdges = n * (n - 1) / 2;
        return secEdge.size() == expectedEdges;
    }

    // Muestra la representación formal del grafo (V, E)
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

    // Muestra el grafo como lista de adyacencia
    public void mostrarListaAdyacencia() {
        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V, E> v = secVertex.get(i);
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

    // Muestra el grafo como matriz de adyacencia
    public void mostrarMatrizAdyacencia() {
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
            VertexObj<V, E> v = secVertex.get(i);
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

    // Calcula el grado de entrada de un vértice (para grafos dirigidos)
    public int gradoEntrada(V info) {
        VertexObj<V, E> v = searchVertex(info);
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

    // Calcula el grado de salida de un vértice (para grafos dirigidos)
    public int gradoSalida(V info) {
        VertexObj<V, E> v = searchVertex(info);
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

    // Verifica si el grafo dirigido es un camino
    public boolean esCaminoDirigido() {
        int inicio = 0, fin = 0, intermedios = 0;

        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V, E> v = secVertex.get(i);
            int in = gradoEntrada(v.getInfo());
            int out = gradoSalida(v.getInfo());

            if (in == 0 && out == 1) inicio++;
            else if (in == 1 && out == 0) fin++;
            else if (in == 1 && out == 1) intermedios++;
            else return false;
        }
        return (inicio == 1 && fin == 1 && intermedios == secVertex.size() - 2);
    }

    // Verifica si el grafo dirigido es un ciclo
    public boolean esCicloDirigido() {
        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V, E> v = secVertex.get(i);
            if (gradoEntrada(v.getInfo()) != 1 || gradoSalida(v.getInfo()) != 1) {
                return false;
            }
        }
        return true;
    }

    // Verifica si el grafo dirigido es una rueda
    public boolean esRuedaDirigida() {
        int centro = 0;
        int periferia = 0;
        int n = secVertex.size();

        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V, E> v = secVertex.get(i);
            int in = gradoEntrada(v.getInfo());
            int out = gradoSalida(v.getInfo());

            if (out == n - 1 && in == 0) centro++;
            else if (in == 1 && out == 1) periferia++;
            else return false;
        }

        return (centro == 1 && periferia == n - 1);
    }

    // Muestra la representación formal del grafo dirigido
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

    // Muestra lista de adyacencia para grafo dirigido
    public void mostrarListaAdyacenciaDirigido() {
        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V, E> v = secVertex.get(i);
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

    // Muestra matriz de adyacencia para grafo dirigido
    public void mostrarMatrizAdyacenciaDirigido() {
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
            VertexObj<V, E> v = secVertex.get(i);
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

    // Verifica si dos grafos son isomorfos (comparación básica)
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

    // Verifica si el grafo es plano usando condición básica
    public boolean esPlano() {
        int v = secVertex.size();
        int e = secEdge.size();
        return e <= (2 * v - 4);
    }

    // Verifica si el grafo es conexo
    public boolean esConexo() {
        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V, E> v = secVertex.get(i);
            if (!alcanzaATodos(v)) return false;
        }
        return true;
    }

    // Verifica si desde un vértice se puede alcanzar todos los demás
    private boolean alcanzaATodos(VertexObj<V,E> origen) {
        ListLinked<VertexObj<V,E>> cola     = new ListLinked<>();
        ListLinked<VertexObj<V,E>> visitados = new ListLinked<>();

        cola.addLast(origen);
        visitados.addLast(origen);

        while (!cola.isEmpty()) {
            VertexObj<V,E> actual = cola.removeFirst();

            for (int i = 0; i < secEdge.size(); i++) {
                EdgeObj<V,E> e = secEdge.get(i);
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

    // Obtiene el grafo complemento
    public GraphListEdge<V,E> obtenerComplemento() {
        GraphListEdge<V,E> complemento = new GraphListEdge<>();

        for (int i = 0; i < secVertex.size(); i++) {
            VertexObj<V,E> v = secVertex.get(i);
            complemento.insertVertex(v.getInfo());
        }

        for (int i = 0; i < secVertex.size(); i++) {
            for (int j = 0; j < secVertex.size(); j++) {
                VertexObj<V,E> vi = secVertex.get(i);
                VertexObj<V,E> vj = secVertex.get(j);
                if (!vi.equals(vj) && !searchEdge(vi.getInfo(), vj.getInfo())) {
                    complemento.insertEdge(vi.getInfo(), vj.getInfo(), null);
                }
            }
        }
        return complemento;
    }

    // Verifica si el grafo es auto-complementario
    public boolean esAutoComplementario() {
        GraphListEdge<V,E> comp = obtenerComplemento();
        return this.esIsomorfo(comp);
    }
}