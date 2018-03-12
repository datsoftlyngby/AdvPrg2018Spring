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
public class DoubleCircularInterpolation implements Interpolator<Double>
{
    private final double min;
    private final double max;

    public DoubleCircularInterpolation(double min, double max)
    {
        this.min = min;
        this.max = max;
    }
    
    public DoubleCircularInterpolation(double max)
    {
        this(0.0, max);
    }
    
    @Override
    public Double interpolate(Double a, Double b, double f)
    {
        if(a < min || a > max || b < min || b > max)
        {
            throw new RuntimeException("Circular interpolator out of range");
        }
        if(a < b)
        {
            double minToA = a - min;
            double bToMax = max - b;
            double distCW = b - a;
            double distCCW = minToA + bToMax;
            
            if(distCW <= distCCW)
            {
                return a + f * distCW;
            }
            else
            {
                double dist = f * distCCW;
                if(dist <= minToA)
                {
                    return a - dist;
                }
                else
                {
                    dist -= minToA;
                    return max - dist;
                }
            }
        }
        else if(b < a)
        {
            double minToB = b - min;
            double aToMax = max - a;
            double distCW = minToB + aToMax;
            double distCCW = a - b;
            if(distCW <= distCCW)
            {
                double dist = f * distCW;
                if(dist <= aToMax)
                {
                    return a + dist;
                }
                else
                {
                    dist -= aToMax;
                    return min + dist;
                }
            }
            else
            {
                return a - f * distCCW;
            }
        }
        else
        {
            return a;
        }
    }

    @Override
    public double distance(Double a, Double b)
    {
        if(a < min || a > max || b < min || b > max)
        {
            throw new RuntimeException("Circular interpolator out of range");
        }
        if(a < b)
        {
            double distCW = b - a;
            double distCCW = max - b + a - min;
            return Math.min(distCW, distCCW);
        }
        else
        {
            double distCW = max - a + b - min;
            double distCCW = a - b;
            return Math.min(distCW, distCCW);
        }
    }
    
}
