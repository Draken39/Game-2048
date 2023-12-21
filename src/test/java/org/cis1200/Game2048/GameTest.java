package org.cis1200.Game2048;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    // JUNIT Tests for the BOARD 4x4 Array only (DOES NOT DEPEND ON GUI)

    @Test
    public void testCreateBoard() {
        BoardElements board = new BoardElements();
        int[][] gameBoard = board.getBoard();
        int numberStartingBlocks = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameBoard[i][j] != 0) {
                    numberStartingBlocks++;
                }
            }
        }
        assertEquals(numberStartingBlocks, 2);
    }

    @Test
    public void testAddBlocks() {
        BoardElements board = new BoardElements();
        int[][] gameBoard = board.getBoard();
        board.addBlocks();
        LinkedList<Integer> numbers = new LinkedList<Integer>();
        int totalBlocks = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (gameBoard[i][j] != 0) {
                    numbers.add(gameBoard[i][j]);
                    totalBlocks++;
                }
            }
        }
        assertEquals(totalBlocks, 3);
        assertTrue(numbers.contains(2));
    }

    @Test
    public void testDoesNotAddBlocksIfFull() {
        BoardElements board = new BoardElements();
        int[][] b = { { 16, 8, 2, 8 }, { 8, 32, 64, 2 }, { 32, 8, 512, 8 }, { 16, 32, 64, 128 } };
        board.setBoard(b);
        int[][] gameBoard = board.getBoard();
        board.addBlocks();
        int[][] expected = { { 16, 8, 2, 8 }, { 8, 32, 64, 2 }, { 32, 8, 512, 8 },
            { 16, 32, 64, 128 } };
        assertArrayEquals(expected, board.getBoard());
    }

    @Test
    public void testPlayLeftTurn() {
        int[][] b = { { 2, 2, 2, 2 }, { 2, 2, 2, 2 }, { 2, 2, 2, 2 }, { 2, 2, 2, 2 } };
        BoardElements board = new BoardElements();
        board.setBoard(b);
        assertArrayEquals(board.getBoard(), b);
        board.playLeftTurn();
        int[][] expected = { { 4, 4, 0, 0 }, { 4, 4, 0, 0 }, { 4, 4, 0, 0 }, { 4, 4, 0, 0 } };
        assertArrayEquals(board.getBoard(), expected);

    }

    @Test
    public void testPlayRightTurn() {
        int[][] b = { { 2, 2, 2, 2 }, { 2, 2, 2, 2 }, { 2, 2, 2, 2 }, { 2, 2, 2, 2 } };
        BoardElements board = new BoardElements();
        board.setBoard(b);
        assertArrayEquals(board.getBoard(), b);
        board.playRightTurn();
        int[][] expected = { { 0, 0, 4, 4 }, { 0, 0, 4, 4 }, { 0, 0, 4, 4 }, { 0, 0, 4, 4 } };
        assertArrayEquals(board.getBoard(), expected);

    }

    @Test
    public void testPlayUpTurn() {
        int[][] b = { { 2, 2, 2, 2 }, { 2, 2, 2, 2 }, { 2, 2, 2, 2 }, { 2, 2, 2, 2 } };
        BoardElements board = new BoardElements();
        board.setBoard(b);
        assertArrayEquals(board.getBoard(), b);
        board.playUpTurn();
        int[][] expected = { { 4, 4, 4, 4 }, { 4, 4, 4, 4 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
        assertArrayEquals(board.getBoard(), expected);
    }

    @Test
    public void testPlayDownTurn() {
        int[][] b = { { 2, 2, 2, 2 }, { 2, 2, 2, 2 }, { 2, 2, 2, 2 }, { 2, 2, 2, 2 } };
        BoardElements board = new BoardElements();
        board.setBoard(b);
        assertArrayEquals(board.getBoard(), b);
        board.playDownTurn();
        int[][] expected = { { 0, 0, 0, 0 }, { 0, 0, 0, 0 }, { 4, 4, 4, 4 }, { 4, 4, 4, 4 } };
        assertArrayEquals(board.getBoard(), expected);

    }

    @Test
    public void testLossDoesNotPlay() {
        int[][] b = { { 16, 8, 2, 8 }, { 8, 32, 64, 2 }, { 32, 8, 512, 8 }, { 16, 32, 64, 128 } };
        BoardElements board = new BoardElements();
        board.setBoard(b);
        assertArrayEquals(board.getBoard(), b);
        board.playDownTurn();
        board.playRightTurn();
        board.playLeftTurn();
        board.playUpTurn();
        int[][] expected = { { 16, 8, 2, 8 }, { 8, 32, 64, 2 }, { 32, 8, 512, 8 },
            { 16, 32, 64, 128 } };
        assertArrayEquals(board.getBoard(), expected);
    }

    @Test
    public void testWinDoesNotPlay() {
        int[][] b = { { 2048, 2, 2, 2 }, { 8, 32, 64, 2 }, { 32, 8, 512, 8 }, { 16, 32, 64, 128 } };
        BoardElements board = new BoardElements();
        board.setBoard(b);
        assertArrayEquals(board.getBoard(), b);
        board.playDownTurn();
        board.playRightTurn();
        board.playLeftTurn();
        board.playUpTurn();
        int[][] expected = { { 2048, 2, 2, 2 }, { 8, 32, 64, 2 }, { 32, 8, 512, 8 },
            { 16, 32, 64, 128 } };
        assertArrayEquals(board.getBoard(), expected);
    }

    @Test
    public void testSearchBoardWithSpace() {
        int[][] b = { { 0, 0, 0, 0 }, { 0, 2, 0, 0 }, { 2, 2, 0, 0 }, { 4, 2, 0, 0 } };
        BoardElements board = new BoardElements();
        board.setBoard(b);
        assertArrayEquals(board.getBoard(), b);
        assertTrue(board.searchEmptyTiles());
    }

    @Test
    public void testSearchBoardWithNoSpace() {
        int[][] b = { { 2, 4, 2, 2 }, { 8, 2, 4, 8 }, { 2, 2, 4, 16 }, { 4, 2, 8, 32 } };
        BoardElements board = new BoardElements();
        board.setBoard(b);
        assertArrayEquals(board.getBoard(), b);
        assertFalse(board.searchEmptyTiles());
    }

    @Test
    public void testUndo() {
        BoardElements board = new BoardElements();
        int[][] expected = board.getBoard();
        board.playLeftTurn();
        board.undo();
        assertArrayEquals(expected, board.getBoard());
    }

    @Test
    public void testReset() {
        int[][] b = { { 2, 4, 2, 2 }, { 8, 2, 4, 8 }, { 2, 2, 4, 16 }, { 4, 2, 8, 32 } };
        BoardElements board = new BoardElements();
        board.setBoard(b);
        board.playLeftTurn();
        board.reset();
        int numberBlocks = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board.getBoard()[i][j] != 0) {
                    numberBlocks++;
                }
            }
        }
        assertEquals(numberBlocks, 2);
    }

    @Test
    public void testCheckForLoss() {
        int[][] b = { { 16, 8, 2, 8 }, { 8, 32, 64, 2 }, { 32, 8, 512, 8 }, { 16, 32, 64, 128 } };
        BoardElements board = new BoardElements();
        board.setBoard(b);
        assertTrue(board.checkForLoss());
    }

    @Test
    public void testCheckForWin() {
        int[][] b = { { 2048, 8, 2, 8 }, { 8, 32, 64, 2 }, { 32, 8, 512, 8 }, { 16, 32, 64, 128 } };
        BoardElements board = new BoardElements();
        board.setBoard(b);
        assertTrue(board.checkForWin());
    }

    @Test
    public void testCloningBoard() {
        int[][] b = { { 2048, 8, 2, 8 }, { 8, 32, 64, 2 }, { 32, 8, 512, 8 }, { 16, 32, 64, 128 } };
        BoardElements board = new BoardElements();
        board.setBoard(b);
        int[][] result = board.clone(b);
        assertArrayEquals(b, result);
    }

    @Test
    public void updateScore() {
        int[][] b = { { 0, 0, 0, 0 }, { 0, 2, 2, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
        BoardElements board = new BoardElements();
        board.setBoard(b);
        board.updateScore();
        assertEquals(board.getScore(), 4);
        int[][] b1 = { { 0, 0, 0, 0 }, { 0, 2, 2, 2 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };
        board.setBoard(b1);
        board.updateScore();
        assertEquals(board.getScore(), 6);
    }

}
