using System;
using System.Collections.Generic;
using System.Text;

namespace GraphAlgorithms
{
    class Pathfinding : IPathfinding
    {
        public IEnumerable<IPathNode<N>> GetReachableNodes<N>(N start, float maxCost) where N : INode<N>
        {
            return ReachableNodes.GetReachableNodes(start, maxCost);
        }

        public IEnumerable<IPathNode<N>> GetShortestPath<N>(N start, N goal, IHeuristic<N> heuristic) where N : INode<N>
        {
            return AStar.GetShortestPath(start, goal, heuristic);
        }
    }
}
