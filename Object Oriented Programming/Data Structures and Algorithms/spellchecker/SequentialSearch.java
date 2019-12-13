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
public class SequentialSearch <Value>
{
    private int n;  //number of objects
    private Node first;
    
    private class Node{
        private Value val;
        private Node next;
        
        public Node(Value val, Node next){
            this.val = val;
            this.next = next;
        }
    }
    
    public boolean contain(Value val){
        return get(val)!= null;
    }
    
    
    public boolean isEmpty()
    {
        return size() == 0;
    }
    
    public int size()
    {
        return n;
    }
    
    public Value get(Value val)
    {
        for(Node x = first; x != null; x = x.next)
        {
            if(val.equals(x.val))
                return x.val;
        }
        return null;
    }
    
    public void put(Value val)
    {
        if(val == null)
        {
            delete(val);
            return;
        }
        for(Node x = first; x != null; x = x.next)
        {
            if(val.equals(x.val))
                return;
        }
        first = new Node(val, first);
        n++;
    }
    
    public void delete(Value val)
    {
        first = delete(first, val);
    }
    
    private Node delete(Node x, Value val)
    {
        if(x == null)   return null;
        if(val.equals(x.val))
        {
            n--;
            return x.next;
        }
        x.next = delete(x.next, val);
        return x;
    }
    
    public void dis()
    {
        for(Node x = first; x != null; x = x.next)
            System.out.println(x.val);
    }
    
}
