/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dpcimpl.geometry;

import app2dapi.geometry.G2D;
import app2dapi.geometry.G2D.PolygonBuilder;
import java.util.ArrayList;

/**
 *
 * @author tog
 */
public class PolygonBuilderImpl implements PolygonBuilder
{
    private ArrayList<G2D.Point2D> points;

    public PolygonBuilderImpl()
    {
        points = new ArrayList<>();
    }

    @Override
    public void addPoint(G2D.Point2D p)
    {
        points.add(p);
    }

    @Override
    public G2D.Polygon buildPolygon()
    {
        return doBuild(true);
    }
    
    @Override
    public G2D.Polygon buildPath()
    {
        return doBuild(false);
    }
    
    private PolygonImpl doBuild(boolean close)
    {
        G2D.Point2D[] res = new G2D.Point2D[points.size()];
        for (int i = 0; i < points.size(); ++i)
        {
            res[i] = points.get(i);
        }
        return new PolygonImpl(res, close);
    }

    
}
