/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.image.BufferedImage;

/**
 *
 * @author jordandashel
 */
public class TileManager {
    
    private static final BufferedImage tile_empty = Sprite.getSprite(2, 2);
    private static final BufferedImage tile_1 = Sprite.getSprite(0, 0);
    private static final BufferedImage tile_2 = Sprite.getSprite(1, 0);
    private static final BufferedImage tile_3 = Sprite.getSprite(2, 0);
    private static final BufferedImage tile_4 = Sprite.getSprite(3, 0);
    private static final BufferedImage tile_5 = Sprite.getSprite(0, 1);
    private static final BufferedImage tile_6 = Sprite.getSprite(1, 1);
    private static final BufferedImage tile_7 = Sprite.getSprite(2, 1);
    private static final BufferedImage tile_8 = Sprite.getSprite(3, 1);
    private static final BufferedImage tile_flag = Sprite.getSprite(0, 2);
    private static final BufferedImage tile_mine = Sprite.getSprite(1, 2);
    private static final BufferedImage tile_blank = Sprite.getSprite(0, 3);
    

    public static BufferedImage getTile_empty() {
        return tile_empty;
    }

    public static BufferedImage getTile_1() {
        return tile_1;
    }

    public static BufferedImage getTile_2() {
        return tile_2;
    }

    public static BufferedImage getTile_3() {
        return tile_3;
    }

    public static BufferedImage getTile_4() {
        return tile_4;
    }

    public static BufferedImage getTile_5() {
        return tile_5;
    }

    public static BufferedImage getTile_6() {
        return tile_6;
    }

    public static BufferedImage getTile_7() {
        return tile_7;
    }

    public static BufferedImage getTile_8() {
        return tile_8;
    }

    public static BufferedImage getTile_flag() {
        return tile_flag;
    }

    public static BufferedImage getTile_mine() {
        return tile_mine;
    }

    public static BufferedImage getTile_blank() {
        return tile_blank;
    }
    
    
}
