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
            return p.father = find(p.father);
        }
    }

    public boolean areInSameComponent(Point x, Point y) {
        Point rootX = find(x);
        Point rootY = find(y);
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

    /* Mejoras
    Compresión de caminos: Se cambia el método FIND, donde en vez de retornar FIND(padre(point))
    se le asigna el padre del actual a la recursión -> padre(point) = FIND(padre(point))

    Unión por Rangos: Componente con mayor altura, predomina por sobre la de
    menor altura, así la al hacer UNION(c1, c2) queda como raíz la de mayor altura
     */
}
