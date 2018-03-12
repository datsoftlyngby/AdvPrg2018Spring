/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.animation.impl;

import app2dapi.animation.Notifiable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tobias Grundtvig
 */
public class WaitForNotify implements Notifiable
{

    private boolean notified = false;
    private final Object monitor;
    
    public WaitForNotify()
    {
        this.monitor = null;
    }

    public WaitForNotify(Object monitor)
    {
        this.monitor = monitor;
    }

    @Override
    public void onNotify()
    {
        if(monitor == null)
        {
            synchronized(this)
            {
                notified = true;
                notifyAll();
            }
        }
        else
        {
            synchronized(monitor)
            {
                notified = true;
                monitor.notifyAll();
            }
        }
    }

    public void waitForNotify()
    {
        if(monitor == null)
        {
            synchronized(this)
            {
                while(!notified)
                {
                    try
                    {
                        wait();
                    }
                    catch(InterruptedException ex)
                    {
                        Logger.getLogger(WaitForNotify.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
        else
        {
            synchronized(monitor)
            {
                while(!notified)
                {
                    try
                    {
                        monitor.wait();
                    }
                    catch(InterruptedException ex)
                    {
                        Logger.getLogger(WaitForNotify.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }
}
