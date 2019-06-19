package com.company;

import java.awt.*;

/**
 * klasa opisujaca zachowanie i wyglad ladowiska
 */
public class Airstrip {

    /**
     * Zmienna opisujaca polozenie x ladowiska
     */
    float x;

    /**
     * Zmienna opisujaca polozenie y ladowiska
     */
    float y;

    /**
     * Zmienna opisujaca szerokosc ladowiska
     */
    float width;

    /**
     * Zmienna opisujaca wysokosc ladowiska
     */
    float height;

    /**
     * Zmienne pomocnicze
     */
    float a,b,c,d;

    /**
     * konstruktor ladowiska
     */
    public Airstrip(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        a = x/700;
        b = y/700;
        c = width/700;
        d = height/700;
    }

    /**
     * Metoda do rysowania ladowiska
     */
    public void draw(Graphics2D g2d){
        g2d.setColor(Color.gray);
        g2d.fillRect((int)x, (int)y, (int)width, (int)height);

        g2d.draw(getBounds());

    }

    /**
     * Metoda do wyznaczania krawedzi ladowiska, uzywana przy wyznaczaniu kolizji
     */
    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, (int)width, (int)height);
    }

    /**
     * Metoda do skalowania przeszkody
     */
     public void resize(int Width,int Height){
        x = a*Width;
        y = b*Height;
        width = c*Width;
        height =d * Height;
    }
}
