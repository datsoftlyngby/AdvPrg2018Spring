package app2dapi;

import app2dapi.geometry.G2D;
import app2dapi.graphics.Screen;
import app2dapi.input.charinput.CharInput;
import app2dapi.input.keyboard.Keyboard;
import app2dapi.input.mouse.Mouse;

/**
 *
 * @author tog
 */
public interface Device
{
    public Screen getScreen();
    public Keyboard getKeyboard();
    public CharInput getCharInput();
    public Mouse getMouse();
    public G2D getGeometry2D();
}
