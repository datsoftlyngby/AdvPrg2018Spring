/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexcore;

/**
 *
 * @author Tobias
 */
public interface HexCoordinate
{
    public HexCoordinate getNeighbour(int i);
    public int getAxialX();
    public int getAxialY();
    public int getAxialZ();
    public int getColumn();
    public int getRow();
    public float getCenterXPos();
    public float getCenterYPos();
    public float getCornerXPos(int index);
    public float getCornerYPos(int index);
    public float getMidEdgeX(int index);
    public float getMidEdgeY(int index);
    
}
