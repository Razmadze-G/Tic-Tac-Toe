package MiniMax;

import Game.TicTacToe;

public class MiniMax {
    private static final int MAX_DEPTH = 6;

    /**
     * სინჯავს ყველა შესაძლო ვარიანტს minimax ალგორითმის გამოყენებით და საუკეთესოს აარჩევს სვლად
     *
     * @param board მასივი რომლის მიხედვითაც უნდა სინჯოს ახალი სვლები
     */
    public static void playBestMove(String[] board) {
        int bestMove = -1;
        int bestValue = Integer.MIN_VALUE;

        for (int i = 0; i < board.length; i++) {
            if (!board[i].equals("X") && !board[i].equals("O")) {
                board[i] = "X";
                int moveValue = miniMax(board, MAX_DEPTH, false);
                board[i] = String.valueOf(i + 1);
                if (moveValue > bestValue) {
                    bestMove = i;
                    bestValue = moveValue;
                }
            }
        }
        board[bestMove] = "X";
    }

    public static int miniMax(String[] board, int depth, boolean isMax) {
        String boardVal = TicTacToe.checkWinner();
        if (boardVal != null) {
            if (boardVal.equals("X"))
                return 10;
            else if (boardVal.equals("O"))
                return -10;
            else if (depth == 0 || boardVal.equals("Draw"))
                return 0;
        }


        // Maximising player, find the maximum attainable value.
        if (isMax) {
            int highestVal = Integer.MIN_VALUE;
            for (int i = 0; i < board.length; i++) {
                if (!board[i].equals("X") && !board[i].equals("O")) {
                    board[i] = "X";
                    highestVal = Math.max(highestVal, miniMax(board, depth - 1, false));
                    board[i] = String.valueOf(i + 1);
                }
            }
            return highestVal;
            // Minimising player, find the minimum attainable value;
        } else {
            int lowestVal = Integer.MAX_VALUE;
            for (int i = 0; i < board.length; i++) {
                if (!board[i].equals("X") && !board[i].equals("O")) {
                    board[i] = "O";
                    lowestVal = Math.min(lowestVal, miniMax(board, depth - 1, true));
                    board[i] = String.valueOf(i + 1);
                }
            }
            return lowestVal;
        }
    }
}