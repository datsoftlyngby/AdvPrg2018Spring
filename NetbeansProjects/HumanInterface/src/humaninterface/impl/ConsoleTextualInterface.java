/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humaninterface.impl;

import humaninterface.TextualInterface;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Tobias
 */
public class ConsoleTextualInterface implements TextualInterface
{
    private Scanner scanner;

    public ConsoleTextualInterface()
    {
        scanner = new Scanner(System.in);
    }
    
    
    @Override
    public void sendMessage(String msg)
    {
        System.out.print(msg);
    }

    @Override
    public String askForString(String question)
    {
        sendMessage(question);
        return scanner.nextLine();
    }

    @Override
    public String askForPassword(String question)
    {
        return askForString(question);
    }

    @Override
    public String askForEmail(String question)
    {
        //ToDo: Check for proper password.
        return askForString(question);
    }

    @Override
    public int askForInteger(String question)
    {
        while(true)
        {
            String input = askForString(question);
            try
            {
                int res = Integer.parseInt(input);
                return res;
            }
            catch(NumberFormatException e)
            {
                sendMessage("Please enter an integer.\n");
            }
        }        
    }

    @Override
    public int askForInteger(String question, int min, int max)
    {
        while(true)
        {
            int res = askForInteger(question);
            if(res >= min && res <= max)
            {
                return res;
            }
            sendMessage("Please enter an integer between " + min + " and " + max + ".\n");
        }
    }

    @Override
    public int makeSingleChoice(String question, String[] choices)
    {
        System.out.println(question + "\n\n");
        for(int i = 0; i < choices.length; ++i)
        {
            System.out.println("  " + (i+1) + " - " + choices[i]);
        }
        System.out.println("\n\n");
        int tmp = askForInteger("Please choose one: ", 1, choices.length);
        return tmp-1;
    }

    @Override
    public void close() throws IOException
    {
        System.out.println("Goodbye!");
    }
    
}
