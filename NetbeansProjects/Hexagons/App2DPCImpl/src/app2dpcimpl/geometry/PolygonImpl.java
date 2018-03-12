/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dpcimpl.geometry;

import app2dapi.geometry.G2D;
import app2dapi.geometry.G2D.BoundingBox2D;
import app2dapi.geometry.G2D.Point2D;
import app2dapi.geometry.G2D.Polygon;
import java.awt.Shape;
import java.awt.geom.Path2D;
import java.util.Iterator;

/**
 *
 * @author tog
 */
public class PolygonImpl implements Polygon
{

    private final Point2D[] points;
    private final Path2D.Float path;
    private BoundingBox2D bbox;

    public PolygonImpl(Point2D[] points, boolean close)
    {
        this.points = points;
        
        path = new Path2D.Float(Path2D.WIND_NON_ZERO, points.length + 1);
        path.moveTo(points[0].x(), points[0].y());
        for(int i = 1; i < points.length; ++i)
        {
            path.lineTo(points[i].x(), points[i].y());
        }
        if(close)
        {
            path.closePath();
        }
        
        this.bbox = null;
    }

    public Shape getShape()
    {
        return path;
    }

    @Override
    public int numberOfPoints()
    {
        return points.length;
    }

    @Override
    public G2D.Point2D getPoint(int index)
    {
        return points[index];
    }

    @Override
    public Iterator<Point2D> iterator()
    {
        return new PolygonIterator();
    }

    @Override
    public boolean contains(Point2D point)
    {
        return path.contains(point.x(), point.y());
    }

    @Override
    public BoundingBox2D getBoundingBox()
    {
        if(bbox == null)
        {
            double minX = points[0].x();
            double maxX = minX;
            double minY = points[0].y();
            double maxY = minY;
            for(int i = 1; i < points.length; ++i)
            {
                double x = points[i].x();
                double y = points[i].y();
                if(x < minX)
                {
                    minX = x;
                }
                else if(x > maxX)
                {
                    maxX = x;
                }
                if(y < minY)
                {
                    minY = y;
                }
                else if(y > maxY)
                {
                    maxY = y;
                }
            }
            this.bbox = new BoundingBox2DImpl(minX, maxX, minY, maxY);
        }
        return bbox;
    }

    private class PolygonIterator implements Iterator<Point2D>
    {

        private int pos;

        public PolygonIterator()
        {
            pos = 0;
        }

        @Override
        public boolean hasNext()
        {
            return pos < points.length;
        }

        @Override
        public Point2D next()
        {
            return points[pos++];
        }

        @Override
        public void remove()
        {
            throw new UnsupportedOperationException("Remove is not supported!");
        }
    }
}
