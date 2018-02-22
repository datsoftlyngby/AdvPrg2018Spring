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
    }
}
