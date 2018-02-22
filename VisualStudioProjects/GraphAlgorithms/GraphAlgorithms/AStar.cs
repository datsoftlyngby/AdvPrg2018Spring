using C5;
using System.Collections.Generic;

namespace GraphAlgorithms
{
    class AStar
    {
        public static IEnumerable<N> GetShortestPath<N>(N start, N goal, IHeuristic<N> heuristic) where N : INode<N>
        {
            C5.IDictionary<N, PathNode<N>> nodeDictionary = new HashDictionary<N, PathNode<N>>();
            C5.IPriorityQueue<PathNode<N>> openSet = new C5.IntervalHeap<PathNode<N>>(new PathNodeComparer<N>(), MemoryType.Strict);
            return null;
        }


        private class PathNode<N> : IPathNode<N> where N : INode<N>
        {
            public N node;
            public PathNode<N> prev;
            public float g;
            public float h;

            public float getCost()
            {
                return g;
            }

            public N getNode()
            {
                return node;
            }

            public IPathNode<N> getPrevious()
            {
                return prev;
            }
        }

        private class PathNodeComparer<N> : IComparer<PathNode<N>> where N : INode<N>
        {
            public int Compare(PathNode<N> x, PathNode<N> y)
            {
                throw new System.NotImplementedException();
            }
        }
    }
}
