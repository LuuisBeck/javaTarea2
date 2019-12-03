package com.company;

import java.util.ArrayList;

public class Kruskal {
    private static UnionFind u = new UnionFind();

    public static void minimumSpanningTree(ArrayList<Point> points, ArrayList<Edge> sortedEdges, int k) {
        int connectedComponents = points.size();
        int edgePointer = 0;
        ArrayList<Edge> MST = new ArrayList<>();

        while (connectedComponents > k) {
            Edge uYv = sortedEdges.get(edgePointer);
            if (!u.areInSameComponent(uYv.getFirst(), uYv.getSecond())) {
                connectedComponents -= 1;
                u.connect(uYv.getFirst(), uYv.getSecond());
                MST.add(uYv);
            }
            edgePointer++;
        }
    }

    //NO -> Sin compresion de caminos
    //NONO -> Sin compresion de caminos ni union por rangos
    //Compresion de caminos -> find
    //Union por rangos -> connect

    public static void minimumSpanningTreeNOUnion(ArrayList<Point> points, ArrayList<Edge> sortedEdges, int k) {
        int connectedComponents = points.size();
        int edgePointer = 0;
        ArrayList<Edge> MST = new ArrayList<>();

        while (connectedComponents > k) {
            Edge uYv = sortedEdges.get(edgePointer);
            if (!u.areInSameComponent(uYv.getFirst(), uYv.getSecond())) {
                connectedComponents -= 1;
                u.connectNOunion(uYv.getFirst(), uYv.getSecond());
                MST.add(uYv);
            }
            edgePointer++;
        }
    }

    public static void minimumSpanningTreeNOCompression(ArrayList<Point> points, ArrayList<Edge> sortedEdges, int k) {
        int connectedComponents = points.size();
        int edgePointer = 0;
        ArrayList<Edge> MST = new ArrayList<>();

        while (connectedComponents > k) {
            Edge uYv = sortedEdges.get(edgePointer);
            if (!u.areInSameComponentNOcompression(uYv.getFirst(), uYv.getSecond())) {
                connectedComponents -= 1;
                u.connectNOcompresion(uYv.getFirst(), uYv.getSecond());
                MST.add(uYv);
            }
            edgePointer++;
        }
    }

    public static void minimumSpanningTreeNOUnionNOCompression(ArrayList<Point> points, ArrayList<Edge> sortedEdges, int k) {
        int connectedComponents = points.size();
        int edgePointer = 0;
        ArrayList<Edge> MST = new ArrayList<>();

        while (connectedComponents > k) {
            Edge uYv = sortedEdges.get(edgePointer);
            if (!u.areInSameComponentNOcompression(uYv.getFirst(), uYv.getSecond())) {
                connectedComponents -= 1;
                u.connectNOcompressionNOunion(uYv.getFirst(), uYv.getSecond());
                MST.add(uYv);
            }
            edgePointer++;
        }
    }

}
