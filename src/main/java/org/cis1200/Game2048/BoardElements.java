package org.cis1200.Game2048;

import java.io.*;
import java.util.LinkedList;
import java.util.Random;

public class BoardElements {
    private int[][] board;

    private int gameScore;
    private boolean gameLost;

    private boolean gameWon;
    private boolean combined;

    LinkedList<int[][]> pastBoards;

    public BoardElements() {
        board = new int[4][4];
        randomizeBoard();
        gameScore = 0;
        gameLost = false;
        gameWon = false;
        combined = false;
        pastBoards = new LinkedList<int[][]>();
        pastBoards.add(board);
    }

    public void randomizeBoard() {
        Random generator = new Random();
        int first = generator.nextInt(4);
        int second = generator.nextInt(4);
        int third = generator.nextInt(4);
        int fourth = generator.nextInt(4);
        if (first == third && second == fourth) {
            randomizeBoard();
        } else {
            board[first][second] = 2;
            board[third][fourth] = 2;
        }
    }

    // Shift blocks right through whitespace logic is good!
    public void moveRight(int x) {
        for (int i = 3; i > -1; i--) {
            int shiftTo = i;
            int current = board[x][i];
            if (board[x][i] != 0) {
                for (int c = i + 1; c < 4; c++) {
                    if (board[x][c] == 0) {
                        shiftTo = c;
                    } else if (board[i][c] != 0) {
                        break;
                    }
                }
            }
            if (shiftTo != i) {
                board[x][i] = 0;
                board[x][shiftTo] = current;
                if (shiftTo < 3) {
                    if (board[x][shiftTo] == board[x][shiftTo + 1]) {
                        board[x][shiftTo] = 0;
                        board[x][shiftTo + 1] = current * 2;

                    }
                }
            }
        }
    }

    // logic for right arrow key
    public void playRightTurn() {
        if (!gameLost && !checkForWin()) {
            for (int i = 0; i < 4; i++) {
                combined = false;
                for (int j = 3; j > 0; j--) {
                    int currentCell = board[i][j];
                    int leftCell = board[i][j - 1];
                    if (leftCell != 0 && currentCell == 0) {
                        board[i][j] = leftCell;
                        board[i][j - 1] = 0;
                        combined = false;
                    } else if (currentCell == leftCell && !combined) {
                        board[i][j] = board[i][j - 1] * 2;
                        board[i][j - 1] = 0;
                        combined = true;
                    } else {
                        combined = false;
                    }
                    this.moveRight(i);
                }
            }
            pastBoards.add(clone(board));
        }
    }

    // logic for shifting up
    public void moveUp(int x) {
        for (int i = 0; i < 4; i++) {
            int shiftTo = i;
            int current = board[i][x];
            if (current != 0) {
                for (int c = i - 1; c > -1; c--) {
                    if (board[c][x] == 0) {
                        shiftTo = c;
                    } else if (board[c][x] != 0) {
                        break;
                    }
                }
            }
            if (shiftTo != i) {
                board[i][x] = 0;
                board[shiftTo][x] = current;
                if (shiftTo > 0) {
                    if (board[shiftTo][x] == board[shiftTo - 1][x]) {
                        board[shiftTo][x] = 0;
                        board[shiftTo - 1][x] = current * 2;

                    }
                }
            }
        }
    }

    // logic for up arrow key input
    public void playUpTurn() {
        if (!gameLost && !checkForWin()) {
            for (int i = 0; i < 4; i++) {
                combined = false;
                for (int j = 1; j < 4; j++) {
                    int currentCell = board[j][i];
                    int topCell = board[j - 1][i];
                    if (currentCell != 0 && topCell == 0) {
                        board[j - 1][i] = currentCell;
                        board[j][i] = 0;
                        combined = false;
                    } else if (currentCell == topCell && !combined) {
                        board[j - 1][i] = board[j][i] * 2;
                        board[j][i] = 0;
                        combined = true;
                    } else {
                        combined = false;
                    }
                    this.moveUp(i);
                }
            }
            pastBoards.add(clone(board));
        }
    }

    // logic for down shift()
    public void moveDown(int x) {
        for (int i = 3; i > -1; i--) {
            int shiftTo = i;
            int current = board[i][x];
            if (current != 0) {
                for (int c = i + 1; c < 4; c++) {
                    if (board[c][x] == 0) {
                        shiftTo = c;
                    } else if (board[c][x] != 0) {
                        break;
                    }
                }
            }
            if (shiftTo != i) {
                board[i][x] = 0;
                board[shiftTo][x] = current;
                if (shiftTo < 3) {
                    if (board[shiftTo][x] == board[shiftTo + 1][x]) {
                        board[shiftTo][x] = 0;
                        board[shiftTo + 1][x] = current * 2;

                    }
                }
            }
        }
    }

    // logic for down arrow key movement
    public void playDownTurn() {
        if (!gameLost && !checkForWin()) {
            for (int i = 0; i < 4; i++) {
                combined = false;
                for (int j = 2; j > -1; j--) {
                    int currentCell = board[j][i];
                    int bottomCell = board[j + 1][i];
                    if (currentCell != 0 && bottomCell == 0) {
                        board[j + 1][i] = currentCell;
                        board[j][i] = 0;
                        combined = false;
                    } else if (currentCell == bottomCell && !combined) {
                        board[j + 1][i] = board[j][i] * 2;
                        board[j][i] = 0;
                        combined = true;
                    } else {
                        combined = false;
                    }
                    this.moveDown(i);
                }
            }
            pastBoards.add(clone(board));
        }
    }

    // shift blocks left
    public void moveLeft(int x) {
        for (int j = 0; j < 4; j++) {
            int shiftTo = j;
            int current = board[x][j];
            if (board[x][j] != 0) {
                for (int c = j - 1; c > -1; c--) {
                    if (board[x][c] == 0) {
                        shiftTo = c;
                    } else if (board[j][c] != 0) {
                        break;
                    }
                }
            }
            if (shiftTo != j) {
                board[x][j] = 0;
                board[x][shiftTo] = current;
                if (shiftTo > 0) {
                    if (board[x][shiftTo] == board[x][shiftTo - 1]) {
                        board[x][shiftTo] = 0;
                        board[x][shiftTo - 1] = current * 2;

                    }
                }
            }
        }
    }

    // logic for left arrow key
    public void playLeftTurn() {
        if (!gameLost && !checkForWin()) {
            for (int i = 0; i < 4; i++) {
                combined = false;
                for (int j = 0; j < 3; j++) {
                    int currentCell = board[i][j];
                    int rightCell = board[i][j + 1];
                    if (rightCell != 0 && currentCell == 0) {
                        board[i][j] = rightCell;
                        board[i][j + 1] = 0;
                        combined = false;
                    } else if (currentCell == rightCell && !combined) {
                        board[i][j] = board[i][j + 1] * 2;
                        board[i][j + 1] = 0;
                        combined = true;
                    } else {
                        combined = false;
                    }
                    this.moveLeft(i);
                }
            }
            pastBoards.add(clone(board));
        }
    }

    public boolean searchEmptyTiles() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addBlocks() {
        if (!gameLost && !checkForWin()) {
            Random gen = new Random();
            int row = gen.nextInt(4);
            int column = gen.nextInt(4);
            double number = Math.random();
            int nextvalue = 0;
            if (number > 0.90) {
                nextvalue = 4;
            } else {
                nextvalue = 2;
            }
            if (searchEmptyTiles()) {
                while (board[row][column] != 0) {
                    row = gen.nextInt(4);
                    column = gen.nextInt(4);
                }
            } else if (!searchEmptyTiles()) {
                return;
            }
            board[row][column] = nextvalue;
        }
    }

    public void undo() {
        if (!gameLost && !gameWon) {
            if (pastBoards.size() > 1) {
                gameLost = false;
                pastBoards.removeLast();
                board = pastBoards.getLast();
            }
            if (pastBoards.size() == 1) {
                gameLost = false;
            }
        }
    }

    public void reset() {
        board = new int[4][4];
        this.randomizeBoard();
        gameLost = false;
        gameWon = false;
        gameScore = 0;
        pastBoards = new LinkedList<int[][]>();
        pastBoards.add(clone(board));
    }

    public int[][] getBoard() {
        return board;
    }

    public void setBoard(int[][] x) {
        board = x;
    }

    public boolean checkForLoss() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        // checks for open blocks and if blocks can be combined
        // check centerpieces
        for (int i = 1; i < 3; i++) {
            for (int j = 1; j < 3; j++) {
                if (board[i][j] == board[i - 1][j]
                        || board[i][j] == board[i + 1][j]
                        || board[i][j] == board[i][j + 1]
                        || board[i][j] == board[i][j - 1]) {
                    return false;
                }
            }
        }
        // check corner pieces
        if (board[3][0] == board[2][0] || board[3][0] == board[3][1]) {
            return false;
        } else if (board[0][0] == board[0][1] || board[0][0] == board[1][0]) {
            return false;
        } else if (board[0][3] == board[0][2] || board[0][3] == board[1][3]) {
            return false;
        } else if (board[3][3] == board[3][2] || board[3][3] == board[2][3]) {
            return false;
        }
        // check pieces on a side but not corner
        if (board[0][1] == board[0][2]) {
            return false;
        } else if (board[1][0] == board[2][0]) {
            return false;
        } else if (board[3][1] == board[3][2]) {
            return false;
        } else if (board[1][3] == board[2][3]) {
            return false;
        }
        return true;
    }

    public int[][] clone(int[][] x) {
        int[][] returned = new int[x.length][x[0].length];
        for (int i = 0; i < x.length; i++) {
            for (int j = 0; j < x[0].length; j++) {
                returned[i][j] = x[i][j];
            }
        }
        return returned;
    }

    public boolean checkForWin() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 2048) {
                    return true;
                }
            }
        }
        return false;
    }

    public void changeLossStatus(boolean y) {
        gameLost = y;
    }

    public void changeWinStatus(boolean y) {
        gameWon = y;
    }

    public int getCell(int c, int r) {
        return board[c][r];
    }

    public void changeScore(int x) {
        gameScore = x;
    }

    public void updateScore() {
        int scoreTotal = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                scoreTotal += board[i][j] * 4;
            }
        }
        gameScore = scoreTotal;
    }

    public int getScore() {
        return gameScore;
    }

}
