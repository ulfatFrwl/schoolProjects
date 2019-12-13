/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package manage;

import java.sql.*;

/**
 *
 * @author Ulfat Fruitwala
 */
public class Connection 
{
    Statement stmt;
    
    
    ResultSet rset;
    protected int countValue = 0;
   
    
    protected int ID = 0;
    protected String name = null;
    protected String date = null;
    protected String description = null;
    protected float price = 0;
    protected float tempPrice = 0;
    public String person1OwesPerson2;
    public String person2OwesPerson1;
    
    String showAllStatement = "select * from expense";
    String showIndividualStatement = "select * from individualexpense";
    String insertAllStatement, insertIndividualStatement;
    String getCountStatement_AllExpense = "select count(*) from expense";
    String getCountStatement_Individual = "select count(*) from individualexpense";
    String updateIndividualPrice;
    
    public Connection() throws SQLException
    {
        try {
         java.sql.Connection conn = DriverManager.getConnection(
               "jdbc:mysql://localhost:3306/apartmentexpense?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC",
               "myuser", "xxxx");
         stmt = conn.createStatement();
         
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    //Executing a query to search certain data from database
    public ResultSet selectQuery(String qstmt) throws SQLException
    {
        return stmt.executeQuery(qstmt);
    }
    
    //Inserting into the database
    public void insertInformation() throws SQLException
    {
        insertAllStatement = "insert into expense (name, date, description, price)values('"+name +"','" + date+ "','" + description + "'," + Float.toString(price)+")";
        stmt.executeUpdate(insertAllStatement);        
        updateIndividualExpense();
        
        //Set everything back to zero or null
        name = null;
        date = null;
        description = null;
        price = 0;
        tempPrice = 0;
    }
    
    protected int getCount(String str) throws SQLException
    {
         rset = stmt.executeQuery(str);
         while(rset.next()){
             countValue = rset.getInt("count(*)");
         }
         return countValue;
    }
    
    //Checks if a name exists in individualExpense table
    protected boolean nameExists(String nm) throws SQLException
    {
        rset = stmt.executeQuery(showIndividualStatement);
        while(rset.next())
        {
            if(rset.getString("name").equals(nm))
            {
                tempPrice = rset.getFloat("totalExpense");
                System.out.println("temp price: " + tempPrice);
                return true;
            }
        }
        return false;
    }
    
    //Updates the individualExpense table in the database
    void updateIndividualExpense() throws SQLException
    {
        if(nameExists(name))
        {
            System.out.println(name);
            tempPrice += price;
            updateIndividualPrice = "update individualexpense set totalExpense = "+ Float.toString(tempPrice)+ " where name = '" + name+ "'";
            stmt.executeUpdate(updateIndividualPrice);
        }
        
        else
        {
            insertIndividualStatement = "insert into individualexpense values('" + name + "'," + Float.toString(price)+")";
            stmt.executeUpdate(insertIndividualStatement);
        }
    }
    
    void settoZero()
    {
        person1OwesPerson2 = "";
        person2OwesPerson1 = "";
    }
    
    void calculateDifferences(String[] nameArray, Float[] priceArray)
    {
        float temp = 0;
        settoZero();
        
        if(priceArray[0]>priceArray[1])
        {
            temp = (priceArray[0]-priceArray[1])/2;
            person2OwesPerson1 = nameArray[1] + " owes " + nameArray[0] + " " + Float.toString(temp);
        }
        
        if(priceArray[1]>priceArray[0])
        {
            temp = (priceArray[1]-priceArray[0])/2;
            person1OwesPerson2 = nameArray[0] + " owes " + nameArray[1] + " " + Float.toString(temp);
        }   
    }
    
    void deleteCurrentEntries() throws SQLException
    {
        settoZero();
        stmt.executeUpdate("insert into backup(name, date, description, price) select name, date, description, price from expense");
        stmt.executeUpdate("truncate table expense");
        stmt.executeUpdate("truncate table individualexpense");
    }
}


