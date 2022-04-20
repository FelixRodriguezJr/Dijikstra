package main;

import javax.swing.*;

public class FrameDisplay extends JFrame
{
    //This is just to set the size of the display.
    int WIDTH = 1000;
    int HEIGHT = 1000;

    public FrameDisplay(Graph matrix)
    {
        setTitle("Graph Display");
        setSize(WIDTH, HEIGHT);

        GraphDisplay panel = new GraphDisplay(matrix);
        add(panel);
    }
}