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
public class AnswerSide implements Runnable
{

    private final TextualInterfaceConnection conn;
    private final TextualInterface ti;

    public AnswerSide(TextualInterfaceConnection conn, TextualInterface ti)
    {
        this.conn = conn;
        this.ti = ti;
    }

    @Override
    public void run()
    {

        try
        {
            while (true)
            {
                String method = conn.readString();
                switch(method)
                {
                    case "sendMessage":
                    {
                        String msg = conn.readString();
                        ti.sendMessage(msg);
                        break;
                    }
                    case "askForString":
                    {
                        String question = conn.readString();
                        String res = ti.askForString(question);
                        conn.writeString(res);
                        break;
                    }
                    case "askForPassword":
                    {
                        String question = conn.readString();
                        String res = ti.askForPassword(question);
                        conn.writeString(res);
                        break;
                    }
                    case "askForEmail":
                    {
                        String question = conn.readString();
                        String res = ti.askForEmail(question);
                        conn.writeString(res);
                        break;
                    }
                    case "askForInteger":
                    {
                        String question = conn.readString();
                        int res = ti.askForInteger(question);
                        conn.writeInt(res);
                        break;
                    }
                    case "askForIntegerRange":
                    {
                        String question = conn.readString();
                        int min = conn.readInt();
                        int max = conn.readInt();
                        int res = ti.askForInteger(question, min, max);
                        conn.writeInt(res);
                        break;
                    }
                    case "makeSingleChoice":
                    {
                        String question = conn.readString();
                        String[] choices = conn.readStringArray();
                        int res = ti.makeSingleChoice(question, choices);
                        conn.writeInt(res);
                        break;
                    }
                    case "close":
                    {
                        ti.close();
                        conn.close();
                        return;
                    }
                    default:
                        throw new RuntimeException("Unknown method: " + method);
                }
            }
        } catch (IOException ex)
        {
            throw new RuntimeException(ex);
        }

    }

}
