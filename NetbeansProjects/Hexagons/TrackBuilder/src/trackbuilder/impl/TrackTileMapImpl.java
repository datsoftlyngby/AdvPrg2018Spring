/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackbuilder.impl;

import hexcore.HexCoordinate;
import hexcore.HexCoordinateSystem;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import trackbuilder.ConnectionPoint;
import trackbuilder.TrackTile;
import trackbuilder.TrackTileMap;

/**
 *
 * @author Tobias
 */
public class TrackTileMapImpl implements TrackTileMap
{
    private final HexCoordinateSystem hexMap;
    private final Map<HexCoordinate, TrackTile> tileMap;
    
    public TrackTileMapImpl(HexCoordinateSystem hexMap)
    {
        this.hexMap = hexMap;
        tileMap = new HashMap<>();
    }
    
    @Override
    public TrackTile createTrackTile(TileType tileType)
    {
        switch(tileType)
        {
            case STRAIGHT:
            case CURVE:
                return new SimpleTrackTileImpl(tileMap, tileType);
            case SWITCH_LEFT:
            case SWITCH_RIGHT:
                return new SwitchTrackTileImpl(tileMap, tileType);     
            case CROSS:
                return new CrossTrackTileImpl(tileMap, tileType);
            case ENDSTOP:
                return new EndStopTrackTileImpl(tileMap, tileType);
            default:
                throw new AssertionError(tileType.name());  
        }   
    }

    @Override
    public TrackTile getTrackTile(HexCoordinate position)
    {
        return tileMap.get(position);
    }

    @Override
    public int[] getValidDirections(HexCoordinate position, TrackTile tile)
    {
        int oldDir = tile.getDirection();
        int[] tmp = new int[6];
        int count = 0;
        for(int dir = 0; dir < 6; ++dir)
        {
            tile.setDirection(dir);
            if(isValid(position, tile))
            {
                tmp[count++] = dir;
            }
        }
        if(count == 6)
        {
            return tmp;
        }
        int[] res = new int[count];
        for(int i = 0; i < count; ++i)
        {
            res[i] = tmp[i];
        }
        tile.setDirection(oldDir);
        return res;
    }

    @Override
    public Iterable<ConnectionPoint> getOpenConnectionPoints()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<ConnectionPoint> getShortestPath(ConnectionPoint begin, ConnectionPoint end)
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterator<TrackTile> iterator()
    {
        return tileMap.values().iterator();
    }
    
    private boolean isValid(HexCoordinate pos, TrackTile tile)
    {
        for(int edge = 0; edge < 6; ++edge)
        {
            if(!checkNeighbourConnection(pos, tile, edge))
            {
                return false;
            }
        }
        return true;
    }
    
    private boolean checkNeighbourConnection(HexCoordinate pos, TrackTile tile, int neighbour)
    {
        HexCoordinate n = hexMap.getNeighbour(pos, neighbour);
        TrackTile neigbourTile = tileMap.get(n);
        if(neigbourTile == null)
        {
            return true;
        }
        int neighbourEdge = (neighbour + 3) % 6;
        return tile.isEntryPoint(neighbour) == neigbourTile.isEntryPoint(neighbourEdge);     
    }
    
}
