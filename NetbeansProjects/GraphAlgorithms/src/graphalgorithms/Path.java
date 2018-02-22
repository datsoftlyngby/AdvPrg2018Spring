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
public interface Path extends Iterable<Node>
{
    public int size();
    public double cost();
}
