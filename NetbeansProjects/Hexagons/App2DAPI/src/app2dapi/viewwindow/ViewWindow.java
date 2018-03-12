/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.viewwindow;

import app2dapi.geometry.G2D.Point2D;
import app2dapi.geometry.G2D.Transformation2D;

/**
 *
 * @author Tobias Grundtvig
 */
public interface ViewWindow
{
    public void setKeepAspectRation(boolean keepAspectRatio);
    public void setHUDDimension(Point2D hudMin, Point2D hudMax);
    public void setWindowWidth(double width);
    public void setWindowHeight(double height);
    public void setWindowRotation(double rotation);
    public void setWorldMatchPoint(Point2D point);
    public void setHUDMatchPoint(Point2D point);
    public Point2D getHUDMatchPoint();
    public Point2D getWorldMatchPoint();
    public double getWindowWidth();
    public double getWindowHeight();
    public double getWindowRotation();
    public Point2D getHUDMin();
    public Point2D getHUDMax();
    public Transformation2D getWorldToHUD();
    public Transformation2D getHUDToWorld();
    public Point2D fromHUDToWorld(Point2D pointOnHUD);
    public Point2D fromWorldtoHUD(Point2D pointInWorld);
}
