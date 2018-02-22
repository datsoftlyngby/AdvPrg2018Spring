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
public class FunnyStuff
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Impl impl = new Impl();
        Intf intf = impl;
        
        impl.foo("Hello World");
        intf.foo("Hello World");
    }
    
}
