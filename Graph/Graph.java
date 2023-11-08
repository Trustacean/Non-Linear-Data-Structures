package Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class Graph {
    ArrayList<ArrayList<Integer>> adjMatrix;
    ArrayList<Vertex> vertexList;

    public Graph() {
        vertexList = new ArrayList<Vertex>();
        adjMatrix = new ArrayList<ArrayList<Integer>>();
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

    public void addEdge(int source, int target) {
        // perbarui relasi antar vertex dalam matriks
        adjMatrix.get(source).set(target, 1);
        adjMatrix.get(target).set(source, 1);
    }

    public void display() {
        System.out.print("~ ");
        for (int i = 0; i < adjMatrix.size(); i++) {
            System.out.print(vertexList.get(i).getLabel() + " ");
        }

        System.out.println();

        for (int row = 0; row < adjMatrix.size(); row++) {
            System.out.print(vertexList.get(row).getLabel() + " ");
            for (int column = 0; column < adjMatrix.size(); column++) {
                System.out.print(adjMatrix.get(row).get(column) + " ");
            }
            System.out.println();
        }
    }

    public void dfs() {
        Stack<Vertex> stack = new Stack<Vertex>();
        stack.push(vertexList.get(0));
        vertexList.get(0).visited = true;
        System.out.println(vertexList.get(0).getLabel() + " ");

        while (!stack.isEmpty()) {
            Vertex current = stack.peek();
            boolean allAdjVisited = true;

            for (int i = 0; i < adjMatrix.size(); i++) {
                if (adjMatrix.get(vertexList.indexOf(current)).get(i) == 1 && !vertexList.get(i).visited) {
                    stack.push(vertexList.get(i));
                    vertexList.get(i).visited = true;
                    System.out.println(vertexList.get(i).getLabel() + " ");
                    allAdjVisited = false;
                    break;
                }
            }

            if (allAdjVisited) {
                stack.pop();
            }
        }

        for (int i = 0; i < adjMatrix.size(); i++) {
            vertexList.get(i).visited = false;
        }
    }

    public void bfs() {
        Queue<Vertex> queue = new LinkedList<Vertex>();
        queue.add(vertexList.get(0));
        vertexList.get(0).visited = true;
        System.out.println(vertexList.get(0).getLabel() + " ");

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();

            for (int i = 0; i < adjMatrix.size(); i++) {
                if (adjMatrix.get(vertexList.indexOf(current)).get(i) == 1 && !vertexList.get(i).visited) {
                    queue.add(vertexList.get(i));
                    vertexList.get(i).visited = true;
                    System.out.println(vertexList.get(i).getLabel() + " ");
                }
            }
        }

        for (int i = 0; i < adjMatrix.size(); i++) {
            vertexList.get(i).visited = false;
        }
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
        System.out.println("Daftar Vertex : ");
        for (int i = 0; i < theGraph.size(); i++) {
            System.out.println(theGraph.displayVertex(i));
        }
        theGraph.addEdge(0, 1); // AB
        theGraph.addEdge(0, 5); // AF
        theGraph.addEdge(0, 8); // AI
        theGraph.addEdge(1, 4); // BE
        theGraph.addEdge(1, 2); // BC
        theGraph.addEdge(2, 3); // CD
        theGraph.addEdge(2, 4); // CE
        theGraph.addEdge(3, 6); // DG
        theGraph.addEdge(3, 7); // DH
        theGraph.addEdge(6, 4); // GE
        theGraph.addEdge(6, 5); // GF

        System.out.println("Matrik adjacency");
        theGraph.display();
        System.out.println("\nDFS");
        theGraph.dfs();
        System.out.println("\nBFS");
        theGraph.bfs();
    }
}

class Vertex {
    public String label;
    public Boolean visited;

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