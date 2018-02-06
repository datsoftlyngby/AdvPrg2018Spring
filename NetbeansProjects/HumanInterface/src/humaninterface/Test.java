/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humaninterface;

import humaninterface.impl.ConsoleTextualInterface;

/**
 *
 * @author Tobias
 */
public class Test
{
    public static void main(String[] args)
    {
        TextualInterface ti = new ConsoleTextualInterface();
        
        String name = ti.askForString("What is your name? : ");
        int age = ti.askForInteger("What is your age? : ", 0, 200);
        String[] food = {"Banana", "Ice cream", "Tomato", "Grapes", "Apple"};
        int favorite = ti.makeSingleChoice("What do like best? : ", food);
        
        System.out.println("Hi " + name);
        System.out.println("It seems that you are " + age + " years old and like " + food[favorite]);
    }
}
