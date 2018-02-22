/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author Tobias
 * @param <N>
 */
public interface Heuristic<N>
{
    public float getMinimumDist(N a, N b);
}
