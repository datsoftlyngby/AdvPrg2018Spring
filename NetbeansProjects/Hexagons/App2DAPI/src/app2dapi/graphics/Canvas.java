package app2dapi.graphics;

import app2dapi.geometry.G2D.Path;
import app2dapi.geometry.G2D.Point2D;
import app2dapi.geometry.G2D.Polygon;
import app2dapi.geometry.G2D.Transformation2D;


/**
 *
 * @author tog
 */
public interface Canvas
{
    public void clear(Color c);
    public void setColor(Color c);
    public Color getColor();
    public void setLineWidth(float width);
    public void setTransformation(Transformation2D t);
    public Transformation2D getTransformation();
    public void drawLine(Point2D p1, Point2D p2);
    public void drawPath(Path path);
    public void drawFilledRectangle(Point2D boundA, Point2D boundB);
    public void drawFilledRectangle(Point2D center, double width, double height);
    public void drawOutlinedRectangle(Point2D boundA, Point2D boundB);
    public void drawOutlinedRectangle(Point2D center, double width, double height);
    public void drawFilledPolygon(Polygon polygon);
    public void drawOutlinedPolygon(Polygon polygon);
    public void drawPoint(Point2D p, double size);
    public void drawText(Point2D p, String text, double height);
    public void drawText(Point2D p, String text, double height, boolean centerX, boolean centerY);
    public double getTextWidth(String text, double height);
}
