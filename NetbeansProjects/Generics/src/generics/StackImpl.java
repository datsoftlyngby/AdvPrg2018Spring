/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

import java.util.LinkedList;

/**
 *
 * @author Tobias
 */
public class StackImpl<E> implements Stack<E>
{
    private LinkedList<E> list;

    public StackImpl()
    {
        list = new LinkedList<>();
    }
    
    
    @Override
    public boolean isEmpty()
    {
        return list.isEmpty();
    }

    @Override
    public int size()
    {
        return list.size();
    }

    @Override
    public void push(E element)
    {
        list.addFirst(element);
    }

    @Override
    public E pop()
    {
        return list.removeFirst();
    }
    
}
