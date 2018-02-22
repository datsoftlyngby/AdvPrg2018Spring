/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

/**
 *
 * @author Tobias
 * @param <E> the element type
 */
public interface Stack<E>
{
    public boolean isEmpty();
    public int size();
    public void push(E element);
    public E pop();
}
