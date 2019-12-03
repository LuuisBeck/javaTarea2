package com.company;

import java.io.*;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;

public class RouteDesign {

    public static void main(String[] args) throws IOException {
        File file = new File("./rutas.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String st;
        String[] edgesDB; //temp variable for each execution of while
        HashMap<String, Point> pointMap = new HashMap<>();
        ArrayList<Point> points = new ArrayList<>();
        ArrayList<Edge> edges = new ArrayList<>();
        while ((st = br.readLine()) != null) {
            edgesDB = st.split(" ");
            String idEdge = edgesDB[0];
            String idStartPoint = edgesDB[1];
            String idEndPoint = edgesDB[2];
            String distance = edgesDB[3];
            Point pointA = new Point(0, Integer.parseInt(idStartPoint), idStartPoint);
            Point pointB = new Point(0, Integer.parseInt(idEndPoint), idEndPoint);
            Edge newEdge = new Edge(pointA, pointB, Double.parseDouble(distance));

            if (!pointMap.containsKey(idStartPoint)) {
                pointMap.put(idStartPoint, pointA);
                points.add(pointA);
            }

            if (!pointMap.containsKey(idEndPoint)) {
                pointMap.put(idEndPoint, pointB);
                points.add(pointB);
            }

            if (!edges.contains(newEdge)) {
                edges.add(newEdge);
            }
        }

        ArrayList<Edge> sortedEdges = Main.sortEdges(edges);
        System.out.println("Aristas: " + sortedEdges.size());
        System.out.println("Puntos: " + points.size());
        long startTime = System.nanoTime();
        Kruskal.minimumSpanningTree(points, sortedEdges, 1);
        long endTime = System.nanoTime();
        System.out.println("Tiempo de ejecución: " + ((endTime - startTime) / 1000000000.0) + " segundos");
    }
}
