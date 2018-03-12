/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackbuilder;

import hexcore.HexCoordinate;
import trackbuilder.TrackTileMap.TileType;

/**
 *
 * @author Tobias
 */
public interface TrackTile
{
    public TileType getTileType();
    public HexCoordinate getHexPosition();
    public void setHexPosition(HexCoordinate position);
    public int getDirection();
    public void setDirection(int direction);
    public void turnClockwise();
    public void turnCounterClockwise();
    public int[] getEntryPoints();
    public boolean isEntryPoint(int edge);
    public int getCurrentExitPoint(int entryPoint);
    public int[] getPossibleExitPoints(int entryPoint);
    public TrackTile getNeighbour(int edge);
    public SwitchTile asSwitch(); //Returns null if this is not a SwitchTile
}
