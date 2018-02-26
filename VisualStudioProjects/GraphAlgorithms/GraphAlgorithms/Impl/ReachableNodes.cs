using System;
using System.Collections.Generic;
using System.Text;

namespace GraphAlgorithms
{
    class ReachableNodes
    {
        public static IEnumerable<IPathNode<N>> GetReachableNodes<N>(N start, float maxCost) where N : INode<N>
        {
            C5.IDictionary<N, PathNode<N>> nodeDictionary = new C5.HashDictionary<N, PathNode<N>>();
            C5.IPriorityQueue<PathNode<N>> openSet = new C5.IntervalHeap<PathNode<N>>(new PathNodeComparer<N>(), C5.MemoryType.Normal);
            C5.ICollection<N> closedSet = new C5.HashSet<N>();
            C5.ArrayList<IPathNode<N>> res = new C5.ArrayList<IPathNode<N>>(C5.MemoryType.Normal);


            PathNode<N> curNode = new PathNode<N>(start);
            curNode.g = 0;
            nodeDictionary.Add(start, curNode);
            while (true)
            {
                res.Add(curNode);
                foreach (IEdge<N> edge in curNode.node)
                {
                    N other = edge.GetEnd();
                    if (!closedSet.Contains(other))
                    {
                        PathNode<N> otherNode = null;
                        if (!nodeDictionary.Find(ref other, out otherNode))
                        {
                            otherNode = new PathNode<N>(other);
                            nodeDictionary.Add(other, otherNode);
                        }
                        float newG = edge.GetCost() + curNode.g;
                        if (otherNode.g > newG)
                        {
                            otherNode.g = newG;
                            if (otherNode.queueHandle != null)
                            {
                                openSet.Replace(otherNode.queueHandle, otherNode);
                            }
                            otherNode.prev = curNode;
                        }
                        if (otherNode.queueHandle == null)
                        {
                            C5.IPriorityQueueHandle<PathNode<N>> handle = null;
                            openSet.Add(ref handle, otherNode);
                            otherNode.queueHandle = handle;
                        }
                    }
                }
                if (openSet.IsEmpty)
                {
                    return res;
                }
                closedSet.Add(curNode.node);
                curNode = openSet.DeleteMin();
                if(curNode.g > maxCost)
                {
                    return res;
                }
            }
        }


        private class PathNode<N> : IPathNode<N>, IEquatable<PathNode<N>> where N : INode<N>
        {
            public N node { get; }
            public PathNode<N> prev { get; set; }
            public C5.IPriorityQueueHandle<PathNode<N>> queueHandle { get; set; }
            public float g { get; set; }

            public PathNode(N node)
            {
                this.node = node;
                this.prev = null;
                this.queueHandle = null;
                this.g = float.MaxValue;
            }

            public float GetCost()
            {
                return g;
            }

            public N GetNode()
            {
                return node;
            }

            public IPathNode<N> GetPrevious()
            {
                return prev;
            }

            public bool Equals(PathNode<N> other)
            {
                // If parameter is null, return false.
                if (Object.ReferenceEquals(other, null))
                {
                    return false;
                }
                return this.node.Equals(other.node);
            }

            public override int GetHashCode()
            {
                return node.GetHashCode();
            }
        }

        private class PathNodeComparer<N> : IComparer<PathNode<N>> where N : INode<N>
        {
            public int Compare(PathNode<N> x, PathNode<N> y)
            {
                if (x.g < y.g) return -1;
                if (x.g > y.g) return 1;
                return 0;
            }
        }
    }
}
