package com.company;

import javax.swing.*;
import java.awt.*;

/**
 * klasa opisujaca obrazek zakończenia gry
 */

public class EndGame {

    float x, y;
    private String end = "/images/end.jpg";
    /**
     * konstruktor obrazka zakończenia gry
     */

    public EndGame (float x, float y) {
        this.x = x;
        this.y = y;

    }
    /**
     * Metoda do rysowania obrazka zakończenia gry
     */

    public void draw(Graphics2D g2d){
        g2d.drawImage(getEndImage(),(int)x,(int)y,null);
    }
    /**
     * Metoda do pobierania obrazka do wyswietlenia
     */

    public Image getEndImage(){
        ImageIcon i = new ImageIcon(getClass().getResource(end));
        return i.getImage();
    }
}
