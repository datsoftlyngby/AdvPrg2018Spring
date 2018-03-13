/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapitests;

import app2dapi.App2D;
import app2dapi.Device;
import app2dapi.Platform;
import app2dapi.geometry.G2D;
import app2dapi.geometry.G2D.Point2D;
import app2dapi.geometry.G2D.Polygon;
import app2dapi.geometry.G2D.PolygonBuilder;
import app2dapi.graphics.Canvas;
import app2dapi.graphics.ColorFactory;
import app2dapi.input.mouse.MouseListener;
import app2dapi.input.mouse.MouseMovedEvent;
import app2dapi.input.mouse.MousePressedEvent;
import app2dapi.input.mouse.MouseReleasedEvent;
import app2dapi.input.mouse.MouseWheelEvent;
import app2dpcimpl.PCPlatformImpl;

/**
 *
 * @author Tobias Grundtvig
 */
public class App2 implements App2D, MouseListener
{
    private G2D g2d;
    private ColorFactory cf;
    private G2D.Point2D mousePos;
    private float scale;
    private Point2D[] roomPoints;
    private Polygon[] roomPolygons;
    
    

    @Override
    public boolean initialize(Device device)
    {
        g2d = device.getGeometry2D();
        cf = device.getScreen().getColorFactory();
        mousePos = g2d.origo();
        device.getMouse().addMouseListener(this);
        scale = 1.0f;
        roomPoints = new Point2D[20];
        //NW
        roomPoints[0] = g2d.newPoint2D(0, 0);
        roomPoints[1] = g2d.newPoint2D(30, 0);
        roomPoints[2] = g2d.newPoint2D(30, 10);
        roomPoints[3] = g2d.newPoint2D(10, 30);
        roomPoints[4] = g2d.newPoint2D(0, 30);
        //NE
        roomPoints[5] = g2d.newPoint2D(100, 0);
        roomPoints[6] = g2d.newPoint2D(100, 30);
        roomPoints[7] = g2d.newPoint2D(90, 30);
        roomPoints[8] = g2d.newPoint2D(70, 10);
        roomPoints[9] = g2d.newPoint2D(70, 0);
        //SE
        roomPoints[10] = g2d.newPoint2D(100, 100);
        roomPoints[11] = g2d.newPoint2D(70, 100);
        roomPoints[12] = g2d.newPoint2D(70, 90);
        roomPoints[13] = g2d.newPoint2D(90, 70);
        roomPoints[14] = g2d.newPoint2D(100, 70);
        //SW
        roomPoints[15] = g2d.newPoint2D(0, 100);
        roomPoints[16] = g2d.newPoint2D(0, 70);
        roomPoints[17] = g2d.newPoint2D(10, 70);
        roomPoints[18] = g2d.newPoint2D(30, 90);
        roomPoints[19] = g2d.newPoint2D(30, 100);
        
        roomPolygons = new Polygon[8];
        //N
        PolygonBuilder tmp = g2d.getPolygonBuilder();
        tmp.addPoint(roomPoints[1]);
        tmp.addPoint(roomPoints[9]);
        tmp.addPoint(roomPoints[8]);
        tmp.addPoint(roomPoints[2]);
        roomPolygons[0] = tmp.buildPolygon();
        //NE
        tmp = g2d.getPolygonBuilder();
        tmp.addPoint(roomPoints[5]);
        tmp.addPoint(roomPoints[6]);
        tmp.addPoint(roomPoints[7]);
        tmp.addPoint(roomPoints[8]);
        tmp.addPoint(roomPoints[9]);
        roomPolygons[1] = tmp.buildPolygon();
        //E
        tmp = g2d.getPolygonBuilder();
        tmp.addPoint(roomPoints[6]);
        tmp.addPoint(roomPoints[14]);
        tmp.addPoint(roomPoints[13]);
        tmp.addPoint(roomPoints[7]);
        roomPolygons[2] = tmp.buildPolygon();
        //SE
        tmp = g2d.getPolygonBuilder();
        tmp.addPoint(roomPoints[10]);
        tmp.addPoint(roomPoints[11]);
        tmp.addPoint(roomPoints[12]);
        tmp.addPoint(roomPoints[13]);
        tmp.addPoint(roomPoints[14]);
        roomPolygons[3] = tmp.buildPolygon();
        //S
        tmp = g2d.getPolygonBuilder();
        tmp.addPoint(roomPoints[11]);
        tmp.addPoint(roomPoints[19]);
        tmp.addPoint(roomPoints[18]);
        tmp.addPoint(roomPoints[12]);
        roomPolygons[4] = tmp.buildPolygon();
        //SW
        tmp = g2d.getPolygonBuilder();
        tmp.addPoint(roomPoints[15]);
        tmp.addPoint(roomPoints[16]);
        tmp.addPoint(roomPoints[17]);
        tmp.addPoint(roomPoints[18]);
        tmp.addPoint(roomPoints[19]);
        roomPolygons[5] = tmp.buildPolygon();
        //W
        tmp = g2d.getPolygonBuilder();
        tmp.addPoint(roomPoints[4]);
        tmp.addPoint(roomPoints[3]);
        tmp.addPoint(roomPoints[17]);
        tmp.addPoint(roomPoints[16]);
        roomPolygons[6] = tmp.buildPolygon();
        //NW
        tmp = g2d.getPolygonBuilder();
        tmp.addPoint(roomPoints[0]);
        tmp.addPoint(roomPoints[1]);
        tmp.addPoint(roomPoints[2]);
        tmp.addPoint(roomPoints[3]);
        tmp.addPoint(roomPoints[4]);
        roomPolygons[7] = tmp.buildPolygon();
        
        
        
        
        return true;
    }
    
    @Override
    public boolean showMouseCursor()
    {
        return false;
    }

    @Override
    public boolean update(double time)
    {
        return true;
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.clear(cf.getWhite());
        canvas.setColor(cf.getBlack());
        G2D.Transformation2D s = g2d.scale(scale, scale);
        for(int y = 0; y < 5000; y+=100)
        {
            for(int x = 0; x < 5000; x+=100)
            {
                G2D.Transformation2D trans = g2d.translate(x, y);
                canvas.setTransformation(g2d.combine(s, trans));
                /*for(Polygon p : roomPolygons)
                {
                    canvas.drawFilledPolygon(p);
                }
                */
                canvas.drawFilledPolygon(roomPolygons[1]);
                canvas.drawFilledPolygon(roomPolygons[3]);
                canvas.drawFilledPolygon(roomPolygons[5]);
                canvas.drawFilledPolygon(roomPolygons[7]);
            }
        }
    }

    @Override
    public void destroy()
    {
        
    }

    @Override
    public void onMouseMoved(MouseMovedEvent e)
    {
        mousePos = g2d.newPoint2D(e.getX(), e.getY());
    }

    @Override
    public void onMousePressed(MousePressedEvent e)
    {
        
    }

    @Override
    public void onMouseReleased(MouseReleasedEvent e)
    {
        
    }

    @Override
    public void onMouseWheel(MouseWheelEvent e)
    {
        int clicks = e.getNumberOfClicks();
        if(clicks > 0)
        {
            for(int i = 0; i < clicks; ++i)
            {
                scale /= 1.05f;
            }
        }
        else
        {
            clicks = -clicks;
            for(int i = 0; i < clicks; ++i)
            {
                scale *= 1.05f;
            }
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Platform p = new PCPlatformImpl();
        App2D app = new App2();
        p.runApplication(app);
    }
    
}
