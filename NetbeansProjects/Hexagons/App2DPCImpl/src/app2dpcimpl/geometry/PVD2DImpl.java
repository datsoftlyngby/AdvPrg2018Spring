/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dpcimpl.geometry;

import app2dapi.geometry.G2D.Dimension2D;
import app2dapi.geometry.G2D.Point2D;
import app2dapi.geometry.G2D.Vector2D;

/**
 *
 * @author tog
 */
public class PVD2DImpl implements Point2D, Vector2D, Dimension2D
{

    private final double x;
    private final double y;

    public PVD2DImpl(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    @Override
    public double x()
    {
        return x;
    }

    @Override
    public double y()
    {
        return y;
    }

    @Override
    public double sqrLength()
    {
        return x * x + y * y;
    }

    @Override
    public double length()
    {
        return Math.sqrt(sqrLength());
    }

    @Override
    public double width()
    {
        return x;
    }

    @Override
    public double height()
    {
        return y;
    }
}
