package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Graph
{
    //I used protected instead of private just to get the variables
    //without having to create getter methods. public works too.
    
    protected int verticesNumber;
    protected int[][] matrix; //adjacency matrix
    
    protected int start;
    protected int finish;
    
    /**
     * Instantiates a graph and initializes it with info from a text file.
     *
     * @param filename text file with graph info
     */
    public Graph(String filename)
    {
        File input = new File(filename);

        Scanner in = null;
        try
        {
            in = new Scanner(input);
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found!");
            System.exit(0);
        }

        while (in.hasNextLine())
        {
            verticesNumber = in.nextInt();
            matrix = new int[verticesNumber][verticesNumber];

            for(int i=0; i<verticesNumber; i++)
            {
                for(int j=0; j<verticesNumber; j++)
                {
                    matrix[i][j] = in.nextInt();
                }
            }
            start = in.nextInt();
            finish = in.nextInt();
        }

        in.close();
    }
    
    /**
     * Finds vertices adjacent to a given vertex.
     * 
     * @param v given vertex
     * @return list of vertices adjacent to v stored in an array
     * size of array = number of adjacent vertices
     */
    public int[] findAdjacencyVertices(int v)
    {
        int[] vert = new int[verticesNumber];
        int total = 0;
        
        for (int i=0; i<verticesNumber; i++)
        {
            if (matrix[v][i] != 0)
            {
                vert[total] = i;
                total++;
            }
        }
        
        return Arrays.copyOf(vert, total);        
    }

    /**
    * Returns smallest element in given array d, out of those that have not
    * been visited (see allShortestPaths method).
    * @param visited visited elements
    * @param d array of distances
    * @return index of smallest element in d
    */
    private int minDistance(boolean[] visited, int[] distance){
        int index = -1;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < verticesNumber; i++){
            if(!visited[i]){
                if(distance[i] <= min){
                    min = distance[i];
                    index = i;
                }
            }
        }
        return index;
    }
    
    /**
    * Calculates the shortest paths from a given vertex to all vertices.
    * @param p paths (p[i] contains previous vertex in the shortest path from v)
    * @param d distances (d[i] contains the shortest distance from v)
    * @param v given vertex
    */
    public void allShortestPaths(int[] p, int[] d, int v){
        boolean[] visited = new boolean[verticesNumber];
        
        for(int i = 0; i < verticesNumber; i++){
            visited[i] = false; // not yet visited
            p[i] = -1; // previous vertex in unknown
            d[i] = Integer.MAX_VALUE; //p[i] = INFINITY
        }
        
        d[v] = 0;
        
        for(int i = 0; i < verticesNumber-1; i++){
            int w = minDistance(visited, d);
            visited[w] = true;
            
            int[] adj = findAdjacencyVertices(w);
            
            for(int u : adj){
                if(!visited[u]){
                    if(d[w]+matrix[w][u] < d[u]){
                        d[u] = d[w]+matrix[w][u];
                        p[u] = w;
                    }
                }
            }
        }
    }
}