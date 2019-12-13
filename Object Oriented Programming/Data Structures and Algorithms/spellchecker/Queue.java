/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spellchecker;


/**
 *
 * @author UlfatFruitwala
 * @param <Value>
 */
public class Queue<Value>
{
    private Node first;
    private Node last;
    private int number;
    
    private class Node
    {
        Value data;
        Node next = null;
        Node previous = null;
    }
    
    public Node first()
    {
        return first;
    }
    
    //Returns true if deque is empty
    public boolean empty()
    {
        if(first==null && last == null)
            return true;
        else
            return false;
    }
    
    //Inserts a new node in the beginning of the deque
    public void insert_front(Value x)
    {
        if(empty())
        {
            first = new Node();
            last = first;
            first.data = x;
            number++;
        }
        else
        {
            Node temp = first;
            first = new Node();
            first.data = x;
            first.next = temp;
            temp.previous = first;
            number++;
        }
    }
    
    //Inserts a new node at the end of the deque
    public void insert_rear(Value x)
    {
        if(empty())
        {
            insert_front(x);
            number++;
        }
        else
        {
            Node temp = last;
            last = new Node();
            last.data = x;
            temp.next = last;
            last.previous = temp;
            number++;
        }
    }
    
    //Removes a node from the beginning of the deque
    public void remove_front()
    {
        Node temp = first.next;
        first = null;
        first = temp;
        first.previous = null;
        number--;
    }
    
    public Value removeFr()
    {
        Value t = first.data;
        remove_front();
        return t;
    }
    
    //Removes a node from the end of the deque
    public void remove_end()
    {
        Node temp = last.previous;
        last = null;
        last = temp;
        last.next = null;
        number--;
    }
    
    public Value removeEn()
    {
        Value t = last.data;
        remove_end();
        return t;
    }
    
    //Removes a specific node
    public void remove(Value x)
    {
        if(first.data == x)
            remove_front();
        
        if(last.data == x)
            remove_end();
        else
        {
            Node temp = first, previous = null;
            while(temp != null && temp.data!=x)
            {
                previous = temp;
                temp = temp.next;
            }
            
            if(temp == null)
                return;
            
            previous.next = temp.next;
            (temp.next).previous = previous;
            temp = null;
            number--;
        }
    }
    
    //Inserts a new node before a given node
    public void insert_before (Value x, Value y)
    {
        if(first.data == y)
        {
            insert_front(x);
        }
        else
        {
            Node temp = first, previous = null;
            while(temp != null && temp.data != y)
               {
                previous = temp;
                temp = temp.next;
               }
            if(temp==null)
                return;
            previous.next = new Node();
            (previous.next).data = x;
            (previous.next).previous = previous;
            (previous.next).next = temp;
            temp.previous = (previous.next);
            number++;
        }
    }
    
    //Inserts a new node after a given node
    public void insert_after (Value x, Value y)
    {
        if(last.data == y)
        {
            insert_rear(x);
        }
        
        else
        {
            Node temp = first, nextNode = temp.next;
            while(temp != null && temp.data != y)
               {
                temp = nextNode;
                nextNode = nextNode.next;
               }
            if(temp==null)
                return;
            temp.next = new Node();
            (temp.next).data = x;
            (temp.next).previous = temp;
            (temp.next).next = nextNode;
            nextNode.previous = (temp.next);
            number++;
        }
    }
    
    //Moves an existing node to the front of queue
    public void move_to_front(Value x)
    {
        if (first.data == x)
            return;
        
        else if(last.data == x)
        {
            Node temp = first, previous = last.previous;
            previous.next = null;
            first = last;
            first.next = temp;
            first.previous = null;
            temp.previous = first;
            last = previous;
        }
        else
        {
            Node previous = null, temp = first, next = temp.next, temp2;
            while(temp!= null && temp.data !=x)
            {
                previous = temp;
                temp = next;
                next = temp.next;
            }
            if(temp==null)
                return;
            previous.next = next;
            next.previous = previous;

            temp2 = first;
            first = temp;
            first.next = temp2;
            first.previous = null;
            temp2.previous = first;
        }
    }
    
    //Moves an existing node to the end of queue
    public void move_to_end(Value x)
    {
        if(last.data == x)
            return;
        
        else if(first.data == x)
        {
            Node temp = last, next = first.next;
            next.previous = null;
            last = first;
            last.next = null;
            temp.next = last;
            last.previous = temp;
            first = next;
        }
        else
        {
            Node previous = null, temp = first, next = temp.next, temp2;
            while(temp!= null && temp.data != x)
            {
                previous = temp;
                temp = next;
                next = temp.next;
            }
            if(temp==null)
                return;
            previous.next = next;
            next.previous = previous;
            
            temp2 = last;
            last = temp;
            temp2.next = last;
            last.next = null;
            last.previous = temp2;
        }
    }
    
    //Displays entire deque. First element to last element
    public void display_first_to_last()
    {
        Node temp = first;
        while(temp != null)
        {
            System.out.println(temp.data);
            temp = temp.next;
        }
        System.out.println();
            
    }

    public String firstToLastSTR()
    {
        Node temp = first;
        String word = "";
        while(temp != null)
        {
            word += temp.data;
            temp = temp.next;
        }
        return word;
    }
    
    //Displays entire deque. Last element to first element
    public void display_last_to_first()
    {
        Node temp = last;
        while(temp != null)
        {
            System.out.println(temp.data);
            temp = temp.previous;
        }
        System.out.println();
    }
}