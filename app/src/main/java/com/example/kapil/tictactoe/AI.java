package com.example.kapil.tictactoe;

/**
 * Created by kapil on 20-01-2018.
 */

public class AI {

    private static int humanPlayer = 1;
    private static int aiPlayer = 2;

    //finding best move for zero  i.e computer whose value in visited is 1
    public int findBestMove (int visited[][]) {
        int bestValue = -1000;
        int move = -1;

        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                // 0 for unvisited 1 for cross and 2 for zero
                if (visited[i][j] == 0) {
                    visited[i][j] = aiPlayer;
                    int currentVal = minimax(visited,0,false);
                    visited[i][j] = 0;
                    if (currentVal > bestValue) {
                        bestValue = currentVal;
                        move = (i*3) + j%3;
                    }
                }
            }
        }

        return move;
    }

    //AI is based on minmax algorithm
    private static int minimax (int visited[][],int depth,boolean isMax) {
        int score = evaluvate(visited);

        if (score == 10 || score == -10)
            return score;

        if (!isMovesLeft(visited))
            return 0;

        if (isMax) {
            int best = -1000;

            for (int i=0;i<3;i++) {
                for (int j=0;j<3;j++) {
                    if (visited[i][j] == 0) {
                        visited[i][j] = aiPlayer;
                        best = Math.max(best,minimax(visited,depth+1,!isMax));
                        visited[i][j] = 0;
                    }
                }
            }
            return best - depth;
        } else {
            int best = 1000;

            for (int i=0;i<3;i++) {
                for (int j=0;j<3;j++) {
                    if (visited[i][j] == 0) {
                        visited[i][j] = humanPlayer;
                        best = Math.min(best,minimax(visited,depth+1,!isMax));
                        visited[i][j] = 0;
                    }
                }
            }
            return best + depth;
        }
    }

    private static int evaluvate (int visited[][]) {

        int humanWin = 10;
        int aiWin = -10;
        int draw = 0;

        //check for row
        for (int i=0;i<3;i++) {
            int temp = visited[i][0] * visited[i][1] * visited[i][2];
            if (temp == 1) {//all fields are 1 mean all cross
                return aiWin;
            } else if (temp == 8) {//all fields are 2 means all zero
                return humanWin;
            }
        }

        //check for column
        for (int i=0;i<3;i++) {
            int temp = visited[0][i] * visited[1][i] * visited[2][i];
            if (temp == 1) {//all fields are 1 mean all cross
                return aiWin;
            } else if (temp == 8) {//all fields are 2 means all zero
                return humanWin;
            }
        }

        //check for diagonals
        int d1 = visited[0][0] * visited[1][1] * visited[2][2];
        int d2 = visited[2][0] * visited[1][1] * visited[0][2];
        if (d1 == 1 || d1 == 8) {
            if (d1 == 1) return aiWin;
            return humanWin;
        } else if (d2 == 1 || d2 == 8) {
            if(d2 == 1) return aiWin;
            return humanWin;
        }

        return draw;
    }

    private static boolean isMovesLeft (int visited[][]) {
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                if (visited[i][j] == 0)
                    return true;
            }
        }
        return false;
    }

}
