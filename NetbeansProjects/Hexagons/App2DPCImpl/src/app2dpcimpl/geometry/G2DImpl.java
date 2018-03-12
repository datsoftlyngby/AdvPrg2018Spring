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

import app2dapi.geometry.AbstractG2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;

/**
 *
 * @author tog
 */
public class G2DImpl extends AbstractG2D
{
    private final static Transformation2D IDENTITY = new Transformation2DImpl(new AffineTransform(1,0,0,1,0,0));
    private final static Transformation2D FLIPX = new Transformation2DImpl(new AffineTransform(-1,0,0,1,0,0));
    private final static Transformation2D FLIPY = new Transformation2DImpl(new AffineTransform(1,0,0,-1,0,0));
    
    @Override
    public Point2D newPoint2D(double x, double y)
    {
        return new PVD2DImpl(x, y);
    }

    @Override
    public Vector2D newVector2D(double x, double y)
    {
        return new PVD2DImpl(x, y);
    }
    
    @Override
    public Dimension2D newDimension2D(double width, double height)
    {
        return new PVD2DImpl(width, height);
    }
    
     @Override
    public Point2D asPoint2D(Vector2D v)
    {
        return (PVD2DImpl) v;
    }

    @Override
    public Vector2D asVector2D(Point2D p)
    {
        return (PVD2DImpl) p;
    }
    
    @Override
    public Transformation2D identity()
    {
        return IDENTITY;
    }

    @Override
    public Transformation2D scale(double sx, double sy)
    {
        AffineTransform t = new AffineTransform(sx, 0, 0, sy, 0, 0);
        return new Transformation2DImpl(t);
    }

    @Override
    public Transformation2D translate(double tx, double ty)
    {
        AffineTransform t = new AffineTransform(1, 0, 0, 1, tx, ty);
        return new Transformation2DImpl(t);
    }

    @Override
    public Transformation2D rotate(double angle)
    {
        return new Transformation2DImpl(AffineTransform.getRotateInstance(angle));
    }
    
    @Override
    public Transformation2D rotate(double angle, Point2D pivot)
    {
        AffineTransform t = AffineTransform.getRotateInstance(angle, pivot.x(), pivot.y());
        return new Transformation2DImpl(t);
    }
    
    @Override
    public Transformation2D flipX()
    {
        return FLIPX;
    }

    @Override
    public Transformation2D flipY()
    {
        return FLIPY;
    }

    @Override
    public Transformation2D inverse(Transformation2D t)
    {
        try
        {
            AffineTransform res = ((Transformation2DImpl) t).getJavaTransform().createInverse();
            return new Transformation2DImpl(res);
        } catch (NoninvertibleTransformException ex)
        {
            throw new IllegalArgumentException("Transformation can not be inverted!", ex);
        }
    }

    @Override
    public Transformation2D combine(Transformation2D a, Transformation2D b, Transformation2D... rest)
    {
        AffineTransform res = new AffineTransform(((Transformation2DImpl) a).getJavaTransform());
        res.concatenate(((Transformation2DImpl) b).getJavaTransform());
        for(Transformation2D t : rest)
        {
            res.concatenate(((Transformation2DImpl) t).getJavaTransform());
        }
        return new Transformation2DImpl(res);
    }

    @Override
    public PolygonBuilder getPolygonBuilder()
    {
        return new PolygonBuilderImpl();
    }

    @Override
    public Polygon createPolygon(Point2D[] points)
    {
        return doCreate(points, true);
    }
    
    @Override
    public Polygon createPath(Point2D[] points)
    {
        return doCreate(points, true);
    }
    
    private PolygonImpl doCreate(Point2D[] points, boolean close)
    {
        //Defensive copy...
        Point2D[] copy = new Point2D[points.length];
        for(int i = 0; i < points.length; ++i)
        {
            copy[i] = points[i];
        }
        return new PolygonImpl(copy, close);
    }

    
}
