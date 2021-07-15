package Game;

import java.util.*;

import MiniMax.MiniMax;

public class TicTacToe {
    static String[] board;
    static String turn;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        board = new String[9];
        turn = "X";
        String winner = null;

        // დაფის შევსება რიცხვებით
        for (int i = 0; i < 9; ++i)
            board[i] = String.valueOf(i + 1);

        System.out.println("დასაწყისი!");
        //თამაშის დასაწყისი
        while (true) {
            //AI-ის სვლა
            if (turn.equals("X")) {
                MiniMax.playBestMove(board);
                printBoard();
                turn = "O";
            }
            int numInput = 0;

            if (winnerIsKnown()) break;
            System.out.println("თქვენი ჯერია, შეიყვანეთ რიცხვი რომელ ადგილასაც უნდა მოთავსდეს O: ");

            // თუ მიღებული რიცხვი არ არის 1-9 დიაპაზონში მაშინ მომდევნო try ისვრის "invalid input" exception -ს
            try {
                while (true) {
                    numInput = in.nextInt();
                    if (numInput > 0 && numInput <= 9)
                        break;
                    else
                        System.out.println("არასწორი რიცხვია, გთხოვთ ხელახლა შეიყვანოთ არსებული რიცხვი: ");
                }
            } catch (InputMismatchException e) {
                System.out.println("ჩანაწერი არ არის რიცხვი, გთხოვთ შეიყვანოთ სწორი რიცხვი: ");

            }
            //მომდევნო if ში ხდება მოთამაშის სვლის განხორციელება
            if (board[numInput - 1].equals(String.valueOf(numInput))) {
                board[numInput - 1] = turn;
                turn = "X";
            } else
                System.out.println("ეს რიცხვი დაკავებულია, გთხოვთ თავიდან შეიყვანოთ რიცხვი: ");
        }
    }

    //ამოწმებს თამაშის საბოლოო მდგომარეობა ცნობილია თუ არა და დადებითი პასუხის შემთხვევაში აბრუნებს მას
    public static String checkWinner() {
        for (int i = 0; i < 8; i++) {
            String line = switch (i) {
                case 0 -> board[0] + board[1] + board[2];
                case 1 -> board[3] + board[4] + board[5];
                case 2 -> board[6] + board[7] + board[8];
                case 3 -> board[0] + board[3] + board[6];
                case 4 -> board[1] + board[4] + board[7];
                case 5 -> board[2] + board[5] + board[8];
                case 6 -> board[0] + board[4] + board[8];
                case 7 -> board[2] + board[4] + board[6];
                default -> null;
            };
            //თუ X-მ გაიმარჯვა
            if (line.equals("XXX"))
                return "X";
                //თუ O-მ გაიმარჯვა
            else if (line.equals("OOO"))
                return "O";
        }
        for (int i = 0; i < 9; i++) {
            if (!Arrays.asList(board).contains(String.valueOf(i + 1))) {
                if (i == 8)
                    return "Draw";
            } else break;
        }
        return null;
    }

    static boolean winnerIsKnown() {
        String winner = checkWinner();
        if (winner != null) {
            if (winner.equals("Draw")) {
                System.out.println("ყაიმია! თამაშის დასასრული.");
            } else if (winner.equals("X")) {
                System.out.println("სამწუხაროდ თქვენ დამარცხდით :(   თამაშის დასასრული.");
            } else System.out.println("გილოცავლთ, თქვენ გაიმარჯვეთ! თამაშის დასასრული");
            return true;
        }
        return false;
    }

    static void printBoard() {
        System.out.println("|---|---|---|");
        System.out.println("| " + board[0] + " | " + board[1] + " | " + board[2] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[3] + " | " + board[4] + " | " + board[5] + " |");
        System.out.println("|-----------|");
        System.out.println("| " + board[6] + " | " + board[7] + " | " + board[8] + " |");
        System.out.println("|---|---|---|");
    }

}