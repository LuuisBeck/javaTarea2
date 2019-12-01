package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Ingrese cantidad de puntos a usar");
        try {
            String amount = br.readLine();
            System.out.println("Ingrese cantidad de grupos a dividir");
            String k_input = br.readLine();
            int kComponents = Integer.parseInt(k_input);
            ArrayList<Point> points = generatePoints(Integer.parseInt(amount));
            ArrayList<Edge> edges = generateSortedEdges(points);
            for (Point a : points) {
                System.out.println("Punto: " + a.x + ", " + a.y);
            }
            System.out.println("Cant. de aristas: " + edges.size());
            for (Edge e : edges) {
                System.out.println("Arista: Distancia=" + e.distance + ", ID=" + e.first.id + " - " + e.second.id);
            }
            Kruskal.minimumSpanningTree(points, edges, kComponents);
            int value = cantComponentesConexas(points);
            System.out.println(value);
            for (Point p : points) {
                System.out.println("ID punto: " + p.id);
            }
            /* No se deben mostrar todos los padres de todos los puntos
            porque solo busco el mínimo de aristas. Y solo muestro esas aristas
             */

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static ArrayList<Point> generatePoints(int n) {
        ArrayList<Point> resultPoints = new ArrayList<Point>();
        for (int i=0; i<n; i++) {
            int x = (int) (Math.random() * 1260);
            int y = (int) (Math.random() * 940);
            Point newPoint = new Point(x,y, x + "," + y);
            resultPoints.add(newPoint);
        }

        return resultPoints;
    }

    private static ArrayList<Edge> generateSortedEdges(ArrayList<Point> points) {
        //Se ordena array despues de generarlo
        ArrayList<Edge> resultEdges = new ArrayList<>();
        int n = points.size();
        for (int i=0; i<n-1; i++) {
            Point pointA = points.get(i);
            for (int j=i+1; j<n; j++) {
                Point pointB = points.get(j);
                double distanceY = Math.abs(pointB.y - pointA.y);
                double distanceX = Math.abs(pointB.x - pointA.x);
                double distance = Math.hypot(distanceY, distanceX);
                Edge newEdge = new Edge(pointA, pointB, distance);
                resultEdges.add(newEdge);
            }
        }

        //Ordenar aristas
        //quickSort(resultEdges, 0, n-1);
        Collections.sort(resultEdges, new Comparator<Edge>() {
            @Override
            public int compare(Edge o1, Edge o2) {
                return Double.compare(o1.distance, o2.distance);
            }
        });

        return resultEdges;
    }

    public static int cantComponentesConexas(ArrayList<Point> points){
        ArrayList<Point> raices = new ArrayList<>();

        for (Point p : points) {
            Point raiz = UnionFind.find(p);
            if (!raices.contains(raiz)) {
                raices.add(raiz);
            }
        }
        return raices.size();
    }
}
