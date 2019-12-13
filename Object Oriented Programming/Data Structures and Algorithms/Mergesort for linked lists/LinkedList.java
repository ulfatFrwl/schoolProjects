/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package linkedlist;

/**
 *
 * @author Ulfat Fruitwala
 */
public class LinkedList{
    
    node head = null;
    node start;
    node sortedListNode;
    
    
    public class node<T extends Comparable<T>>{
        T T;
        node next;

        public node(T x) {
            T = x;
            next = null;
        }
    }
    
    private node getPointer (node start)
    {
        node temp = start;
        
        while(temp.next!= null && temp.next.T.compareTo(temp.T) >= 1)
        {
            temp = temp.next;
        }
        return temp;
        
    }
    
    private void merge(node x)
    {
        if(x == null)   return;
       
        node start2 = x; //starting point of list 2
        node end2 = getPointer(start2);   //end of list 2
        node temp = end2.next;
        getPointer(x).next = null;   //breaking list 1 and 2
        end2.next = null;            //breaking list 2 and 3
        
        sortedListNode = mergingList(sortedListNode, start2);
        merge(temp);
    }
  
    node mergingList(node S1, node S2)
    {
        node tempSorted = null;
        if(S1 == null) return S2;
        else if(S2 == null) return S1;
        
        if(S2.T.compareTo(S1.T) >= 1)
        {
            tempSorted = S1;
            tempSorted.next = mergingList(S1.next, S2);
        }
        else
        {
            tempSorted = S2;
            tempSorted.next = mergingList(S1, S2.next);
        }
        return tempSorted;
    }
    
    void setStart()
    {
        if(head!=null)
        {
            sortedListNode = head;
            start = getPointer(head).next;
        }
        node temp = sortedListNode;
        while (temp.next != start)
        {
            temp = temp.next;
        }
        temp.next = null;
            
    }
    
    private void add(String T)
    {
        if(head == null)
            head = new node(T);
        
        else
        {
            node temp = head;
            while(temp.next!=null)
                temp = temp.next;
            temp.next = new node(T);
        }
    }
    
    private void displayStarttoEnd(node start, node end)
    {
        node temp = start;
        while (temp != end)
        {
            System.out.println(temp.T);
            temp = temp.next;
        }
        System.out.println(end.T);
    }
    
    private void display(node x)
    {
        node temp = x;
        while (temp != null)
        {
            System.out.println(temp.T);
            temp = temp.next;   
        }
    }
    
    public static void main(String[] args) 
    {
        LinkedList listOne = new LinkedList();
        LinkedList sortedListOne = new LinkedList();
        listOne.add("Alize");
        listOne.add("Amy");
        listOne.add("Cathy");
        listOne.add("Dandan");
        listOne.add("Annie");
        listOne.add("Beatrice");
        listOne.add("Rachael");
        listOne.add("Zed");
        listOne.add("Diana");
        listOne.add("Ekko");
        listOne.add("Zyra");
        listOne.add("Typhoon");
        listOne.add("Alana");
        System.out.println("-----Unsorted-----");
        listOne.display(listOne.head);
        System.out.println();
        
        listOne.setStart();
        listOne.merge(listOne.start);
        
        sortedListOne.head = listOne.sortedListNode;
        System.out.println("-----Sorted-----");
        sortedListOne.display(sortedListOne.head);
    }

}
