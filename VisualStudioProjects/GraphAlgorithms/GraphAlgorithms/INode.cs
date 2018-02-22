using System;
using System.Collections.Generic;
using System.Text;

namespace GraphAlgorithms
{
    interface INode<N> : IEnumerable<IEdge<N>> where N : INode<N>
    {
    }
}
