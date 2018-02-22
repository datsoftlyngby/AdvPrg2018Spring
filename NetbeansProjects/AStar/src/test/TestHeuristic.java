/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import astar.Heuristic;

/**
 *
 * @author Tobias
 */
public class TestHeuristic implements Heuristic<TestNode>
{

    @Override
    public float getMinimumDist(TestNode a, TestNode b)
    {
        return (Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY()))*2;
    }
    
}
