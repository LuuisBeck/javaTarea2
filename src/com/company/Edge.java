package com.company;

import java.util.Comparator;

public class Edge {
    public Point first;
    public Point second;

    public double distance;

    public Edge(Point first, Point second, double distance) {
        this.first = first;
        this.second = second;
        this.distance = distance;
    }

    public Point getFirst() {
        return first;
    }

    public Point getSecond() {
        return second;
    }

    public double getDistance() {
        return distance;
    }
}
