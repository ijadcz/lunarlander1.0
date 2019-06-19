package com.company;

import java.awt.*;


/**
 * klasa opisujaca przeszkody
 */
public class Obstacles {

    /**
     * Zmienna opisujaca polozenie x poczatku przeszkody
     */
    float x1;

    /**
     * Zmienna opisujaca polozenie y poczatku przeszkody
     */
    float y1;

    /**
     * Zmienna opisujaca polozenie x konca przeszkody
     */
    float x2;

    /**
     * Zmienna opisujaca polozenie y konca przeszkody
     */
    float y2;

    /**
     * Zmienne pomocnicze
     */
    float a,b,c,d;

    /**
     * konstruktor przeszkody
     */
    public Obstacles(float x1, float y1, float x2, float y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        a = x1/700;
        b = y1/700;
        c = x2/700;
        d = y2/700;
    }

    /**
     * Metoda do rysowania przeszkody
     */
    public void draw(Graphics2D g2d){
        g2d.setColor(Color.gray);
        g2d.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
    }

    /**
     * Metoda do wyznaczania krawedzi przeszkody, uzywana przy wyznaczaniu kolizji
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x1, (int) y1,4,4);
    }


    /**
     * Metoda do skalowania przeszkody
     */
    public void resize(int Width,int Height){
         x1 = a*Width;
         y1 = b*Height;
         x2 = c*Width;
         y2 = d*Height;
    }
}
