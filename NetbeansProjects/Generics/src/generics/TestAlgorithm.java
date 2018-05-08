/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * @author Tobias
 */
public class TestAlgorithm implements SortingAlgorithm
{
    
    @Override
    public <E> void sort(E[] array, Comparator<E> comp)
    {
        Arrays.sort(array, comp);
    }

    @Override
    public <E extends Comparable<E>> void sort(E[] array)
    {
        Arrays.sort(array);
    }
    
}
