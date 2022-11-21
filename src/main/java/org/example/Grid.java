package org.example;

import java.awt.*;
import java.util.*;

public class Grid {
    private final Tile[][] grid;
    private final int row;
    private final int col;
    private final Set<Point> emptyPos = new HashSet<Point>();// holds the empty position and when size is 0 game won

    public Grid(int row, int col) {
        this.row = row;
        this.col = col;
        grid = new Tile[row][col];
        initialize();
        // initialize grid


    }

    //iff the tile is not visible then you can flag it
    public void flag(int rowTemp, int colTemp) {
        if (!grid[rowTemp][colTemp].getisVisible()) {
            grid[rowTemp][colTemp].setisFlagged(true);
        }
    }

    public boolean getIsFlagged(int rowTemp, int colTemp) {

        return grid[rowTemp][colTemp].getisFlagged();
    }

    // removing flag - so you can make tile visible later
    public void unFlag(int rowTemp, int colTemp) {
        grid[rowTemp][colTemp].setisFlagged(false);
    }

    //initializes 2d array with tiles which have no mines
    private void initialize() {


        for (int rowI = 0; rowI < row; rowI++) {

            for (int colI = 0; colI < col; colI++) {

                grid[rowI][colI] = new Tile(0);
            }


        }

    }

    //once you loose then all mines are displayed
    public void showAllMines() {
        for (int rowI = 0; rowI < row; rowI++) {

            for (int colI = 0; colI < col; colI++) {
                if (grid[rowI][colI].getisMine()) {
                    unFlag(rowI,colI);
                    setTilesVisible(rowI, colI);

                }

            }


        }


    }

    //there are no mines when first move is made
    // then the mines are generated and numbers and the empty position set is added to
    public void firstMoveSetup(int tempRow, int tempCol) {
        grid[tempRow][tempCol].setisVisible(true);
        genMines();
        generateNumbers();
        populateEmpties();
        setTilesVisible(tempRow, tempCol);// insures if first pos has no mines or numbers all adjacent empty pos are revieled
    }

    // randomly generates mines with the chance of 20 percent
    private void genMines() {
        int minesCount=0;
        for (int rowI = 0; rowI < row; rowI++) {

            for (int colI = 0; colI < col; colI++) {
                if (!grid[rowI][colI].getisVisible()) {

                    if (Math.random() <= 0.05) {
                        grid[rowI][colI].setisMine(true);
                        minesCount++;

                    }
                }


            }


        }
        if (minesCount==0){
            genMines();
        }

    }

    // checks all positions to get surrounding number of mines and sets the value in tile
    private void generateNumbers() {
        for (int rowI = 0; rowI < row; rowI++) {

            for (int colI = 0; colI < col; colI++) {
                if (!grid[rowI][colI].getisMine()) {
                    int minesCount = 0;
                    if (checkIfInIndex(rowI + 1, row) && checkIfInIndex(colI, col) && grid[rowI + 1][colI].getisMine()) {

                        minesCount++;
                    }
                    if (checkIfInIndex(rowI - 1, row) && checkIfInIndex(colI, col) && grid[rowI - 1][colI].getisMine()) {

                        minesCount++;
                    }
                    if (checkIfInIndex(rowI, row) && checkIfInIndex(colI - 1, col) && grid[rowI][colI - 1].getisMine()) {

                        minesCount++;
                    }
                    if (checkIfInIndex(rowI, row) && checkIfInIndex(colI + 1, col) && grid[rowI][colI + 1].getisMine()) {

                        minesCount++;
                    }//here
                    if (checkIfInIndex(rowI + 1, row) && checkIfInIndex(colI + 1, col) && grid[rowI + 1][colI + 1].getisMine()) {

                        minesCount++;
                    }
                    if (checkIfInIndex(rowI - 1, row) && checkIfInIndex(colI - 1, col) && grid[rowI - 1][colI - 1].getisMine()) {

                        minesCount++;
                    }
                    if (checkIfInIndex(rowI - 1, row) && checkIfInIndex(colI + 1, col) && grid[rowI - 1][colI + 1].getisMine()) {

                        minesCount++;
                    }
                    if (checkIfInIndex(rowI + 1, row) && checkIfInIndex(colI - 1, col) && grid[rowI + 1][colI - 1].getisMine()) {

                        minesCount++;
                    }
                    if (minesCount > 0) {
                        grid[rowI][colI].setValue(minesCount);

                    }

                }

            }


        }

    }

    // populating set of empty position -ie not mines
    //used to see if game has been won
    private void populateEmpties() {

        for (int rowI = 0; rowI < row; rowI++) {

            for (int colI = 0; colI < col; colI++) {
                if (!grid[rowI][colI].getisMine() && !grid[rowI][colI].getisVisible()) {
                    emptyPos.add(new Point(rowI, colI));
                }

            }


        }
    }

    // set method to make tiles visible treated differently depending on if there is a mine, number>0, 0
    public void setTilesVisible(int tempRow, int tempCol) {
        if (grid[tempRow][tempCol].getisMine()) {
            grid[tempRow][tempCol].setisVisible(true);

        } else {
            if (grid[tempRow][tempCol].getValue() == 0) {
                displayEmpties(tempRow, tempCol);// will set this pos to visible aswell
            } else {
                setNonMineTileVisible(tempRow, tempCol);
            }
        }

    }

    public boolean isMine(int tempRow, int tempCol) {
        return grid[tempRow][tempCol].getisMine();


    }

    public boolean gameWon() {

        return emptyPos.size() == 0;
    }

    //sets to visible and removes from set
    private void setNonMineTileVisible(int tempRow, int tempCol) {

        grid[tempRow][tempCol].setisVisible(true);

        emptyPos.remove(new Point(tempRow, tempCol));

    }

    private void displayEmpties(int tempRow, int tempCol) {


        ArrayList<int[]> emptyToCheck = new ArrayList<int[]>();


        emptyToCheck.add(new int[]{tempRow, tempCol});
        setNonMineTileVisible(tempRow, tempCol);
        //goes through all adacent tiles and addes them on to list if there not visible and dont have mine or number >0
        // used to display all empty tiles
        ArrayList<int[]> coordinateIncrementList = new ArrayList<int[]>();

        coordinateIncrementList.add(new int[]{-1,0});
        coordinateIncrementList.add(new int[]{1,0});
        coordinateIncrementList.add(new int[]{0,-1});
        coordinateIncrementList.add(new int[]{0,1});
        //added the list so dont need 4 repeat if statements
        while (!emptyToCheck.isEmpty()) {
            int[] cordinates = emptyToCheck.get(0);

            for (int[] coordinateIncrement:coordinateIncrementList){

                int newCoordinateRow=cordinates[0] +coordinateIncrement[0];
                int newCoordinateCol=cordinates[1] +coordinateIncrement[1];

                if (checkIfNotMineAndNotVisibleAndNumbIsZero(newCoordinateRow, newCoordinateCol)) {

                    unFlag(newCoordinateRow, newCoordinateCol);
                    setNonMineTileVisible(newCoordinateRow, newCoordinateCol);
                    emptyToCheck.add(new int[]{newCoordinateRow, newCoordinateCol});

                }else if (checkIfNotMineAndNotVisible(newCoordinateRow, newCoordinateCol)){
                    unFlag(newCoordinateRow, newCoordinateCol);
                    setNonMineTileVisible(newCoordinateRow, newCoordinateCol);

                }

            }
//            if (checkIfNotMineAndNotVisibleAndNumbIsZero(cordinates[0] - 1, cordinates[1])) {
//                unFlag(cordinates[0] - 1, cordinates[1]);
//                setNonMineTileVisible(cordinates[0] - 1, cordinates[1]);
//                queue.add(new int[]{cordinates[0] - 1, cordinates[1]});
//
//            }else if (checkIfNotMineAndNotVisible(cordinates[0] - 1, cordinates[1])){
//                numberedCells.add(new int[]{cordinates[0] - 1, cordinates[1]} );
//
//            }
//
//            if (checkIfNotMineAndNotVisibleAndNumbIsZero(cordinates[0] + 1, cordinates[1])) {
//                unFlag(cordinates[0] + 1, cordinates[1]);
//                setNonMineTileVisible(cordinates[0] + 1, cordinates[1]);
//                queue.add(new int[]{cordinates[0] + 1, cordinates[1]});
//            }else if (checkIfNotMineAndNotVisible(cordinates[0] + 1, cordinates[1])){
//                numberedCells.add(new int[]{cordinates[0] + 1, cordinates[1]} );
//
//            }
//
//
//            if (checkIfNotMineAndNotVisibleAndNumbIsZero(cordinates[0], cordinates[1] - 1)) {
//                unFlag(cordinates[0], cordinates[1] - 1);
//                setNonMineTileVisible(cordinates[0], cordinates[1] - 1);
//                queue.add(new int[]{cordinates[0], cordinates[1] - 1});
//
//            }else if (checkIfNotMineAndNotVisible(cordinates[0], cordinates[1] - 1)){
//                numberedCells.add(new int[]{cordinates[0], cordinates[1] - 1} );
//
//            }
//
//
//            if (checkIfNotMineAndNotVisibleAndNumbIsZero(cordinates[0], cordinates[1] + 1)) {
//                unFlag(cordinates[0], cordinates[1] + 1);
//                setNonMineTileVisible(cordinates[0], cordinates[1] + 1);
//                queue.add(new int[]{cordinates[0], cordinates[1] + 1});
//            }else if (checkIfNotMineAndNotVisible(cordinates[0], cordinates[1] + 1)){
//                numberedCells.add(new int[]{cordinates[0], cordinates[1] + 1} );
//
//            }


            emptyToCheck.remove(0);

        }


    }

    private boolean checkIfNotMineAndNotVisibleAndNumbIsZero(int tempRow, int tempCol) {

       return checkIfNotMineAndNotVisible(tempRow,tempCol)&& grid[tempRow][tempCol].getValue() == 0;
    }
    private boolean checkIfNotMineAndNotVisible(int tempRow, int tempCol) {

        if (checkIfInIndex(tempRow, row) && checkIfInIndex(tempCol, col) && !grid[tempRow][tempCol].getisMine()
                && !grid[tempRow][tempCol].getisVisible() ) {
            return true;


        }
        return false;
    }

    private boolean checkIfInIndex(int val, int indexMax) {
        //avoiding index out of bounds

        return val >= 0 && val < indexMax;
    }

    public void printAll() {
        System.out.print("   ");
        for (int colI = 0; colI < col; colI++) {
            System.out.print(colI + " ");
            if (colI>9){
                System.out.print(" ");
            }else {
                System.out.print("  ");
            }


        }
        System.out.println();
        for (int rowI = 0; rowI < row; rowI++) {
            System.out.print(rowI + "|");
            for (int colI = 0; colI < col; colI++) {
                if (colI==0 && rowI<10){
                    System.out.print(" ");
                }
                grid[rowI][colI].print();
                System.out.print(",  ");
            }

            System.out.println();

        }
        System.out.println();
    }
}
