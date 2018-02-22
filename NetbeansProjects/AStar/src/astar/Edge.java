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
public interface Edge<N extends Node<N>>
{
    public float getCost();
    public N getEnd();
}
