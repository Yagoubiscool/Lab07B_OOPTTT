public class TTTBoard {
    private char[][] board;
    private char currentPlayer;
    private boolean isGameOver;

    public TTTBoard() {
        board = new char[3][3];
        resetBoard();
    }

    public void resetBoard() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                board[r][c] = '-';
            }
        }
        currentPlayer = 'X';
        isGameOver = false;
    }

    public boolean makeMove(int row, int col) {
        if (isGameOver || board[row][col] != '-') {
            return false;
        }
        board[row][col] = currentPlayer;
        if (checkWin() || checkDraw()) {
            isGameOver = true;
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
        return true;
    }

    public boolean checkWin() {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != '-' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) return true;
            if (board[0][i] != '-' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) return true;
        }
        // Check diagonals
        if (board[0][0] != '-' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) return true;
        if (board[0][2] != '-' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) return true;

        return false;
    }

    public boolean checkDraw() {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (board[r][c] == '-') return false;
            }
        }
        return true;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public boolean isGameOver() {
        return isGameOver;
    }
}
