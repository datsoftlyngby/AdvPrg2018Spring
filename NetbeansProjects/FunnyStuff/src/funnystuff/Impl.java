/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funnystuff;

/**
 *
 * @author Tobias
 */
public class Impl implements Intf
{
    
    public void foo(String s)
    {
        System.out.println("Method 1: " + s);
    }
    
    @Override
    public <E> void foo(E e)
    {
        System.out.println("Method 2: " + e);
    }
    
}
