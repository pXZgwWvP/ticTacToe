import java.util.*;
import java.util.stream.IntStream;

public class ticTacToe {
    public static final Scanner SCANNER = new Scanner(System.in);
    public static boolean winner;
    public static char[] board = new char[9];
    public static char blank = '-';

    public static void main(String[] args) {
        IntStream.range(0, 9).forEach(i -> board[i] = blank);

        final char[] turnLoop = {'X', 'O', 'X', 'O', 'X', 'O', 'X', 'O', 'X', ' '};
        for (char player : turnLoop) {
            printBoard();

            if (player != ' ') {
                move(player);
            } else {
                printDrawMessage();
                break;
            }

            checkWinner();
            if (winner) {
                printBoard();
                printWinMessage(player);
                break;
            }
        }
    }

    private static void checkWinner() {
        checkWinningConditions(0, 1, 2);
        checkWinningConditions(3, 4, 5);
        checkWinningConditions(6, 7, 8);
        checkWinningConditions(0, 3, 6);
        checkWinningConditions(1, 4, 7);
        checkWinningConditions(2, 5, 8);
        checkWinningConditions(0, 4, 8);
        checkWinningConditions(2, 4, 6);
    }

    private static void checkWinningConditions(int a, int b, int c) {
        winner = board[a] != blank && board[a] == board[b] && board[b] == board[c] || winner;
    }

    private static void move(char player) {
        System.out.printf("%s: ", player);
        try {
            int x = Integer.parseInt(SCANNER.next()) - 1;
            if (board[x] == blank) {
                board[x] = player;
            } else {
                System.out.println("That position is occupied, try another one.");
                move(player);
            }
        } catch (NumberFormatException e) {
            System.out.println("You should enter numbers.");
            move(player);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Numbers should be from 1 to 9.");
            move(player);
        }
    }

    private static void printDrawMessage() {
        System.out.println("Draw!");
    }

    private static void printWinMessage(char player) {
        System.out.printf("Winner = %s", player);
    }

    private static void printBoard() {
        IntStream.range(0, 10).forEach(i -> System.out.println());
        Arrays.asList("---------",
                "| " + board[6] + " " + board[7] + " " + board[8] + " |",
                "| " + board[3] + " " + board[4] + " " + board[5] + " |",
                "| " + board[0] + " " + board[1] + " " + board[2] + " |",
                "---------").forEach(System.out::println);
    }
}