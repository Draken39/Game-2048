package org.cis1200.Game2048;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static java.awt.Color.blue;
import static java.awt.Color.red;

public class BoardLayout extends JPanel {
    private SaveAndLoad tiles;
    private JLabel status;
    private JLabel score;

    private static Integer finalWidth = 1200;
    private static Integer finalHeight = 600;

    public BoardLayout(JLabel statusInIt, JLabel scoreInIt) {
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setFocusable(true);
        this.tiles = new SaveAndLoad(new BoardElements());
        status = statusInIt;
        score = scoreInIt;

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (status.getText().equals("Game Ongoing")) {
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        tiles.getBoard().playLeftTurn();
                        updateStatus();
                        updateScore();
                        tiles.getBoard().addBlocks();
                    } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        tiles.getBoard().playRightTurn();
                        updateStatus();
                        updateScore();
                        tiles.getBoard().addBlocks();
                    } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        tiles.getBoard().playDownTurn();
                        updateStatus();
                        updateScore();
                        tiles.getBoard().addBlocks();
                    } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                        tiles.getBoard().playUpTurn();
                        updateStatus();
                        updateScore();
                        tiles.getBoard().addBlocks();
                    }
                }
                repaint();

            }
        }
        );
    }

    public void reset() {
        tiles.getBoard().reset();
        tiles.getBoard().pastBoards.clear();
        updateStatus();
        updateScore();
        requestFocusInWindow();
        repaint();
    }

    private void updateStatus() {
        if (tiles.getBoard().checkForWin()) {
            status.setText("2048 Won");
        } else if (tiles.getBoard().checkForLoss()) {
            status.setText("2048 Lost");
        } else if (!tiles.getBoard().checkForLoss() && !tiles.getBoard().checkForWin()) {
            status.setText("Game Ongoing");
        }

    }

    public void undo() {
        tiles.getBoard().undo();
        updateScore();
        updateStatus();
        requestFocusInWindow();
        repaint();
    }

    public void loadState() {
        tiles.loadState();
        updateScore();
        updateStatus();
        repaint();
        requestFocusInWindow();
    }

    public void saveState() {
        tiles.saveState();
        updateScore();
        updateStatus();
        repaint();
        requestFocusInWindow();
    }

    private void updateScore() {
        tiles.getBoard().updateScore();
        score.setText("Score:" + Integer.toString(tiles.getBoard().getScore()));
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawLine(150, 0, 150, 600);
        g.drawLine(300, 0, 300, 600);
        g.drawLine(450, 0, 450, 600);
        g.drawLine(600, 0, 600, 600);
        g.drawLine(0, 150, 600, 150);
        g.drawLine(0, 300, 600, 300);
        g.drawLine(0, 450, 600, 450);
        g.drawLine(0, 600, 600, 600);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int value = tiles.getBoard().getCell(i, j);
                if (value != 0) {
                    Graphics2D graphics = (Graphics2D) g;
                    int fontSize = 20;
                    Font f = new Font("Comic Sans MS", Font.BOLD, fontSize);
                    graphics.setFont(f);
                    graphics.setColor(blue);
                    g.drawString(Integer.toString(value), 65 + 150 * j, 80 + 150 * i);
                }
            }
        }
        Graphics2D graphics = (Graphics2D) g;
        int fontSize = 15;
        Font f = new Font("Times New Roman", Font.PLAIN, fontSize);
        graphics.setFont(f);
        graphics.setColor(red);
        g.drawString("Instructions", 625, 30);
        g.drawString(
                "-This game is 2048.",
                625, 70
        );
        g.drawString("-The game ends when you hit 2048.", 625, 110);
        g.drawString("-Or when you cannot combine anymore blocks.", 625, 150);
        g.drawString("-Only powers of two are possible for blocks, like 2, 4, 8, 16", 625, 190);
        g.drawString("-Undo and Reset capabilities are available for the game", 625, 230);
        g.drawString(
                "-Reset will stop the game and load in a fresh board, undo undoes one move", 625,
                270
        );
        g.drawString("-Save and Load capabilities are also available", 625, 310);
        g.drawString(
                "-Load will load in the last game, even after you have exited the game", 625, 350
        );
        g.drawString("-Save will save the current game state.", 625, 390);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(finalWidth, finalHeight);
    }
}
