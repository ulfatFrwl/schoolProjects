/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spellchecker;
/**
 *
 * @author Ulfat Fruitwala
 */
public class SeparateChaining<Value> 
{
    private int N;  //number of objects
    private int M;  //hash table size
    private SequentialSearch<Value>[] st;   //array of objects
    
    public SeparateChaining()
    {
        this(997);
    }
    
    public SeparateChaining(int M)
    {
        this.M = M;
        st = (SequentialSearch<Value>[]) new SequentialSearch[M];
        for(int i = 0; i < M; i++)  //initialization of each bag
            st[i] = new SequentialSearch<>();   
    }
    
    public int hash(Value val)
    {
        return (val.hashCode() & 0x7fffffff) % M;
    }
    
    public Value get(Value val)
    {
        return  st[hash(val)].get(val);
    }
    
    public boolean contains(Value val)
    {
        return st[hash(val)].contain(val);
    }
    
    public void put(Value val)
    {
        st[hash(val)].put(val);
    }
    
    public void display()
    {
        for(int i = 0; i < M; i++)
        {
            st[i].dis();
        }
    }
    
    public int hashSize()
    {
        return M;
    }
    
}
