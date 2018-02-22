/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humaninterface.remote;

import humaninterface.TextualInterface;
import java.io.IOException;

/**
 *
 * @author Tobias
 */
public class QuestionSide implements TextualInterface
{
    private final TextualInterfaceConnection conn;

    public QuestionSide(TextualInterfaceConnection conn)
    {
        this.conn = conn;
    }
    
    
    @Override
    public void sendMessage(String msg)
    {
        try
        {
            conn.writeString("sendMessage");
            conn.writeString(msg);
        } catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String askForString(String question)
    {
        try
        {
            conn.writeString("askForString");
            conn.writeString(question);
            return conn.readString();
        } catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String askForPassword(String question)
    {
        try
        {
            conn.writeString("askForPassword");
            conn.writeString(question);
            return conn.readString();
        } catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public String askForEmail(String question)
    {
        try
        {
            conn.writeString("askForEmail");
            conn.writeString(question);
            return conn.readString();
        } catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int askForInteger(String question)
    {
        try
        {
            conn.writeString("askForInteger");
            conn.writeString(question);
            return conn.readInt();
        } catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int askForInteger(String question, int min, int max)
    {
        try
        {
            conn.writeString("askForIntegerRange");
            conn.writeString(question);
            conn.writeInt(min);
            conn.writeInt(max);
            return conn.readInt();
        } catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public int makeSingleChoice(String question, String[] choices)
    {
        try
        {
            conn.writeString("makeSingleChoice");
            conn.writeString(question);
            conn.writeStringArray(choices);
            return conn.readInt();
        } catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void close() throws IOException
    {
        try
        {
            conn.writeString("close");
            conn.close();
        } catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }
    }
    
}
