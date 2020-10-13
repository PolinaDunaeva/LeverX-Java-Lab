import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToeGame {

    private static int n = 0;
    private static char[][] field;
    private static boolean checker = true;
    private static Random rand = new Random();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int game = 0;

        System.out.println("Hi there! This is a TIC-TAC-toe game. Enter the initial configuration of the field size:");
        try {
            while (true) {
                n = sc.nextInt();
                if (n > 2) {
                    break;
                }
                System.out.println("The field size must be non-zero and non-negative. Try again to enter the field size:");
            }
        } catch (InputMismatchException e) {
            System.out.println("You can only enter an integer!");
            return;
        }

        while (checker) {
            System.out.println("Press 1 if you want to play with another player.");
            System.out.println("Press 2 if you want to play with the AI.");
            System.out.println("Press 3 if you want to change the field size.");
            System.out.println("Press 4 if you want to exit the game.");
            try {
                while (true) {
                    game = sc.nextInt();
                    if (game == 1 || game == 2 || game == 3 || game == 4) {
                        break;
                    } else {
                        System.out.println("Enter 1, 2, or 3:");
                    }
                }
            } catch (InputMismatchException e) {
                System.out.println("You can only enter an integer!");
                return;
            }

            switch (game) {
                case 1:
                    initializeTheField(n);
                    printField(n);
                    while (true) {
                        try {
                            firstPlayerMove(n);
                        } catch (InputMismatchException e) {
                            System.out.println("You can only enter an integer!");
                            return;
                        }
                        printField(n);
                        if (checkWinner('X', n)) {
                            System.out.println("The first player won!");
                            break;
                        }
                        if (isFieldFull(n)) {
                            break;
                        }
                        try {
                            secondPlayerMove(n);
                            ;
                        } catch (InputMismatchException e) {
                            System.out.println("You can only enter an integer!");
                            return;
                        }
                        printField(n);
                        if (checkWinner('O', n)) {
                            System.out.println("The second player won!");
                            break;
                        }
                        if (isFieldFull(n)) {
                            break;
                        }
                    }
                    System.out.println("Game Over!");
                    System.out.println();
                    break;

                case 2:
                    initializeTheField(n);
                    printField(n);
                    while (true) {
                        try {
                            firstPlayerMove(n);
                        } catch (InputMismatchException e) {
                            System.out.println("You can only enter an integer!");
                            return;
                        }
                        printField(n);
                        if (checkWinner('X', n)) {
                            System.out.println("The player won!");
                            break;
                        }
                        if (isFieldFull(n)) {
                            break;
                        }
                        aiMove(n);
                        printField(n);
                        if (checkWinner('O', n)) {
                            System.out.println("AI won!");
                            break;
                        }
                        if (isFieldFull(n)) {
                            break;
                        }
                    }
                    System.out.println("Game Over!");
                    System.out.println();
                    break;

                case 3:
                    System.out.println("Enter the field size:");
                    try {
                        while (true) {
                            n = sc.nextInt();
                            if (n > 2) {
                                break;
                            }
                            System.out.println("The field size must be non-zero and non-negative. Try again to enter the field size:");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("You can only enter an integer!");
                        return;
                    }
                    break;

                case 4:
                    System.out.println("Come back soon :)");
                    checker = false;
                    break;
            }
        }
    }

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

    private static void firstPlayerMove(int n) {
        int x;
        int y;
        do {
            System.out.println("Enter the coordinates (row and column):");
            x = sc.nextInt() - 1;
            if (x < 0 || x >= n) {
                System.out.println("Incorrect input!");
                System.exit(1);
            }
            y = sc.nextInt() - 1;
            if (y < 0 || y >= n) {
                System.out.println("Incorrect input!");
                System.exit(1);
            }
        } while (!isCellEmpty(x, y, n));
        field[x][y] = 'X';
    }

    private static void secondPlayerMove(int n) {
        int x;
        int y;
        do {
            System.out.println("Enter the coordinates (row and column):");
            x = sc.nextInt() - 1;
            if (x < 0 || x >= n) {
                System.out.println("Incorrect input!");
                System.exit(1);
            }
            y = sc.nextInt() - 1;
            if (y < 0 || y >= n) {
                System.out.println("Incorrect input!");
                System.exit(1);
            }
        } while (!isCellEmpty(x, y, n));
        field[x][y] = 'O';
    }

    public static void aiMove(int n) {
        int x;
        int y;
        do {
            x = rand.nextInt(n);
            y = rand.nextInt(n);
        } while (!isCellEmpty(x, y, n));
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

    private static boolean isFieldFull(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (field[i][j] == '*') {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean isCellEmpty(int x, int y, int n) {
        if (x < 0 || x > (n - 1) || y < 0 || y > (n - 1)) {
            return false;
        }
        if (field[x][y] != '*') {
            return false;
        }
        return true;
    }
}
