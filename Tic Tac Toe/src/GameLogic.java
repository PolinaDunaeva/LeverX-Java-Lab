import java.util.Random;
import java.util.Scanner;

public class GameLogic {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random rand = new Random();
    private static char[][] field;

    public static void initializeTheField(int n) {
        field = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                field[i][j] = '*';
            }
        }
    }

    public static void printField(int n) {
        System.out.print("  ");
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + " ");
        }
        System.out.println();
        for (int i = 0; i < n; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < n; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static void playerMove(int n, char parameter) {
        int x;
        int y;
        do {
            System.out.println("Enter the coordinates (row and column):");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;

        } while (isCellFull(x, y, n));
        field[x][y] = parameter;
    }

    public static void aiMove(int n) {
        int x;
        int y;
        do {
            x = rand.nextInt(n);
            y = rand.nextInt(n);
        } while (isCellFull(x, y, n));
        field[x][y] = 'O';
    }

    public static boolean checkWinner(char elem, int n) {
        int check = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (field[i][j] == elem) {
                    check++;
                }
            }
            if (check == n) {
                return true;
            }
            check = 0;
        }

        for (int j = 0; j < n; j++) {
            for (int i = 0; i < n; i++) {
                if (field[i][j] == elem) {
                    check++;
                }
            }
            if (check == n) {
                return true;
            }
            check = 0;
        }

        for (int i = 0; i < n; i++) {
            if (field[i][i] == elem) {
                check++;
            }
            if (check == n) {
                return true;
            }
        }
        return false;
    }

    public static boolean isFieldFull(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (field[i][j] == '*') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isCellFull(int x, int y, int n) {
        return x < 0 || x > (n - 1) || y < 0 || y > (n - 1) || field[x][y] != '*';
    }
}
