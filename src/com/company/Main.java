package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

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

            long startTime = System.nanoTime();
            Kruskal.minimumSpanningTree(points, edges, kComponents);
            long endTime = System.nanoTime();
            int value = cantComponentesConexas(points);
            System.out.println(value);
            for (Point p : points) {
                System.out.println("ID punto: " + p.id);
            }

            // Asignamos números a cada raiz para mostrarlo en archivo de texto
            // (solo admite de 0 hasta k-1)
            int init = 0;
            HashMap<String, Integer> mapConexas = new HashMap<>();
            for (Point p : points) {
                if (!mapConexas.containsKey(p.id)) {
                    mapConexas.put(p.id, init);
                    p.setID(Integer.toString(init));
                    init++;
                } else {
                    p.setID(Integer.toString(mapConexas.get(p.id)));
                }

            }


            //Archivo a exportar
            File newfile = new File("./k_clustering.txt");
            newfile.createNewFile();
            PrintStream writer = new PrintStream(newfile);
            for (Point p : points) {
                writer.println(p.x + " " + p.y + " " + p.id);
            }
            writer.close();
            System.out.println("Tiempo de ejecución: " + ((endTime - startTime) / 1000000000.0) + " segundos");
 
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
