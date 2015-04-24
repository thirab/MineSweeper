package com.MineSweeper;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by thirabayashi on 4/22/15.
 */
public class GameBoard extends JPanel {

    private GameStatus status;


    public GameBoard(int w, int h, GameStatus status) {
        this.status = status;

        //create a grid layout of the GameBoard's dimensions (0 to width)
        GridLayout gameboardLayout = new GridLayout(0, w);
        setLayout(gameboardLayout);
        Tile[] tiles = new Tile[w * h];


        for (int i = 0; i < tiles.length; i++) {
            tiles[i] = new Tile(status);
        }

        Random rand = new Random();
        for (int j = 0; j < status.getMines(); j++) {
            int choice = rand.nextInt(tiles.length);
            if (!tiles[choice].getMineStatus()) {
                tiles[choice].setMineStatus(true);
            } else {
                j--;
            }
        }

        System.out.println("Tiles " + tiles.length);
        for (int i = 0; i < w; i++) {
            for (int j = 0; j < h; j++) {
                int place = getRealValue(j, i, w);
                System.out.println("i= " + i + "j= " + j + "place= " + place);
                if (i < 1) {
                    if (j < 1) {
                        tiles[place].setRight(tiles[place + 1]);
                        tiles[place].setDown(tiles[place + w]);
                        tiles[place].setRightDown(tiles[place + w + 1]);

                    } else if (j == (h - 1)) {
                        tiles[place].setLeft(tiles[place - 1]);
                        tiles[place].setDown(tiles[place + w]);
                        tiles[place].setLeftDown(tiles[place + w - 1]);


                    } else {
                        tiles[place].setRight(tiles[place + 1]);
                        tiles[place].setLeft(tiles[place - 1]);
                        tiles[place].setDown(tiles[place + w]);
                        tiles[place].setRightDown(tiles[place + w + 1]);
                        tiles[place].setLeftDown(tiles[place + w - 1]);
                    }
                } else if (i == (w - 1)) {
                    if (j < 1) {
                        tiles[place].setUp(tiles[place - w]);
                        tiles[place].setRight(tiles[place + 1]);
                        tiles[place].setRightUp(tiles[place - w + 1]);


                    } else if (j == (h - 1)) {
                        tiles[place].setUp(tiles[place - w]);
                        tiles[place].setLeft(tiles[place - 1]);
                        tiles[place].setLeftUp(tiles[place - w - 1]);


                    } else {
                        tiles[place].setUp(tiles[place - w]);
                        tiles[place].setRight(tiles[place + 1]);
                        tiles[place].setLeft(tiles[place - 1]);
                        tiles[place].setLeftUp(tiles[place - w - 1]);
                        tiles[place].setRightUp(tiles[place - w + 1]);
                    }
                } else {
                    if (j < 1) {
                        tiles[place].setDown(tiles[place + w]);
                        tiles[place].setUp(tiles[place - w]);
                        tiles[place].setRight(tiles[place + 1]);
                        tiles[place].setRightUp(tiles[place - w + 1]);
                        tiles[place].setRightDown(tiles[place + w + 1]);

                    } else if (j == (h - 1)) {
                        tiles[place].setUp(tiles[place - w]);
                        tiles[place].setLeft(tiles[place - 1]);
                        tiles[place].setDown(tiles[place + w]);
                        tiles[place].setLeftUp(tiles[place - w - 1]);
                        tiles[place].setLeftDown(tiles[place + w - 1]);


                    } else {
                        tiles[place].setUp(tiles[place - w]);
                        tiles[place].setDown(tiles[place + w]);
                        tiles[place].setLeft(tiles[place - 1]);
                        tiles[place].setRight(tiles[place + 1]);


                        tiles[place].setLeftUp(tiles[place - w - 1]);
                        tiles[place].setRightUp(tiles[place - w + 1]);
                        tiles[place].setRightDown(tiles[place + w + 1]);
                        tiles[place].setLeftDown(tiles[place + w - 1]);

                    }
                }

                add(tiles[place]);
            }

        }
    }

    private int getRealValue(int i, int j, int w) {
        int realValue = i + (j * w);
        return realValue;
    }
}
