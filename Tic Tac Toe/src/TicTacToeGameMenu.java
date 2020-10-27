import java.util.InputMismatchException;
import java.util.Scanner;

public class TicTacToeGameMenu {

    private static final Scanner scanner = new Scanner(System.in);
    private static boolean gameMenu = true;
    private static int fieldSize = 0;
    private static String playerChoice = "";

    public static void main(String[] args) {

        System.out.println("Hi there! This is a TIC-TAC-toe game. Enter the initial configuration of the field size:");
        try {
            while (true) {
                fieldSize = scanner.nextInt();
                if (fieldSize > 2) {
                    break;
                }
                System.out.println("The field size must be greater than 2. Try again to enter the field size:");
            }
        } catch (InputMismatchException e) {
            System.out.println("You can only enter an integer!");
            return;
        }

        while (gameMenu) {
            System.out.println("Enter PvP if you want to play with another player.");
            System.out.println("Press PvE if you want to play with the AI.");
            System.out.println("Press Size if you want to change the field size.");
            System.out.println("Press Exit if you want to exit the game.");

            while (true) {
                playerChoice = scanner.next();
                if (playerChoice.equals("PvP") || playerChoice.equals("PvE") || playerChoice.equals("Size") || playerChoice.equals("Exit")) {
                    break;
                } else {
                    System.out.println("Enter PvP, PvE, Size or Exit:");
                }
            }

            switch (playerChoice) {
                case "PvP" -> {
                    GameLogic.initializeTheField(fieldSize);
                    GameLogic.printField(fieldSize);
                    while (true) {
                        try {
                            GameLogic.playerMove(fieldSize, 'X');
                        } catch (InputMismatchException e) {
                            System.out.println("You can only enter a string!");
                            return;
                        }
                        GameLogic.printField(fieldSize);
                        if (GameLogic.checkWinner('X', fieldSize)) {
                            System.out.println("The first player won!");
                            break;
                        }
                        if (GameLogic.isFieldFull(fieldSize)) {
                            break;
                        }
                        try {
                            GameLogic.playerMove(fieldSize, 'O');
                        } catch (InputMismatchException e) {
                            System.out.println("You can only enter a string!");
                            return;
                        }
                        GameLogic.printField(fieldSize);
                        if (GameLogic.checkWinner('O', fieldSize)) {
                            System.out.println("The second player won!");
                            break;
                        }
                        if (GameLogic.isFieldFull(fieldSize)) {
                            break;
                        }
                    }
                    System.out.println("Game Over!");
                    System.out.println();
                }
                case "PvE" -> {
                    GameLogic.initializeTheField(fieldSize);
                    GameLogic.printField(fieldSize);
                    while (true) {
                        try {
                            GameLogic.playerMove(fieldSize, 'X');
                        } catch (InputMismatchException e) {
                            System.out.println("You can only enter a string!");
                            return;
                        }
                        GameLogic.printField(fieldSize);
                        if (GameLogic.checkWinner('X', fieldSize)) {
                            System.out.println("The player won!");
                            break;
                        }
                        if (GameLogic.isFieldFull(fieldSize)) {
                            break;
                        }
                        GameLogic.aiMove(fieldSize);
                        GameLogic.printField(fieldSize);
                        if (GameLogic.checkWinner('O', fieldSize)) {
                            System.out.println("AI won!");
                            break;
                        }
                        if (GameLogic.isFieldFull(fieldSize)) {
                            break;
                        }
                    }
                    System.out.println("Game Over!");
                    System.out.println();
                }
                case "Size" -> {
                    System.out.println("Enter the field size:");
                    try {
                        while (true) {
                            fieldSize = scanner.nextInt();
                            if (fieldSize > 2) {
                                break;
                            }
                            System.out.println("The field size must be non-zero and non-negative. Try again to enter the field size:");
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("You can only enter a string!");
                        return;
                    }
                }
                case "Exit" -> {
                    System.out.println("Come back soon :)");
                    gameMenu = false;
                }
            }
        }
    }

}
