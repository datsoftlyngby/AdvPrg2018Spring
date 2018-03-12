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
public interface HexCoordinateSystem
{
    public enum ColumnRowType {ODD_POINT_TOP, EVEN_POINT_TOP, ODD_FLAT_TOP, EVEN_FLAT_TOP}
    public float getHexRadius();
    public ColumnRowType getColumnRowType();
    public HexCoordinate getFromAxial(int x, int y, int z);
    public HexCoordinate getFromColumnAndRow(int col, int row);
    public HexCoordinate getFromPosition(float x, float y);
    public HexCoordinate getNeighbour(HexCoordinate self, int edge);
    public int getOppositeEdgeIndex(int edge);
    public int getDist(HexCoordinate a, HexCoordinate b);  
}
