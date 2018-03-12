/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app2dapi.menus;

import app2dapi.animation.Notifiable;
import app2dapi.geometry.G2D.BoundingBox2D;
import app2dapi.geometry.G2D.Point2D;

/**
 *
 * @author Tobias Grundtvig
 */
public interface Clickable
{
    public void setClickListener(Notifiable clickListener);
    public BoundingBox2D getBoundingBox();
    public boolean isHit(Point2D clickPoint);
}
