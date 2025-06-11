package Sesion09.Graph.HolaJgraph;


import org.jgrapht.*;
import org.jgrapht.graph.*;
import org.jgrapht.graph.DefaultDirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.nio.*;
import org.jgrapht.nio.dot.*;
import org.jgrapht.traverse.*;

import java.io.StringWriter;
import java.io.Writer;
import java.net.URI;
import java.net.URISyntaxException;
import java.rmi.server.ExportException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class HelloJGrapht {
    private HelloJGrapht(){
    }
    public static void main(String[] args) throws URISyntaxException, ExportException {
        Graph<String, DefaultEdge> stringGraph = createStringGraph();

        //
        System.out.println("__ tostring output");
        System.out.println(stringGraph);
        System.out.println();

        //
        Graph<URI, DefaultEdge> hrefGraph = createHrefGraph();


        URI start = hrefGraph.
                vertexSet().stream().filter(uri -> uri.getHost().equals("www.jgrapht.org")).findAny().get();

        //
        System.out.println("__ traverseHrefGraph output");
        traverseHrefGraph(hrefGraph, start);
        System.out.println();

        System.out.println("__ renderHrefGraph output");
        renderHrefGraph(hrefGraph);
        System.out.println();
    }

    private static Graph<URI, DefaultEdge> createHrefGraph() throws URISyntaxException, ExportException {
        Graph<URI, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);

        URI google = new URI("https://www.google.com/");
        URI wikipedia = new URI("https://www.wikipedia.org/");
        URI jgrapht = new URI("https://www.jgrapht.org/");

        g.addVertex(google);
        g.addVertex(wikipedia);
        g.addVertex(jgrapht);

        g.addEdge(jgrapht, wikipedia);
        g.addEdge(google, jgrapht);
        g.addEdge(google, wikipedia);
        g.addEdge(wikipedia, google);

        return g;
    }

    private static void traverseHrefGraph(Graph<URI, DefaultEdge> hrefGraph, URI start){
        Iterator<URI> iterator = new DepthFirstIterator<>(hrefGraph, start);
        while (iterator.hasNext()){
            URI uri = iterator.next();
            System.out.println(uri);
        }
    }

    private static void renderHrefGraph(Graph<URI, DefaultEdge> hrefGraph) throws ExportException {
        DOTExporter<URI, DefaultEdge> exporter = new DOTExporter<>(v -> v.getHost().replace(".","_"));
        exporter.setVertexAttributeProvider((v)->{
            Map<String, Attribute> map = new LinkedHashMap<>();
            map.put("Label", DefaultAttribute.createAttribute(v.toString()));
            return map;
        });
        Writer writer = new StringWriter();
        exporter.exportGraph(hrefGraph, writer);
        System.out.println(writer.toString());

    }

    private static Graph<String, DefaultEdge> createStringGraph() {
        Graph<String, DefaultEdge> g = new DefaultDirectedGraph<>(DefaultEdge.class);
        String v1 = "v1";
        String v2 = "v2";
        String v3 = "v3";
        String v4 = "v4";

        g.addVertex(v1);
        g.addVertex(v2);
        g.addVertex(v3);
        g.addVertex(v4);

        g.addEdge(v1, v2);
        g.addEdge(v2, v3);
        g.addEdge(v3, v4);
        g.addEdge(v4, v1);

        return g;
    }
}
