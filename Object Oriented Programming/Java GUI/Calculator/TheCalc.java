package common;

import java.util.Stack;

public class TheCalc {
	
	String input;
	public String postfix = "";
	public String result;
	
	public TheCalc(String IN) {
		
		input = IN;
		toPostfix();
		result();
	}
	

	//toPostfix function
	private void toPostfix()
	{
		String x = input;
        Stack<Character> opStack = new Stack();

        for(int i = 0; i < x.length(); i++)
        {
            char current = x.charAt(i);

            if(!isOperator(current))
            {
                postfix += current;
            }
            
            else if(current == '+' || current == '-' || current == '*' || current == '/' || current == '^')
            {
                if(opStack.isEmpty())
                    opStack.push(current);
                
                else
                {
                 while(!opStack.isEmpty() && precedence(opStack.peek(), current) && opStack.peek() != '(')
                    {
                        postfix += opStack.peek();
                        opStack.pop();
                    }
                 opStack.push(current);
                }
            }
            
            else if (current == '(')
            {
                opStack.push(current);
            }
            
            else if(current == ')')
            {
                while(opStack.peek() != '(')
                    postfix += opStack.pop();
                opStack.pop();
            }
        }
        while(opStack.isEmpty() != true)
            {
                postfix += opStack.pop();
            }
        
	}
	
	//Result function
	private void result()
	{
		String y = postfix;
		Stack<Double> operands = new Stack();
        
        for (int i = 0; i < y.length(); i++)
        {
            char curChar = y.charAt(i);
           
            if(!isOperator(curChar))
            {
                double num = Character.getNumericValue(curChar);
                operands.push(num);
            }
            
            if(isOperator(curChar))
            {
                double temp1 = operands.pop();
                double temp2 = operands.pop();
                
                switch(curChar)
                {
                    case '+': operands.push(temp2+temp1);
                    break;
                        
                    case '-': operands.push(temp2-temp1);
                    break;
                        
                    case '*': operands.push(temp2*temp1);
                    break;
                        
                    case '/': operands.push(temp2/temp1);
                    break;
                        
                    case '^': operands.push(Math.pow(temp2, temp1));
                    break;
                }
            }
        }
        result = Double.toString(operands.pop());
	}
	
	 //Returns true if character is an operator
    private static boolean isOperator(char x)
    {
        if(x == '+' || x == '-' || x == '*' || x =='/' || x== '^' || x=='(' || x==')')
            return true;
        return false;
    }
    
    //Returns a number based on the precedence of each operator
    private static int OperatorPrecedence(char x)
    {
        if(x == '(' || x==')')
            return 1;
        if(x == '+' || x=='-')
            return 2;
        if(x=='*' || x=='/')
            return 3;
        if(x=='^')
            return 4;
        return 0;
    }
    
    //Returns true if character x is ^
    private static boolean rightAssociative(char x)
    {
        if(x == '^')
            return true;
        return false;
    }
    
    //Compares two characters
    private static boolean precedence(char x, char y)
    {
        if(OperatorPrecedence(x)>OperatorPrecedence(y))
            return true;
        if(OperatorPrecedence(x)==OperatorPrecedence(y))
        {
            if(rightAssociative(x) && rightAssociative(y))
                return false;
            else
                return true;
        }
        return false;
    }

}
