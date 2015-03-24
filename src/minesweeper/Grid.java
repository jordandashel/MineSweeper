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

    public Grid(int size, int numberOfMines) {
        grid = new Square[size][size];
        dim = size;
        numMines = numberOfMines;

        initGrid();
        populateMinefield();
    }

    private void initGrid() {
        for (int i = 0; i < dim; i++) {
            for (int j = 0; j < dim; j++) {
                grid[i][j] = new Square(false);
            }
        }
    }

    private int x, y;
    private Random rand = new Random();

    private void populateMinefield() {
        for (int i = 0; i < numMines; i++) {
            placeMineInRandomPosition();
        }
    }

    private void placeMineInRandomPosition() {
        x = rand.nextInt(dim);
        y = rand.nextInt(dim);
        checkForRepeatLocation();
    }

    private boolean isRepeat;

    private void checkForRepeatLocation() {
        isRepeat = true;
        while (isRepeat) {
            checkSquareHasMine();
        }
    }

    private void checkSquareHasMine() {
        if (grid[x][y].isSquareIsMine()) {
            x = rand.nextInt(dim);
            y = rand.nextInt(dim);
        } else {
            grid[x][y].setSquareIsMine(true);
            isRepeat = false;
        }
    }
    
    /**
     * Will gather all adjacent blank tiles into the returned ArrayList.
     * @return
     */
    public ArrayList<Point> checkBlankSquare(Point point) {
                
        ArrayList<Point> surroundingBlankSquares = filterForBlankSquares(
                getAdjacentSquares(point));
        
        ArrayList<Point> allBlankSquares = new ArrayList<Point>();
        for (Point p : surroundingBlankSquares){
            if(!getIsCheckedSquare(p.x, p.y)){
                allBlankSquares.add(p);
                setIsCheckedSquare(point.x, point.y);
                allBlankSquares.addAll(checkBlankSquare(p));
            }
        }
        
        return allBlankSquares;
    }

    public ArrayList<Point> getAdjacentSquares(Point point){
        ArrayList<Point> blanks = new ArrayList<Point>();
        
        for (int horiz = -1; horiz <= 1; horiz++) { //x
            for (int vert = -1; vert <= 1; vert++) { //y
                Point sibling = new Point(point.x + horiz, point.y + vert);

                boolean isSelf = sibling == point;
                if (isSelf) continue;
                
                boolean isOnGridHorizontally = sibling.x >= 0 && sibling.x < dim;
                boolean isOnGridVertically = sibling.y >= 0 && sibling.y < dim;
                
                if (isOnGridHorizontally && isOnGridVertically ){
                    blanks.add(sibling);
                }
            }
        }
        return blanks;
    }
    
    public ArrayList<Point> filterForBlankSquares(ArrayList<Point> points){
        ArrayList<Point> results = new ArrayList<Point>();
        
        for (Point p : points){
            if (getIsBlankSquare(p.x, p.y)){
                results.add(p);
            }
        }
        return results;
    }
    
    public ArrayList<Point> filterForMines(ArrayList<Point> points){
        ArrayList<Point> results = new ArrayList<Point>();
        
        for (Point p : points){
            if (getIsMine(p.x, p.y)){
                results.add(p);
            }
        }
        return results;
    }
        
    /**
     * Checks to see if a given coordinate is within the bounds of the grid.
     *
     * @param x
     * @param y
     * @return True if the requested coordinates are invalid.
     */
    private boolean isValidForChecking(int x, int y) {
        return !(x < 0 || x > this.dim-1 || y < 0 || y > this.dim-1);
    }

    public int getSurroundingMineNumber(int x, int y) {

        // get siblings
        // filter for siblings with mines
        // .size
        
        final int MINE = 9;
        if (this.getIsMine(x, y)) {
            return MINE;
        }

        return filterForMines(getAdjacentSquares(new Point(x, y))).size();
    }

    public void setFlagged(int x, int y, boolean isFlagged) {
        grid[x][y].setSquareIsFlagged(isFlagged);
    }

    public boolean getSquareIsFlagged(int x, int y) {
        return grid[x][y].getSquareIsFlagged();
    }

    public boolean getIsMine(int x, int y) {
        return (grid[x][y].isSquareIsMine());
    }
    
    public boolean getIsCheckedSquare(int x, int y){
        return grid[x][y].getIsChecked();
    }
    
    public void setIsCheckedSquare(int x, int y){
        grid[x][y].setIsChecked(true);
    }

    /**
     * Returns true if this square is surrounded by no mines.
     */
    public boolean getIsBlankSquare(int x, int y) {
        return (0 == this.getSurroundingMineNumber(x, y));
    }

    public int getDim() {
        return dim;
    }

    public int getNumMines() {
        return numMines;
    }
}
