package com.company;

import java.awt.*;
import java.util.LinkedList;

/**
 * kontener przechowujacy uksztaltowanie terenu
 */

public class Ground {

   static LinkedList<Obstacles> o = new LinkedList<Obstacles>();

    Obstacles tempObstacle;

    /**
     * Metoda do rysowania terenu
     */
     public void draw (Graphics2D g2d){
         for (int i = 0; i < o.size(); i++){
             tempObstacle = o.get(i);

             tempObstacle.draw(g2d);
         }
     }

    /**
     * Metoda do skalowania terenu
     */
    public void resizeGround (int Width, int Height){
        for (int i = 0; i < o.size(); i++){
            tempObstacle = o.get(i);

            tempObstacle.resize(Width,Height);
        }
    }

    /**
     * Metoda dodajaca przeszkode do terenu
     */
    public void addObstacles(Obstacles obstacles){
        o.add(obstacles);
    }

    /**
     * Metoda usuwajaca przeszkode z terenu
     */
    public void removeObstacles(Obstacles obstacles) {
        o.remove(obstacles);
    }

    /**
     * Metoda do wyznaczania krawedzi terenu, uzywana przy wyznaczaniu kolizji
     */
    public static LinkedList<Obstacles> getObstaclesBounds(){
        return o;
    }
}
