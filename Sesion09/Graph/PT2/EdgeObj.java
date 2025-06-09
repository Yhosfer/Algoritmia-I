package Sesion09.Graph.PT2;

public class EdgeObj<V, E> {
    protected E info;
    protected VertexObj<V> endVertex1;
    protected VertexObj<V> endVertex2;
    protected int position;

    public EdgeObj(VertexObj<V> vert1, VertexObj<V> vert2, E info, int position) {
        this.endVertex1 = vert1;
        this.endVertex2 = vert2;
        this.info = info;
        this.position = position;
    }

    public VertexObj<V> getEndVertex1() {
        return endVertex1;
    }

    public VertexObj<V> getEndVertex2() {
        return endVertex2;
    }

    public E getInfo() {
        return info;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int pos) {
        this.position = pos;
    }
}
