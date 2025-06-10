public class nqueens {

    static int N = 8; // You can change this for different board sizes

    // Function to print the board
    static void printSolution(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) System.out.print(
                (board[i][j] == 1 ? "Q " : ". ")
            );
            System.out.println();
        }
        System.out.println();
    }

    // Check if queen can be placed at board[row][col]
    static boolean isSafe(int[][] board, int row, int col) {
        // Check this column on upper side
        for (int i = 0; i < row; i++) if (board[i][col] == 1) return false;

        // Check upper-left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) if (
            board[i][j] == 1
        ) return false;

        // Check upper-right diagonal
        for (int i = row, j = col; i >= 0 && j < N; i--, j++) if (
            board[i][j] == 1
        ) return false;

        return true;
    }

    // Solve N Queens using backtracking
    static boolean solveNQueens(int[][] board, int row) {
        if (row >= N) {
            printSolution(board);
            return true;
        }

        boolean res = false;
        for (int col = 0; col < N; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = 1; // Place queen
                res = solveNQueens(board, row + 1) || res;
                board[row][col] = 0; // Backtrack
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] board = new int[N][N];

        if (!solveNQueens(board, 0)) System.out.println("No solution exists");
    }
}
