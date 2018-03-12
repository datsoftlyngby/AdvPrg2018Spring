/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.animation.impl.interpolators;

import app2dapi.animation.Interpolator;



/**
 *
 * @author Tobias Grundtvig
 */
public class DoubleInterpolator implements Interpolator<Double>
{

    @Override
    public Double interpolate(Double a, Double b, double f)
    {
        double dist = b - a;
        return a + dist * f;
    }

    @Override
    public double distance(Double a, Double b)
    {
        return Math.abs(b - a);
    }
    
}
