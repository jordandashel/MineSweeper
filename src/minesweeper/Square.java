/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

/**
 *
 * @author jordandashel
 */
public class Square {

    private boolean squareIsFlagged;
    private boolean squareIsMine;
    private boolean isChecked = false; // used as a check in Grid
    
    Square(boolean isMine){
        squareIsMine = isMine;
    }

    public boolean getSquareIsFlagged() {
        return squareIsFlagged;
    }

    public void setSquareIsFlagged(boolean squareIsFlagged) {
        this.squareIsFlagged = squareIsFlagged;
    }

    public boolean isSquareIsMine() {
        return squareIsMine;
    }

    public void setSquareIsMine(boolean squareIsMine) {
        this.squareIsMine = squareIsMine;
    }

    public boolean getIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }
}
