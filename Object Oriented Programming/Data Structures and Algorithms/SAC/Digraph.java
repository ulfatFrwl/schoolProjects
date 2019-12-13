 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sac;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Ulfat Fruitwala
 */
public class Digraph 
{
    private int V;
    private int E;
    private Bag<Edge>[] adj;
    
    //Constructor initializes Array list
    public Digraph(int V)
    {
        this.V = V;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for(int v = 0; v<V; v++)
            adj[v] = new Bag<>();
    }
    
    //Constructor adds edges
    public Digraph(int vV, int eE)
    {
        this.V = vV;
        this.E = 0;
        adj = (Bag<Edge>[]) new Bag[V];
        for(int v = 0; v<V; v++)
            adj[v] = new Bag<>();
        int[]arr = new int[384];
        int counter = 0;
        
        try{
            BufferedReader r = new BufferedReader(new FileReader("C:\\ABC.txt"));
            String temp;
            while((temp = r.readLine())!=null)
                {
                    int a = Integer.parseInt(temp);
                    arr[counter]=a;
                    counter++;
                }
        }
        catch (Exception e) {
        }
        for(int i = 0; i < arr.length; i+=3){
            addEdge(new Edge(arr[i], arr[i+1], arr[i+2]));
            addEdge(new Edge(arr[i+1], arr[i], arr[i+2]));
        }
    }
    
    public void addEdge(Edge x)
    {
        adj[x.from()].add(x);
        E++;
    }
    
    public int V(){
        return V;
    }
    
    public int E(){
        return E;
    }
    
    
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }
    
    public void display()
    {
        for(int i = 0; i <V; i++)
        {
            System.out.println("vertex " + i);
            for(Edge w:adj(i))
                System.out.println(w);
            System.out.println();
        } 
        System.out.println();
    }
     
}
