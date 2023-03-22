package game_or_fun;

//修改这个2048游戏代码中的逻辑错误bug
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game2048 extends JFrame implements KeyListener {

    private static final int ROWS = 4;
    private static final int COLS = 4;
    private static final int TILE_SIZE = 128;
    private static final int GAP = 16;

    private int[][] board;
    private JLabel[][] tiles;
    private JLabel scoreLabel;
    private int score;

    public Game2048() {
        board = new int[ROWS][COLS];
        tiles = new JLabel[ROWS][COLS];
        score = 0;

        setTitle("2048 Game");
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(GAP, GAP, GAP, GAP));
        contentPane.setBackground(new Color(0xbbada0));
        add(contentPane);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(ROWS, COLS, GAP, GAP));
        boardPanel.setBackground(new Color(0xbbada0));
        contentPane.add(boardPanel, BorderLayout.CENTER);

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                tiles[row][col] = new JLabel("", SwingConstants.CENTER);
                tiles[row][col].setPreferredSize(new Dimension(TILE_SIZE, TILE_SIZE));
                tiles[row][col].setFont(new Font("SansSerif", Font.BOLD, 48));
                tiles[row][col].setOpaque(true);
                tiles[row][col].setBackground(getTileColor(0));
                boardPanel.add(tiles[row][col]);
            }
        }

        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        scoreLabel.setForeground(Color.WHITE);
        contentPane.add(scoreLabel, BorderLayout.NORTH);

        generateTile();
        generateTile();

        addKeyListener(this);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void generateTile() {
        int value = Math.random() < 0.9 ? 2 : 4;
        int row, col;
        do {
            row = (int) (Math.random() * ROWS);
            col = (int) (Math.random() * COLS);
        } while (board[row][col] != 0);
        board[row][col] = value;
        tiles[row][col].setText(Integer.toString(value));
        tiles[row][col].setBackground(getTileColor(value));
    }

    private Color getTileColor(int value) {
        switch (value) {
            case 2:
                return new Color(0xeee4da);
            case 4:
                return new Color(0xede0c8);
            case 8:
                return new Color(0xf2b179);
            case 16:
                return new Color(0xf59563);
            case 32:
                return new Color(0xf67c5f);
            case 64:
                return new Color(0xf65e3b);
            case 128:
                return new Color(0xedcf72);
            case 256:
                return new Color(0xedcc61);
            case 512:
                return new Color(0xedc850);
            case 1024:
                return new Color(0xedc53f);
            case 2048:
                return new Color(0xedc22e);
            default:
                return new Color(0xcdc1b4);
        }
    }

    private boolean moveTiles(int dx, int dy) {
        boolean moved = false;
        int[][] newBoard = new int[ROWS][COLS];
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int newRow = row + dy;
                int newCol = col + dx;
                if (board[row][col] != 0) {
                    while (newRow >= 0 && newRow < ROWS && newCol >= 0 && newCol < COLS && newBoard[newRow][newCol] == 0) {
                        newRow += dy;
                        newCol += dx;
                    }
                    newRow -= dy;
                    newCol -= dx;
                    if (newRow != row || newCol != col) {
                        moved = true;
                    }
                    if (newBoard[newRow][newCol] == 0) {
                        newBoard[newRow][newCol] = board[row][col];
                    } else {
                        newBoard[newRow][newCol] *= 2;
                        score += newBoard[newRow][newCol];
                        moved = true;
                    }
                }
            }
        }
        if (moved) {
            board = newBoard;
            updateTiles();
            scoreLabel.setText("Score: " + score);
            if (isGameOver()) {
                JOptionPane.showMessageDialog(this, "Game over!");
                resetGame();
            } else {
                generateTile();
            }
        }
        return moved;
    }

    private boolean isGameOver() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                if (board[row][col] == 0) {
                    return false;
                }
                if (col < COLS - 1 && board[row][col] == board[row][col + 1]) {
                    return false;
                }
                if (row < ROWS - 1 && board[row][col] == board[row + 1][col]) {
                    return false;
                }
            }
        }
        return true;
    }

    private void updateTiles() {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                tiles[row][col].setText(board[row][col] == 0 ? "" : Integer.toString(board[row][col]));
                tiles[row][col].setBackground(getTileColor(board[row][col]));
            }
        }
    }

    private void resetGame() {
        score = 0;
        scoreLabel.setText("Score: 0");
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                board[row][col] = 0;
                tiles[row][col].setText("");
                tiles[row][col].setBackground(getTileColor(0));
            }
        }
        generateTile();
        generateTile();
    }

    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                moveTiles(0, -1);
                break;
            case KeyEvent.VK_DOWN:
                moveTiles(0, 1);
                break;
            case KeyEvent.VK_LEFT:
                moveTiles(-1, 0);
                break;
            case KeyEvent.VK_RIGHT:
                moveTiles(1, 0);
                break;
            case KeyEvent.VK_R:
                resetGame();
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setTitle("2048");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(340, 400);
        frame.setResizable(false);

        Game2048 game = new Game2048();
        frame.add(game);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}