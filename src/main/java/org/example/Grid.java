package org.example;

import org.example.Tile;

import java.util.PrimitiveIterator;

public class Grid {
    private Tile[][] grid;
    private int row;
    private int col;
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

                grid[rowI][colI]=new Tile("");
            }


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
