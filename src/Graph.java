//package Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;


/**
 * @author Zhao Yan
 */
public class Graph {
    private int V;
    private int E;
    private final LinkedList<Edge>[] adj;
    private final HashSet<Edge> edges;
    private final HashSet<Integer> vertices;

    /**
     * Init default Graph
     * @param V
     */
    public Graph(int V){
        this.V = V;
        adj = new LinkedList[V+1];
        for (int i = 1; i <= V; i++) {
            adj[i] = new LinkedList<Edge>();
        }
        edges = new HashSet<Edge>();
        vertices = new HashSet<>();
        for (int i = 1; i <= V; i++){
            vertices.add(i);
        }
    }

    /**
     * Init by deep copy given graph
     * @param g
     */
    public Graph(Graph g) {
        this.V = g.V;
        this.E = g.E;
        this.adj = new LinkedList[V+1];
        this.edges = new HashSet<Edge>();
        this.vertices = new HashSet<>();
        for (int i = 1; i <= V ; i ++) {
            this.adj[i] = g.getAdj()[i];
        }

        for (Edge e : g.getEdges()) {
            this.edges.add(e);
        }

    }

    public void addEdge(int u, int v){
        Edge e = new Edge(u, v);
        adj[u].add(e);
        this.edges.add(e);
        E++;
    }

    public int getDegree(int curV){
        //return the degree of current vertex
        return this.adj[curV].size();
    }

    public ArrayList<Integer> getAdjV(int curV){
        //get adjacent vertex array
        ArrayList<Integer> adjV = new ArrayList<>();
        for (Edge e : adj[curV]){
            adjV.add(e.endPoint(curV));
        }
        return adjV;
    }

    public int numOfVertices(){
        return V;
    }

    public int numOfEdges(){
        return E/2;
    }

    public LinkedList<Edge>[] getAdj(){
        return this.adj;
    }


    public HashSet<Edge> getEdges () {
        return this.edges;
    }

    public HashSet<Integer> getVertices() {
        return this.vertices;
    }

}

