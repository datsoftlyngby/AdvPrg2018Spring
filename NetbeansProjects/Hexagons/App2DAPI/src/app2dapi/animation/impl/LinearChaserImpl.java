/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.animation.impl;

import app2dapi.animation.Interpolator;
import app2dapi.animation.LinearChaser;
import app2dapi.animation.Notifiable;

/**
 *
 * @author Tobias Grundtvig
 * @param <E>
 */
public class LinearChaserImpl<E> implements LinearChaser<E>
{

    private final Interpolator<E> interpolator;
    private Notifiable goalListener;
    private E goal;
    private E current;
    private double speed;
    private double curTime;
    private boolean updated;
    private boolean atGoal;

    public LinearChaserImpl(Interpolator<E> interpolator, double speed)
    {
        this.interpolator = interpolator;
        this.speed = speed;
        this.atGoal = false;
        this.goalListener = null;
    }
    
    @Override
    public synchronized void setDuration(double duration)
    {
        if(duration < 0.00001)
        {
            current = goal;
            speed = 1;
        }
        else
        {
            double dist = interpolator.distance(current, goal);
            speed = dist / duration;
        }
    }
    
    @Override
    public synchronized void setSpeed(double speed)
    {
        this.speed = speed;
    }
    
    @Override
    public synchronized double getSpeed()
    {
        return speed;
    }

    @Override
    public synchronized void setCurrent(E current)
    {
        this.current = current;
        atGoal = false;
    }

    @Override
    public synchronized void setGoal(E goal)
    {
        this.goal = goal;
        atGoal = false;
    }

    @Override
    public synchronized E getCurrent()
    {
        return current;
    }

    @Override
    public synchronized E getGoal()
    {
        return goal;
    }

    @Override
    public synchronized boolean atGoal()
    {
        return atGoal;
    }

    @Override
    public synchronized double timeToGoal()
    {
        double dist = interpolator.distance(current, goal);
        return dist / speed;
    }

    @Override
    public synchronized void update(double time)
    {
        if(updated)
        {
            double dt = time - curTime;
            double ds = dt * speed;
            double dist = interpolator.distance(current, goal);
            if(ds >= dist)
            {
                atGoal = true;
                current = goal;
                if(goalListener != null)
                {
                    goalListener.onNotify();
                }
            }
            else
            {
                current = interpolator.interpolate(current, goal, ds / dist);
            }
        }
        curTime = time;
        updated = true;
    }

    @Override
    public synchronized void setGoalListener(Notifiable listener)
    {
        goalListener = listener;
    }

}
