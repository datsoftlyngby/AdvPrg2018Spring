/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humaninterface.remote;

import humaninterface.TextualInterface;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tobias
 */
public class Conversation implements Runnable
{
    private final TextualInterface ti;

    public Conversation(TextualInterface ti)
    {
        this.ti = ti;
    }
    
    @Override
    public void run()
    {
        String name = ti.askForString("What is your name? : ");
        int age = ti.askForInteger("What is your age? : ", 0, 200);
        String[] food = {"Banana", "Ice cream", "Tomato", "Grapes", "Apple"};
        int favorite = ti.makeSingleChoice("What do like best? : ", food);
        ti.sendMessage("Hi " + name + "\n");
        ti.sendMessage("It seems that you are " + age + " years old and like " + food[favorite] + "\n");
        try
        {
            ti.close();
        } catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }
    
}
