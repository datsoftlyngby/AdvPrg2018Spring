/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.geometry;

/**
 *
 * @author tog
 */
public abstract class AbstractG2D implements G2D
{
    private final double FULL_CIRCLE = Math.PI * 2;
    private final double DEGREES_TO_RADIANS = FULL_CIRCLE / 360.0;
    private final double RADIANS_TO_DEGREES = 360.0 / FULL_CIRCLE;
    private final Point2D ORIGO = newPoint2D(0, 0);
    private final Vector2D ZEROVECTOR = newVector2D(0, 0);
    private final Vector2D UNITXVECTOR = newVector2D(1, 0);
    private final Vector2D UNITYVECTOR = newVector2D(0, 1);

    @Override
    public abstract Point2D newPoint2D(double x, double y);

    @Override
    public abstract Vector2D newVector2D(double x, double y);

    @Override
    public abstract Point2D asPoint2D(Vector2D v);

    @Override
    public abstract Vector2D asVector2D(Point2D p);

    @Override
    public abstract Transformation2D scale(double sx, double sy);

    @Override
    public abstract Transformation2D translate(double tx, double ty);

    @Override
    public abstract Transformation2D rotate(double angle);

    @Override
    public abstract Transformation2D inverse(Transformation2D t);

    @Override
    public abstract Transformation2D combine(Transformation2D a, Transformation2D b, Transformation2D... rest);

    @Override
    public abstract PolygonBuilder getPolygonBuilder();
    
    @Override
    public double degreesToRadians(double degrees)
    {
        return degrees * DEGREES_TO_RADIANS;
    }
            
    @Override
    public double radiansToDegrees(double radians)
    {
        return radians * RADIANS_TO_DEGREES;
    }

    @Override
    public Point2D origo()
    {
        return ORIGO;
    }

    @Override
    public Vector2D zeroVector2D()
    {
        return ZEROVECTOR;
    }

    @Override
    public Vector2D unitXVector()
    {
        return UNITXVECTOR;
    }

    @Override
    public Vector2D unitYVector()
    {
        return UNITYVECTOR;
    }

    @Override
    public Transformation2D scale(Vector2D s)
    {
        return scale(s.x(), s.y());
    }

    @Override
    public Transformation2D translate(Vector2D t)
    {
        return translate(t.x(), t.y());
    }

    @Override
    public Transformation2D translateOrigoToPoint(Point2D point)
    {
        return translate(point.x(), point.y());
    }

    @Override
    public Transformation2D translatePointToOrigo(Point2D point)
    {
        return translate(-point.x(), -point.y());
    }

    @Override
    public Transformation2D translateAToB(Point2D a, Point2D b)
    {
        return translate(b.x() - a.x(), b.y() - a.y());
    }

    @Override
    public double angle(Vector2D a, Vector2D b)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public double distance(Point2D a, Point2D b)
    {
        double dx = b.x() - a.x();
        double dy = b.y() - a.y();
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public double length(Vector2D v)
    {
        return Math.sqrt(sqrLength(v));
    }

    @Override
    public double sqrLength(Vector2D v)
    {
        double x = v.x();
        double y = v.y();
        return x * x + y * y;
    }

    @Override
    public Vector2D normalized(Vector2D v)
    {
        return times(v, 1.0f / length(v));
    }

    @Override
    public Vector2D inverse(Vector2D v)
    {
        return newVector2D(-v.x(), -v.y());
    }

    @Override
    public double dot(Vector2D a, Vector2D b)
    {
        return a.x() * b.x() + a.y() * b.y();
    }

    @Override
    public Vector2D projection(Vector2D a, Vector2D b)
    {
        Vector2D unitB = normalized(b);
        double f = dot(a, unitB);
        return times(a, f);
    }

    @Override
    public Vector2D rejection(Vector2D a, Vector2D b)
    {
        return subtract(a, projection(a, b));
    }

    @Override
    public Vector2D add(Vector2D a, Vector2D b)
    {
        return newVector2D(a.x() + b.x(), a.y() + b.y());
    }

    @Override
    public Vector2D subtract(Vector2D a, Vector2D b)
    {
        return newVector2D(a.x() - b.x(), a.y() - b.y());
    }

    @Override
    public Point2D subtract(Point2D p, Vector2D v)
    {
        return newPoint2D(p.x() - v.x(), p.y() - v.y());
    }

    @Override
    public Vector2D fromTo(Point2D a, Point2D b)
    {
        return newVector2D(b.x() - a.x(), b.y() - a.y());
    }

    @Override
    public Point2D add(Point2D p, Vector2D v)
    {
        return newPoint2D(p.x() + v.x(), p.y() + v.y());
    }

    @Override
    public Vector2D times(Vector2D v, double s)
    {
        return newVector2D(v.x() * s, v.y() * s);
    }

    @Override
    public Point2D center(Point2D a, Point2D b)
    {
        return interpolate(a, b, 0.5);
    }
    
    @Override
    public Point2D interpolate(Point2D a, Point2D b, double f)
    {
        double invf = 1.0f - f;
        return newPoint2D(a.x() * invf + b.x() * f, a.y() * invf + b.y() * f);
    }

    @Override
    public Transformation2D match(Point2D p1Src, Point2D p2Src, Point2D p1Dst, Point2D p2Dst)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Polygon createRectangle(Point2D lowerLeft, Point2D upperRight)
    {
        Point2D lowerRight = newPoint2D(upperRight.x(), lowerLeft.y());
        Point2D upperLeft = newPoint2D(lowerLeft.x(), upperRight.y());
        PolygonBuilder bld = getPolygonBuilder();
        bld.addPoint(lowerLeft);
        bld.addPoint(lowerRight);
        bld.addPoint(upperRight);
        bld.addPoint(upperLeft);
        return bld.buildPolygon();
    }

    @Override
    public Polygon createRectangle(Point2D center, double width, double height)
    {
        PolygonBuilder bld = getPolygonBuilder();
        double halfWidth = width * 0.5f;
        double halfHeight = height * 0.5f;
        double minX = center.x() - halfWidth;
        double maxX = center.x() + halfWidth;
        double minY = center.y() - halfHeight;
        double maxY = center.y() + halfHeight;
        bld.addPoint(newPoint2D(minX, minY));
        bld.addPoint(newPoint2D(maxX, minY));
        bld.addPoint(newPoint2D(maxX, maxY));
        bld.addPoint(newPoint2D(minX, maxY));
        return bld.buildPolygon();
    }

    @Override
    public Polygon createCircle(Point2D center, double radius, int segments)
    {
        if(segments < 3)
        {
            throw new RuntimeException("There must be at least 3 segments! There are only " + segments + " segments!");
        }
        PolygonBuilder res = getPolygonBuilder();
        double angle = FULL_CIRCLE / segments;
        Transformation2D r = rotate(angle);
        Vector2D v = newVector2D(radius, 0);
        res.addPoint(add(center, v));
        for(int i = 1; i < segments; ++i)
        {
            v = r.transform(v);
            res.addPoint(add(center, v));
        }
        return res.buildPolygon();
    }

    @Override
    public Polygon createArrow(Point2D begin, Point2D end, double width)
    {
        PolygonBuilder res = getPolygonBuilder();
        Vector2D vx = fromTo(begin, end);
        double xLength = vx.length();
        Vector2D unitX = times(vx, 1.0f / xLength);
        Vector2D unitY = newVector2D(-unitX.y(), unitX.x());
        Vector2D l = times(unitX, xLength - width);
        Vector2D h = times(unitY, width * 0.5f);
        Point2D p = subtract(begin, h);
        res.addPoint(p);
        p = add(p, l);
        res.addPoint(p);
        p = subtract(p, h);
        res.addPoint(p);
        res.addPoint(end);
        p = add(p, times(h, 4));
        res.addPoint(p);
        p = subtract(p, h);
        res.addPoint(p);
        p = subtract(p, l);
        res.addPoint(p);
        return res.buildPolygon();
    }

    @Override
    public Polygon createLine(Point2D begin, Point2D end, double width)
    {
        PolygonBuilder res = getPolygonBuilder();
        Vector2D vx = fromTo(begin, end);
        double xLength = vx.length();
        Vector2D unitX = times(vx, 1.0f / xLength);
        Vector2D unitY = newVector2D(-unitX.y(), unitX.x());
        Vector2D l = times(unitX, xLength);
        Vector2D h = times(unitY, width);
        Point2D p = subtract(begin, times(h, 0.5f));
        res.addPoint(p);
        p = add(p, l);
        res.addPoint(p);
        p = add(p, h);
        res.addPoint(p);
        p = subtract(p, l);
        res.addPoint(p);
        return res.buildPolygon();
    }

    @Override
    public Polygon createDoubleArrow(Point2D begin, Point2D end, double width)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
    @Override
    public Path createCircleSegment(Point2D center, double radius, int segments, double startAngle, double endAngle)
    {
        if(segments < 1)
        {
            throw new RuntimeException("There must be at least 1 segments! There are only " + segments + " segments!");
        }
        PolygonBuilder res = getPolygonBuilder();
        double dAngle = (startAngle > endAngle) ? (FULL_CIRCLE - startAngle + endAngle) : endAngle - startAngle;
        if(dAngle >= FULL_CIRCLE)
        {
            return createCircle(center, radius, segments);
        }
        Transformation2D r = rotate(dAngle / segments);
        Vector2D v = newVector2D(Math.cos(startAngle)*radius, Math.sin(startAngle)*radius);
        res.addPoint(add(center, v));
        for(int i = 0; i < segments; ++i)
        {
            v = r.transform(v);
            res.addPoint(add(center, v));
        }
        return res.buildPath();
    }

}
