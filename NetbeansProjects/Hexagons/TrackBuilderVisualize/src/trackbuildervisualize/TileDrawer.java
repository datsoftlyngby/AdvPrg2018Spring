package trackbuildervisualize;

import app2dapi.geometry.G2D;
import app2dapi.geometry.G2D.Path;
import app2dapi.geometry.G2D.Point2D;
import app2dapi.geometry.G2D.PolygonBuilder;
import app2dapi.geometry.G2D.Transformation2D;
import app2dapi.graphics.Canvas;
import app2dapi.graphics.ColorFactory;
import hexcore.HexCoordinate;
import hexcore.HexCoordinateSystem;
import trackbuilder.TrackTile;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Tobias
 */
public class TileDrawer
{
    private boolean debugView;
    private final ColorFactory cf;
    private final G2D g2d;
    private final HexCoordinateSystem hexSys;
    private final Transformation2D[] rotations;
    private final Point2D[] edgePoints;
    private final Point2D[] cornerPoints;
    private Path straightLeft;
    private Path straightRight;
    private Path endStop;
    private Path curveInner;
    private Path curveOuter;

    public TileDrawer(G2D g2d, ColorFactory cf, HexCoordinateSystem hexSys, float trackWidth)
    {
        this.hexSys = hexSys;
        this.g2d = g2d;
        this.cf = cf;
        HexCoordinate orig = hexSys.getFromAxial(0, 0, 0);
        edgePoints = getEdgePoints(orig);
        cornerPoints = getCornerPoints(orig);
        //Set up rotations...
        rotations = new Transformation2D[6];
        double angle = 0.0;
        double delta = Math.PI / 3;
        for (int i = 0; i < 6; ++i)
        {
            rotations[i] = this.g2d.rotate(angle);
            angle += delta;
        }
        //Build paths
        buildStraight(trackWidth);
        buildCurve(trackWidth);
        buildEndStop(trackWidth);

    }
    
    public void setDebugView(boolean b)
    {
        debugView = b;
    }
    
    public void toggleDebugView()
    {
        debugView = !debugView;
    }

    public void drawTile(Canvas canvas, TrackTile tile, float posX, float posY)
    {
        Transformation2D orig = canvas.getTransformation();
        Transformation2D translate = g2d.translate(posX, posY);
        Transformation2D rotate = rotations[tile.getDirection()];
        Transformation2D text = g2d.combine(orig, translate);
        Transformation2D draw = g2d.combine(text, rotate);
        canvas.setTransformation(draw);
        canvas.setColor(cf.getBlack());
        switch (tile.getTileType())
        {
            case STRAIGHT:
            {
                
                canvas.drawPath(straightLeft);
                canvas.drawPath(straightRight);
                break;
            }
            case CURVE:
            {
                canvas.drawPath(curveInner);
                canvas.drawPath(curveOuter);
                break;
            }
            case SWITCH_LEFT:
            {
                canvas.drawPath(straightLeft);
                canvas.drawPath(straightRight);
                Transformation2D tmp = canvas.getTransformation();
                canvas.setTransformation(g2d.combine(tmp, rotations[4]));
                canvas.drawPath(curveInner);
                canvas.drawPath(curveOuter);
                canvas.setTransformation(tmp);
                break;
            }
            case SWITCH_RIGHT:
            {
                //Right switch
                canvas.drawPath(straightLeft);
                canvas.drawPath(straightRight);
                canvas.drawPath(curveInner);
                canvas.drawPath(curveOuter);
                break;
            }
            case CROSS:
            {
                canvas.drawPath(straightLeft);
                canvas.drawPath(straightRight);
                Transformation2D tmp = canvas.getTransformation();
                canvas.setTransformation(g2d.combine(tmp, rotations[1]));
                canvas.drawPath(straightLeft);
                canvas.drawPath(straightRight);
                canvas.setTransformation(tmp);
                break;
            }
            case ENDSTOP:
            {
                canvas.drawPath(endStop);
                break;
            }
            default:
                throw new AssertionError(tile.getTileType().name());
        }
        
        if(debugView)
        {
            canvas.setTransformation(text);
            int[] entryPoints = tile.getEntryPoints();
            for (int i = 0; i < entryPoints.length; ++i)
            {
                canvas.drawText(edgePoints[entryPoints[i]], "Entry: " + entryPoints[i], 10, true, true);
            }
            HexCoordinate origin = hexSys.getFromAxial(0, 0, 0);
            for (int i = 0; i < 6; ++i)
            {
                HexCoordinate n = origin.getNeighbour(i);
                Point2D nPos = getCenterPoint(n);
                canvas.drawText(nPos, "N: " + i, 10, true, true);
            }
        }
        
        canvas.setTransformation(orig);
        
    }

    private void buildStraight(float trackWidth)
    {
        PolygonBuilder builder = g2d.getPolygonBuilder();
        Point2D aLeft = g2d.interpolate(edgePoints[0], cornerPoints[0], trackWidth);
        Point2D bLeft = g2d.interpolate(edgePoints[3], cornerPoints[4], trackWidth);
        builder.addPoint(aLeft);
        builder.addPoint(bLeft);
        straightLeft = builder.buildPath();

        builder = g2d.getPolygonBuilder();
        Point2D aRight = g2d.interpolate(edgePoints[0], cornerPoints[1], trackWidth);
        Point2D bRight = g2d.interpolate(edgePoints[3], cornerPoints[3], trackWidth);
        builder.addPoint(aRight);
        builder.addPoint(bRight);
        straightRight = builder.buildPath();
    }

    private void buildCurve(double trackWidth)
    {
        double startAngle;
        double endAngle;
        int centerNeighbour;
        switch (hexSys.getColumnRowType())
        {
            case ODD_POINT_TOP:
            case EVEN_POINT_TOP:
                startAngle = g2d.degreesToRadians(270);
                endAngle = g2d.degreesToRadians(330);
                centerNeighbour = 1;
                break;
            case ODD_FLAT_TOP:
            case EVEN_FLAT_TOP:
                startAngle = g2d.degreesToRadians(240);
                endAngle = g2d.degreesToRadians(300);
                centerNeighbour = 0;
                break;
            default:
                throw new RuntimeException("Unknown Column/Row type: " + hexSys.getColumnRowType());
        }
        HexCoordinate orig = hexSys.getFromAxial(0, 0, 0);
        Point2D center = getCenterPoint(orig.getNeighbour(centerNeighbour));
        double invf = 1.0 - trackWidth;
        double minRadius = trackWidth + 1.5 * invf;
        double maxRadius = 1.5 * invf + 2.0 * trackWidth;
        curveInner = g2d.createCircleSegment(center, hexSys.getHexRadius() * minRadius, 32, startAngle, endAngle);
        curveOuter = g2d.createCircleSegment(center, hexSys.getHexRadius() * maxRadius, 32, startAngle, endAngle);
    }
    
    private void buildEndStop(float trackWidth)
    {   
        Point2D aLeft = g2d.interpolate(edgePoints[0], cornerPoints[0], trackWidth);
        Point2D bLeft = g2d.interpolate(edgePoints[3], cornerPoints[4], trackWidth);
        Point2D midLeft = g2d.interpolate(aLeft, bLeft, 0.5);
        
        Point2D aRight = g2d.interpolate(edgePoints[0], cornerPoints[1], trackWidth);
        Point2D bRight = g2d.interpolate(edgePoints[3], cornerPoints[3], trackWidth);
        Point2D midRight = g2d.interpolate(aRight, bRight, 0.5);
        
        PolygonBuilder builder = g2d.getPolygonBuilder();
        builder.addPoint(aLeft);
        builder.addPoint(midLeft);
        builder.addPoint(midRight);
        builder.addPoint(aRight);
        endStop = builder.buildPath();
    }

    private Point2D getCenterPoint(HexCoordinate coord)
    {
        return g2d.newPoint2D(coord.getCenterXPos(), coord.getCenterYPos());
    }

    private Point2D[] getCornerPoints(HexCoordinate coord)
    {
        Point2D[] res = new Point2D[6];
        for (int i = 0; i < 6; ++i)
        {
            res[i] = g2d.newPoint2D(coord.getCornerXPos(i), coord.getCornerYPos(i));
        }
        return res;
    }

    private Point2D[] getEdgePoints(HexCoordinate coord)
    {
        Point2D[] res = new Point2D[6];
        for (int i = 0; i < 6; ++i)
        {
            res[i] = g2d.newPoint2D(coord.getMidEdgeX(i), coord.getMidEdgeY(i));
        }
        return res;
    }
}
