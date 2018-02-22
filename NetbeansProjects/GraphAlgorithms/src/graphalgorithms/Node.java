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
public interface Node
{
    public Iterable<Edge> getOutEdges();
    public Iterable<Edge> getInEdges();
}
