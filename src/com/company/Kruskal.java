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
}
