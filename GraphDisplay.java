package main;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

public class GraphDisplay extends JPanel
{
    
    private final Graph graph;
   
    public GraphDisplay(Graph matrix){
        graph = matrix;
    }

    @Override
    public void paint(Graphics g)
    {      
        //Center point of the Frame
        int y = getWidth()/2;
        int x = getHeight()/2;
        
        int radius = getWidth()/3;
        int vertexRadius = 30;
 
        //Drawing Positions
        int[] xDraw = new int[graph.verticesNumber];
        int[] yDraw = new int[graph.verticesNumber];
        
        //Dijkstra's algorithm
        int[] paths = new int[graph.verticesNumber];
        int[] distances = new int[graph.verticesNumber];
        graph.allShortestPaths(paths, distances, graph.start);
        
        
        ArrayList<Integer> path = new ArrayList<>();
        
        int current = graph.finish;
        
        while(current != graph.start){
            path.add(current);
            current = paths[current];
        }
        path.add(graph.start);
        
        //Draw Results
        g.setColor(Color.black);
        g.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 20));
        g.drawString("Starting Vertex: " + graph.start + "\t\t" + 
                     "Finishing Vertex: " + graph.finish + "\t\t" + 
                     "Path Distance: " + distances[graph.finish], 10, 30);
        Collections.reverse(path);
        g.drawString("Route: " + path, 10, 60);
        
        //Drawing Positions
        for(int i = 0; i < graph.verticesNumber; i++){
            double circle = i * (2 * Math.PI / graph.verticesNumber);
            
            xDraw[i] = (int)Math.round(x + radius * Math.cos(circle));
            yDraw[i] = (int)Math.round(y + radius * Math.sin(circle));
        }
                
        //Draws Shortest Path
        Graphics2D stroke = (Graphics2D) g;
        
        for(int i = 0; i < path.size()-1; i++){
            g.setColor(Color.GREEN);
            stroke.setStroke(new BasicStroke(15));
            
            if(i == 0){
                g.fillOval(xDraw[path.get(i)] - 45, yDraw[path.get(i)] - 45, 3 * vertexRadius, 3 * vertexRadius);
            }else if(i == path.size()-2){
                g.fillOval(xDraw[path.get(i+1)] - 45, yDraw[path.get(i+1)] - 45, 3 * vertexRadius, 3 * vertexRadius);
            }
            g.drawLine(xDraw[path.get(i)], yDraw[path.get(i)], xDraw[path.get(i+1)], yDraw[path.get(i+1)]);
        }
        
        stroke.setStroke(new BasicStroke(5));
       
        //Draw Edges
        for(int i = 0; i < graph.verticesNumber; i++){
            for(int j = 0; j < graph.verticesNumber; j++){
                if(graph.matrix[i][j] != 0){
                    g.setColor(Color.BLACK);
                    g.drawLine(xDraw[i], yDraw[i], xDraw[j], yDraw[j]);
                }
            }
        }
        
        //Draw Edge Distances
        for(int i = 0; i < graph.verticesNumber; i++){
            for(int j = 0; j < graph.verticesNumber; j++){
                if(graph.matrix[i][j] != 0){
                    g.setColor(Color.BLACK);
                    g.fillRoundRect(((xDraw[i] + xDraw[j])/2)-14, ((yDraw[i] + yDraw[j])/2)-17, vertexRadius+2, vertexRadius+2, 5, 5);
                    
                    g.setColor(Color.WHITE);
                    g.fillRoundRect(((xDraw[i] + xDraw[j])/2)-14, ((yDraw[i] + yDraw[j])/2)-17, vertexRadius, vertexRadius, 5, 5);
                    
                    g.setColor(Color.BLACK);
                    g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
                    g.drawString(Integer.toString(graph.matrix[i][j]), ((xDraw[i] + xDraw[j])/2) - 5, ((yDraw[i] + yDraw[j])/2) + 5);
                }
            }
        }
        
        //Draw Vertices
        for(int i = 0; i < graph.verticesNumber; i++){
            double circle = i * (2 * Math.PI / graph.verticesNumber);
            
            int xPosition = (int)Math.round(x + radius * Math.cos(circle));
            int yPosition = (int)Math.round(y + radius * Math.sin(circle));
            
            g.setColor(Color.BLACK);
            g.fillOval(xPosition - vertexRadius, yPosition - vertexRadius, (2*vertexRadius)+4, (2*vertexRadius)+4);
            
            g.setColor(Color.WHITE);
            g.fillOval(xPosition - vertexRadius, yPosition - vertexRadius, 2*vertexRadius, 2*vertexRadius);
           
            g.setColor(Color.BLACK);        
            g.setFont(new Font(Font.MONOSPACED, Font.ITALIC, 30));
            g.drawString(Integer.toString(i), xPosition-10, yPosition+10);
        }
    }
}