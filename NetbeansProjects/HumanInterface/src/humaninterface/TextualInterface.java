/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humaninterface;

import java.io.Closeable;

/**
 *
 * @author Tobias
 */
public interface TextualInterface extends Closeable
{
    public void sendMessage(String msg);
    public String askForString(String question);
    public String askForPassword(String question);
    public String askForEmail(String question);
    public int askForInteger(String question);
    public int askForInteger(String question, int min, int max);
    public int makeSingleChoice(String question, String[] choices);
}
