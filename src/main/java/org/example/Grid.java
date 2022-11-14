package org.example;

import org.example.Tile;

import java.awt.*;
import java.util.*;

public class Grid {
    private Tile[][] grid;
    private int row;
    private int col;
    private Set<Point> emptyPos = new HashSet<Point>();
    public Grid(int row,int col){
        this.row=row;
        this.col=col;
        grid= new Tile[row][col];
        initilize();
        // intitaze grid


    }

    private void initilize(){
        // depending on size choose number of mines
        // loop thought grid
       // intialize tiles and randomly place mines
        //

        for (int rowI=0;rowI<row;rowI++){

            for (int colI=0;colI<col;colI++){

                grid[rowI][colI]=new Tile("s");
            }


        }

        grid[0][0].setisMine(true);
        setTileVisible(0,0);


        generateNumbers();
        populateEmpties();
        displayEmpties(0,3);
        displayEmpties(0,1);
        displayEmpties(1,1);
        displayEmpties(1,0);
        System.out.println( emptyPos.size());
}

private void generateNumbers(){
    for (int rowI=0;rowI<row;rowI++){

        for (int colI=0;colI<col;colI++){
            if(!grid[rowI][colI].getisMine()) {
                int minesCount = 0;
                if(checkIfInIndex(rowI+1,row) && checkIfInIndex(colI,col)&& grid[rowI+1][colI].getisMine() ){

                    minesCount++;
                }
                if(checkIfInIndex(rowI-1,row) && checkIfInIndex(colI,col)&& grid[rowI-1][colI].getisMine() ){

                    minesCount++;
                }
                if(checkIfInIndex(rowI,row) && checkIfInIndex(colI-1,col)&& grid[rowI][colI-1].getisMine() ){

                    minesCount++;
                }
                if(checkIfInIndex(rowI,row) && checkIfInIndex(colI+1,col)&& grid[rowI][colI+1].getisMine() ){

                    minesCount++;
                }//here
                if(checkIfInIndex(rowI+1,row) && checkIfInIndex(colI+1,col)&& grid[rowI+1][colI+1].getisMine() ){

                    minesCount++;
                }
                if(checkIfInIndex(rowI-1,row) && checkIfInIndex(colI-1,col)&& grid[rowI-1][colI-1].getisMine() ){

                    minesCount++;
                }
                if(checkIfInIndex(rowI-1,row) && checkIfInIndex(colI+1,col)&& grid[rowI-1][colI+1].getisMine() ){

                    minesCount++;
                }
                if(checkIfInIndex(rowI+1,row) && checkIfInIndex(colI-1,col)&& grid[rowI+1][colI-1].getisMine() ){

                    minesCount++;
                }
                if(minesCount>0){
                    grid[rowI][colI].setValue(""+minesCount);
                   // grid[rowI][colI].setisVisible(true);
                }

            }

        }


    }

}

private void populateEmpties(){

    for (int rowI=0;rowI<row;rowI++){

        for (int colI=0;colI<col;colI++){
            if(!grid[rowI][colI].getisMine()) {
                emptyPos.add(new Point(rowI, colI));
            }

        }


    }
}

private void setTileVisible(int temprow,int tempCol){
    grid[temprow][tempCol].setisVisible(true);
    System.out.println(emptyPos.size());
    emptyPos.remove(new Point(temprow,tempCol));
    System.out.println("new size "+emptyPos.size());
}
private void displayEmpties(int tempRow,int tempCol){


    ArrayList<int[]> queue = new ArrayList<int[]>();


   queue.add(new int[]{tempRow, tempCol});
    setTileVisible(tempRow,tempCol);

    while (queue.size()!=0){
        int[] cordinates=queue.get(0);

        if(checkIfNotMineAndNotVisibleAndNotNumbers(cordinates[0]-1,cordinates[1]) ){
            setTileVisible(cordinates[0]-1,cordinates[1]);
            queue.add(new int[]{cordinates[0]-1, cordinates[1]});

        }
        if(checkIfNotMineAndNotVisibleAndNotNumbers(cordinates[0]+1,cordinates[1]) ){
            setTileVisible(cordinates[0]+1,cordinates[1]);
          //  grid[cordinates[0]+1][cordinates[1]].setisVisible(true);
            queue.add(new int[]{cordinates[0]+1, cordinates[1]});
        }
        if(checkIfNotMineAndNotVisibleAndNotNumbers(cordinates[0],cordinates[1]-1) ){
            setTileVisible(cordinates[0],cordinates[1]-1);
           // grid[cordinates[0]][cordinates[1]-1].setisVisible(true);
            queue.add(new int[]{cordinates[0], cordinates[1]-1});

        }
        if(checkIfNotMineAndNotVisibleAndNotNumbers(cordinates[0],cordinates[1]+1) ){
            setTileVisible(cordinates[0],cordinates[1]+1);
           // grid[cordinates[0]][cordinates[1]+1].setisVisible(true);
            queue.add(new int[]{cordinates[0], cordinates[1]+1});
        }
        queue.remove(0);

    }


}
private boolean checkIfNotMineAndNotVisibleAndNotNumbers(int tempRow,int tempCol){

    if(checkIfInIndex(tempRow,row)&&checkIfInIndex(tempCol,col)&& !grid[tempRow][tempCol].getisMine()
            &&!grid[tempRow][tempCol].getisVisible()&&grid[tempRow][tempCol].getValue()=="s" ){
        return true;


    }
    return false;
}
private boolean checkIfInIndex(int val,int indexMax){

        if (val>=0&& val<indexMax){
            return true;
        }else {
            return false;
        }
}
    public void printAll(){


        for (int rowI=0;rowI<row;rowI++){
            System.out.println();
            for (int colI=0;colI<col;colI++){

                grid[rowI][colI].print();
                System.out.print(",");
            }


        }
    }
}
