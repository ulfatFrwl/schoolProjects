/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package quicksort;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.*;

/**
 *
 * @author Ulfat Fruitwala
 */
public class Quicksort<T extends Comparable<T>>{
    
    private T[]a;

    public Quicksort(int cap) {
        a = (T[])(new Comparable[cap]);    
    }
    
    private boolean less(Comparable i, Comparable j){
        if(i==j)    return false;
        return i.compareTo(j) < 0;
    }

    private void exchange(int i, int j){
        T temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }
    
    private int partition(int lo, int hi)
    {
        int i = lo, j = hi+1;
        while(true)
        {
            while(less(a[++i], a[lo]))
                if(i==hi)   break;
            while(less(a[lo], a[--j]))
                if(j==lo)   break;
            if(i>=j)    break;
            exchange(i, j);
        }
        exchange(lo, j);
        
        return j;
    }
    
    //Uses the median element for partition
    ////Exchanges first element with median element 
    //and then continues the regular partition method above
    private int partitionMedian (int lo, int hi)
    {
        int median = getMedianPivot(lo, hi);
        exchange(lo, median);
        
        return partition(lo, hi);
    }
    
    private void sort(int lo, int hi)
    {
        if(hi<=lo) return;
        
        //If sub-array size is less than 30
        if((hi-lo)<=30)
        {
            insertionSort(lo, hi+1);
        }
        
        else 
        {
            int j = partitionMedian(lo, hi);
            sort(lo, j-1);
            sort(j+1, hi);
        }
    }
    //Getting the median
    private int getMedianPivot(int lo, int hi)
    {
        int mid = (lo+hi)/2;
        if(less(a[lo], a[mid]) && !less(a[hi], a[mid])) return mid;
        else if(!less(a[lo], a[mid])&& less(a[hi], a[mid])) return mid;
        else if(less(a[lo], a[mid]) && !less(a[lo], a[hi])) return lo;
        else if(!less(a[lo], a[mid]) && less(a[lo], a[hi])) return lo;
        return hi;
    }
    
    private void insertionSort(int lo, int hi)
    {
        for(int i = lo+1; i < hi; i++)
        {
            for(int j = i; j>0 && less(a[j], a[j-1]); j--)
                exchange(j, j-1);
        }
    }
    
    private void display()
    {
        for(int i = 0; i < a.length; i++)
        {
            System.out.println(a[i]);
        }
    }

    public static void main(String[] args) 
    {
        Quicksort QS = new Quicksort(1000);
        try {
            BufferedReader f = new BufferedReader(new FileReader("C:\\1000.txt"));
            for(int i = 0; i< 1000; i++)
            {
                QS.a[i] = f.read();
            }
        } catch (Exception e) {
        }
        
        long startTime = System.currentTimeMillis();
        
        QS.display();
        QS.sort(0, QS.a.length-1);
        System.out.println();
        System.out.println("---------------Sorted---------------");
        QS.display();
        
        long endTime = System.currentTimeMillis();
        long time = endTime-startTime;
        double elapsedT = time/1000;
        System.out.println("Time elapsed: " + elapsedT);
    }

}
