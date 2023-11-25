package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Queue;

public class Graph {
    ArrayList<ArrayList<Integer>> adjMatrix;
    ArrayList<Vertex> vertexList;
    ArrayList<Edge> edgeList;

    public Graph() {
        vertexList = new ArrayList<Vertex>();
        adjMatrix = new ArrayList<ArrayList<Integer>>();
        edgeList = new ArrayList<Edge>();
    }

    public int size() {
        return vertexList.size();
    }

    public Vertex displayVertex(int index) {
        return vertexList.get(index);
    }

    public void addVertex(String label) {
        Vertex newVertex = new Vertex(label);
        vertexList.add(newVertex);
        newVertex.visited = false;

        // Menambahkan kolom baru ke dalam matriks
        ArrayList<Integer> newColumn = new ArrayList<Integer>();
        for (int i = 0; i < adjMatrix.size(); i++) {
            newColumn.add(0);
        }

        adjMatrix.add(newColumn);

        // menambahkan baris baru ke dalam matriks
        for (int i = 0; i < adjMatrix.size(); i++) {
            adjMatrix.get(i).add(0);
        }

    }

    public void addEdge(int source, int target, int weight) {
        // perbarui relasi antar vertex dalam matriks
        adjMatrix.get(source).set(target, weight);
        adjMatrix.get(target).set(source, weight);
        edgeList.add(new Edge(source, target, weight));
    }

    public void display() {
        System.out.print("~ ");
        for (int i = 0; i < adjMatrix.size(); i++) {
            System.out.print(String.format("%3s", vertexList.get(i).getLabel()));
        }

        System.out.println();

        for (int row = 0; row < adjMatrix.size(); row++) {
            System.out.print(String.format("%-3s", vertexList.get(row).getLabel()));
            for (int column = 0; column < adjMatrix.size(); column++) {
                System.out.print(String.format("%3s", adjMatrix.get(row).get(column) + " "));
            }
            System.out.println();
        }
    }

    void setAllVisitedToFalse() {
        for (int i = 0; i < adjMatrix.size(); i++) {
            vertexList.get(i).visited = false;
        }
    }

    public void dfs() {
        Stack<Vertex> stack = new Stack<Vertex>();
        stack.push(vertexList.get(0));
        vertexList.get(0).visited = true;
        System.out.print(vertexList.get(0).getLabel() + " ");

        while (!stack.isEmpty()) {
            Vertex current = stack.peek();
            boolean allAdjVisited = true;

            for (int i = 0; i < adjMatrix.size(); i++) {
                if (adjMatrix.get(vertexList.indexOf(current)).get(i) != 0 && !vertexList.get(i).visited) {
                    stack.push(vertexList.get(i));
                    vertexList.get(i).visited = true;
                    System.out.print(vertexList.get(i).getLabel() + " ");
                    allAdjVisited = false;
                    break;
                }
            }

            if (allAdjVisited) {
                stack.pop();
            }
        }
        setAllVisitedToFalse();
        System.out.println();
    }

    public void bfs() {
        Queue<Vertex> queue = new LinkedList<Vertex>();
        queue.add(vertexList.get(0));
        vertexList.get(0).visited = true;
        System.out.print(vertexList.get(0).getLabel() + " ");

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();

            for (int i = 0; i < adjMatrix.size(); i++) {
                if (adjMatrix.get(vertexList.indexOf(current)).get(i) != 0 && !vertexList.get(i).visited) {
                    queue.add(vertexList.get(i));
                    vertexList.get(i).visited = true;
                    System.out.print(vertexList.get(i).getLabel() + " ");
                }
            }
        }

        setAllVisitedToFalse();
        System.out.println();
    }

    int getMinimumVertex(boolean[] mst, int[] key) {
        int minKey = Integer.MAX_VALUE;
        int vertex = -1;
        for (int i = 0; i < vertexList.size(); i++) {
            // Neighboring vertex havent visited and the weight is lesser
            if (mst[i] == false && minKey > key[i]) {
                minKey = key[i];
                vertex = i;
            }
        }
        // Returning the vertex with smallest edge weight
        return vertex;
    }

    public void primMST() {
        boolean[] mst = new boolean[vertexList.size()];
        int[] key = new int[vertexList.size()];
        Edge[] mstEdges = new Edge[vertexList.size() - 1];

        Arrays.fill(key, Integer.MAX_VALUE);

        key[0] = 0;

        for (int i = 0; i < vertexList.size() - 1; i++) {
            int vertex = getMinimumVertex(mst, key);
            mst[vertex] = true;

            for (int j = 0; j < vertexList.size(); j++) {
                if (adjMatrix.get(vertex).get(j) > 0 && !mst[j] && adjMatrix.get(vertex).get(j) < key[j]) {
                    mstEdges[j - 1] = new Edge(vertex, j, adjMatrix.get(vertex).get(j));
                    key[j] = adjMatrix.get(vertex).get(j);
                }
            }
        }

        Arrays.sort(mstEdges, Comparator.comparingInt(edge -> (edge != null) ? edge.vertex1 : Integer.MAX_VALUE));

        printPrimMST(mstEdges);
    }

    void printPrimMST(Edge[] primMst) {
        int totalMinWeight = 0;
        System.out.println("Minimum Spanning Tree: ");

        for (int i = 0; i < primMst.length; i++) {
            if (primMst[i] != null) {
                System.out.println(
                        "Edge: " + vertexList.get(primMst[i].vertex1).getLabel() +
                                " - " + vertexList.get(primMst[i].vertex2).getLabel() +
                                " Weight: " + primMst[i].weight);
                totalMinWeight += primMst[i].weight;
            }
        }
        System.out.println("Total minimum weight: " + totalMinWeight);
    }

    public void kruskalMST() {
        PriorityQueue<Edge> pq = new PriorityQueue<>(edgeList.size(), Comparator.comparingInt(o -> o.weight));
        int[] parent = new int[vertexList.size()];
        ArrayList<Edge> mstEdges = new ArrayList<>();

        for (int i = 0; i < edgeList.size(); i++) {
            pq.add(edgeList.get(i));
        }

        for (int i = 0; i < vertexList.size(); i++) {
            parent[i] = i;
        }

        while (!pq.isEmpty() && mstEdges.size() < vertexList.size() - 1) {
            Edge edge = pq.poll();
            int root1 = find(parent, edge.vertex1);
            int root2 = find(parent, edge.vertex2);

            // If including this edge does not cause a cycle, add it to the MST
            if (root1 != root2) {
                mstEdges.add(edge);
                union(parent, root1, root2);
            }
        }

        printKruskalMST(mstEdges);

    }

    // Union-Find (Disjoint Set) Operations
    private int find(int[] parent, int vertex) {
        if (parent[vertex] != vertex) {
            parent[vertex] = find(parent, parent[vertex]); // Path compression
        }
        return parent[vertex];
    }

    private void union(int[] parent, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        parent[rootX] = rootY;
    }

    void printKruskalMST(ArrayList<Edge> kruskalMST) {
        int totalMinWeight = 0;
        System.out.println("Minimum Spanning Tree: ");

        for (Edge edge : kruskalMST) {
            System.out.println(
                    "Edge: " + vertexList.get(edge.vertex1).getLabel() +
                            " - " + vertexList.get(edge.vertex2).getLabel() +
                            " Weight: " + edge.weight);
            totalMinWeight += edge.weight;
        }
        System.out.println("Total minimum weight: " + totalMinWeight);
    }

    public static void main(String[] args) {
        Graph theGraph = new Graph();
        theGraph.addVertex("A"); // 0
        theGraph.addVertex("B"); // 1
        theGraph.addVertex("C"); // 2
        theGraph.addVertex("D"); // 3
        theGraph.addVertex("E"); // 4
        theGraph.addVertex("F"); // 5
        theGraph.addVertex("G"); // 6
        theGraph.addVertex("H"); // 7
        theGraph.addVertex("I"); // 8
        theGraph.addVertex("J"); // 9
        System.out.println("Daftar Vertex : ");
        for (int i = 0; i < theGraph.size(); i++) {
            System.out.print(theGraph.displayVertex(i) + " ");
        }
        System.out.println();

        theGraph.addEdge(0, 1, 5); // AB
        theGraph.addEdge(0, 2, 5); // AC
        theGraph.addEdge(1, 3, 4); // BD
        theGraph.addEdge(1, 4, 9); // BE
        theGraph.addEdge(2, 3, 4); // CD
        theGraph.addEdge(2, 5, 7); // CF
        theGraph.addEdge(3, 4, 8); // DE
        theGraph.addEdge(3, 5, 6); // DF
        theGraph.addEdge(4, 5, 10); // EF
        theGraph.addEdge(4, 6, 10); // EG
        theGraph.addEdge(4, 7, 8); // EH
        theGraph.addEdge(4, 8, 9); // EI
        theGraph.addEdge(5, 6, 3); // FG
        theGraph.addEdge(6, 7, 6); // GH
        theGraph.addEdge(6, 9, 7); // GJ
        theGraph.addEdge(7, 8, 4); // HI
        theGraph.addEdge(7, 9, 6); // HJ
        theGraph.addEdge(8, 9, 7); // IJ

        System.out.println("Matrik adjacency");
        theGraph.display();
        theGraph.primMST();
        System.out.println();
        theGraph.kruskalMST();

    }
}

class Vertex {
    String label;
    Boolean visited;

    public Vertex(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public String toString() {
        return label;
    }
}

class Edge {
    int vertex1;
    int vertex2;
    int weight;

    public Edge(int vertex1, int vertex2, int weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }
}
