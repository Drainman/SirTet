package fr.polytech.ihm;

import fr.polytech.back.Grid;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class MainPannel extends JFrame{

    private JTable game_table;
    private Grid grid;
    private int height;
    private int width;
    private JLabel[][] jTextPaneTab;
    private static final int SIZE_CASE = 20;

    public MainPannel(Grid p_grid){
        super();
        setTitle("SirTet");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,1000);
        setLayout(new GridLayout(24,10));

        grid  = p_grid;
        height = Grid.getSize().get("height");
        width = Grid.getSize().get("width");
        jTextPaneTab = new JLabel[height][width];



        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                jTextPaneTab[i][j] = new JLabel("");

                if(grid.grideMatrix[j][i] != 0){
                    jTextPaneTab[i][j].setBackground(convertInt(grid.getActivePieceColor()));
                    jTextPaneTab[i][j].setOpaque(true);
                    jTextPaneTab[i][j].setPreferredSize(new Dimension(SIZE_CASE,SIZE_CASE));
                }

                this.add(jTextPaneTab[i][j]);
            }
        }



    }

    private Color convertInt(int colorInt){
        if(colorInt == 1) return new Color(230,175,45);
        else if(colorInt == 2) return  new Color(170,55,55);
        else if(colorInt == 4) return  new Color(25,110,85);
        else if(colorInt == 5) return  new Color(80,30,115);
        else if(colorInt == 6) return  new Color(140,30,85);
        else return new Color(115,160,35);
    }

    public void refresh_tab(Grid p_grid){
        clearAllLabel();
        this.grid = p_grid;
        fillAllLabel();

        pack();
    }

    public void clearAllLabel(){
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                this.remove(jTextPaneTab[i][j]);
            }
        }
    }

    public void fillAllLabel(){
        for(int i=0;i<height;i++){
            for(int j=0;j<width;j++){
                jTextPaneTab[i][j] = new JLabel("");
                if(grid.grideMatrix[j][i] != 0)
                {
                    jTextPaneTab[i][j].setBackground(convertInt(grid.getActivePieceColor()));
                    jTextPaneTab[i][j].setOpaque(true);
                    jTextPaneTab[i][j].setPreferredSize(new Dimension(SIZE_CASE,SIZE_CASE));
                }
                this.add(jTextPaneTab[i][j]);
            }
        }
    }


}
