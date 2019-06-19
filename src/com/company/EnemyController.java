package com.company;

import java.awt.*;
import java.util.LinkedList;

/**
 * kontener przechowujacy wrogow
 */

public class EnemyController {

    static LinkedList<Enemy> e = new LinkedList<Enemy>();

    Enemy tempEnemy;

    /**
     * Metoda do rysowania kolejnych wrogow
     */
    public void draw (Graphics2D g2d){
        for (int i = 0; i < e.size(); i++){
            tempEnemy = e.get(i);

            tempEnemy.draw(g2d);
        }
    }

    /**
     * Metoda do aktualizowania kolejnych wrogow
     */

    public void update (){
        for (int i = 0; i < e.size(); i++){
            tempEnemy = e.get(i);

            tempEnemy.update();
        }
    }

    /**
     * Metoda dodajaca wroga do kontener
     */
    public void addEnemy(Enemy enemy){
        e.add(enemy);
    }

    /**
     * Metoda usuwajaca wroga z kontenera
     */
    public void removeEnemy(Enemy enemy) {
        e.remove(enemy);
    }

    /**
     * Metoda do wyznaczania krawedzi dla kazdego wroga, uzywana przy wyznaczaniu kolizji
     */

    public static LinkedList<Enemy> getEnemiesBounds(){
        return e;
    }
}
