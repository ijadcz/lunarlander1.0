package com.company;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

/**
 * klasa opisujaca okno z menu
 */

public class Menu extends JFrame implements ActionListener {

    private JFrame fMenu, fHelp, fBest, fNick;
    public static  JFrame fGame ;
    private JButton bStart, bHelp, bExit, bBest, bOkHelp, bOkBest, bOkNick, bAnulujNick;
    private JLabel lMenu, lNick;
    private JPanel pHelp, pBest;
    private JTextArea tHelp, tBest;
    private JTextField tNick;
    private String background = "/images/background.jpg";
    Game game;

    /**
     * konstruktor Menu
     */
    public Menu(){
        windowMenu();
    }

    /**
     * Metoda do rysowania okienka menu
     */

    public void windowMenu(){
        fMenu = new JFrame("Lunar Lander");
        fMenu.setSize(500, 500);
        fMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fMenu.setLayout(null);
        fMenu.setLocationRelativeTo(null);

        fMenu.setContentPane(new JLabel(new ImageIcon(getClass().getResource(background))));

        lMenu = new JLabel("MENU");
        lMenu.setBounds(220, 10, 100, 50);
        lMenu.setForeground(Color.magenta);
        fMenu.add(lMenu);
        lMenu.setFont(new Font("SansSerif", Font.BOLD, 20));

        bStart  = new JButton("START");
        bStart.setBounds(200, 100, 100, 50);
        fMenu.add(bStart);
        bStart.addActionListener(this);

        bHelp  = new JButton("POMOC");
        bHelp.setBounds(200, 180, 100, 50);
        fMenu.add(bHelp);
        bHelp.addActionListener(this);

        bBest  = new JButton("NAJLEPSZE WYNIKI");
        bBest.setBounds(175, 260, 150, 50);
        fMenu.add(bBest);
        bBest.addActionListener(this);

        bExit = new JButton("WYJŚCIE");
        bExit.setBounds(200, 340, 100, 50);
        fMenu.add(bExit);
        bExit.addActionListener(this);

        fMenu.setVisible(true);
        fMenu.setResizable(false);
    }
    /**
     * Metoda do rysowania okienka z grą
     */

    public void windowGame(){
        fGame = new JFrame("LunarLander");
        fGame.pack();
        fGame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fGame.setSize(700,700);
        fGame.setResizable(true);
        fGame.setLocationRelativeTo(null);

        fGame.add(game = new Game());
        fGame.setVisible(true);
    }
    /**
     * Metoda do rysowania okienka pomocy
     */

    public void windowHelp(){

        fHelp = new JFrame("Pomoc");
        fHelp.setSize(400, 260);
        fHelp.setLocationRelativeTo(null);

        bOkHelp = new JButton("OK");
        bOkHelp.setBounds(160,180, 80,30);
        fHelp.add(bOkHelp);
        bOkHelp.addActionListener(this);

        pHelp = new JPanel();
        fHelp.add(pHelp);
        pHelp.setBackground(Color.black);

        tHelp= new JTextArea();

        try{
            File file = new File("pomoc.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));

            String st;
            try{
                while ((st = br.readLine()) != null) {
                    tHelp.append(st+ "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: %s\n, ex");
        }

        tHelp.setFont(new Font("SansSerif", Font.BOLD, 16));
        tHelp.setBackground(Color.lightGray);
        tHelp.setEditable(false);

        fHelp.add(tHelp);
        fHelp.setVisible(true);
        fHelp.setResizable(false);
    }

    /**
     * Metoda do rysowania okienka z najlepszymi wynikami
     */

    public void windowListOfTheBest() {
        fBest = new JFrame("Najlepsze wyniki");
        fBest.setSize(400, 260);
        fBest.setLocationRelativeTo(null);

        bOkBest = new JButton("OK");
        bOkBest.setBounds(160,180, 80,30);
        fBest.add(bOkBest);
        bOkBest.addActionListener(this);

        pBest = new JPanel();
        fBest.add(pBest);
        pBest.setBackground(Color.black);

        tBest= new JTextArea();

        try{
            File file1 = new File("highscore.txt");
            BufferedReader br1 = new BufferedReader(new FileReader(file1));

            String st1;
            try{
                while ((st1 = br1.readLine()) != null) {
                    tBest.append(st1+ "\n");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR: %s\n, ex");
        }

        tBest.setFont(new Font("SansSerif", Font.BOLD, 16));
        tBest.setBackground(Color.lightGray);
        tBest.setEditable(false);

        fBest.add(tBest);
        fBest.setVisible(true);
        fBest.setResizable(false);

    }
    /**
     * Metoda do rysowania okienka do wpisywania nicku
     */

    public void windowNick(){
        fNick = new JFrame("Nick");
        fNick.setSize(300, 200);
        fNick.setLayout(null);
        fNick.setLocationRelativeTo(null);

        bOkNick = new JButton("OK");
        bOkNick.setBounds(50, 100, 60, 40);
        fNick.add(bOkNick);
        bOkNick.addActionListener(this);

        bAnulujNick = new JButton("Anuluj");
        bAnulujNick.setBounds(160, 100, 90, 40);
        fNick.add(bAnulujNick);
        bAnulujNick.addActionListener(this);

        lNick = new JLabel("Nick: ");
        lNick.setBounds(20, 30, 50, 30);
        tNick = new JTextField();
        tNick.setBounds(60, 30, 180, 30);
        fNick.add(lNick);
        fNick.add(tNick);

        fNick.setVisible(true);

    }

    /**
     * Metoda opisujaca dzialanie przyciskow
     */

    public void actionPerformed(ActionEvent e) {

        Object source = e.getSource();
        if(source == bStart) {
            windowNick();
        }
        else if (source == bExit){
            fMenu.dispose();
        }
        else if (source == bHelp){
            windowHelp();
        }
        else if (source == bOkHelp){
            fHelp.dispose();
        }
        else if (source == bBest){
            windowListOfTheBest();
        }
        else if (source == bOkBest){
            fBest.dispose();
        }
        else if (source == bOkNick){
            fNick.dispose();
            windowGame();
            game.nick = tNick.getText();
        }
        else if(source == bAnulujNick){
            fNick.dispose();
        }
    }
}
