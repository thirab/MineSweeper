package com.MineSweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by thirabayashi on 4/22/15.
 */
public class Tile extends JButton {

    private Tile left;
    private Tile right;
    private Tile up;
    private Tile down;
    private Tile leftUp;
    private Tile leftDown;
    private Tile rightUp;
    private Tile rightDown;

    private boolean mineStatus = false;
    private boolean currentlyFlagged = false;
    private boolean clicked = false;
    private boolean displayed = false;

    private GameStatus status;


    public Tile(GameStatus status) {

        //make button color show
        setContentAreaFilled(false);
        setOpaque(true);

        this.status = status;

        addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (isTheGameFinished() || clicked) {
                    return;
                } else {
                    // are we on flag mode?
                    if (areWeFlagging()) {
                        //if we are flagged remove the flag from this square
                        if (currentlyFlagged) {
                            removeFlag();
                        } else {
                            //other wise flag it!
                            flag();
                        }
                    } else {
                        // we cannot click this button again
                        clicked = true;
                        //we are not flagging
                        if (mineStatus) {
                            //the game is over we hit a mine
                            endGame();
                        } else {
                            safeClicked();

                        }
                    }

                }
            }
        });
    }

    public void safeClicked() {
        displayTile();


        int nearbyBombs = getSafeCount();
        setText(nearbyBombs + "");
        if (nearbyBombs > 0) {
            return;
        } else {
            showNeighbors();
        }
    }

    public void displayTile() {

        if(!displayed) {
            status.clickedSquare();
        }
        setBackground(Color.GREEN);
        displayed = true;
    }

    public boolean isTheGameFinished() {
        return status.isFinished();
    }

    public void endGame() {
        status.lost();
        status.setFinished(true);
        setBackground(Color.RED);
        setText("XX");
    }

    public void flag() {
        setText("F");
        status.incrementFlagCount();
        currentlyFlagged = true;
    }

    public void removeFlag() {
        status.decrementFlagCount();
        setText("");
        currentlyFlagged = false;
    }

    public boolean areWeCurrentlyFlagged() {
        return currentlyFlagged;

    }

    public boolean areWeFlagging() {
        return status.isFlagStatus();
    }

    public Tile getLeft() {
        return left;
    }

    public void setLeft(Tile left) {
        this.left = left;
    }

    public Tile getRight() {
        return right;
    }

    public void setRight(Tile right) {
        this.right = right;
    }

    public Tile getUp() {
        return up;
    }

    public void setUp(Tile up) {
        this.up = up;
    }

    public Tile getDown() {
        return down;
    }

    public void setDown(Tile down) {
        this.down = down;
    }

    public boolean getMineStatus() {
        return mineStatus;
    }

    public void setMineStatus(boolean isMine) {
        this.mineStatus = isMine;
        setText("B");
    }

    public Tile getLeftDown() {
        return leftDown;
    }

    public void setLeftDown(Tile leftDown) {
        this.leftDown = leftDown;
    }

    public Tile getLeftUp() {
        return leftUp;
    }

    public void setLeftUp(Tile leftUp) {
        this.leftUp = leftUp;
    }

    public Tile getRightUp() {
        return rightUp;
    }

    public void setRightUp(Tile rightUp) {
        this.rightUp = rightUp;
    }

    public Tile getRightDown() {
        return rightDown;
    }

    public void setRightDown(Tile rightDown) {
        this.rightDown = rightDown;
    }

    public void showNeighbors() {
        displayValidTiles(left);
        displayValidTiles(right);
        displayValidTiles(up);
        displayValidTiles(down);
        displayValidTiles(leftUp);
        displayValidTiles(leftDown);
        displayValidTiles(rightUp);
        displayValidTiles(rightDown);
    }

    public int getSafeCount() {
        int count = 0;
        count += addBombs(left);
        count += addBombs(right);
        count += addBombs(up);
        count += addBombs(down);
        count += addBombs(leftUp);
        count += addBombs(leftDown);
        count += addBombs(rightUp);
        count += addBombs(rightDown);
        return count;
    }

    private void displayValidTiles(Tile t) {
        if (t != null) {
            t.displayTile();
            t.setText(t.getSafeCount() +"");
        }

    }

    private int addBombs(Tile t) {
        if (t != null && t.getMineStatus()) {
            return 1;
        }
        return 0;
    }

}
