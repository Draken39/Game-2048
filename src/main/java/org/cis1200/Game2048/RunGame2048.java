package org.cis1200.Game2048;

import java.awt.*;
import javax.swing.*;

public class RunGame2048 implements Runnable {
    @Override
    public void run() {
        // top level frame
        final JFrame frame = new JFrame("2048");
        frame.setLocation(300, 300);

        // game progress
        final JPanel status_panel = new JPanel();
        frame.add(status_panel, BorderLayout.SOUTH);
        final JLabel status = new JLabel("Game Loading");
        status_panel.add(status);

        // Instructions
        final JPanel instructions_panel = new JPanel();
        frame.add(instructions_panel, BorderLayout.EAST);

        // game score
        final JPanel score_panel = new JPanel();
        final JLabel score = new JLabel("Score Loading...");
        score_panel.add(score);

        // Game Board
        final BoardLayout board = new BoardLayout(status, score);
        frame.add(board, BorderLayout.CENTER);

        // Save and Load
        final JPanel saveAndLoad = new JPanel();
        final JButton load = new JButton("Load");
        final JButton save = new JButton("Save");
        load.addActionListener(e -> board.loadState());
        save.addActionListener(e -> board.saveState());
        saveAndLoad.add(load);
        saveAndLoad.add(save);
        frame.add(saveAndLoad, BorderLayout.WEST);

        // Reset Button
        final JPanel control_panel = new JPanel();
        control_panel.add(score_panel);
        frame.add(control_panel, BorderLayout.NORTH);
        final JButton reset = new JButton("Reset");
        reset.addActionListener(e -> board.reset());
        control_panel.add(reset);

        // Undo Button
        final JButton undoButton = new JButton("Undo");
        undoButton.addActionListener(
                e -> board.undo()
        );
        control_panel.add(undoButton);

        // draw frane and pack
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        // start
        board.reset();
    }
}
