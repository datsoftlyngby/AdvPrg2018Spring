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
import app2dapi.graphics.Canvas;
import app2dapi.graphics.ColorFactory;
import app2dapi.input.mouse.MouseListener;
import app2dapi.input.mouse.MouseMovedEvent;
import app2dapi.input.mouse.MousePressedEvent;
import app2dapi.input.mouse.MouseReleasedEvent;
import app2dapi.input.mouse.MouseWheelEvent;
import app2dapi.viewwindow.ViewWindow;
import app2dapi.viewwindow.impl.ViewWindowImpl;
import app2dpcimpl.PCPlatformImpl;


/**
 *
 * @author Tobias Grundtvig
 */
public class App3 implements App2D, MouseListener
{
    private G2D g2d;
    private ColorFactory cf;
    private Point2D mousePos;
    private ViewWindow view;

    @Override
    public boolean showMouseCursor()
    {
        return true;
    }

    @Override
    public boolean initialize(Device device)
    {
        g2d = device.getGeometry2D();
        cf = device.getScreen().getColorFactory();
        device.getMouse().addMouseListener(this);
        mousePos = g2d.origo();
        this.view = new ViewWindowImpl( g2d,
                                        g2d.newPoint2D(device.getScreen().getPixelWidth()*-0.5, device.getScreen().getPixelHeight()*-0.5),
                                        g2d.newPoint2D(device.getScreen().getPixelWidth()*0.5, device.getScreen().getPixelHeight()*0.5),
                                        device.getScreen().getPixelWidth(),
                                        g2d.origo());
        view.setWindowRotation(0.1);
        return true;
    }

    @Override
    public boolean update(double time)
    {
        view.setHUDMatchPoint(mousePos);
        return true;
    }

    @Override
    public void draw(Canvas canvas)
    {
        canvas.setTransformation(view.getWorldToHUD());
        canvas.clear(cf.getWhite());
        canvas.setColor(cf.getRed());
        canvas.drawPoint(g2d.origo(), 10);
        canvas.setColor(cf.getGreen());
        canvas.drawPoint(g2d.newPoint2D(50, 50), 10);
        //canvas.drawText(mousePos, "Mouse", 30, true, true);
        canvas.setColor(cf.getBlue());
        canvas.drawOutlinedRectangle(g2d.origo(), mousePos);
        canvas.drawOutlinedPolygon(g2d.createArrow(g2d.origo(), mousePos, 5));
        //canvas.drawOutlinedPolygon(g2d.createRectangle(g2d.origo(), mousePos));
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
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Platform p = new PCPlatformImpl();
        App2D app = new App3();
        p.runApplication(app);
    }
    
}
