/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import astar.AStar;
import astar.Node;
import java.util.Random;

/**
 *
 * @author Tobias
 */
public class Test
{
    public static void main(String[] args)
    {
        
        int width = 10;
        int height = 10;
        
        Random rnd = new Random();
        TestNode[][] grid = new TestNode[width][height];
        for(int y = 0; y < height; ++y)
        {
            for(int x = 0; x < width; ++x)
            {
                int moveCost = rnd.nextInt(9) + 1;
                grid[x][y] = new TestNode(x, y, moveCost);
            }
        }
        
        for(int y = 0; y < height; ++y)
        {
            for(int x = 0; x < width; ++x)
            {
                if(y > 0)
                {
                   grid[x][y].addEdge(grid[x][y-1]);
                }
                if(y < 9)
                {
                    grid[x][y].addEdge(grid[x][y+1]);
                }
                if(x > 0)
                {
                   grid[x][y].addEdge(grid[x-1][y]);
                }
                if(x < 9)
                {
                    grid[x][y].addEdge(grid[x+1][y]);
                }
            }
        }
        
        StringBuilder map = new StringBuilder();
        map.append("  ");
        for(int i = 0; i < 10; ++i)
        {
            map.append(i);
        }
        map.append("\n\n");
        for(int y = height - 1; y >= 0; --y)
        {
            map.append(y);
            map.append(" ");
            for(int x = 0; x < width; ++x)
            {
                map.append(grid[x][y].getMoveCost());
            }
            map.append(" ");
            map.append(y);
            map.append("\n");
        }
        map.append("\n  ");
        for(int i = 0; i < 10; ++i)
        {
            map.append(i);
        }
        System.out.println(map + "\n\n");
        
        Iterable<TestNode> path = AStar.getShortestPath(grid[0][0], grid[9][9], new TestHeuristic());
        for(TestNode n : path)
        {
            System.out.println(n);
        }
    }
}
