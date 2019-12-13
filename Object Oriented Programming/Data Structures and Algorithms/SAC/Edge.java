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
public class Edge
{
    private final int v;
    private final int w;
    private final double weight;
    
    public Edge(int v, int w, double weight)
    {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    
    public double weight(){
       return weight;
    }
            
    public int from(){
        return v;
    }
    
    public int to(){
        return w;
    }

    public int compareTo(Edge that) 
    {
         if      (this.weight() < that.weight())  return -1;
         else if (this.weight() > that.weight())  return +1;
         else                                     return 0;
    }
    
    public String toString(){
        return String.format("%d-%d %.2f", v, w, weight);
                
    }
    
    
}
