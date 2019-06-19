package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.*;

import static com.company.Menu.fGame;

/**
 * klasa opisujaca zachowanie i wyglad gry
 */

public class Game extends JPanel implements ActionListener {


    private String background = "/images/background.jpg";
    private String fileName ="level_1.txt";

    /**
     * Zmienna przechowujaca nazwe gracza- nick
     */
    protected String nick;
    private File file= new File(fileName);
    int numberLevel = 1;
    private String levelFileName = "listOfLevel.txt";
    private File levelFile = new File(levelFileName);
    private boolean endgame = false;
    private boolean existEnemy = false;

    /**
     * Zmienna przechowujaca liczbe zyc
     */
    private int life = 3;
    private long timeStart, gameTime, currentTime;
    private float points;
    int Width, Height;
    private int xb=0, yb=20, widthB = 700, heightB = 700;
    private JLabel lNick,lLife, lLevel, lPoints;
    Timer gameTimer;
    Ground ground;
    Spaceship s;
    Airstrip a;
    EnemyController enemy;
    EndGame endGame = new EndGame(113,160);

    /**
     * konstruktor planszy gry
     */
    public Game(){
        setFocusable(true);
        setBackground(Color.black);
        setLayout(null);
        gameTimer = new Timer(10,this);
        gameTimer.start();
        timeStart = System.currentTimeMillis();
        ground = new Ground();
        enemy = new EnemyController();
        loadLevel(file);


        lLife = new JLabel("LIFES: " + life);
        lLife.setBounds(240, 0, 100, 20);
        lLife.setForeground(Color.white);
        lLife.setFont(new Font("SansSerif", Font.ROMAN_BASELINE, 15));
        add(lLife);

        lLevel = new JLabel("LEVEL: " + numberLevel);
        lLevel.setBounds(130, 0, 100, 20);
        lLevel.setForeground(Color.white);
        lLevel.setFont(new Font("SansSerif", Font.ROMAN_BASELINE, 15));
        add(lLevel);
    }

    /**
     * Metoda do rysowania planszy gry
     */
    public void paint(Graphics g){
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(getBackgroundImage(),xb,yb,widthB,heightB,this);

        s.draw(g2d);
        a.draw(g2d);
        ground.draw(g2d);

        if(existEnemy == true){
            enemy.draw(g2d);
        }

        lNick = new JLabel("NICK: " + nick);
        lNick.setBounds(15, 0, 100, 20);
        lNick.setForeground(Color.white);
        lNick.setFont(new Font("SansSerif",Font.ROMAN_BASELINE, 15));
        add(lNick);

        remove(lLife);
        lLife = new JLabel("LIFES: " + life);
        lLife.setBounds(240, 0, 100, 20);
        lLife.setForeground(Color.white);
        lLife.setFont(new Font("SansSerif", Font.ROMAN_BASELINE, 15));
        add(lLife);

        remove(lLevel);
        lLevel = new JLabel("LEVEL: " + numberLevel);
        lLevel.setBounds(130, 0, 100, 20);
        lLevel.setForeground(Color.white);
        lLevel.setFont(new Font("SansSerif", Font.ROMAN_BASELINE, 15));
        add(lLevel);

        if(s.velX > s.velXmin && s.velX < s.velXmax && s.velY > s.velYmin && s.velY < s.velYmax){
            g2d.setColor(Color.green);
            g2d.fillOval(620*Width/700, 20*Height/700, 40*Width/700, 40*Height/700);
        }
        else {
            g2d.setColor(Color.red);
            g2d.fillOval(620*Width/700, 20*Height/700, 40*Width/700, 40*Height/700);
        }

        if(endgame == true){
            endGame.draw(g2d);
        }
}
    /**
     * Metoda dodajaca zdjecie tla gry
     */
    public Image getBackgroundImage(){
        ImageIcon i = new ImageIcon(getClass().getResource(background));
        return i.getImage();
    }

    /**
     * Metoda z petla gry
     */
    public void actionPerformed(ActionEvent e) {

        currentTime = System.currentTimeMillis();
        gameTime = (currentTime - timeStart)/1000;

        Width = fGame.getWidth();
        Height = fGame.getHeight();


        if(s.getBounds().intersects(a.getBounds()) && s.velX > s.velXmin && s.velX < s.velXmax
                && s.velY > s.velYmin && s.velY < s.velYmax ){
            s.velY = 0;
            nextLevel(levelFile);
            System.out.println(fileName);
        }
        else if(s.getBounds().intersects(a.getBounds()) && (s.velX < s.velXmin || s.velX > s.velXmax
                || s.velY < s.velYmin || s.velY > s.velYmax) && life > 0){
            life--;
            file = new File (fileName);
            loadLevel(file);
        }
        else if(s.getBounds().intersects(a.getBounds()) && (s.velX < s.velXmin || s.velX > s.velXmax
                || s.velY < s.velYmin || s.velY > s.velYmax) && life == 0){
            endgame = true;
        }

        else if(s.collision == true){
            if(life > 0) {
                file = new File (fileName);
                loadLevel(file);
                life--;
                s.collision = false;
            }
            else if(life == 0){
            endgame = true;
            }

        }
        if(endgame == true){
            points = ((numberLevel-1)*30)+(life*5)-(0.1f*(gameTime-s.timeAll));
            gameTimer.stop();
            saveHighScore();

            lPoints = new JLabel("POINTS: " + points);
            lPoints.setBounds(340, 0, 100, 20);
            lPoints.setForeground(Color.white);
            lPoints.setFont(new Font("SansSerif", Font.ROMAN_BASELINE, 15));
            add(lPoints);
        }

        autoResize();
        s.update();
        if(existEnemy == true){
            enemy.update();
        }

        repaint();
    }

    /**
     * Metoda wczytujaca plik z nazwa kolejnych poziomow
     */
    public void nextLevel(File file ){
        numberLevel ++;
        try {
            BufferedReader read= new BufferedReader(new FileReader(file));
            String line = null;

            while((line = read.readLine()) != null) {
                String[] l = line.split(" ");
                if (Float.parseFloat(l[0]) == numberLevel) {
                    fileName = l[1];
                    file = new File (fileName);
                    loadLevel(file);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda wczytujaca kolejny poziom
     */
    public void loadLevel(File file) {
        for(int i =0; i < ground.o.size(); i = 0) {
            ground.o.remove(i);
        }
        for(int i =0; i < enemy.e.size(); i = 0) {
            enemy.e.remove(i);
        }
        try {
            BufferedReader read= new BufferedReader(new FileReader(file));
            String line = null;

            while((line = read.readLine()) != null){
                // System.out.println(line);
                String[] l = line.split(" ");
                if (Float.parseFloat(l[0]) == 1){
                    s = new Spaceship(Float.parseFloat(l[1]),Float.parseFloat(l[2]),Float.parseFloat(l[3]),Float.parseFloat(l[4]),
                            Float.parseFloat(l[5]),Float.parseFloat(l[6]),Float.parseFloat(l[7]),Float.parseFloat(l[8]));
                    addKeyListener(new KeyInput(s));
                }
                else if(Float.parseFloat(l[0]) == 2){
                    a = new Airstrip (Float.parseFloat(l[1]), Float.parseFloat(l[2]),
                            Float.parseFloat(l[3]), Float.parseFloat(l[4]));
                }
                else if(Float.parseFloat(l[0]) == 3){
                    ground.addObstacles(new Obstacles(Float.parseFloat(l[1]), Float.parseFloat(l[2]),
                            Float.parseFloat(l[3]), Float.parseFloat(l[4])));
                }
                else if(Float.parseFloat(l[0]) == 4){
                    enemy.addEnemy(new Enemy(Float.parseFloat(l[1]), Float.parseFloat(l[2]),
                             Float.parseFloat(l[3]), Float.parseFloat(l[4]), Float.parseFloat(l[5])));
                     existEnemy = true;
                }
                else{
                    endgame = true;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda zapisujaca wynik do listy najlepszych wynikow
     */

    public void saveHighScore() {
        FileReader readFile ;
        BufferedReader reader = null;
        String[] temp = new String[6];
        FileWriter writeFile ;
        BufferedWriter writer = null;

        int i = 0;

        try {


            readFile = new FileReader("highscore.txt");
            reader = new BufferedReader(readFile);
            String score;
            try {
                while ((score = reader.readLine()) != null) {

                    temp[i] = score;
                    i++;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        temp[i] = nick + ":" + points;
        String temp2;



        for (int a = 0; a < i ; a++) {
            for (int j = 0; j < i ; j++) {
                if ((Float.parseFloat(temp[j].split(":")[1])) < (Float.parseFloat(temp[j + 1].split(":")[1]))) {
                    temp2 = temp[j];
                    temp[j] = temp[j + 1];
                    temp[j + 1] = temp2;
                }
            }
        }


        try {


            writeFile = new FileWriter("highscore.txt");
            writer = new BufferedWriter(writeFile);


            try {


                for (i = 0; i <5; i++) {
                    if (temp[i] != null) {

                        writer.write(temp[i]);
                        writer.write("\r\n");
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (
                IOException e) {
            e.printStackTrace();
        } finally {
            try {
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Metoda skalujaca gre
     */
    public void autoResize(){
        widthB = Width;
        heightB = Height;

        ground.resizeGround(Width,Height);
        a.resize(Width,Height);

        repaint();
    }

}
