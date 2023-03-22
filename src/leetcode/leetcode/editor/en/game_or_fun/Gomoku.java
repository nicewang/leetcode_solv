package leetcode.leetcode.editor.en.game_or_fun;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Gomoku extends Application {
    private static final int BOARD_SIZE = 15; // 棋盘大小
    private static final int CELL_SIZE = 40; // 棋盘格子大小
    private static final int BOARD_OFFSET = 20; // 棋盘偏移量
    private static final int CANVAS_SIZE = BOARD_OFFSET * 2 + CELL_SIZE * BOARD_SIZE; // 画布大小

    private static final int WIN_COUNT = 5; // 胜利所需棋子数

    private boolean isBlack = true; // 当前玩家是否是黑棋
    private boolean isGameOver = false; // 游戏是否结束

    private Circle[][] board = new Circle[BOARD_SIZE][BOARD_SIZE]; // 棋盘

    private Text statusText = new Text(); // 显示游戏状态的文本

    @Override
    public void start(Stage primaryStage) {
        // 创建画布和棋盘
        Pane root = new Pane();
        GridPane boardPane = new GridPane();
        boardPane.setLayoutX(BOARD_OFFSET);
        boardPane.setLayoutY(BOARD_OFFSET);
        boardPane.setPrefSize(CELL_SIZE * BOARD_SIZE, CELL_SIZE * BOARD_SIZE);
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                Circle circle = new Circle(CELL_SIZE / 2);
                circle.setFill(Color.TRANSPARENT);
                circle.setStroke(Color.BLACK);
                circle.setStrokeWidth(2);
                boardPane.add(circle, j, i);
                board[i][j] = circle;
                int x = i, y = j;
                circle.setOnMouseClicked(event -> {
                    if (!isGameOver && circle.getFill() == Color.TRANSPARENT) {
                        placePiece(x, y);
                        checkForWinner();
                    }
                });
            }
        }

        // 创建重置按钮
        Button resetButton = new Button("重置");
        resetButton.setFont(new Font(20));
        resetButton.setOnAction(event -> resetGame());

        // 创建状态文本
        statusText.setFont(new Font(20));
        statusText.setFill(Color.BLUE);
        statusText.setLayoutX(BOARD_OFFSET);
        statusText.setLayoutY(CANVAS_SIZE - BOARD_OFFSET - 20);

        // 将棋盘和按钮添加到根面板
        root.getChildren().addAll(boardPane, resetButton, statusText);

        // 创建场景并显示窗口
        Scene scene = new Scene(root, CANVAS_SIZE, CANVAS_SIZE);
        primaryStage.setScene(scene);
        primaryStage.setTitle("五子棋");
        primaryStage.show();
    }

    // 重置游戏
    private void resetGame() {
        isBlack = true;
        isGameOver = false;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j].setFill(Color.TRANSPARENT);
            }
        }
        statusText.setText("");
    }

    // 在指定位置落子
    private void placePiece(int x, int y) {
        Circle circle = new Circle(CELL_SIZE / 2);
        circle.setFill(isBlack ? Color.BLACK : Color.WHITE);
        circle.setStroke(Color.BLACK);
        circle.setStrokeWidth(2);
        board[x][y] = circle;
        GridPane boardPane = (GridPane) board[0][0].getParent();
        boardPane.add(circle, y, x);
        isBlack = !isBlack;
    }

    // 检查是否有胜者
    private void checkForWinner() {
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (board[i][j].getFill() == Color.TRANSPARENT) {
                    continue;
                }
                // 水平方向
                int count = 1;
                for (int k = j + 1; k < BOARD_SIZE && board[i][k].getFill() == board[i][j].getFill(); k++) {
                    count++;
                }
                if (count >= WIN_COUNT) {
                    gameOver(board[i][j].getFill() == Color.BLACK ? "黑棋胜利" : "白棋胜利");
                    return;
                }
                // 垂直方向
                count = 1;
                for (int k = i + 1; k < BOARD_SIZE && board[k][j].getFill() == board[i][j].getFill(); k++) {
                    count++;
                }
                if (count >= WIN_COUNT) {
                    gameOver(board[i][j].getFill() == Color.BLACK ? "黑棋胜利" : "白棋胜利");
                    return;
                }
                // 左斜方向
                count = 1;
                for (int k = i + 1, l = j + 1; k < BOARD_SIZE && l < BOARD_SIZE && board[k][l].getFill() == board[i][j].getFill(); k++, l++) {
                    count++;
                }
                if (count >= WIN_COUNT) {
                    gameOver(board[i][j].getFill() == Color.BLACK ? "黑棋胜利" : "白棋胜利");
                    return;
                }
                // 右斜方向
                count = 1;
                for (int k = i + 1, l = j - 1; k < BOARD_SIZE && l >= 0 && board[k][l].getFill() == board[i][j].getFill(); k++, l--) {
                    count++;
                }
                if (count >= WIN_COUNT) {
                    gameOver(board[i][j].getFill() == Color.BLACK ? "黑棋胜利" : "白棋胜利");
                    return;
                }
            }
        }
    }

    // 游戏结束
    private void gameOver(String message) {
        isGameOver = true;
        statusText.setText(message);
    }

    public static void main(String[] args) {
        launch(args);
    }

}