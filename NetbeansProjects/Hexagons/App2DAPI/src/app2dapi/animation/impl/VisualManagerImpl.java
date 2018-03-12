/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.animation.impl;

import app2dapi.animation.Visual;
import app2dapi.animation.VisualManager;
import java.util.ArrayList;


/**
 *
 * @author Tobias Grundtvig
 */
public class VisualManagerImpl<CANVAS> implements VisualManager<CANVAS>
{
    private final boolean keepAlive;
    private boolean isStarted;
    private double curTime;
    private ArrayList<Visual> visuals;

    public VisualManagerImpl(boolean keepAlive)
    {
        this.keepAlive = keepAlive;
        isStarted = false;
        visuals = new ArrayList<>();
    }
    
    @Override
    public synchronized void addVisual(Visual visual)
    {
        if(isStarted)
        {
            visual.start(curTime);
        }
        visuals.add(visual);
    }

    @Override
    public synchronized void start(double time)
    {
        curTime = time;
        for(Visual visual : visuals)
        {
            visual.start(time);
        }
        isStarted = true;
    }

    @Override
    public synchronized boolean isFinished()
    {
        return !keepAlive && visuals.isEmpty();
    }

    @Override
    public synchronized void update(double time)
    {
        curTime = time;
        boolean clean = false;
        for(Visual visual : visuals)
        {
            visual.update(time);
            if(visual.isFinished())
            {
                clean = true;
            }
        }
        if(clean)
        {
            ArrayList<Visual> tmp = new ArrayList<>(visuals.size());
            for(Visual visual : visuals)
            {
                if(!visual.isFinished())
                {
                    tmp.add(visual);
                }
            }
            visuals = tmp;
        }
    }

    @Override
    public synchronized void draw(CANVAS canvas)
    {
        for(Visual visual : visuals)
        {
            visual.draw(canvas);
        }
    }
    
}
