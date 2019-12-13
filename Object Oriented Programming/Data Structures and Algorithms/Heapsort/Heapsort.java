/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package heapsort;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Ulfat Fruitwala
 */
public class Heapsort<T extends Comparable<T>>{
    
    private T[]PQ;  //array
    private int N;  //number of elements in PQ
    
    //Constructor
    public Heapsort(int cap) {
        PQ = (T[])(new Comparable[cap]);
        N = 0;
    }
    
    private void sort()
    {
        for(int i = (N/3)-1; i>=0; i--)
            sink(i);
        while(N>0)
        {
            exchange(0, --N);
            sink(0);
        }
    }
    
    public boolean isEmpty()    {return N==0;}
    
    private boolean less(int i, int j) {
        return PQ[i].compareTo(PQ[j]) < 0;
        //if(PQ[i].compareTo(PQ[j])<= -1)  return true;
        //return false;
    }
    
    private void exchange(int x, int y)
    {
        T temp = PQ[x];
        PQ[x] = PQ[y];
        PQ[y] = temp;
    }
    
    private void sink(int k)
    {
        while(3*k+1 <= N-1)
        {
            int j = 3*k+1;
            if(j<N-1 && less(j, j+1))
            {
                j++;
                if(j<N-1 && less(j, j+1))
                    j++;
            }
            
            else if(j<N-1 && !less(j, j+1))
            {
                if((j+1)<N-1 && less(j, j+2))
                    j = j+2;
            }
            
            if(!less(k, j))     break;
            exchange(k, j);
            k = j;
        }
    }
    
    private void display()
    {
        for(int i = 0; i< PQ.length; i++)
        {
            System.out.println(PQ[i]);
        }
    }
    
    public static void main(String[] args) 
    {
        Heapsort aHeap = new Heapsort(100);
        try {
            BufferedReader f = new BufferedReader(new FileReader("C:\\NA.txt"));
            
            for(int i = 0; i< 100; i++)
            {
                aHeap.PQ[i] = f.readLine();
                aHeap.N++;
            }
            
        } catch (Exception e) {
        }
        System.out.println("-----Unsorted-----");
        aHeap.display();
        aHeap.sort();
        System.out.println();
        System.out.println("-----Sorted-----");
        aHeap.display();
    }

}
