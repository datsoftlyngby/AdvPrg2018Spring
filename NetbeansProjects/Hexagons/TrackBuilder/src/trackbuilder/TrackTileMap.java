/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackbuilder;

import hexcore.HexCoordinate;

/**
 *
 * @author Tobias
 */
public interface TrackTileMap extends Iterable<TrackTile>
{
    public enum TileType {STRAIGHT, CURVE, SWITCH_LEFT, SWITCH_RIGHT, CROSS, ENDSTOP}
    public TrackTile createTrackTile(TileType tileType);
    public TrackTile getTrackTile(HexCoordinate position);
    public int[] getValidDirections(HexCoordinate position, TrackTile tile);
    public Iterable<ConnectionPoint> getOpenConnectionPoints();
    public Iterable<ConnectionPoint> getShortestPath(ConnectionPoint begin, ConnectionPoint end);
}
