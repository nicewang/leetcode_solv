//According to the Wikipedia's article: "The Game of Life, also known simply as 
//Life, is a cellular automaton devised by the British mathematician John Horton C
//onway in 1970." 
//
// Given a board with m by n cells, each cell has an initial state live (1) or d
//ead (0). Each cell interacts with its eight neighbors (horizontal, vertical, dia
//gonal) using the following four rules (taken from the above Wikipedia article): 
//
//
// 
// Any live cell with fewer than two live neighbors dies, as if caused by under-
//population. 
// Any live cell with two or three live neighbors lives on to the next generatio
//n. 
// Any live cell with more than three live neighbors dies, as if by over-populat
//ion.. 
// Any dead cell with exactly three live neighbors becomes a live cell, as if by
// reproduction. 
// 
//
// Write a function to compute the next state (after one update) of the board gi
//ven its current state. The next state is created by applying the above rules sim
//ultaneously to every cell in the current state, where births and deaths occur si
//multaneously. 
//
// Example: 
//
// 
//Input: 
//[
//  [0,1,0],
//  [0,0,1],
//  [1,1,1],
//  [0,0,0]
//]
//Output: 
//[
//  [0,0,0],
//  [1,0,1],
//  [0,1,1],
//  [0,1,0]
//]
// 
//
// Follow up: 
//
// 
// Could you solve it in-place? Remember that the board needs to be updated at t
//he same time: You cannot update some cells first and then use their updated valu
//es to update other cells. 
// In this question, we represent the board using a 2D array. In principle, the 
//board is infinite, which would cause problems when the active area encroaches th
//e border of the array. How would you address these problems? 
// 
// Related Topics Array


package leetcode.leetcode.editor.en;

import java.util.Arrays;

//Java：Game of Life
public class P289GameOfLife{
    public static void main(String[] args) {
        Solution solution = new P289GameOfLife().new Solution();
        // TO TEST
    }
    //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 0ms 38MB
    public void gameOfLife(int[][] board) {
        if(board == null || board.length <= 0) {
            return;
        }
        int l1 = board.length;
        int l2 = board[0].length;
        int[][] newBoard = new int[l1][l2];
        for(int i = 0; i < l1; i++) {
            for(int j = 0; j < l2; j++) {
                int count = 0;
                if(i - 1 >= 0 && board[i-1][j] == 1) {
                    count++;
                }
                if(i + 1 < l1 && board[i+1][j] == 1) {
                    count++;
                }
                if(i - 1 >= 0 && j - 1 >= 0 && board[i-1][j-1] == 1) {
                    count++;
                }
                if(i + 1 < l1 && j - 1 >= 0 && board[i+1][j-1] == 1) {
                    count++;
                }
                if(i - 1 >= 0 && j + 1 < l2 && board[i-1][j+1] == 1) {
                    count++;
                }
                if(i + 1 < l1 && j + 1 < l2 && board[i+1][j+1] == 1) {
                    count++;
                }
                if(j - 1 >= 0 && board[i][j-1] == 1) {
                    count++;
                }
                if(j + 1 < l2 && board[i][j+1] == 1) {
                    count++;
                }
                if(board[i][j] == 0 && count == 3) {
                    newBoard[i][j] = 1;
                }
                if(board[i][j] == 1 && (count == 2 || count == 3)) {
                    newBoard[i][j] = 1;
                }
            }
        }
        for(int i = 0; i < l1; i++) {
            for(int j = 0; j < l2; j++) {
                board[i][j] = newBoard[i][j];
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}