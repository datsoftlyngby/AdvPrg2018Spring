/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexcore;

import static hexcore.HexCoordinateSystem.ColumnRowType.EVEN_FLAT_TOP;
import hexcore.impl.HexCoordinateSystemImpl;

/**
 *
 * @author Tobias
 */
public class HexCore
{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        HexCoordinateSystem sys = new HexCoordinateSystemImpl(EVEN_FLAT_TOP, 1);
        HexCoordinate coord1 = sys.getFromColumnAndRow(1, 1);
        HexCoordinate coord2 = sys.getFromColumnAndRow(3, 4);
        for(int i = 0; i < 6; ++i)
        {
            HexCoordinate n = sys.getNeighbour(coord1, i);
            System.out.println(n);
        }
        System.out.println(coord1);
        System.out.println(coord2);
        System.out.println("Dist: " + sys.getDist(coord1, coord2));
    }
    
}
