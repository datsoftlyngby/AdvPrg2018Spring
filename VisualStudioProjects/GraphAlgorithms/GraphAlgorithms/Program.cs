using GraphAlgorithms.Test;
using System;
using System.Collections.Generic;
using System.Text;

namespace GraphAlgorithms.Test
{
    class TestProgram
    {
        static void Main(string[] args)
        {
            int width = 10;
            int height = 10;

            Random rnd = new Random();
            TestNode[,] grid = new TestNode[width, height];
            for (int y = 0; y < height; ++y)
            {
                for (int x = 0; x < width; ++x)
                {
                    int moveCost = rnd.Next(1, 10);
                    grid[x, y] = new TestNode(x, y, moveCost);
                }
            }

            for (int y = 0; y < height; ++y)
            {
                for (int x = 0; x < width; ++x)
                {
                    if (y > 0)
                    {
                        grid[x, y].AddEdgeTo(grid[x, y - 1]);
                    }
                    if (y < 9)
                    {
                        grid[x, y].AddEdgeTo(grid[x, y + 1]);
                    }
                    if (x > 0)
                    {
                        grid[x, y].AddEdgeTo(grid[x - 1, y]);
                    }
                    if (x < 9)
                    {
                        grid[x, y].AddEdgeTo(grid[x + 1, y]);
                    }
                }
            }

            StringBuilder map = new StringBuilder();
            map.Append("  ");
            for (int i = 0; i < 10; ++i)
            {
                map.Append(i);
            }
            map.Append("\n\n");
            for (int y = height - 1; y >= 0; --y)
            {
                map.Append(y);
                map.Append(" ");
                for (int x = 0; x < width; ++x)
                {
                    map.Append(grid[x, y].MoveCost);
                }
                map.Append(" ");
                map.Append(y);
                map.Append("\n");
            }
            map.Append("\n  ");
            for (int i = 0; i < 10; ++i)
            {
                map.Append(i);
            }

            Console.WriteLine(map + "\n\n");

            IEnumerable<TestNode> path = AStar.GetShortestPath(grid[0, 0], grid[9, 9], new TestHeuristic());
            foreach (TestNode n in path)
            {
                Console.WriteLine(n);
            }
            Console.ReadLine();
        }
    }
}
