package leetcode.leetcode.editor.en;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Game2048 extends JFrame implements KeyListener {
    private static final long serialVersionUID = 1L;
    private static final int ROWS = 4;
    private static final int COLS = 4;
    private static final int CELL_SIZE = 128;
    private static final int GAP = 16;
    private static final Font FONT = new Font("Arial", Font.BOLD, 64);
    private static final Color[] COLORS = {new Color(0xEEE4DA), new Color(0xEDE0C8), new Color(0xF2B179),
            new Color(0xF59563), new Color(0xF67C5F), new Color(0xF65E3B),
            new Color(0xEDCF72), new Color(0xEDCC61), new Color(0xEDC850),
            new Color(0xEDC53F), new Color(0xEDC22E), new Color(0x3C3A32)};
    private static final int[] VALUES = {0, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048};
    private int[][] grid;
    private JLabel[][] cells;

    public Game2048() {
        setTitle("2048");
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addKeyListener(this);

        JPanel gridPanel = new JPanel(new GridLayout(ROWS, COLS, GAP, GAP));
        gridPanel.setBackground(new Color(0xBBADA0));
        getContentPane().add(gridPanel);

        cells = new JLabel[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                cells[row][col] = new JLabel("", SwingConstants.CENTER);
                cells[row][col].setFont(FONT);
                cells[row][col].setOpaque(true);
                cells[row][col].setBackground(COLORS[0]);
                cells[row][col].setPreferredSize(new Dimension(CELL_SIZE, CELL_SIZE));
                gridPanel.add(cells[row][col]);
            }
        }

        pack();
        setLocationRelativeTo(null);

        newGame();
    }

    private void newGame() {
        grid = new int[ROWS][COLS];
        addRandomTile();
        addRandomTile();
        updateGrid();
    }

    private void addRandomTile() {
        ArrayList<Point> emptyCells = new ArrayList<Point>();
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (grid[row][col] == 0) {
                    emptyCells.add(new Point(row, col));
                }
            }
        }
        if (emptyCells.isEmpty()) {
            return;
        }
        Point p = emptyCells.get((int) (Math.random() * emptyCells.size()));
        grid[p.x][p.y] = (Math.random() < 0.9) ? 2 : 4;
    }

    private void updateGrid() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                cells[row][col].setText(grid[row][col] == 0 ? "" : String.valueOf(grid[row][col]));
                cells[row][col].setBackground(COLORS[getIndexForValue(grid[row][col])]);
            }
        }
        pack();
    }

    private int getIndexForValue(int value) {
        for (int i = 0; i < VALUES.length; i++) {
            if (VALUES[i] == value) {
                return i;
            }
        }
        return 0;
    }

    private void moveTilesLeft() {
        boolean moved = false;
        for (int row = 0; row < ROWS; row++) {
            int[] line = grid[row];
            int[] merged = mergeTiles(line);
            if (!Arrays.equals(line, merged)) {
                grid[row] = merged;
                moved = true;
            }
        }
        if (moved) {
            addRandomTile();
            updateGrid();
        }
    }

    private void moveTilesRight() {
        grid = reverseGrid(grid);
        moveTilesLeft();
        grid = reverseGrid(grid);
    }

    private void moveTilesUp() {
        grid = transposeGrid(grid);
        moveTilesLeft();
        grid = transposeGrid(grid);
    }

    private void moveTilesDown() {
        grid = transposeGrid(grid);
        moveTilesRight();
        grid = transposeGrid(grid);
    }

    private int[] mergeTiles(int[] line) {
        int[] merged = new int[COLS];
        int mergeIndex = 0;
        boolean canMerge = false;
        for (int value : line) {
            if (value != 0) {
                if (merged[mergeIndex] == 0) {
                    merged[mergeIndex] = value;
                } else if (merged[mergeIndex] == value && canMerge) {
                    merged[mergeIndex] *= 2;
                    canMerge = false;
                } else {
                    mergeIndex++;
                    merged[mergeIndex] = value;
                    canMerge = true;
                }
            }
        }
        return merged;
    }

    private int[][] reverseGrid(int[][] grid) {
        int[][] newGrid = new int[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                newGrid[row][col] = grid[row][COLS - 1 - col];
            }
        }
        return newGrid;
    }

    private int[][] transposeGrid(int[][] grid) {
        int[][] newGrid = new int[COLS][ROWS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                newGrid[col][row] = grid[row][col];
            }
        }
        return newGrid;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
                moveTilesLeft();
                break;
            case KeyEvent.VK_RIGHT:
                moveTilesRight();
                break;
            case KeyEvent.VK_UP:
                moveTilesUp();
                break;
            case KeyEvent.VK_DOWN:
                moveTilesDown();
                break;
            default:
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Game2048 game = new Game2048();
                game.setVisible(true);
            }
        });
    }
}