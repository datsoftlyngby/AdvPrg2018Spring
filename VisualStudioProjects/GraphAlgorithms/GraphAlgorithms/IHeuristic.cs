namespace GraphAlgorithms
{
    public interface IHeuristic<N> where N : INode<N>
    {
        float MinDist(N a, N b);
    }
}