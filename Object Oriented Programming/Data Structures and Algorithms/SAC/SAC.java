/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sac;

import jdk.nashorn.internal.parser.TokenType;

/**
 *
 * @author Ulfat Fruitwala
 */
public class SAC {

    public static int minIndex;
    public static double minValue;
    public static void main(String[] args) 
    {
         
        Digraph G = new Digraph(50,0);
        int station_7 = 7, station_14 = 14, station_25 = 25, station_28 = 28, station_40 = 40; 
        SP sp7 = new SP(G, station_7);
        SP sp14 = new SP(G, station_14);
        SP sp25 = new SP(G, station_25);
        SP sp28 = new SP(G, station_28);
        SP sp40 = new SP(G, station_40);
        int c7 = 0, c14 = 0, c25 = 0, c28 = 0, c40 = 0;
        
        for(int t = 0; t < G.V(); t++)
        {
            getMin(t, sp7, sp14, sp25, sp28, sp40);
            System.out.println(minIndex + " to " + t +": " + minValue);
            if(minIndex==7)
            {
                c7++;
                if(sp7.hasPathTo(t))
                    for(Edge x : sp7.pathTo(t))
                        System.out.println(x + " ");
            }
            else if(minIndex==14)
            {
                c14++;
                if(sp14.hasPathTo(t))
                    for(Edge x : sp14.pathTo(t))
                        System.out.println(x + " ");
            }
            else if(minIndex==25)
            {
                c25++;
                if(sp25.hasPathTo(t))
                    for(Edge x : sp25.pathTo(t))
                        System.out.println(x + " ");
            }
            else if(minIndex==28)
            {
                c28++;
                if(sp28.hasPathTo(t))
                    for(Edge x : sp28.pathTo(t))
                        System.out.println(x + " ");
            }
            else
            {
                c40++;
                if(sp40.hasPathTo(t))
                    for(Edge x : sp40.pathTo(t))
                        System.out.println(x + " ");
            }
            System.out.println();
        }
        System.out.println("Number of customers served from each stations: ");
        System.out.println("Station 7: " + c7);
        System.out.println("Station 14: " + c14);
        System.out.println("Station 25: " + c25);
        System.out.println("Station 28: " + c28);
        System.out.println("Station 40: " + c40);
    }
    
    public static void getMin(int x, SP a, SP b, SP c, SP d, SP e)
    {
        minIndex = -1; minValue = -1;
        double[]arr = new double[5];
        arr[0] = a.distTo(x);
        arr[1] = b.distTo(x);
        arr[2] = c.distTo(x);
        arr[3] = d.distTo(x);
        arr[4] = e.distTo(x);
        bubbleSort(arr);
        minValue = arr[0];
        
        if((a.distTo(x))==minValue)
            minIndex = 7;
        else if((b.distTo(x))==minValue)
            minIndex = 14;
        else if((c.distTo(x))==minValue)
            minIndex = 25;
        else if((d.distTo(x))==minValue)
            minIndex = 28;
        else    minIndex = 40;
        
        
    }
    
    static void bubbleSort(double arr[])
    {
        int n = arr.length;
        for (int i = 0; i < n-1; i++)
            for (int j = 0; j < n-i-1; j++)
                if (arr[j] > arr[j+1])
                {
                    // swap temp and arr[i]
                    double temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
    }

}
