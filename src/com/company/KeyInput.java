package com.company;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * klasa obslugujaca klawisze
 */

public class KeyInput extends KeyAdapter {

    Spaceship s;

    public KeyInput(Spaceship s){
        this.s = s;
    }

    public void keyPressed(KeyEvent e){
        s.keyPressed(e);
    }
    public void keyReleased(KeyEvent e){
        s.keyReleased(e);
    }

}
