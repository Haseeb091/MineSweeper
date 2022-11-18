package org.example;

import java.util.Scanner;

public class Minesweeper {
    private Grid grid;

    private boolean gameWon;
    private final Scanner scanner;
    private boolean gameLost;
    private boolean firstMove = true;
    private int rowMax = 0;
    private int colMax = 0;

    public Minesweeper() {
        gameLost = false;
        gameWon = false;
        scanner = new Scanner(System.in);
        startGame();

    }

    public void setupRowAndCols() {
        int row = 0;
        int col = 0;
        //insures numbers are within range
        while (row < 5 || row > 30) {
            System.out.println("please Enter Number of rows between 5-30");
            //if there is no int then loop until they enter one
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Please enter a valid  Number of rows");
            }
            row = scanner.nextInt();

        }
        while (col < 5 || col > 30) {
            System.out.println("please Enter Number of columns between 5-30");
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Please enter a valid  Number of columns");
            }
            col = scanner.nextInt();

        }


        this.rowMax = row;
        this.colMax = col;
        grid = new Grid(this.rowMax, this.colMax);
        grid.printAll();

    }

    private void getCoordinates(char value) {
        int rowCoordinate = -1;//-1 because it would never enter loop
        int colCoordinate = -1;
        while (rowCoordinate < 0 || rowCoordinate > rowMax - 1) {
            System.out.println("Please enter a valid row for tile between 0-" + (rowMax - 1));
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Please enter a valid row for tile between 0-" + (rowMax - 1));
            }
            rowCoordinate = scanner.nextInt();
        }

        while (colCoordinate < 0 || colCoordinate > colMax - 1) {
            System.out.println("Please enter a valid col for tile between 0-" + (colMax - 1));
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Please enter a valid col for tile between 0-" + (colMax - 1));
            }
            colCoordinate = scanner.nextInt();
        }
        optionsSelector(rowCoordinate, colCoordinate, value);// depending on value a different method is called to handle input

    }

    private void optionsSelector(int rowCoordinate, int colCoordinate, char value) {
        if (value == 'v') {
            if (!grid.getIsFlagged(rowCoordinate, colCoordinate)) {
                if (firstMove) {
                    grid.firstMoveSetup(rowCoordinate, colCoordinate);
                    firstMove = false;
                    grid.printAll();
                } else {
                    grid.setTilesVisible(rowCoordinate, colCoordinate);

                    if (grid.isMine(rowCoordinate, colCoordinate)) {

                        grid.showAllMines();

                        gameLost = true;
                    } else if (grid.gameWon()) {

                        gameWon = true;
                    }
                    grid.printAll();
                }

            } else {
                grid.printAll();
            }
        } else if (value == 'f') {
            grid.flag(rowCoordinate, colCoordinate);
            grid.printAll();
        } else if (value == 'u') {
            grid.unFlag(rowCoordinate, colCoordinate);
            grid.printAll();
        }

    }

    public void startGame() {
        setupRowAndCols();

        while (!gameWon && !gameLost) {

            boolean validInput = false;
            while (!validInput) {
                System.out.println("v-Make tile visible, f-flag, u-unflag ");
                char c = scanner.next().charAt(0);
                if (c == 'v' || c == 'f' || c == 'u') {


                    getCoordinates(c);
                    validInput = true;
                    // gameWon=true;
                }

            }


        }

        if (gameWon) {

            System.out.println("you won");
        } else if (gameLost) {

            System.out.println("you lost");
        }

    }
}
