/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackbuilder.impl;

import hexcore.HexCoordinate;
import java.util.Map;
import trackbuilder.SwitchTile;
import trackbuilder.TrackTile;
import trackbuilder.TrackTileMap;

/**
 *
 * @author Tobias
 */
public class EndStopTrackTileImpl extends AbstractTrackTileImpl
{
    private int dir;

    public EndStopTrackTileImpl(Map<HexCoordinate, TrackTile> tileMap, TrackTileMap.TileType tileType)
    {
        super(tileMap, tileType);
    }
    
    @Override
    public void setNewDirection(int i)
    {
        this.dir = i;
    }

    @Override
    public int[] getEntryPoints()
    {
        int[] res = new int[1];
        res[0] = dir;
        return res;
    }

    @Override
    public int getCurrentExitPoint(int entryPoint)
    {
        if(entryPoint == dir)
        {
            return -1;
        }
        throw new AssertionError(   "Invalid entry point: " + entryPoint +
                                    " Valid entry point : " + dir + ".");
    }

    @Override
    public int[] getPossibleExitPoints(int entryPoint)
    {
        if(entryPoint == dir)
        {
            return new int[0];
        }
        throw new AssertionError(   "Invalid entry point: " + entryPoint +
                                    " Valid entry point : " + dir + ".");
    }

    @Override
    public SwitchTile asSwitch()
    {
        return null;
    }
    
}
