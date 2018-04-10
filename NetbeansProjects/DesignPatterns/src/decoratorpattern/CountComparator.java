/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decoratorpattern;

import java.util.Comparator;

/**
 *
 * @author Tobias
 * @param <T>
 */
public class CountComparator<T> implements Comparator<T>
{
    private Comparator<T> original;
    private int count;

    public CountComparator(Comparator<T> original)
    {
        this.original = original;
        this.count = 0;
    }
    
    public int getCount()
    {
        return count;
    }
    
    public void reset()
    {
        count = 0;
    }
    
    @Override
    public int compare(T o1, T o2)
    {
        ++count;
        return original.compare(o1, o2);
    }
    
}
