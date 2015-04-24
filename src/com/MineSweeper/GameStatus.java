package com.MineSweeper;

import javax.swing.*;

/**
 * Created by thirabayashi on 4/22/15.
 */
public class GameStatus {
    private boolean flagStatus = false;
    private boolean finished = false;
    private int validFlagCount = 0;
    private int cleanSquares;
    private int mines;
    private JPanel menu;

    public GameStatus(int squares, int mines){
        cleanSquares = squares-mines;
        this.mines = mines;
    }

    public void addMenu(JPanel menu){
        this.menu = menu;
    }
    public int getMines(){
        return mines;
    }

    public boolean checkWin(){
        System.out.println(cleanSquares);
        System.out.println(cleanSquares);

        if(cleanSquares == 0){
            System.out.println("You won!");
            menu.add(new JTextArea("Awesome You won!"));
            finished=true;
            return true;
        }
        return false;
    }

    public void lost(){
        System.out.println(cleanSquares);

        menu.add(new JTextArea("You Lost!"));
    }

    public void clickedSquare(){
        System.out.println(cleanSquares);
        cleanSquares--;
        checkWin();
    }
    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public boolean isFlagStatus() {
        return flagStatus;
    }

    public void setFlagStatus(boolean flagStatus) {
        this.flagStatus = flagStatus;
    }


    public int getValidFlagCount() {
        return validFlagCount;
    }

    public void setValidFlagCount(int validFlagCount) {
        this.validFlagCount = validFlagCount;
    }

    public void incrementFlagCount(){
        validFlagCount++;
    }

    public void decrementFlagCount(){
        validFlagCount--;
    }

}
