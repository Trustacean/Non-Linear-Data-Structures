package Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;
import java.util.Queue;

public class DirectedGraph {
    ArrayList<ArrayList<Integer>> adjMatrix;
    ArrayList<Vertex> vertexList;
    ArrayList<Edge> edgeList;

    public DirectedGraph() {
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

    public void topologicalSort() {
        Stack<Vertex> stack = new Stack<Vertex>();

        for (int i = 0; i < vertexList.size(); i++) {
            if (!vertexList.get(i).visited) {
                topologicalDFS(i, stack);
            }
        }

        while (!stack.isEmpty()) {
            System.out.print(stack.pop().getLabel()+" ");
        }
        System.out.println();
        setAllVisitedToFalse();
    }

    private void topologicalDFS(int vertex, Stack<Vertex> stack) {
        vertexList.get(vertex).visited = true;

        for (int i = 0; i < vertexList.size(); i++) {
            if (adjMatrix.get(vertex).get(i)!=0 && !vertexList.get(i).visited) {
                topologicalDFS(i, stack);
            }
        }
        stack.push(vertexList.get(vertex));
    }

    public static void main(String[] args) {
        DirectedGraph theGraph = new DirectedGraph();
        theGraph.addVertex("J1"); // 0
        theGraph.addVertex("J2"); // 1
        theGraph.addVertex("J3"); // 2
        theGraph.addVertex("J4"); // 3
        theGraph.addVertex("J5"); // 4
        theGraph.addVertex("J6"); // 5
        theGraph.addVertex("J7"); // 6
        theGraph.addEdge(0, 1, 1); // J1 J2
        theGraph.addEdge(0, 2, 1); // J1 J3
        theGraph.addEdge(1, 5, 1); // J2 J6
        theGraph.addEdge(1, 3, 1); // J2 J4
        theGraph.addEdge(1, 4, 1); // J2 J5
        theGraph.addEdge(2, 3, 1); // J3 J4
        theGraph.addEdge(3, 4, 1); // J4 J5
        theGraph.addEdge(4, 6, 1); // J5 J7


        System.out.println("Matrik adjacency");
        theGraph.display();
        theGraph.topologicalSort();
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
