/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minesweeper;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author jordandashel
 */
public class View extends JFrame {
    
    public Grid grid;
    private JPanel p;
    private final HashMap<String, JLabel> labels = new HashMap<>();
    public static final int GRID_SPACE = 0; // buffer between squares
    
    
    
    public View(Grid board){
      
        
        grid = board;
        
        JFrame f = new JFrame("Minesweeper");
        f.setSize(new Dimension(grid.getDim()*Sprite.getTILE_SIZE(), 
            grid.getDim()*Sprite.getTILE_SIZE()));
        
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        p = new JPanel();
        LayoutManager lm = new GridLayout(board.getDim(), board.getDim(), 0, 0);
        p.setLayout(lm);
        
        
        for (int i = 0; i < grid.getDim(); i++){
            for (int j = 0; j < grid.getDim(); j++){
                JLabel label = new JLabel();
                
                ImageIcon icon = new ImageIcon(TileManager.getTile_blank());
                label.setIcon(icon);
                
                label.setName(i + " " + j);
                labels.put(label.getName(), label);
                label.addMouseListener(new MouseAdapter(){
                    
                    @Override
                    public void mousePressed(MouseEvent e){
                        JLabel jlabel = (JLabel)e.getSource();
                        String buttName = jlabel.getName(); // name contains location
                        String delim = "[ ]";
                        String[] temp = buttName.split(delim);
                        int[] coords = new int[2];
                        for (int k = 0; k < 2; k++){
                            coords[k] = Integer.parseInt(temp[k]);
                        }
                        
                        if(SwingUtilities.isRightMouseButton(e)||e.isControlDown()){
                            jlabel.setIcon(new ImageIcon(TileManager.getTile_flag()));
                        }else{
                            if(grid.getSurroundingMineNumber(coords[0], coords[1]) == 0) {
                                ArrayList<Point> blanks = new ArrayList<>();
                                blanks = grid.checkBlankSquare(new Point(coords[0], coords[1]));
                                clear(blanks);
                            }
                            jlabel.setIcon(dispNumber(coords[0], coords[1]));
                        }
                    }
                    
                });
                p.add(label);
            }
        }
        
        f.add(p);
        f.pack();
        f.setVisible(true);
    }
    
    /**
     * Returns the tile associated with the number of mines surrounding the square.
     * @param x
     * @param y
     * @return ImageIcon associated with the tile's identity
     */
    private ImageIcon dispNumber(int x, int y){
        
        int numMines = grid.getSurroundingMineNumber(x, y);
        
        switch (numMines){
            case 0  :   return new ImageIcon(TileManager.getTile_empty());
                
            case 1  :   return new ImageIcon(TileManager.getTile_1());

            case 2  :   return new ImageIcon(TileManager.getTile_2());
                        
            case 3  :   return new ImageIcon(TileManager.getTile_3());
                  
            case 4  :   return new ImageIcon(TileManager.getTile_4());
                       
            case 5  :   return new ImageIcon(TileManager.getTile_5());
            
            case 6  :   return new ImageIcon(TileManager.getTile_6());
                
            case 7  :   return new ImageIcon(TileManager.getTile_7());
                
            case 8  :   return new ImageIcon(TileManager.getTile_8());    
                     
            case 9  :   return new ImageIcon(TileManager.getTile_mine());
                
            default :   return null;
        }
    }
    
    private void clear(ArrayList<Point> blanks){
        String id;
        for (Point pnt : blanks){
            for (int i = -1; i < 2; i++) { //x
                for (int k = -1; k < 2; k++) { //y

                //  EDGE CHECKS
                    if ((pnt.x == 0 && i == -1) || (pnt.y == 0 && k == -1)) { // EDGE CHECK
                        //do nothing
                    } else if ((pnt.x == (grid.getDim() - 1) && i == 1) || // EDGE CHECK
                            (pnt.y == (grid.getDim() - 1) && k == 1)) {
                        //do nothing
                    } else{
                        id = ((pnt.x + i) + " " + (pnt.y + k));
                        ((JLabel)labels.get(id)).setIcon(dispNumber(pnt.x + i, pnt.y + k));
                
                    }
                }
            }
        }
    }
    
    public void gameOver(){
        //TODO reveal all mines and declare game over
    }
}
