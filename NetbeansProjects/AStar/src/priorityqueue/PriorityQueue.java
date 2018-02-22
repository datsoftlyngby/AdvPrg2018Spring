/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priorityqueue;

/**
 *
 * @author Tobias
 */
public interface PriorityQueue<E>
{
    public boolean isEmpty();
    public int size();
    public boolean contains(E element);
    public boolean add(E element);
    public E peek();
    public E poll();
    public boolean update(E element);
    public boolean remove(E element);
}
