namespace GraphAlgorithms
{
    internal interface IEdge<N> where N : INode<N>
    {
        N GetEnd();
        float GetCost();
    }
}