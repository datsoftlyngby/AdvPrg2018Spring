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
public class Generics
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Stack<String> stack = new StackImpl<>();
        
        stack.push("Element 1");
        stack.push("Element 2");
        stack.push("Element 3");
        stack.push("Element 4");
        while(!stack.isEmpty())
        {
            System.out.println(stack.pop());
        }
    }
    
}
