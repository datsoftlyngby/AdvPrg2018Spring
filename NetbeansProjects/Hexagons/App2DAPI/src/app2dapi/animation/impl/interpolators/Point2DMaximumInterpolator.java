/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.animation.impl.interpolators;

import app2dapi.animation.Interpolator;
import app2dapi.geometry.G2D;
import app2dapi.geometry.G2D.Point2D;



/**
 *
 * @author Tobias Grundtvig
 */
public class Point2DMaximumInterpolator implements Interpolator<Point2D>
{
    private final G2D g2d;

    public Point2DMaximumInterpolator(G2D g2d)
    {
        this.g2d = g2d;
    }
    
    @Override
    public Point2D interpolate(Point2D a, Point2D b, double f)
    {
        double distX = b.x() - a.x();
        double distY = b.y() - a.y();
        return g2d.newPoint2D(a.x() + distX * f, a.y() + distY * f);
    }

    @Override
    public double distance(Point2D a, Point2D b)
    {
        return Math.max(Math.abs(b.x() - a.x()), Math.abs(b.y() - a.y()));
    }

}
