/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generics;

/**
 *
 * @author Tobias
 */
public class RunTest
{
    public static void main(String[] args)
    {
        TestAlgorithm algo = new TestAlgorithm();
        SortingAlgorithm algo2 = algo;
        
        String[] strings = {"ffhsfk","hdka"};
        
        algo.sort(strings);
        algo2.sort(strings);
        
        Foo[] foos = (Foo[]) new Object[2];
        foos[0] = new Foo();
        foos[1] = new Foo();
        
        algo.sort(foos);
    }
    
    private static class Foo implements Comparable<Foo>
    {

        @Override
        public int compareTo(Foo o)
        {
            return 0;
        }
        
    }
}
