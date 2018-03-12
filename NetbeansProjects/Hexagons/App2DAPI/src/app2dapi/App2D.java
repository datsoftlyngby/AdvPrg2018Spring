package app2dapi;

import app2dapi.graphics.Canvas;

/**
 *
 * @author tog
 */
public interface App2D
{
    public boolean showMouseCursor();
    public boolean initialize(Device device);
    public boolean update(double time);
    public void draw(Canvas canvas);
    public void destroy();
}
