/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humaninterface;

import humaninterface.impl.ConsoleTextualInterface;
import humaninterface.remote.Conversation;
import java.io.IOException;

/**
 *
 * @author Tobias
 */
public class Test
{
    public static void main(String[] args) throws IOException
    {
        TextualInterface ti = new ConsoleTextualInterface();  
        Conversation conversation = new Conversation(ti);
        conversation.run();
    }
}
