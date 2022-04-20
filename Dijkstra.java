package main;

import javax.swing.JFrame;
     
/**
 *
 * @author felixrodriguez
 */
public class Dijkstra {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Graph matrix = new Graph("Graph.txt");
        
        FrameDisplay frame = new FrameDisplay(matrix);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    
}
