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
public class checkSpelling
{
    public SeparateChaining ST = new SeparateChaining();
    private String W;
    
    public checkSpelling(SeparateChaining ST){
        this.ST = ST;
    }
    
    public checkSpelling(SeparateChaining ST,String word){
        this.ST = ST;
        this.W = word;
    }
    
    public void check(String word)
    {
        W = word;
        if(ST.contains(W))
        {
            System.out.println("No mistakes found");
            return;
        }
        
        char[]arr = word.toCharArray();        //changing input word to a char array
        Queue theWord = new Queue();           //changing input word to a queue
        for(int i = 0; i<W.length(); i++)
            theWord.insert_rear(W.charAt(i));
        System.out.println("INPUT: " + word);
        addToBeginningEnd(theWord);
        deleteBeginningEnd(theWord);
        exchangeAdj(arr);
    }
    
    //Adds letters to the beginning of the word and checks the dictionary for it
    private void addToBeginningEnd(Queue theWord)
    {
        //Adding letters a to z
        for(char i = 97; i<=122; i++)
        {
            String tempWord;
            theWord.insert_front(i);
            tempWord = theWord.firstToLastSTR();
            if(ST.contains(tempWord))
                System.out.println(tempWord);
            theWord.remove_front();
            
            theWord.insert_rear(i);
            tempWord = theWord.firstToLastSTR();
            if(ST.contains(tempWord))
                System.out.println(tempWord);
            theWord.remove_end();
        }
        
        //Adding letters A to Z
        for(char j = 65; j<=90; j++)
        {
            String tempWord;
            theWord.insert_front(j);
            tempWord = theWord.firstToLastSTR();
            if(ST.contains(tempWord))
                System.out.println(tempWord);
            theWord.remove_front();
            
            theWord.insert_rear(j);
            tempWord = theWord.firstToLastSTR();
            if(ST.contains(tempWord))
                System.out.println(tempWord);
            theWord.remove_end();
        }
    }
    
    //Adds letters to the end of the word and checks dictionary for it
    private void deleteBeginningEnd(Queue theWord)
    {
        //Delete the beginning letter
        Object x = theWord.removeFr();
        if(ST.contains(theWord.firstToLastSTR()))
            System.out.println(theWord.firstToLastSTR());
        theWord.insert_front(x);
        
        //Delete the end letter
        Object y = theWord.removeEn();
        if(ST.contains(theWord.firstToLastSTR()))
            System.out.println(theWord.firstToLastSTR());
        theWord.insert_rear(x);
    }
    
    //Exchanges adjacent letters and checks dictionary for it
    private void exchangeAdj(char[]arr)
    {
        for(int i = 0; i< arr.length-1; i++)
        {
            String word = "";
            swap(i, i+1, arr);
            for(int j = 0; j < arr.length; j++)
                word += arr[j];
            if(ST.contains(word))
                System.out.println(word);
            swap(i, i+1, arr);
        }
    }
    
    private void swap(int x, int y, char []arr)
    {
        char temp = arr[x];
        arr[x] = arr[y];
        arr[y] = temp;
    }
    
    public void display()
    {
        ST.display();
    }
}
