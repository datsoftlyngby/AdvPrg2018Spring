using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;

namespace GraphAlgorithms.Test
{
    class TestNode : INode<TestNode>
    {
        public int X { get; }
        public int Y { get; }
        public int MoveCost { get; }
        private ICollection<IEdge<TestNode>> edges;

        public override string ToString()
        {
            return "(" + X + ", " + Y + ") :" + MoveCost;
        }

        public TestNode(int x, int y, int moveCost)
        {
            this.X = x;
            this.Y = y;
            this.MoveCost = moveCost;
            this.edges = new List<IEdge<TestNode>>();
        }

        public void AddEdgeTo(TestNode node)
        {
            IEdge<TestNode> edge = new TestEdge(MoveCost + node.MoveCost, node);
            edges.Add(edge);
        }

        public override bool Equals(object obj)
        {
            var node = obj as TestNode;
            return node != null &&
                   X == node.X &&
                   Y == node.Y;
        }

        public IEnumerator<IEdge<TestNode>> GetEnumerator()
        {
            return edges.GetEnumerator();
        }

        public override int GetHashCode()
        {
            var hashCode = 1502939027;
            hashCode = hashCode * -1521134295 + X.GetHashCode();
            hashCode = hashCode * -1521134295 + Y.GetHashCode();
            return hashCode;
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return edges.GetEnumerator();
        }



        private class TestEdge : IEdge<TestNode>
        {
            private readonly float cost;
            private TestNode end;

            public TestEdge(float cost, TestNode end)
            {
                this.cost = cost;
                this.end = end;
            }

            public float GetCost()
            {
                return cost;
            }

            public TestNode GetEnd()
            {
                return end;
            }
        }
    }
}
