/*tem
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package spellchecker;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 *
 * @author Ulfat Fruitwala
 */
public class SpellChecker {


    public static void main(String[] args) 
    {
        SeparateChaining ST = new SeparateChaining();
        Queue<Character> qq = new Queue<Character>();
        checkSpelling SPELL = new checkSpelling(ST);
        //Initializing dictionary
        try {
            BufferedReader f = new BufferedReader(new FileReader("C:\\wordTest.txt"));
            String temp = null;
            while((temp = f.readLine()) != null)
            {
                SPELL.ST.put(temp);
            }
        } catch (Exception e) {
        }
        System.out.println("---Found words---");
        SPELL.check("CSI");
        SPELL.check("Aall");
        SPELL.check("manN");
        SPELL.check("nly");
        SPELL.check("whe");
      
        
    }
    
    
    
   

}
