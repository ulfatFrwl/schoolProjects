/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sac;

/**
 *
 * @author Ulfat Fruitwala
 */
public class SP 
{
    private Edge[] edgeTo;
    private double [] distTo;
    private IndexMinPQ<Double> pq;
    
    public SP(Digraph G, int s)
    {
        edgeTo = new Edge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<>(G.V());
        
        for(int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;
        
        pq.insert(s, distTo[s]);
        while(!pq.isEmpty()){
            int v = pq.delMin();
            for(Edge x : G.adj(v))
                relax(G, x);
        }
    }
    
    private void relax(Digraph G, Edge v)
    {
        int x = v.from(), w = v.to();
        if(distTo[w]>distTo[x]+v.weight())
            {
               distTo[w] = distTo[x]+v.weight();
               edgeTo[w] = v;
               if(pq.contains(w))
                   pq.change(w, distTo[w]);
               else
                   pq.insert(w, distTo[w]);
            }
    }
    
    public double distTo(int v){
        return distTo[v];
    }
    
    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    
    public Iterable<Edge> pathTo(int v)
    {
        if(!hasPathTo(v))   return null;
        Stack<Edge> path = new Stack<>();
        for(Edge x = edgeTo[v]; x!=null; x = edgeTo[x.from()]){
            path.push(x);
        }
        return path;
    }
    
}
