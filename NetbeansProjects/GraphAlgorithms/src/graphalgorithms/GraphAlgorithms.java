/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphalgorithms;

/**
 *
 * @author Tobias
 */
public interface GraphAlgorithms
{
    public Path getShortestPath(Node begin, Node end, Heuristic h);
    public Path getShortestPath(Node begin, Node end);
    public Iterable<Node> getReachableNodes(Node begin, double maxCost);
}
