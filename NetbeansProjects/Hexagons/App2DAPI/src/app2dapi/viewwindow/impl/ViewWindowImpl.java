/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.viewwindow.impl;

import app2dapi.geometry.G2D;
import app2dapi.geometry.G2D.Dimension2D;
import app2dapi.geometry.G2D.Point2D;
import app2dapi.geometry.G2D.Transformation2D;
import app2dapi.viewwindow.ViewWindow;

/**
 *
 * @author Tobias Grundtvig
 */
public class ViewWindowImpl implements ViewWindow
{
    private final G2D g2d;
    private boolean keepAspectRatio;
    private double aspectRatio;
    private Dimension2D sizeHUD;
    private Point2D hudMax;
    private Point2D hudMin;
    private Point2D hudCenter;
    private double windowWidth;
    private double windowHeight;
    private double windowRotation;
    private Point2D matchPointWorld;
    private Point2D matchPointHUD;
    private Transformation2D transWorldToHUD;
    private Transformation2D transHUDToWorld;
    
    public ViewWindowImpl(G2D g2d, Point2D hudMin, Point2D hudMax, double windowWidth, Point2D worldStartPoint)
    {
        this.g2d = g2d;
        this.hudMin = hudMin;
        this.hudMax = hudMax;
        this.sizeHUD = g2d.newDimension2D(hudMax.x()-hudMin.x(), hudMax.y() - hudMin.y());
        this.hudCenter = g2d.center(hudMin, hudMax);
        this.aspectRatio = sizeHUD.width() / sizeHUD.height();
        this.keepAspectRatio = true;
        this.windowWidth = windowWidth;
        this.windowHeight = windowWidth / aspectRatio;
        this.windowRotation = 0;
        this.matchPointHUD = hudCenter;
        this.matchPointWorld = worldStartPoint;
        this.transWorldToHUD = null;
        this.transHUDToWorld = null;
    }
    
    @Override
    public void setHUDDimension(Point2D hudMin, Point2D hudMax)
    {
        this.hudMin = hudMin;
        this.hudMax = hudMax;
        this.sizeHUD = g2d.newDimension2D(hudMax.x()-hudMin.x(), hudMax.y() - hudMin.y());
        this.hudCenter = g2d.center(hudMin, hudMax);
        this.aspectRatio = sizeHUD.width() / sizeHUD.height();
        if(keepAspectRatio)
        {
            //Cover the same area in the new HUD
            double area = windowWidth * windowHeight;
            windowHeight = Math.sqrt(area / aspectRatio);
            windowWidth = windowHeight * aspectRatio;
        }
        this.transWorldToHUD = null;
        this.transHUDToWorld = null;
    }

    @Override
    public void setKeepAspectRation(boolean keepAspectRatio)
    {
        this.keepAspectRatio = keepAspectRatio;
    }

    @Override
    public void setWindowWidth(double width)
    {
        this.windowWidth = width;
        if(keepAspectRatio)
        {
            windowHeight = windowWidth / aspectRatio;
        }
        this.transWorldToHUD = null;
        this.transHUDToWorld = null;
    }

    @Override
    public void setWindowHeight(double height)
    {
        this.windowHeight = height;
        if(keepAspectRatio)
        {
            windowWidth = windowHeight * aspectRatio;
        }
        this.transWorldToHUD = null;
        this.transHUDToWorld = null;
    }

    @Override
    public void setWindowRotation(double rotation)
    {
        this.windowRotation = rotation;
        this.transWorldToHUD = null;
        this.transHUDToWorld = null;
    }


    @Override
    public void setWorldMatchPoint(Point2D point)
    {
        this.matchPointWorld = point;
        this.transWorldToHUD = null;
        this.transHUDToWorld = null;
    }

    @Override
    public void setHUDMatchPoint(Point2D point)
    {
        this.matchPointHUD = point;
        this.transWorldToHUD = null;
        this.transHUDToWorld = null;
    }

    @Override
    public Point2D getHUDMatchPoint()
    {
        return matchPointHUD;
    }

    @Override
    public Point2D getWorldMatchPoint()
    {
        return matchPointWorld;
    }

    @Override
    public double getWindowWidth()
    {
        return windowWidth;
    }

    @Override
    public double getWindowHeight()
    {
        return windowHeight;
    }

    @Override
    public double getWindowRotation()
    {
        return windowRotation;
    }

    @Override
    public Transformation2D getWorldToHUD()
    {
        if(transWorldToHUD == null)
        {
            calculateWorldToHUD();
        }
        return transWorldToHUD;
    }

    @Override
    public Transformation2D getHUDToWorld()
    {
        if(transHUDToWorld == null)
        {
            calculateHUDToWorld();
        }
        return transHUDToWorld;
    }

    @Override
    public Point2D fromHUDToWorld(Point2D pointOnHUD)
    {
        if(transHUDToWorld == null)
        {
            calculateHUDToWorld();
        }
        return transHUDToWorld.transform(pointOnHUD);
    }

    @Override
    public Point2D fromWorldtoHUD(Point2D pointInWorld)
    {
        if(transWorldToHUD == null)
        {
            calculateWorldToHUD();
        }
        return transWorldToHUD.transform(pointInWorld);
    }
    
    private void calculateWorldToHUD()
    {
        //World
        Transformation2D tw = g2d.translatePointToOrigo(matchPointWorld);
        Transformation2D rw = g2d.rotate(windowRotation*Math.PI*2.0);
        double sx = sizeHUD.width() / windowWidth;
        double sy = sx;
        if(!keepAspectRatio)
        {
            sy = sizeHUD.height() / windowHeight;
        }
        Transformation2D sw = g2d.scale(sx, sy);
        
        //HUD
        Transformation2D th = g2d.translateOrigoToPoint(matchPointHUD);
        
        transWorldToHUD = g2d.combine(th, sw, rw, tw);
    }
    
    private void calculateHUDToWorld()
    {
        if(transWorldToHUD == null)
        {
            calculateWorldToHUD();
        }
        transHUDToWorld = g2d.inverse(transWorldToHUD);
    }

    @Override
    public Point2D getHUDMin()
    {
        return hudMin;
    }

    @Override
    public Point2D getHUDMax()
    {
        return hudMax;
    }
}
