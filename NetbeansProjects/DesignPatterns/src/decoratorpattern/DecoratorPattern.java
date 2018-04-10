/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package decoratorpattern;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 *
 * @author Tobias
 */
public class DecoratorPattern
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Random rnd = new Random();
        ArrayList<Integer> list = new ArrayList<>();
        
        for(int i = 0; i < 100; ++i)
        {
            list.add(rnd.nextInt(100) + 1);
        }
        CountComparator<Integer> comp = new CountComparator<Integer>(Comparator.naturalOrder());
        
        Collections.sort(list, comp);
        
        System.out.println("Comparisons: " + comp.getCount());
    }
    
}
