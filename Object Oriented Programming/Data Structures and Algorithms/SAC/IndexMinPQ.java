/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sac;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *
 * @author Ulfat Fruitwala
 */
public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer>     
{
    private int maxN;
    private int n;
    private int [] pq;
    private int [] qp;
    private Key[] keys;
    
    public IndexMinPQ(int maxN)
    {
        this.maxN = maxN;
        n = 0;
        keys = (Key[]) new Comparable[maxN+1];
        pq = new int[maxN+1];
        qp = new int[maxN+1];
        for(int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }
    
    public void changeKey(int i, Key key) {
        if (i < 0 || i >= maxN) throw new IllegalArgumentException();
        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }
    
    public void change(int i, Key key) {
        changeKey(i, key);
    }
    
    public boolean isEmpty(){
        return n==0;
    }
    
    public boolean contains(int i){
        return qp[i]!=-1;
    }
    
    public int size(){
        return n;
    }
    
    public void insert(int i, Key key){
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }
    
    public int minIndex()
    {
        return pq[1];
    }
    
    public int delMin()
    {
        int min = pq[1];
        exch(1, n--);
        sink(1);
        qp[min] = -1;
        keys[min] = null;
        pq[n+1] = -1;
        return min;
    }
    
    public Key getKey(int i){
        return keys[i];
    }
    
    private boolean greater(int i, int j)
    {
        return keys[pq[i]].compareTo(keys[pq[j]])>0;
    }
    
    private void exch(int i, int j)
    {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }
    
      private void swim(int k){
        while (k > 1 && greater(k/2, k)) 
        {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k){
        while (2*k <= n) 
        {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    @Override
    public Iterator<Integer> iterator() { return new HeapIterator(); }

    private class HeapIterator implements Iterator<Integer> {
        // create a new pq
        private IndexMinPQ<Key> copy;

        // add all elements to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            copy = new IndexMinPQ<Key>(pq.length - 1);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i], keys[pq[i]]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }

}
