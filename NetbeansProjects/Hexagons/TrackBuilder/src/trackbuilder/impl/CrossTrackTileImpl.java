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
public class CrossTrackTileImpl extends AbstractTrackTileImpl
{
    private final int[] entryPoints;

    public CrossTrackTileImpl(Map<HexCoordinate, TrackTile> tileMap, TrackTileMap.TileType tileType)
    {
        super(tileMap, tileType);
        entryPoints = new int[4];
        updateDirection(0);
    }
    
    
    @Override
    public void setNewDirection(int i)
    {
        updateDirection(i);
    }
    
    private void updateDirection(int i)
    {
        entryPoints[0] = (i+0) % 6;
        entryPoints[1] = (i+3) % 6;
        entryPoints[2] = (i+1) % 6;
        entryPoints[3] = (i+4) % 6;
    }

    @Override
    public int[] getEntryPoints()
    {
        return entryPoints;
    }

    @Override
    public int getCurrentExitPoint(int entryPoint)
    {
        if(entryPoint == entryPoints[0] || entryPoint == entryPoints[0] ||
           entryPoint == entryPoints[0] || entryPoint == entryPoints[0])
        {
            return (entryPoint + 3) % 6;
        }
        throw new AssertionError(   "Invalid entry point: " + entryPoint +
                                    " Valid entry points : " + entryPoints[0] +
                                    ", " + entryPoints[1] + ", " +
                                    entryPoints[2] + " and " +
                                    entryPoints[3] + ".");
    }

    @Override
    public int[] getPossibleExitPoints(int entryPoint)
    {
        int[] res = new int[1];
        res[0] = getCurrentExitPoint(entryPoint);
        return res;
    }

    @Override
    public SwitchTile asSwitch()
    {
        return null;
    }
    
}
