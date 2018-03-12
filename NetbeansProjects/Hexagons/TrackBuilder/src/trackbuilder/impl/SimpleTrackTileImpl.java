package trackbuilder.impl;

import hexcore.HexCoordinate;
import java.util.Map;
import trackbuilder.SwitchTile;
import trackbuilder.TrackTile;
import trackbuilder.TrackTileMap.TileType;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Tobias
 *
 * Used for STRAIGHT and CURVE tiles;
 */
public class SimpleTrackTileImpl extends AbstractTrackTileImpl implements TrackTile
{

    private final int entryPoints[];

    public SimpleTrackTileImpl(Map<HexCoordinate, TrackTile> tileMap, TileType tileType)
    {
        super(tileMap, tileType);
        this.entryPoints = new int[2];
        updateDirection(0);
    }
    
    private void updateDirection(int i)
    {
        entryPoints[0] = i+0;
        switch (getTileType())
        {
            case STRAIGHT:
                entryPoints[1] = (i+3)%6;
                break;
            case CURVE:
                entryPoints[1] = (i+2)%6;
                break;
            default:
                throw new AssertionError("Invalid tile type for SimpleTrackTileImpl: " + getTileType().name());
        }
    }

    @Override
    public void setNewDirection(int i)
    {
        updateDirection(i);
    }

    @Override
    public int[] getEntryPoints()
    {
        //Consider defensive copy?
        return entryPoints;
    }

    @Override
    public int getCurrentExitPoint(int entryPoint)
    {
        if(entryPoints[0] == entryPoint)
        {
            return entryPoints[1];
        }
        if(entryPoints[1] == entryPoint)
        {
            return entryPoints[0];
        }
        throw new AssertionError("Invalid entry point: " + entryPoint + " Valid entry points : (" + entryPoints[0] + ", " + entryPoints[1] + ")");
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
