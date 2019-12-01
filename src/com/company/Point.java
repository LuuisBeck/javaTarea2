package com.company;

public class Point {
    public int x;
    public int y;
    public String id;
    public Point father;
    public int altura;

    public Point(int x, int y, String id) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.father = this;
        this.altura = 0;
    }

    public void setFather(Point newFather) {
        this.father = newFather;
        this.id = newFather.id;
    }

    public void setID(String newID) {
        this.id = newID;
    }
}
