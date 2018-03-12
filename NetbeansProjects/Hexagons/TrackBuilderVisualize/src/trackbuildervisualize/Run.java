/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trackbuildervisualize;

import app2dapi.panandzoom2dapp.PanAndZoom2DApp;
import app2dapi.panandzoom2dapp.PanAndZoomAdapter;
import app2dpcimpl.PCPlatformImpl;
import hexcore.HexCoordinateSystem.ColumnRowType;

/**
 *
 * @author Tobias
 */
public class Run
{
    public static void main(String[] args)
    {
        PCPlatformImpl platform = new PCPlatformImpl(true);
        PanAndZoom2DApp app = new TrackBuilderVisualize(30, 20, ColumnRowType.ODD_FLAT_TOP, 50, 0.5f);
        platform.runApplication(new PanAndZoomAdapter(app));
    }
}
