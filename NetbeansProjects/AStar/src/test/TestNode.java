/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import astar.Edge;
import astar.Node;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Tobias
 */
public class TestNode implements Node<TestNode>
{
    private final int x;
    private final int y;
    private final int moveCost;
    private final ArrayList<Edge<TestNode>> edges;

    public TestNode(int x, int y, int moveCost)
    {
        this.x = x;
        this.y = y;
        this.moveCost = moveCost;
        this.edges = new ArrayList<>();
    }

    @Override
    public int hashCode()
    {
        int hash = 7;
        hash = 37 * hash + this.x;
        hash = 37 * hash + this.y;
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final TestNode other = (TestNode) obj;
        if (this.x != other.x)
        {
            return false;
        }
        return this.y == other.y;
    }
    
    

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getMoveCost()
    {
        return moveCost;
    }

    public void addEdge(TestNode node)
    {
        Edge e = new TestEdge(node, this.moveCost + node.moveCost); 
        edges.add(e);
    }

    @Override
    public Iterator<Edge<TestNode>> iterator()
    {
        return edges.iterator();
    }

    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ") " + moveCost;
    }

    private static class TestEdge implements Edge<TestNode>
    {

        private TestNode end;
        private float cost;

        public TestEdge(TestNode end, float cost)
        {
            this.end = end;
            this.cost = cost;
        }

        @Override
        public float getCost()
        {
            return cost;
        }

        @Override
        public TestNode getEnd()
        {
            return end;
        }

    }

}
