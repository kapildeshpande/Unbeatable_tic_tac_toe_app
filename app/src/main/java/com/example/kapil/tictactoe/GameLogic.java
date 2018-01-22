package com.example.kapil.tictactoe;

import java.util.Random;

/**
 * Created by kapil on 19-01-2018.
 */

public class GameLogic {

    private int visited[][]; //0 for unvisited 2 for circle and 1 for cross
    private int numMovesPlayed;
    private final int humanPlayer = 1;
    private final int aiPlayer = 2;
    private AI ai;

    public GameLogic () {
        visited = new int[3][3];
        numMovesPlayed = 0;
        ai = new AI();
    }

    public void reset () {
        numMovesPlayed = 0;
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                visited[i][j] = 0;
            }
        }
    }

    public int aiMove () {
        int move;
        if (numMovesPlayed == 0) {
            Random rand = new Random(); //first ai move is random
            move = rand.nextInt(9);
        }
        else
            move = ai.findBestMove(visited);
        int rowNum = move/3;
        int colNum = move%3;

        numMovesPlayed++;
        visited[rowNum][colNum] = aiPlayer;
        return move;
    }

    public boolean humanMove (int pos) {
        int rowNum = pos / 3;
        int colNum = pos % 3;

        if (visited[rowNum][colNum] != 0)
            return false;

        numMovesPlayed++;
        visited[rowNum][colNum] = humanPlayer;
        return true;
    }

    //return 1 for win 2 for draw otherwise 0
    public int logic () {

        //check for row
        for (int i=0;i<3;i++) {
            int temp = visited[i][0] * visited[i][1] * visited[i][2];
            if (temp == 1 || temp == 8) {
                return 1;
            }
        }

        //check for column
        for (int i=0;i<3;i++) {
            int temp = visited[0][i] * visited[1][i] * visited[2][i];
            //all fields are 1 mean all cross
            //all fields are 2 means all zero
            if (temp == 1 || temp == 8) {
                return 1;
            }
        }

        //check for diagonals
        int d1 = visited[0][0] * visited[1][1] * visited[2][2];
        int d2 = visited[2][0] * visited[1][1] * visited[0][2];
        if (d1 == 1 || d1 == 8) {
            return 1;
        } else if (d2 == 1 || d2 == 8) {
            return 1;
        }
        if (numMovesPlayed == 9) {
            return 2;
        }
        return 0;
    }

    //make all visited so that no button is pressed after win
    public void gameOver () {

        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                visited[i][j] = 1;
            }
        }
    }
}
