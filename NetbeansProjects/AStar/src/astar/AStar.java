/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package astar;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import priorityqueue.PriorityQueue;
import priorityqueue.PriorityQueueImpl;

/**
 *
 * @author Tobias NOTE: Need to create a customary priority queue, since
 * remove(Object o) in java is based on equals and therefore is O(n).
 */
public class AStar
{

    public static <N extends Node<N>> Iterable<N> getShortestPath(N start, N goal, Heuristic<N> heuristic)
    {
        Map<N, NodeData> nodeDataMap = new HashMap<>();
        PriorityQueue<NodeData> openSet = new PriorityQueueImpl<>(new Order());
        Set<N> closedSet = new HashSet<>();

        NodeData<N> curNode = new NodeData<>(start);
        curNode.g = 0;
        nodeDataMap.put(start, curNode);
        while (true)
        {
            for (Edge<N> edge : curNode.node)
            {
                N other = edge.getEnd();
                if (!closedSet.contains(other))
                {
                    boolean inOpenSet = true;
                    NodeData<N> otherNode = nodeDataMap.get(other);
                    if (otherNode == null)
                    {
                        otherNode = new NodeData<>(other);
                        nodeDataMap.put(other, otherNode);
                        inOpenSet = false;
                    }
                    float newG = edge.getCost() + curNode.g;
                    if (otherNode.g > newG)
                    {
                        otherNode.g = newG;
                        if (inOpenSet)
                        {
                            openSet.update(otherNode);
                        }
                        otherNode.prev = curNode;
                    }
                    if (!inOpenSet)
                    {
                        otherNode.h = heuristic.getMinimumDist(other, goal);
                        openSet.add(otherNode);
                    }
                }
            }
            if (openSet.isEmpty())
            {
                return null;
            }
            closedSet.add(curNode.node);
            curNode = openSet.poll();
            if (curNode.node.equals(goal))
            {
                ArrayList<N> res = new ArrayList<>();
                do
                {
                    res.add(curNode.node);
                    curNode = curNode.prev;
                } while (curNode != null);
                Collections.reverse(res);
                return res;
            }
        }
    }

    private static class NodeData<N>
    {

        public N node;
        public float g;
        public float h;
        public NodeData prev;

        public NodeData(N node)
        {
            this.node = node;
            this.g = Float.MAX_VALUE;
            this.h = 0;
            this.prev = null;
        }

        @Override
        public int hashCode()
        {
            return node.hashCode();
        }

        @Override
        public boolean equals(Object obj)
        {
            if (this == obj)
            {
                return true;
            }
            if (obj == null)
            {
                return false;
            }
            if (getClass() != obj.getClass())
            {
                return false;
            }
            final NodeData<N> other = (NodeData<N>) obj;
            return Objects.equals(this.node, other.node);
        }
    }

    private static class Order implements Comparator<NodeData>
    {

        @Override
        public int compare(NodeData d1, NodeData d2)
        {
            float f1 = d1.g + d1.h;
            float f2 = d2.g + d2.h;
            if (f1 < f2)
            {
                return -1;
            }
            if (f1 > f2)
            {
                return 1;
            }
            if(d1.h < d2.h)
            {
                return -1;
            }
            if(d1.h > d2.h)
            {
                return 1;
            }
            return 0;
        }
    }

}
