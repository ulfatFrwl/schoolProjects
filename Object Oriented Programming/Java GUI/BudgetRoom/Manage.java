/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package manage;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.ResultSet;


public class Manage implements ActionListener, MouseListener
{
    Connection connObject;
    
    //Variables to temporarily hold data
    protected int ID = 0;
    protected String name = null;
    protected String date = null;
    protected String description = null;
    protected float price = 0;
    protected float totalExpense = 0;
    String[] nameArr = new String[2];
    Float[] amountArr = new Float[2];
    int count = 0;
    
    //MAIN FRAME
    JFrame frame = new JFrame("Apartment Manager");
    Toolkit tk = Toolkit.getDefaultToolkit();
    
    //ALL IMAGES AND ICONS
    Image frameIcon = tk.getImage("C:\\Users\\Ulfat Fruitwala\\Documents\\NetBeansProjects\\Manage\\src\\manage\\house.png");      //Setting icon of the frame
    ImageIcon addEntryIcon = new ImageIcon("C:\\Users\\Ulfat Fruitwala\\Documents\\NetBeansProjects\\Manage\\src\\manage\\add.png");
    ImageIcon allEntriesIcon = new ImageIcon("C:\\Users\\Ulfat Fruitwala\\Documents\\NetBeansProjects\\Manage\\src\\manage\\list.png");
    ImageIcon roommateEntriesIcon = new ImageIcon("C:\\Users\\Ulfat Fruitwala\\Documents\\NetBeansProjects\\Manage\\src\\manage\\users.png");
    ImageIcon checkMarkIcon = new ImageIcon("C:\\Users\\Ulfat Fruitwala\\Documents\\NetBeansProjects\\Manage\\src\\manage\\check.png");
    ImageIcon clearAllIcon = new ImageIcon("C:\\Users\\Ulfat Fruitwala\\Documents\\NetBeansProjects\\Manage\\src\\manage\\clear.png");
    ImageIcon warningIcon = new ImageIcon("C:\\Users\\Ulfat Fruitwala\\Documents\\NetBeansProjects\\Manage\\src\\manage\\warning.png");
    
    //PANELS
    JPanel topPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel addInformationPanel = new JPanel();
    JPanel allEntriesPanel = new JPanel();
    JPanel roommateInformationPanel = new JPanel();
    
    //TOP PANEL BUTTONS
    JButton addEntry = new JButton(addEntryIcon);
    JButton viewAllEntries = new JButton(allEntriesIcon);
    JButton viewRoommates = new JButton(roommateEntriesIcon);
    JButton clearAll = new JButton(clearAllIcon);
    
    //BOTTOM PANEL LABEL
    JLabel bottomPanelLabel = new JLabel("CenterCenter", SwingConstants.CENTER);
    JLabel totalExpenseLabel = new JLabel("Total Expense", SwingConstants.CENTER);
    JLabel oweLabel1 = new JLabel("owe1", SwingConstants.CENTER);
    JLabel oweLabel2 = new JLabel("owe2", SwingConstants.CENTER);

    //INFORMATION PANEL TEXT FIELD and BUTTONS
    JTextField roommateNameTextField = new JTextField();
    JTextField dateTextField = new JTextField();
    JTextField descriptionTextField = new JTextField();
    JTextField priceTextField = new JTextField();
    JButton saveButton = new JButton("SAVE");
    
    //TABLES: one for all entries, another for individual roommate information
    //A scroll pane container will hold each table
    String[] columnNames1 = {"ID", "Name", "Date", "Description", "Price"};
    DefaultTableModel allEntriesModel = new DefaultTableModel(columnNames1, 0);
    JTable allEntriesTable = new JTable(allEntriesModel);
    JScrollPane allEntriesScrollPane = new JScrollPane(allEntriesTable);
    ResultSet rset;
    
    String[] columnNames2 = {"Name", "Total Expense"};
    DefaultTableModel roommateInformationModel = new DefaultTableModel(columnNames2,0);
    JTable roommateInformationTable = new JTable(roommateInformationModel);
    JScrollPane roommateInformationScrollPane = new JScrollPane(roommateInformationTable);
    
    //CONSTRUCTOR
    public Manage() throws SQLException
    {
        guiSetup();
        connObject = new Connection();
    }

    //SETTING UP MAIN FRAME
    void guiSetup(){
        
        //MAIN FRAME
        frame.setIconImage(frameIcon);
        frame.setSize(700, 700);
        frame.setResizable(false);
        frame.add(topPanel,BorderLayout.PAGE_START);
        frame.add(bottomPanel,BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        //PANELS AND SUB-PANELS
        topPanel();
        bottomPanel();
        informationPanel();
        allEntriesPanel();
        roommateInformationPanel();
    }
    
    //SETTING UP SUB-PANELS
    //top panel where main 3 buttons are located
    void topPanel()
    {
        //PANEL
        topPanel.setBackground(Color.yellow);
        topPanel.setPreferredSize(new Dimension(500, 100));
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 30));
        
            //adding buttons to the panel
        topPanel.add(addEntry);
        topPanel.add(viewAllEntries);
        topPanel.add(viewRoommates);
        topPanel.add(clearAll);
        
        //BUTTONS ADJUSTMENTS
        
                //button size
        addEntry.setPreferredSize(new Dimension(30, 30));
        viewAllEntries.setPreferredSize(new Dimension(30, 30));
        viewRoommates.setPreferredSize(new Dimension(30, 30));
        clearAll.setPreferredSize(new Dimension(30, 30));
        
                //button area
        addEntry.setContentAreaFilled(false);
        viewAllEntries.setContentAreaFilled(false);
        viewRoommates.setContentAreaFilled(false);
        clearAll.setContentAreaFilled(false);
        
                //button border
        addEntry.setBorder(null);
        viewAllEntries.setBorder(null);
        viewRoommates.setBorder(null);
        clearAll.setBorder(null);
        
                //ADDING action command for buttons
        addEntry.setActionCommand("add");
        viewAllEntries.setActionCommand("viewAll");
        viewRoommates.setActionCommand("roommateInfo");
        clearAll.setActionCommand("clearAll");
        
                //adding actionlistener to the buttons
        addEntry.addActionListener(this);
        viewAllEntries.addActionListener(this);
        viewRoommates.addActionListener(this);
        clearAll.addActionListener(this);
    }
    
    //bottom panel where 3 other panels live
    void bottomPanel()
    {
        //PANEL
        bottomPanel.setBackground(Color.white);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
      
        //LABEL
        bottomPanelLabel.setMaximumSize(new Dimension(150, 40));
        bottomPanelLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
       
        //PANELS AND LABELS AREN'T VISIBLE
        bottomPanelLabel.setVisible(false);
        totalExpenseLabel.setVisible(false);
        addInformationPanel.setVisible(false);
        allEntriesPanel.setVisible(false);
        roommateInformationPanel.setVisible(false);
         
        //Adding components to the panel
        bottomPanel.add(bottomPanelLabel);
        bottomPanel.add(addInformationPanel);
        bottomPanel.add(allEntriesPanel);
        bottomPanel.add(roommateInformationPanel);
    }
    
    //panel for inserting data (this panel lives in the bottom panel)
    void informationPanel()
    {
        //PANEL
        addInformationPanel.setBackground(Color.yellow);
        addInformationPanel.setMaximumSize(new Dimension(450, 400));
        addInformationPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        addInformationPanel.setLayout(new BoxLayout(addInformationPanel, BoxLayout.Y_AXIS));
        addInformationPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        addInformationPanel.add(roommateNameTextField);
        addInformationPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        addInformationPanel.add(dateTextField);
        addInformationPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        addInformationPanel.add(descriptionTextField);
        addInformationPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        addInformationPanel.add(priceTextField);
        addInformationPanel.add(Box.createRigidArea(new Dimension(0, 25)));

        addInformationPanel.add(saveButton);
        
        /////////TEXT FIELDS AND BUTTONS
        setDefaultTextValues();
        
        //sizing text fields
        roommateNameTextField.setMaximumSize(new Dimension(340, 30));
        dateTextField.setMaximumSize(new Dimension(340, 30));
        descriptionTextField.setMaximumSize(new Dimension(340, 30));
        priceTextField.setMaximumSize(new Dimension(340, 30));
        
        //aligning text fields
        roommateNameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        dateTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        priceTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        //setting borders
        roommateNameTextField.setBorder(null);
        dateTextField.setBorder(null);
        descriptionTextField.setBorder(null);
        priceTextField.setBorder(null);
        
        //save button adjustments
        saveButton.setMaximumSize(new Dimension(100, 30));
        saveButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveButton.setBackground(Color.white);
        
        //Add mouse listener to each of the text fields and action listener to the save button
        roommateNameTextField.addMouseListener(this);
        dateTextField.addMouseListener(this);
        descriptionTextField.addMouseListener(this);
        priceTextField.addMouseListener(this);
        saveButton.addActionListener(this);
    }

    //panel where all past entries are shown (lives in the bottom panel)
    void allEntriesPanel()
    {
        allEntriesPanel.setBackground(Color.yellow);
        allEntriesPanel.setMaximumSize(new Dimension(650, 500));
        allEntriesPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        allEntriesPanel.setLayout(new BoxLayout(allEntriesPanel, BoxLayout.Y_AXIS));
        
        totalExpenseLabel.setMaximumSize(new Dimension(150, 40));
        totalExpenseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        allEntriesPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        allEntriesPanel.add(allEntriesScrollPane);
        allEntriesPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        allEntriesPanel.add(totalExpenseLabel);
        
        allEntriesTable.setFillsViewportHeight(true);
        allEntriesScrollPane.setMaximumSize(new Dimension(480, 250));
    }
    
    //panel where each roommates' total expense is shown (lives in the bottom panel)
    void roommateInformationPanel()
    {
        roommateInformationPanel.setBackground(Color.yellow);
        roommateInformationPanel.setMaximumSize(new Dimension(600, 500));
        roommateInformationPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        roommateInformationPanel.setLayout(new BoxLayout(roommateInformationPanel, BoxLayout.Y_AXIS));
        roommateInformationPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        roommateInformationPanel.add(roommateInformationScrollPane);
        
        oweLabel1.setMaximumSize(new Dimension(150, 35));
        oweLabel2.setMaximumSize(new Dimension(150, 35));
        oweLabel1.setAlignmentX(Component.CENTER_ALIGNMENT);
        oweLabel2.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        roommateInformationPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        oweLabel1.setVisible(false);    oweLabel2.setVisible(false);
        roommateInformationPanel.add(oweLabel1);
        roommateInformationPanel.add(oweLabel2);
        
        
        roommateInformationTable.setFillsViewportHeight(true);
        roommateInformationScrollPane.setMaximumSize(new Dimension(400, 100));
    }
    
    //MISC FUNCTIONS
    void setDefaultTextValues()
    {
        roommateNameTextField.setForeground(Color.LIGHT_GRAY);
        dateTextField.setForeground(Color.LIGHT_GRAY);
        descriptionTextField.setForeground(Color.LIGHT_GRAY);
        priceTextField.setForeground(Color.LIGHT_GRAY);
        
        roommateNameTextField.setText("Name of Purchaser");
        dateTextField.setText("Date");
        descriptionTextField.setText("Description of Purchase");
        priceTextField.setText("Price");
    }
    
    //ActionListener and MouseListener
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getActionCommand().equals("add"))
        {
            bottomPanelLabel.setText("New Entry");
            bottomPanelLabel.setVisible(true);
            addInformationPanel.setVisible(true);
            
            //Make all other panels non-visible
            allEntriesPanel.setVisible(false);
            roommateInformationPanel.setVisible(false);
            totalExpenseLabel.setVisible(false);
        }
        
        if(ae.getActionCommand().equals("viewAll"))
        {
            allEntriesModel.setRowCount(0);
            totalExpense = 0;
            bottomPanelLabel.setText("All Entries");
            bottomPanelLabel.setVisible(true);
            totalExpenseLabel.setVisible(true);
            allEntriesPanel.setVisible(true);
            
            //Make all other panels non-visible
            addInformationPanel.setVisible(false);
            roommateInformationPanel.setVisible(false);
            
                //Data is retrieved from the database
            try 
            {
                rset = connObject.selectQuery(connObject.showAllStatement);
                //Parsing through each row of database query
                while(this.rset.next())
                {
                    this.ID = this.rset.getInt("id");
                    this.name = this.rset.getString("name");
                    this.date = this.rset.getString("date");
                    this.description = this.rset.getString("description");
                    this.price = this.rset.getFloat("price");
                    totalExpense += this.price;
                    String[] dataRow={Integer.toString(this.ID),this.name,this.date,this.description,Float.toString(this.price)};
                    allEntriesModel.addRow(dataRow);
                }
                totalExpenseLabel.setText("Total Expense: $" + totalExpense);
            } 
            catch (SQLException ex) {
                Logger.getLogger(Manage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(ae.getActionCommand().equals("roommateInfo"))
        {
            connObject.settoZero();
            count = 0;
            roommateInformationModel.setRowCount(0);
            bottomPanelLabel.setText("Individual Information");
            bottomPanelLabel.setVisible(true);
            roommateInformationPanel.setVisible(true);
            
            //Make all other panels non-visible
            addInformationPanel.setVisible(false);
            allEntriesPanel.setVisible(false);
            try 
            {
                rset = connObject.selectQuery(connObject.showIndividualStatement);
                while(this.rset.next())
                {
                    this.name = this.rset.getString("name");
                    this.price = this.rset.getFloat("totalExpense");
                    String[] dataRow = {this.name, Float.toString(this.price)};
                    roommateInformationModel.addRow(dataRow);
                    nameArr[count] = this.name;
                    amountArr[count] = this.price;
                    count++;
                }
                connObject.calculateDifferences(nameArr, amountArr);
                if(!(connObject.person2OwesPerson1).equals("")) 
                {
                    oweLabel1.setText(connObject.person2OwesPerson1);
                    System.out.println("owelabel1: " + oweLabel1.getText());
                    System.out.println("person2owes: " + connObject.person2OwesPerson1);
                    connObject.settoZero();
                    oweLabel1.setVisible(true);
                }
                else if(!(connObject.person1OwesPerson2.equals("")))
                {
                    oweLabel2.setText(connObject.person1OwesPerson2);
                    System.out.println("owelabel1: " + oweLabel2.getText());
                    System.out.println("person2owes: " + connObject.person1OwesPerson2);
                    connObject.settoZero();
                    oweLabel2.setVisible(true);
                }
                else
                {
                    oweLabel1.setText("nobody owes");
                    connObject.settoZero();
                    oweLabel1.setVisible(true);
                }
            } catch (Exception e) {
            }
            
        }
        
        if(ae.getActionCommand().equals("clearAll"))
        {
            int option = JOptionPane.showConfirmDialog(frame, "This will delete all current entires", "Confirm Deletion", JOptionPane.OK_CANCEL_OPTION, JOptionPane.CANCEL_OPTION, warningIcon);
            if(option==0)
            {
                if(allEntriesModel.getRowCount() != 0)
                    try 
                    {
                        connObject.deleteCurrentEntries();
                        allEntriesModel.setRowCount(0);
                        roommateInformationModel.setRowCount(0);
                        connObject.settoZero();
                        oweLabel1.setVisible(false); oweLabel2.setVisible(false);
                    } 
                    catch (SQLException ex) 
                    {
                    Logger.getLogger(Manage.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
        }
        
        if(ae.getActionCommand().equals("SAVE")){
            try {
                if(priceTextField.getText().matches("\\b\\d+(\\.\\d+)?\\b")){
                    connObject.name = roommateNameTextField.getText();
                    connObject.date = dateTextField.getText();
                    connObject.description = descriptionTextField.getText();
                    connObject.price = Float.parseFloat(priceTextField.getText());
                    //THIRD: If no error occurs, dialog pops up and text fields are set to default for another entry
                    JOptionPane.showMessageDialog(frame, "Information Saved", "Message", JOptionPane.INFORMATION_MESSAGE, checkMarkIcon);
                    setDefaultTextValues();
                    
                    connObject.insertInformation();
                }
                else
                    throw new Exception();
                
            } catch (Exception ex){
                System.out.println(ex);
                priceTextField.setForeground(Color.RED);
                priceTextField.setText("ENTER A NUMBER");
            }
            
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) 
    {
        if(e.getComponent().getY()== 40){
            roommateNameTextField.setText("");
            roommateNameTextField.setForeground(Color.BLACK);}
        
        if(e.getComponent().getY()==95){
            dateTextField.setText("");
            dateTextField.setForeground(Color.BLACK);}
        
        if(e.getComponent().getY()==150){
            descriptionTextField.setText("");
            descriptionTextField.setForeground(Color.BLACK);}
        
        if(e.getComponent().getY()==205){
            priceTextField.setText("");
            priceTextField.setForeground(Color.BLACK);
        }
             
    }

    @Override
    public void mousePressed(MouseEvent me) {}

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) 
    {}

    @Override
    public void mouseExited(MouseEvent me) {
       
    }

    public static void main(String[] args) throws SQLException 
    {
        
        new Manage();
    }

}
