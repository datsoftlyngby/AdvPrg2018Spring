using System;
using System.Collections.Generic;
using System.Text;

namespace GraphAlgorithms
{
    interface IPathNode<N> where N : INode<N>
    {
        N GetNode();
        float GetCost();
        IPathNode<N> GetPrevious();
    }
}
