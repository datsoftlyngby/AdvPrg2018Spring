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
public class SwitchTrackTileImpl extends AbstractTrackTileImpl implements SwitchTile
{    
    private final int[] entryPoints;
    private SwitchState state;

    public SwitchTrackTileImpl(Map<HexCoordinate, TrackTile> tileMap, TrackTileMap.TileType tileType)
    {
        super(tileMap, tileType);
        entryPoints = new int[3];
        state = SwitchState.STRAIGHT;
        updateDirection(0);
    }

    private void updateDirection(int i)
    {
        entryPoints[0] = i + 0;
        entryPoints[1] = (i + 3) % 6;
        switch (getTileType())
        {
            case SWITCH_LEFT:
                entryPoints[2] = (i + 4) % 6;
                break;
            case SWITCH_RIGHT:
                entryPoints[2] = (i + 2) % 6;
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
        return entryPoints;
    }

    @Override
    public int getCurrentExitPoint(int entryPoint)
    {
        if (state == SwitchState.STRAIGHT)
        {
            if (entryPoint == entryPoints[0])
            {
                return entryPoints[1];
            }
            if (entryPoint == entryPoints[1])
            {
                return entryPoints[0];
            }
            if (entryPoint == entryPoints[2])
            {
                return -1;
            }
            throw new RuntimeException("Invalid entry point: " + entryPoint
                    + " Valids: " + entryPoints[0] + ", "
                    + entryPoints[1] + " and "
                    + entryPoints[3]);
        } else
        {
            if (entryPoint == entryPoints[0])
            {
                return entryPoints[2];
            }
            if (entryPoint == entryPoints[1])
            {
                return -1;
            }
            if (entryPoint == entryPoints[2])
            {
                return entryPoints[0];
            }
            throw new RuntimeException("Invalid entry point: " + entryPoint
                    + " Valids: " + entryPoints[0] + ", "
                    + entryPoints[1] + " and "
                    + entryPoints[3]);
        }
    }

    @Override
    public int[] getPossibleExitPoints(int entryPoint)
    {
        if (entryPoint == entryPoints[0])
        {
            int[] res = new int[2];
            res[0] = entryPoints[1];
            res[1] = entryPoints[2];
            return res;
        }
        if (entryPoint == entryPoints[1] || entryPoint == entryPoints[2])
        {
            int[] res = new int[1];
            res[0] = entryPoints[0];
            return res;
        }
        throw new RuntimeException("Invalid entry point: " + entryPoint
                + " Valids: " + entryPoints[0] + ", "
                + entryPoints[1] + " and "
                + entryPoints[3]);
    }

    @Override
    public SwitchTile asSwitch()
    {
        return this;
    }

    @Override
    public SwitchState getState()
    {
        return state;
    }

    @Override
    public void setState(SwitchState state)
    {
        this.state = state;
    }

    @Override
    public void toggleState()
    {
        if(state == SwitchState.STRAIGHT)
        {
            state = SwitchState.TURN;
        }
        else
        {
            state = SwitchState.STRAIGHT;
        }
    }

}
