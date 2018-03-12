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
public interface LinearChaser<E> extends Chaser<E>
{
    public void setDuration(double duration);
    public void setSpeed(double speed);
    public double getSpeed();
}
