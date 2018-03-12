/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hexcore.impl;

import hexcore.HexCoordinate;
import hexcore.HexCoordinateSystem;
import static hexcore.HexCoordinateSystem.ColumnRowType.EVEN_FLAT_TOP;
import static hexcore.HexCoordinateSystem.ColumnRowType.EVEN_POINT_TOP;
import static hexcore.HexCoordinateSystem.ColumnRowType.ODD_FLAT_TOP;
import static hexcore.HexCoordinateSystem.ColumnRowType.ODD_POINT_TOP;

/**
 *
 * @author Tobias
 */
public class HexCoordinateSystemImpl implements HexCoordinateSystem
{
    private final float SQRT3 = (float) Math.sqrt(3.0);
    private final float SQRT3DIV3 = SQRT3 / 3;
    private final float TWOTHIRDS = 2.0f / 3.0f;
    private final ColumnRowType crType;
    private final float radius;
    private final float[] pointXs;
    private final float[] pointYs;
    

    public HexCoordinateSystemImpl(ColumnRowType crType, float radius)
    {
        this.crType = crType;
        this.radius = radius;
        pointXs = new float[6];
        pointYs = new float[6];
        switch(crType)
        {
            case ODD_POINT_TOP:
            case EVEN_POINT_TOP:
                for(int i = 0; i < 6; ++i)
                {
                    double angle = 60 * i + 30;
                    double rad = (Math.PI / 180.0) * angle;
                    pointXs[i] = (float) (Math.cos(rad) * radius);
                    pointYs[i] = (float) (Math.sin(rad) * radius);
                }
                break;
            case ODD_FLAT_TOP:
            case EVEN_FLAT_TOP:
                for(int i = 0; i < 6; ++i)
                {
                    double angle = 60 * i;
                    double rad = (Math.PI / 180.0) * angle;
                    pointXs[i] = (float) (Math.cos(rad) * radius);
                    pointYs[i] = (float) (Math.sin(rad) * radius);
                }             
        }
    }
    
    @Override
    public ColumnRowType getColumnRowType()
    {
        return crType;
    }
    
     @Override
    public float getHexRadius()
    {
        return radius;
    }

    @Override
    public HexCoordinate getFromAxial(int x, int y, int z)
    {

        return new HexCoordinateImpl(x, y, z);
    }

    @Override
    public HexCoordinate getFromColumnAndRow(int col, int row)
    {
        switch (crType)
        {
            case ODD_POINT_TOP:
            {
                int x = col - (row - (row&1))/2;
                int y = -x-row;
                return new HexCoordinateImpl(x, y, row);
            }
            case EVEN_POINT_TOP:
            {
                int x = col - (row + (row&1))/2;
                int y = -x-row;
                return new HexCoordinateImpl(x, y, row);
            }
            case ODD_FLAT_TOP:
            {
                int z = row - (col - (col&1))/2;
                int y = -col-z;
                return new HexCoordinateImpl(col, y, z);
            }
            case EVEN_FLAT_TOP:
            {
                int z = row - (col + (col&1))/2;
                int y = -col-z;
                return new HexCoordinateImpl(col, y, z);
            }
            default:
                throw new RuntimeException("Unknown Column/Row type: " + crType);
        }
    }
    
    @Override
    public HexCoordinate getFromPosition(float x, float y)
    {
        switch (crType)
        {
            case ODD_POINT_TOP:
            case EVEN_POINT_TOP:
            {
                // q = (x * sqrt(3)/3 - y / 3) / size
                // r = y * 2/3 / size
                
                float q = ((-x * SQRT3DIV3) - (y / 3)) / radius;
                float r = (y * TWOTHIRDS) / radius;
                float s = -q-r;
                return round(s, q, r);
            }
            case ODD_FLAT_TOP:
            case EVEN_FLAT_TOP:
            {
                // q = x * 2/3 / size
                // r = (-x / 3 + sqrt(3)/3 * y) / size
                float q = (x * TWOTHIRDS) / radius;
                float r = (-x / 3 - SQRT3DIV3 * y) / radius;
                float s = -q-r;
                return round(q, r, s);
            }
            default:
                throw new RuntimeException("Unknown Column/Row type: " + crType);
        }
    }

    @Override
    public HexCoordinate getNeighbour(HexCoordinate self, int dir)
    {
        switch (dir)
        {
            case 0:
                return new HexCoordinateImpl(self.getAxialX(), self.getAxialY() - 1, self.getAxialZ() + 1);
            case 1:
                return new HexCoordinateImpl(self.getAxialX() - 1, self.getAxialY(), self.getAxialZ() + 1);
            case 2:
                return new HexCoordinateImpl(self.getAxialX() - 1, self.getAxialY() + 1, self.getAxialZ());
            case 3:
                return new HexCoordinateImpl(self.getAxialX(), self.getAxialY() + 1, self.getAxialZ() - 1);
            case 4:
                return new HexCoordinateImpl(self.getAxialX() + 1, self.getAxialY(), self.getAxialZ() - 1); 
            case 5:
                return new HexCoordinateImpl(self.getAxialX() + 1, self.getAxialY() - 1, self.getAxialZ());
            default:
                throw new RuntimeException("Invalid neighbour direction: " + dir);
        }
    }
    
     @Override
    public int getOppositeEdgeIndex(int edge)
    {
        return (edge + 3) % 6;
    }

    @Override
    public int getDist(HexCoordinate a, HexCoordinate b)
    {
        return  (   Math.abs(a.getAxialX() - b.getAxialX()) +
                    Math.abs(a.getAxialY() - b.getAxialY()) +
                    Math.abs(a.getAxialZ() - b.getAxialZ())     ) / 2; 
    }
    
    private HexCoordinate round(float q, float r, float s)
    {
        int rx = Math.round(q);
        int ry = Math.round(r);
        int rz = Math.round(s);
        if(rx+ry+rz != 0)
        {
            float diffX = Math.abs(q - rx);
            float diffY = Math.abs(r - ry);
            float diffZ = Math.abs(s - rz);
            if(diffX > diffY && diffX > diffZ) rx = -ry-rz;
            else if(diffY > diffZ) ry = -rx-rz;
            else rz = -rx-ry;
        }     
        return new HexCoordinateImpl(rx, ry, rz);
    }

    private class HexCoordinateImpl implements HexCoordinate
    {
        private final int x;
        private final int y;
        private final int z;

        public HexCoordinateImpl(int x, int y, int z)
        {
            if (z != -(x + y))
            {
                throw new RuntimeException("Invalid Axial coordinate: (" + x + ", " + y + ", " + z + ")");
            }
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public int getAxialX()
        {
            return x;
        }

        @Override
        public int getAxialY()
        {
            return y;
        }

        @Override
        public int getAxialZ()
        {
            return z;
        }

        @Override
        public int getColumn()
        {
            switch (crType)
            {
                case ODD_POINT_TOP:
                    return x + (z - (z & 1)) / 2;
                case EVEN_POINT_TOP:
                    return x + (z + (z & 1)) / 2;
                case ODD_FLAT_TOP:
                    return x;
                case EVEN_FLAT_TOP:
                    return x;
                default:
                    throw new RuntimeException("Unknown cartesian type: " + crType);
            }
        }

        @Override
        public int getRow()
        {
            switch (crType)
            {
                case ODD_POINT_TOP:
                    return z;
                case EVEN_POINT_TOP:
                    return z;
                case ODD_FLAT_TOP:
                    return z + (x - (x & 1)) / 2;
                case EVEN_FLAT_TOP:
                    return z + (x + (x & 1)) / 2;
                default:
                    throw new RuntimeException("Unknown cartesian type: " + crType);
            }
        }

        @Override
        public float getCenterXPos()
        {
            switch (crType)
            {
                case ODD_POINT_TOP:
                case EVEN_POINT_TOP:
                {
                    float q = x;
                    float r = z;
                    return radius * SQRT3 * (q + r*0.5f);
                }
                case ODD_FLAT_TOP:
                case EVEN_FLAT_TOP:
                {
                    float q = x;
                    return radius * 1.5f * q;
                }
               
                default:
                    throw new RuntimeException("Unknown cartesian type: " + crType);
            }
        }

        @Override
        public float getCenterYPos()
        {
            switch (crType)
            {
                case ODD_POINT_TOP:
                case EVEN_POINT_TOP:
                {
                    float r = z;
                    return radius * 1.5f * r;
                }
                case ODD_FLAT_TOP:
                case EVEN_FLAT_TOP:
                {
                    float q = x;
                    float r = z;
                    return radius * SQRT3 * (r + q*0.5f);
                }
                default:
                    throw new RuntimeException("Unknown cartesian type: " + crType);
            }
        }
        
        @Override
        public float getCornerXPos(int index)
        {
            return getCenterXPos() + pointXs[index];
        }

        @Override
        public float getCornerYPos(int index)
        {
            return getCenterYPos() + pointYs[index];
        }
        
        @Override
        public HexCoordinate getNeighbour(int i)
        {
            return HexCoordinateSystemImpl.this.getNeighbour(this, i);
        }

        @Override
        public float getMidEdgeX(int index)
        {
            int end = (index + 1) % 6;
            return getCenterXPos() + (pointXs[index] + pointXs[end]) * 0.5f;
        }

        @Override
        public float getMidEdgeY(int index)
        {
            int end = (index + 1) % 6;
            return getCenterXPos() + (pointYs[index] + pointYs[end]) * 0.5f;
        }

        @Override
        public int hashCode()
        {
            int hash = 7;
            hash = 61 * hash + this.x;
            hash = 61 * hash + this.y;
            return hash;
        }

        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
            {
                return true;
            }
            if (obj == null)
            {
                return false;
            }
            if (getClass() != obj.getClass())
            {
                return false;
            }
            final HexCoordinateImpl other = (HexCoordinateImpl) obj;
            if (this.x != other.x)
            {
                return false;
            }
            return this.y == other.y;
        }

        @Override
        public String toString()
        {
            return  "Hex Axial(" + "x=" + x + ", y=" + y + ", z=" + z + ")" +
                    " Column: " + getColumn() + " Row: " + getRow() +
                    " WorldPos: (" + getCenterXPos() + ", " + getCenterYPos() + ")";
        }

    }

}
