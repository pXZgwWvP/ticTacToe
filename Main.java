import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {
    static final Scanner SCANNER = new Scanner(System.in);
    static final char[] board = new char[9];
    static final char blank = '-';

    public static void main(String[] args) {
        IntStream.range(0, 9).forEach(i -> board[i] = blank);

        for (int i = 1; i <= 10; i++) {
            char player = i % 2 == 0 ? 'X' : 'O';
            printBoard();

            if (i != 10) {
                move(player);
            } else {
                printDrawMessage();
                break;
            }

            if (i < 5) continue;

            if (hasWinner()) {
                printBoard();
                printWinMessage(player);
                break;
            }
        }
    }

    static boolean hasWinner() {
        // checks rows.
        for (int i = 0; i < 9; i += 3)
            if (board[i] != blank && board[i] == board[i + 1] && board[i + 1] == board[i + 2])
                return true;
        // checks columns.
        for (int i = 0; i < 3; i++)
            if (board[i] != blank && board[i] == board[i + 3] && board[i + 3] == board[i + 6])
                return true;
        // checks diagonals.
        for (int i = 0; i <= 2; i += 2)
            if (board[i] != blank && board[i] == board[4] && board[4] == board[8 - i])
                return true;
        return false;
    }

    static void move(char player) {
        System.out.printf("%s: ", player);
        try {
            int pos = Integer.parseInt(SCANNER.next()) - 1;
            if (board[pos] == blank) {
                board[pos] = player;
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

    static void printDrawMessage() {
        System.out.println("Draw!");
    }

    static void printWinMessage(char player) {
        System.out.printf("Winner = %s", player);
    }

    static void printBoard() {
        IntStream.range(0, 10).forEach(i -> System.out.println());
        Arrays.asList("---------", "| " + board[6] + " " + board[7] + " " + board[8] + " |",
                                   "| " + board[3] + " " + board[4] + " " + board[5] + " |",
                                   "| " + board[0] + " " + board[1] + " " + board[2] + " |", "---------").forEach(System.out::println);
    }
}
