package com.company;
import java.awt.*;
import static com.company.Menu.fGame;

/**
 * klasa opisujaca zachowanie i wyglad wroga
 */

public class Enemy {

    /**
     * Zmienna opisujaca polozenie x wroga
     */
    float x;

    /**
     * Zmienna opisujaca polozenie y wroga
     */
    float y;

    /**
     * Zmienna opisujaca szerokosc wroga
     */
    float width;

    /**
     * Zmienna opisujaca wysokosc wroga
     */
    float height;

    /**
     * Zmienna predkosc wroga
     */
    float speed;

    /**
     * konstruktor wroga
     */
    public Enemy(float x, float y, float width, float height, float speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;

    }

    /**
     * Metoda do aktualizowania wroga
     */
    public void update(){
        x+=speed;

        if(x > fGame.getWidth()-40){
            speed = -5;
        }
        if(x < 0){
            speed = 5;
        }
    }

    /**
     * Metoda do rysowania wroga
     */
    public void draw(Graphics2D g2d){
        g2d.setColor(Color.red.darker());
        g2d.fillRect((int)x,(int)y,(int)width,(int)height);
    }

    /**
     * Metoda do wyznaczania krawedzi wroga, uzywana przy wyznaczaniu kolizji
     */
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y,(int)width,(int)height);
    }
}
