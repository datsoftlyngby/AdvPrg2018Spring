/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priorityqueue;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tobias
 * @param <E>
 */
public class PriorityQueueImpl<E> implements PriorityQueue<E>
{

    private final Comparator<E> comp;
    private final Map<E, Node<E>> nodeMap;
    private Node<E>[] array;

    public PriorityQueueImpl(Comparator<E> comp)
    {
        this.comp = comp;
        this.nodeMap = new HashMap<>();
        this.array = (Node<E>[]) new Node[8];
    }

    @Override
    public boolean isEmpty()
    {
        return nodeMap.isEmpty();
    }

    @Override
    public int size()
    {
        return nodeMap.size();
    }

    @Override
    public boolean contains(E element)
    {
        return nodeMap.containsKey(element);
    }

    @Override
    public boolean add(E element)
    {
        if (nodeMap.containsKey(element))
        {
            return false;
        }
        int index = nodeMap.size();
        Node<E> newNode = new Node<>(element, index);
        if (index == array.length)
        {
            //Extend array
            array = Arrays.copyOf(array, 2 * array.length);
        }
        array[index] = newNode;
        nodeMap.put(element, newNode);
        heapifyUp(index);
        return true;
    }

    @Override
    public E peek()
    {
        if (nodeMap.isEmpty())
        {
            return null;
        }
        return array[0].element;
    }

    @Override
    public E poll()
    {
        if (nodeMap.isEmpty())
        {
            return null;
        }
        E res = array[0].element;
        nodeMap.remove(res);
        int lastIndex = nodeMap.size();
        Node<E> last = array[lastIndex];
        last.index = 0;
        array[0] = last;
        array[lastIndex] = null;
        heapifyDown(0);
        shrinkIfAppropriate();
        return res;
    }

    @Override
    public boolean update(E element)
    {
        Node<E> n = nodeMap.get(element);
        if (n == null)
        {
            return false;
        }
        update(n.index);
        return true;
    }

    @Override
    public boolean remove(E element)
    {
        Node<E> n = nodeMap.get(element);
        if (n == null)
        {
            return false;
        }
        nodeMap.remove(element);
        int lastIndex = nodeMap.size();
        array[n.index] = array[lastIndex];
        array[n.index].index = n.index;
        array[lastIndex] = null;
        update(n.index);
        shrinkIfAppropriate();
        return true;
    }

    private void shrinkIfAppropriate()
    {
        if (array.length > 8 && nodeMap.size() * 4 < array.length)
        {
            Arrays.copyOf(array, array.length / 2);
        }
    }

    private void update(int index)
    {
        int p = parent(index);
        if (p == -1)
        {
            heapifyDown(index);
        } else
        {
            int c = comp.compare(array[p].element, array[index].element);
            if (c < 0)
            {
                heapifyDown(index);
            } else if (c > 0)
            {
                swap(p, index);
                heapifyUp(p);
            }
        }
    }

    private void heapifyDown(int n)
    {
        int leftIndex = leftChild(n);
        if (leftIndex == -1)
        {
            return;
        }
        int minChildIndex = leftIndex;
        int rightIndex = rightChild(n);
        if (rightIndex != -1 && comp.compare(array[rightIndex].element, array[leftIndex].element) < 0)
        {
            minChildIndex = rightIndex;
        }
        if (comp.compare(array[minChildIndex].element, array[n].element) < 0)
        {
            swap(minChildIndex, n);
            heapifyDown(minChildIndex);
        }
    }

    private void heapifyUp(int n)
    {
        int parent = parent(n);
        if (parent == -1)
        {
            return;
        }
        if (comp.compare(array[n].element, array[parent].element) < 0)
        {
            swap(parent, n);
            heapifyUp(parent);
        }
    }

    private void swap(int a, int b)
    {
        Node<E> tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
        tmp.index = a;
        array[a].index = b;
    }

    private int leftChild(int i)
    {
        int res = i * 2 + 1;
        return res < nodeMap.size() ? res : -1;
    }

    private int rightChild(int i)
    {
        int res = i * 2 + 2;
        return res < nodeMap.size() ? res : -1;
    }

    private int parent(int i)
    {
        return i > 0 ? (i - 1) / 2 : -1;
    }

    private static class Node<E>
    {

        public Node(E element, int index)
        {
            this.element = element;
            this.index = index;
        }

        public E element;
        public int index;
    }
}
