package org.cis1200.Game2048;

import java.io.*;

public class SaveAndLoad {
    private BoardElements board;

    public SaveAndLoad(BoardElements layout) {
        this.board = layout;
    }

    public void loadState() {
        try {
            FileReader fr = new FileReader("files/2048Saved.txt");
            BufferedReader br = new BufferedReader(fr);
            int[][] savedBoard = new int[4][4];
            board.changeScore(br.read());
            br.readLine();
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    savedBoard[row][col] = br.read();
                    br.readLine();
                }
            }
            br.close();
            board.changeLossStatus(false);
            board.changeWinStatus(false);
            board.setBoard(board.clone(savedBoard));
        } catch (IOException e) {
            System.out.println("No Saved Game States were found");
        }
    }

    public void saveState() {
        try {
            new FileWriter("files/2048Saved.txt", true);
            FileWriter fw = new FileWriter("files/2048Saved.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(board.getScore());
            bw.newLine();
            for (int row = 0; row < 4; row++) {
                for (int col = 0; col < 4; col++) {
                    bw.write(board.getBoard()[row][col]);
                    bw.newLine();
                }
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Could Not Save Game Properly");
        }
    }

    public BoardElements getBoard() {
        return board;
    }
}
