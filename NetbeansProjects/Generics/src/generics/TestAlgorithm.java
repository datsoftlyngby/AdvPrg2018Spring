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
public class TestAlgorithm implements SortingAlgorithm
{
    
    public void sort(String[] array)
    {
        System.out.println("public void sort(String[] array)");
    }

    @Override
    public <E> void sort(E[] array, Comparator<E> comp)
    {
        System.out.println("public <E> void sort(E[] array, Comparator<E> comp)");  
    }

    @Override
    public <E extends Comparable<E>> void sort(E[] array)
    {
        System.out.println("public <E extends Comparable<E>> void sort(E[] array)");
    }
    
}
