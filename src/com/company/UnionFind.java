package com.company;

public class UnionFind {

    public UnionFind() {    }

    /*¿Debería dejar el Union-find separado o juntarlo con la definicion de point?,
    - Dejarlo separado, debería tener n Union-find para empezar, pero así sería mas fácil
    poder tener mejoras.
    - Al dejarlo junto, no me perdería con la definición del enunciado
     */

    public static Point find(Point p) {
        if (p.father.equals(p)) {
            return p;
        } else {
            Point newFather = find(p.father);
            p.id = newFather.id;
            return p.father = newFather;
        }
    }

    private Point findNO(Point p) { //without path compression
        if (p.father.equals(p)) {
            return p;
        } else {
            return findNO(p.father);
        }
    }

    public boolean areInSameComponent(Point x, Point y) {
        Point rootX = find(x);
        Point rootY = find(y);
        return rootX.equals(rootY);
    }

    public boolean areInSameComponentNOcompression(Point x, Point y) {
        Point rootX = findNO(x);
        Point rootY = findNO(y);
        return rootX.equals(rootY);
    }

    //Funciona como UNION para un Union-Find
    public void connect(Point a, Point b) {
        Point rootA = find(a);
        Point rootB = find(b);
        if (rootA.altura > rootB.altura) {
            rootB.setFather(rootA);
        } else {
            rootA.setFather(rootB);
            if (rootA.altura == rootB.altura) {
                rootB.altura++;
            }
        }
    }

    public void connectNOcompresion(Point a, Point b) {
        Point rootA = findNO(a);
        Point rootB = findNO(b);
        if (rootA.altura > rootB.altura) {
            rootB.setFather(rootA);
        } else {
            rootA.setFather(rootB);
            if (rootA.altura == rootB.altura) {
                rootB.altura++;
            }
        }
    }

    public void connectNOcompressionNOunion(Point a, Point b) {
        Point rootA = findNO(a);
        Point rootB = findNO(b);
        rootA.setFather(rootB);
    }

    public void connectNOunion(Point a, Point b) {
        Point rootA = find(a);
        Point rootB = find(b);
        rootA.setFather(rootB);
    }

    /* Mejoras
    Compresión de caminos: Se cambia el método FIND, donde en vez de retornar FIND(padre(point))
    se le asigna el padre del actual a la recursión -> padre(point) = FIND(padre(point))

    Unión por Rangos: Componente con mayor altura, predomina por sobre la de
    menor altura, así la al hacer UNION(c1, c2) queda como raíz la de mayor altura
     */
}
