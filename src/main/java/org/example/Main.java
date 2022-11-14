package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        int row;
        int col;
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        System.out.println("Enter Number of rows");
        while (!myObj.hasNextInt()){
            myObj.next();
            System.out.println("Please enter a valid  Number of rows");
        }
        row=myObj.nextInt();
        System.out.println("Enter Number of columns");
        while (!myObj.hasNextInt()){
            myObj.next();
            System.out.println("Please enter a valid  Number of columns");
        }
        col=myObj.nextInt();
        Grid grid=new Grid(row,col);
        grid.printAll();

        //first print grid then x any why cordinates
        //verify cordinates
        // make points visible
        //send to grid to check if its a mine
            //exit
        //loop unitl empty is 0
        

    }
}