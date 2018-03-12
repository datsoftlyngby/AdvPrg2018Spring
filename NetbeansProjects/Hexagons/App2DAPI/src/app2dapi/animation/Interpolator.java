/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.animation;

/**
 *
 * @author Tobias Grundtvig
 */
public interface Interpolator<E>
{
    public E interpolate(E a, E b, double f);
    public double distance(E a, E b);
}
