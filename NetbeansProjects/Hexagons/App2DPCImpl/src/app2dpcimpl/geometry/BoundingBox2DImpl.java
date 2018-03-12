/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dpcimpl.geometry;

import app2dapi.geometry.G2D.BoundingBox2D;
import app2dapi.geometry.G2D.Point2D;

/**
 *
 * @author Tobias Grundtvig
 */
public class BoundingBox2DImpl implements BoundingBox2D
{
    private final double minX;
    private final double maxX;
    private final double minY;
    private final double maxY;

    public BoundingBox2DImpl(double minX, double maxX, double minY, double maxY)
    {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
    }
    
    @Override
    public double minX()
    {
        return minX;
    }

    @Override
    public double maxX()
    {
        return maxX;
    }

    @Override
    public double minY()
    {
        return minY;
    }

    @Override
    public double maxY()
    {
        return maxY;
    }

    @Override
    public Point2D getLowerLeft()
    {
        return new PVD2DImpl(minX, minY);
    }

    @Override
    public Point2D getLowerRight()
    {
        return new PVD2DImpl(maxX, minY);
    }

    @Override
    public Point2D getUpperLeft()
    {
        return new PVD2DImpl(minX, maxY);
    }

    @Override
    public Point2D getUpperRight()
    {
        return new PVD2DImpl(maxX, maxY);
    }

    @Override
    public Point2D getCenter()
    {
        return new PVD2DImpl((minX + maxX) * 0.5, (maxY - minY) * 0.5);
    }

    @Override
    public double getWidth()
    {
        return maxX - minX;
    }

    @Override
    public double getHeight()
    {
        return maxY - minY;
    }

    @Override
    public boolean contains(Point2D point)
    {
        return contains(point.x(), point.y());
    }
    
    public boolean contains(double x, double y)
    {
        return (x >= minX && x <= maxX && y >= minY && y <= maxY);
    }
    
    @Override
    public boolean contains(BoundingBox2D bbox)
    {
        return (bbox.minX() >= minX && bbox.maxX() <= maxX && bbox.minY() >= minY && bbox.maxY() <= maxY);
    }

    @Override
    public BoundingBox2D getExtendedBoundingBox(Point2D point)
    {
        double newMinX = minX;
        double newMaxX = maxX;
        double newMinY = minY;
        double newMaxY = maxY;
        boolean changed = false;
        if(point.x() < minX)
        {
            newMinX = point.x();
            changed = true;
        }
        else if(point.x() > maxX)
        {
            newMaxX = point.x();
            changed = true;
        }
        if(point.y() < minY)
        {
            newMinY = point.y();
            changed = true;
        }
        else if(point.y() > maxY)
        {
            newMaxY = point.y();
            changed = true;
        }
        if(changed)
        {
            return new BoundingBox2DImpl(newMinX, newMaxX, newMinY, newMaxY);
        }
        return this;
    }
}
