/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackbuilder.impl;

import hexcore.HexCoordinate;
import java.util.Map;
import trackbuilder.TrackTile;
import trackbuilder.TrackTileMap.TileType;

/**
 *
 * @author Tobias
 */
public abstract class AbstractTrackTileImpl implements TrackTile
{
    private final Map<HexCoordinate, TrackTile> tileMap;
    private final TileType tileType;
    private HexCoordinate pos;
    private int direction;

    public AbstractTrackTileImpl(Map<HexCoordinate, TrackTile> tileMap, TileType tileType)
    {
        this.tileMap = tileMap;
        this.tileType = tileType;
        this.pos = null;
        this.direction = 0;
    }
    
    
    @Override
    public TileType getTileType()
    {
        return tileType;
    }

    @Override
    public HexCoordinate getHexPosition()
    {
        return pos;
    }

    @Override
    public void setHexPosition(HexCoordinate pos)
    {
        if(this.pos != null)
        {
            tileMap.remove(this.pos);
        }
        this.pos = pos;
        if(this.pos != null)
        {
            tileMap.put(this.pos, this);
        }
    }

    @Override
    public int getDirection()
    {
        return direction;
    }

    @Override
    public void setDirection(int direction)
    {
        if(this.direction != direction)
        {
            this.direction = direction;
            setNewDirection(direction);
        }
    }

    @Override
    public void turnClockwise()
    {
        setDirection((direction + 5) % 6);
    }

    @Override
    public void turnCounterClockwise()
    {
        setDirection((direction + 1) % 6);
    }
    
    @Override
    public TrackTile getNeighbour(int i)
    {
        if(getHexPosition() == null)
        {
            return null;
        }
        HexCoordinate neighbour = getHexPosition().getNeighbour(i);
        return tileMap.get(neighbour);
    }
    
    @Override
    public boolean isEntryPoint(int edge)
    {
        int[] entryPoints = getEntryPoints();
        for(int i = 0; i < entryPoints.length; ++i)
        {
            if(edge == entryPoints[i])
            {
                return true;
            }
        }
        return false;
    }

    public abstract void setNewDirection(int i);
    
}
