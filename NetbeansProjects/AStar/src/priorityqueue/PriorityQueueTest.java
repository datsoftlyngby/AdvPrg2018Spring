/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package priorityqueue;

import java.util.Comparator;
import java.util.Objects;

/**
 *
 * @author Tobias
 */
public class PriorityQueueTest
{
    
    public static void main(String[] args)
    {
        PriorityQueue<Item> queue = new PriorityQueueImpl<>(new ItemComparator());
        
        Item Anne = new Item("Anne", 4);
        queue.add(new Item("Adam", 8));
        queue.add(new Item("Niels", 1));
        queue.add(new Item("Holger", 4));
        queue.add(new Item("Brian", 5));
        queue.add(new Item("Dorte", 6));
        queue.add(Anne);
        queue.add(new Item("Helen", 3));
        queue.add(new Item("Sofus", 7));
        queue.add(new Item("Klaus", 17));
        
        queue.remove(new Item("Dorte",0));
        Anne.value = 9;
        queue.update(Anne);
        
        while(!queue.isEmpty())
        {
            Item it = queue.poll();
            System.out.println(it);
        }
        
    }
    
    
    
    private static class ItemComparator implements Comparator<Item>
    {
        @Override
        public int compare(Item o1, Item o2)
        {
            return o1.value - o2.value;
        }    
    }
           
    private static class Item
    {
        public String name;
        public int value;

        public Item(String name, int value)
        {
            this.name = name;
            this.value = value;
        }

        @Override
        public int hashCode()
        {
            int hash = 3;
            hash = 11 * hash + Objects.hashCode(this.name);
            return hash;
        }

        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
            {
                return true;
            }
            if (obj == null)
            {
                return false;
            }
            if (getClass() != obj.getClass())
            {
                return false;
            }
            final Item other = (Item) obj;
            if (!Objects.equals(this.name, other.name))
            {
                return false;
            }
            return true;
        }

        @Override
        public String toString()
        {
            return "Item{" + "name=" + name + ", value=" + value + '}';
        }
        
        
    }
}
