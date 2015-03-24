/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author jordandashel
 */
public class Grid {

    private final Square[][] grid;
    private final int dim, numMines;

    public Grid(int size, int numberOfMines){
        
        //initialize grid to specifications
        grid = new Square[size][size];
        dim = size;
        numMines = numberOfMines;
        
        // Fill grid with empty squares
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                grid[i][j] = new Square(false);
            }
        }
        
        Random rand = new Random();
        
        int x;
        int y;
        
        // randomly place mines throughout the grid
        
        for (int i = 0; i < numberOfMines; i++){
            x = rand.nextInt(size);
            y = rand.nextInt(size);
            boolean check = true; //boolean to check if the coordinates are a repeat
            while (check){
                if(grid[x][y].isSquareIsMine()){ // Is this square already a mine?
                    x = rand.nextInt(size);
                    y = rand.nextInt(size);
                } else {
                    grid[x][y].setSquareIsMine(true);
                    check = false;
                }
            }
        }
        
    }
   
    /**
     * Will gather all adjacent blank tiles into the returned ArrayList. 
     * 
     * @param x
     * @param y
     * @param blanks
     * @return 
     */
    public ArrayList<Point> checkBlankSquare(int x, int y, ArrayList<Point> blanks){
        for (int i = -1; i < 2; i++){ //x
            for (int k = -1; k < 2; k++){ //y
                
                
                if((i == 0 && k == 0) || isInvalidForChecking(x + i, y + k)){ // SELF CHECK
                    //do nothing
                }
                
                else if (this.getIsBlank(x + i, y + k)){
                    if (!grid[x + i][y + k].getIsChecked()){ // Has this square already been visited?
                        blanks.add(new Point(x + i, y + k));
                        grid[x + i][y + k].setIsChecked(true);
                        blanks.addAll(checkBlankSquare(x + i, y + k, new ArrayList<Point>()));
                    }
                    
                }
            }
        }
        return blanks;
    }
    
    /**
     * Checks to see if a given coordinate is within the bounds of the grid.
     * @param x
     * @param y
     * @return True if the requested coordinates are invalid.
     */
    private boolean isInvalidForChecking(int x, int y){
        return (x < 0 || x >= this.dim || y < 0 || y >= this.dim);
    }
    
    /**
     * Returns the number of mines surrounding any square. 
     * Used to display a number after a square has been triggered.
     * 
     * @param x
     * @param y
     * @return 
     */
    public int getSurroundingMineNumber(int x, int y){
        
        final int MINE = 9;
        int count = 0;
        
        if(this.getIsMine(x, y)){
            return MINE;
        }
        
        for (int i = -1; i < 2; i++){ //x
            for (int k = -1; k < 2; k++){ //y
                if ((x == 0 && i == -1) || (y==0 && k == -1)){
                    //do nothing
                }else if ((x == (this.dim - 1) && i == 1) || 
                        (y == (this.dim - 1) && k == 1)){
                    //do nothing
                }else {
                    if (this.getIsMine(x + i, y + k)){
                        count++;
                    }
                }
            }
        }           
        return count;
    }
    
    /**
     * Set a square as flagged by the player.
     * @param x
     * @param y
     * @param isFlagged 
     */
    public void setFlagged(int x, int y, boolean isFlagged){
        grid[x][y].setSquareIsFlagged(isFlagged);
    }
    
    /**
     * Has this square been flagged by the user?. Flags are used by the player in
     * squares they believe to be a mine.
     * 
     * @param x
     * @param y
     * @return 
     */
    public boolean getIsFlagged(int x, int y){
        return grid[x][y].getSquareIsFlagged();
    }
    
    /**
     * 
     * @param x x-coordinate
     * @param y y-coordinate
     * @return boolean is this square a mine?
     */
    public boolean getIsMine(int x, int y){
        return (grid[x][y].isSquareIsMine());
    }
    
    /**
     * Returns true if this square is surrounded by no mines. 
     * @param x x-coordinate
     * @param y y-coordinate
     * @return 
     */
    public boolean getIsBlank(int x, int y){
        return (0 == this.getSurroundingMineNumber(x, y));
    }

    /**
     * Get grid dimension.
     * @return 
     */
    public int getDim() {
        return dim;
    }
    /**
     * Gives the total number of mines on the board.
     * @return 
     */
    public int getNumMines() {
        return numMines;
    }
    
    
}
