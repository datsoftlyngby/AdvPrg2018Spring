/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

/**
 *
 * @author Tobias
 */
public interface GraphAlgorithms
{
    public <N extends Node<N>> Iterable<N> getShortestPath(N start, N goal, Heuristic heuristic);
    //public Iterable<Node> getShortestPath(Node start, Node goal);
    //public Set<Node> getReachableNodes(Node start, float maxCost);
}
