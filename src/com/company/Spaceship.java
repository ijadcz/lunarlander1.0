package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

import static com.company.Menu.fGame;

/**
 * klasa opisujaca zachowanie i wyglad statku
 */
public class Spaceship {

    private String spaceshipImage = "/images/ship.png";
    private LinkedList<Obstacles> o = Ground.getObstaclesBounds();
    private LinkedList<Enemy> e = EnemyController.getEnemiesBounds();
    protected long timePause, timeStart,timeStop, timeAll;
    public boolean pause, start;
    protected int width=49, height=90;




    /**
     * Zmienna opisujaca polozenie x statku
     */
    private float x;

    /**
     * Zmienna opisujaca polozenie y statku
     */
    private float y;

    /**
     * Zmienna opisujaca predkosc x statku
     */
    float velX;

    /**
     * Zmienna opisujaca predkosc y statku
     */
    float velY;

    /**
     * Zmienne opisujace polozenie tymczasowe statku
     */
    private float velXtemp, velYtemp, gravityYtemp, gravityXtemp;

    /**
     * Zmienna opisujaca minimalna predkosci stastku w kierunku x, dopuszczalna do ladowania
     */
    float velXmin;

    /**
     * Zmienna opisujaca minimalna predkosci statku w kierunku y, dopuszczalna do ladowania
     */
    float velYmin;

    /**
     * Zmienna opisujaca maksymalna predkosci statku w kierunku x, dopuszczalna do ladowania
     */
    float velXmax;

    /**
     * Zmienna opisujaca maksymalna predkosci statku w kierunku y, dopuszczalna do ladowania
     */
    float velYmax;

    /**
     * Zmienna opisujaca grawitacje w kierunku y
     */
    private float gravityY;

    /**
     * Zmienna opisujaca grawitacje w kierunku x
     */
    private float gravityX;

    /**
     * Zmienna boolean okreslajaca czy zaszla kolizja-true, lub nie-false
     */
    boolean collision = false;

    /**
     * konstruktor statku
     */
    public Spaceship(float x, float y, float velXmin, float velYmin, float velXmax, float velYmax,
                     float gravityX, float gravityY) {
        this.x = x;
        this.y = y;
        this.velXmin = velXmin;
        this.velYmin = velYmin;
        this.velXmax = velXmax;
        this.velYmax = velYmax;
        this.gravityY = gravityY;
        this.gravityX = gravityX;
        tempParameters();
    }

    /**
     * Metoda do aktualizowania statek
     */
    public void update() {
        y += velY;
        x += velX;

        velY += gravityY;
        velX += gravityX;

        //COLLISIONS WITH OUTSIDE
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (x > fGame.getWidth()-60) {
            x = fGame.getWidth()-60;
        }

        Collision();

    }

    /**
     * Metoda wykrywajaca kolizje
     */
    public void Collision() {
        for (int i = 0; i < o.size(); i++) {
            if (getBounds().intersects(o.get(i).getBounds())) {
                collision = true;
            }
        }
        for (int i = 0; i < e.size(); i++) {
            if (getBounds().intersects(e.get(i).getBounds())) {
                collision = true;
            }
        }
    }
    /**
     * Metoda opisujaca klawisze ktroymi sie poruszamy
     */
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            velX = -3;
        } else if (key == KeyEvent.VK_LEFT) {
            velX = 3;
        } else if (key == KeyEvent.VK_DOWN) {
            velY = -1;
        } else if (key == KeyEvent.VK_UP) {
            velY = 3;
        }
        if (key == KeyEvent.VK_P) {
            velX = 0;
            velY = 0;
            gravityX = 0;
            gravityY = 0;
            pause = true;
            timeStop = System.currentTimeMillis();


        }
        if (key == KeyEvent.VK_K) {
            velX = velXtemp;
            velY = velYtemp;
            gravityX = gravityXtemp;
            gravityY = gravityYtemp;
            pause = false;
            start = true;
            timeStart = System.currentTimeMillis();
            timePause = (timeStart - timeStop)/1000;
            timeAll+=timePause;
            System.out.println(timePause);
            System.out.println(timeAll);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_RIGHT) {
            velX = 0;
        } else if (key == KeyEvent.VK_LEFT) {
            velX = 0;
        } else if (key == KeyEvent.VK_DOWN) {
            velY = 0;
        } else if (key == KeyEvent.VK_UP) {
            velY = 0;
        }
    }

    /**
     * Metoda do rysowania statku
     */
    public void draw(Graphics2D g2d) {
        g2d.drawImage(getSpaceshipImage(), (int) x*fGame.getWidth()/700, (int) y*fGame.getHeight()/700, width*fGame.getWidth()/700, height*fGame.getHeight()/700,null);
    }

    /**
     * Metoda dodajaca obrazek statku
     */
    public Image getSpaceshipImage() {
        ImageIcon i = new ImageIcon(getClass().getResource(spaceshipImage));
        return i.getImage();
    }

    /**
     * Metoda do wyznaczania krawedzi statku, uzywana przy wyznaczaniu kolizji
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x*fGame.getWidth()/700, (int) y*fGame.getHeight()/700, width*fGame.getWidth()/700, height*fGame.getHeight()/700);

    }

    /**
     * Metoda do pobierania aktualnych parametrow statku
     */
    public void tempParameters(){
        velYtemp = velY;
        velXtemp = velX;
        gravityYtemp = gravityY;
        gravityXtemp = gravityX;

    }

}
