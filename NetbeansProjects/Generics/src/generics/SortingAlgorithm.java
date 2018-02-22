/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

import java.util.Comparator;

/**
 *
 * @author Tobias
 */
public interface SortingAlgorithm
{
    public <E> void sort(E[] array, Comparator<E> comp);
    public <E extends Comparable<E>> void sort(E[] array);
}
