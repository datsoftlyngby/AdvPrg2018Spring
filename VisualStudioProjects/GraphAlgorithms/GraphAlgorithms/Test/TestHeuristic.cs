using System;
using System.Collections.Generic;
using System.Text;

namespace GraphAlgorithms.Test
{
    class TestHeuristic : IHeuristic<TestNode>
    {
        public float MinDist(TestNode a, TestNode b)
        {
            return MathF.Abs(a.X - b.X) + MathF.Abs(a.Y - b.Y);
        }
    }
}
