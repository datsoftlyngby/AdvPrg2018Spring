/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package app2dpcimpl.geometry;

import app2dapi.geometry.G2D.BoundingBox2D;
import app2dapi.geometry.G2D.Point2D;
import app2dapi.geometry.G2D.Transformation2D;
import app2dapi.geometry.G2D.Vector2D;
import java.awt.geom.AffineTransform;

/**
 *
 * @author tog
 */
public class Transformation2DImpl implements Transformation2D
{
    private final AffineTransform trans;

        public Transformation2DImpl(AffineTransform trans)
        {
            this.trans = trans;
        }
        
        
        public AffineTransform getJavaTransform()
        {
            return trans;
        }
        
        @Override
        public Point2D transform(Point2D p)
        {
            java.awt.geom.Point2D.Double jp = new java.awt.geom.Point2D.Double(p.x(), p.y());
            trans.transform(jp, jp);
            return new PVD2DImpl(jp.x, jp.y);
        }

        @Override
        public Vector2D transform(Vector2D v)
        {
            java.awt.geom.Point2D.Double jp = new java.awt.geom.Point2D.Double(v.x(), v.y());
            trans.deltaTransform(jp, jp);
            return new PVD2DImpl(jp.x, jp.y);
        }

    @Override
    public BoundingBox2D transform(BoundingBox2D boundingBox)
    {
        Point2D ll = transform(boundingBox.getLowerLeft());
        double minX = ll.x();
        double maxX = minX;
        double minY = ll.y();
        double maxY = minY;
        BoundingBox2D res = new BoundingBox2DImpl(minX, maxX, minY, maxY);
        res = res.getExtendedBoundingBox(transform(boundingBox.getLowerRight()));
        res = res.getExtendedBoundingBox(transform(boundingBox.getUpperLeft()));
        res = res.getExtendedBoundingBox(transform(boundingBox.getUpperRight()));
        return res;
    }
}
