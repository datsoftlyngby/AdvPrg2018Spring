/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.animation;

/**
 *
 * @author Tobias Grundtvig
 * @param <E>
 */
public interface Chaser<E> extends AnimatedValue<E>, Updatable
{
    public void setCurrent(E current);
    public void setGoal(E goal);
    public E getGoal();
    public boolean atGoal();
    public double timeToGoal();
    public void setGoalListener(Notifiable listener);
}
