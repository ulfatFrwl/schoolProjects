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
public class Bag<Item> implements Iterable<Item>
{
    private Node<Item> first;
    private int n;

    private class Node<Item>{
        private Item item;
        private Node next;
    }
    
    public Bag(){
        first = null;
        n = 0;
    }
    
    public boolean isEmpty(){
        return first == null;
    }
    
    public int size(){
        return n;
    }
    
    public void add(Item item){
        Node oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldFirst;
        n++;
    }
    
    public void display()
    {
        Node temp = first;
        while(temp != null)
        {
            System.out.println(temp.item);
            temp = temp.next;
        }
    }
    
    public Iterator<Item> iterator()  {
        return new ListIterator<Item>(first);  
    }
    
    private class ListIterator<Item> implements Iterator<Item> {
        private Node<Item> current;

        public ListIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
}