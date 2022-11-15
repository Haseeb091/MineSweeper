package org.example;

import java.util.Scanner;

public class Minesweeper {
    private Grid grid;

    private boolean gameWon;
    Scanner myObj ;
    private boolean gameLost;
    int rowMax=0;
    int colMax=0;
    public Minesweeper(){
        gameLost=false;
        gameWon=false;
        myObj = new Scanner(System.in);
        startGame();

    }

    public void setupRowAndCols(){

        int row=0;
        int col=0;
        while (row <= 4 || row > 100 && (col <= 4 || col > 100)) {
      if(row<5||row>100){
          System.out.println("please Enter Number of rowsbetween 5-100");
          while (!myObj.hasNextInt()) {
              myObj.next();
              System.out.println("Please enter a valid  Number of rows");
          }
          row = myObj.nextInt();

      }
            if(col<5||col>100){
                System.out.println("please Enter Number of columns between 5-100");
                while (!myObj.hasNextInt()) {
                    myObj.next();
                    System.out.println("Please enter a valid  Number of columns");
                }
                col = myObj.nextInt();

            }




        }
        rowMax=row;
        colMax=col;
        grid=new Grid(row,col);
        grid.printAll();

    }
private void makeTileVisible(){
        int rowCordinate=-1;
        int colCordinate=-1;
        while (rowCordinate<0||rowCordinate>rowMax-1){
            System.out.println("Please enter a valid row for tile between 0-"+(rowMax-1));
            while (!myObj.hasNextInt()) {
                myObj.next();
                System.out.println("Please enter a valid row for tile between 0-"+(rowMax-1));
            }
            rowCordinate = myObj.nextInt();
        }

    while (colCordinate<0||colCordinate>rowMax-1){
        System.out.println("Please enter a valid row for tile between 0-"+(colMax-1));
        while (!myObj.hasNextInt()) {
            myObj.next();
            System.out.println("Please enter a valid row for tile between 0-"+(colMax-1));
        }
        colCordinate = myObj.nextInt();
    }
    grid.setTilesVisible(rowCordinate,colCordinate);
    grid.printAll();
    if(grid.isMine(rowCordinate,colCordinate)){
        gameLost=true;

    } else if (grid.gameWon()) {
        gameWon=true;
    }

}
    public void startGame(){
        setupRowAndCols();

        while (!gameWon&&!gameLost){
            String moveChoice="";
            boolean validInput=false;
            while (!validInput) {
                System.out.println("v-Make tile visible, f-flag, u-unflag ");
                char c = myObj.next().charAt(0);
                if(c=='v'){

                    System.out.println("char v");
                    makeTileVisible();
                    validInput=true;
                   // gameWon=true;
                }

            }


        }

        if(gameWon){

            System.out.println("you won");
        } else if(gameLost){

            System.out.println("you lost");
        }

    }
}
