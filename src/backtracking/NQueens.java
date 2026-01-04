package backtracking;

import java.util.ArrayList;
import java.util.List;

public class NQueens {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> result = new ArrayList<>();
        char[][] board = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = '0';
            }
        }
        backtrack(board, 0, result);
        return result;
    }

    private void backtrack(char[][] board, int col, List<List<String>> result) {
        if (col == board.length) {
            List<String> solution = new ArrayList<>();
            for (char[] row : board) {
                solution.add(new String(row));
            }
            result.add(solution);
            return;
        }
        for (int row = 0; row < board.length; row++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 'Q';
                backtrack(board, col + 1, result);
                board[row][col] = '0'; // backtrack
            }
        }
    }

    private boolean isSafe(char[][] board, int row, int col) {
        for (int i = 0; i < col; i++) {
            if (board[row][i] == 'Q') {
                return false;
            }
        }
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row, j = col; i < board.length && j >= 0; i++, j--) {
            if (board[i][j] == 'Q') {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        NQueens nQueens = new NQueens();
        int n = 8; // Example input
        List<List<String>> solutions = nQueens.solveNQueens(n);
        for (List<String> solution : solutions) {
            for (String row : solution) {
                System.out.println(row);
            }
            System.out.println();
        }
    }
}
