using System;
using System.Collections.Generic;
using System.Text;

namespace GraphAlgorithms
{
    interface IPathfinding
    {
        IEnumerable<IPathNode<N>> GetShortestPath<N>(N start, N goal, IHeuristic<N> heuristic) where N : INode<N>;
        IEnumerable<IPathNode<N>> GetReachableNodes<N>(N start, float maxCost) where N : INode<N>;
    }
}
