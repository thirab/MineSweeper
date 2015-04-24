package com.MineSweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MineSweeper {

    private int EASY_WIDTH  = 8;
    private int EASY_HEIGHT  = 8;
    private int EASY_BOMBS  = (int)((EASY_WIDTH*EASY_HEIGHT)*.12);


    private int MEDIUM_WIDTH  = 10;
    private int MEDIUM_HEIGHT  = 10;
    private int MEDIUM_BOMBS  = (int)((MEDIUM_WIDTH*MEDIUM_HEIGHT)*.2);

    private int HARD_WIDTH  = 15;
    private int HARD_HEIGHT  = 15;
    private int HARD_BOMBS  = (int)((HARD_HEIGHT*HARD_WIDTH)*.5);


    private int RECENT_WIDTH;
    private int RECENT_HEIGHT;
    private int RECENT_BOMBS;
    private JPanel RECENT_GAME_BOARD;

    private GameStatus status;
    private String gameName = "MineSweeper";
    private boolean finish = false;

    //create a new window
    JFrame mineSweeper = new JFrame(gameName);
    JPanel headerMenu;

    public MineSweeper(){


        mineSweeper.setLayout(new BorderLayout());

        //set to close
        mineSweeper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        headerMenu = createHeaderMenu();

        mineSweeper.add(headerMenu,BorderLayout.NORTH);

        createNewBoard(EASY_WIDTH,EASY_HEIGHT,EASY_BOMBS);
        //create a restart menu
        mineSweeper.add(createRestartMenu(),BorderLayout.SOUTH);

        mineSweeper.pack();
        mineSweeper.setVisible(true);
    }

    public void createNewBoard(int w, int h, int b){
        System.out.println("creating a new gameboard");
        RECENT_WIDTH=w;
        RECENT_HEIGHT=h;
        RECENT_BOMBS=b;

        status = new GameStatus((w*h),b);
        status.addMenu(headerMenu);

        if(RECENT_GAME_BOARD !=null){
            mineSweeper.remove(RECENT_GAME_BOARD);
            RECENT_GAME_BOARD.removeAll();

        }

        RECENT_GAME_BOARD = new GameBoard(w,h, status);

        mineSweeper.add(RECENT_GAME_BOARD, BorderLayout.CENTER);
        mineSweeper.revalidate();
        mineSweeper.repaint();

    }

    public JPanel createHeaderMenu() {
        JPanel menuBar = new JPanel();

        menuBar.setLayout(new GridLayout(0,1));
        GridBagConstraints c = new GridBagConstraints();
        JTextArea welcome = new JTextArea("Welcome to MineSweeper! If you would " +
                "like to change your level please select from the buttons below");
        menuBar.add(welcome);

        return menuBar;
    }


    public JPanel createRestartMenu() {

        JButton easy = new JButton("Easy");
        JButton med = new JButton("Med.");
        JButton hard = new JButton("Hard");
        JButton restart = new JButton("Restart");
        JButton real = new JButton("REAL!");

        easy.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                createNewBoard(EASY_WIDTH, EASY_HEIGHT, EASY_BOMBS);
            }
        });

        med.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                createNewBoard(MEDIUM_WIDTH,MEDIUM_HEIGHT,MEDIUM_BOMBS);
            }
        });
        hard.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                createNewBoard(HARD_WIDTH,HARD_HEIGHT,HARD_BOMBS);
            }
        });
        restart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                createNewBoard(RECENT_WIDTH,RECENT_HEIGHT,RECENT_BOMBS);
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(0,4));

        buttonPanel.add(easy);
        buttonPanel.add(med);
        buttonPanel.add(hard);
        buttonPanel.add(restart);


        return buttonPanel;
    }

    public static void main(String[] args) {

        MineSweeper game = new MineSweeper();
	// write your code here
    }
}
