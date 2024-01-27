/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 5 */
/* GUI class: interacts with the user, creates GUI */
/**************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GUI extends JFrame implements ActionListener { 
    //this creates the basic button inputs of the program,
    //so when you click one, it will take you to another screen for that task
    String inputThing;//for define
    String userChoice = ""; //to get the users choice

    private JPanel loadScreen; //for load screen
    private JButton [] basicInputs; //for the buttons
    private String [] basicInputNames = { //names for the buttons
        "Define a term",
        "Search for matching terms",
        "Print all records",
        "Add new definition",
        "delete a definition",
        "print to text file",
        "re-initialize the database",
        "Exit"
    };

    private ArrayList<JButton> buttonListeners = new ArrayList<JButton>(); //creates the list of button listener

    /**************************************************************/
    /* Method: GUI() */
    /* Purpose: sets up the window for the GUI class */
    /* Parameters: */
    /* Returns: output */
    /**************************************************************/
    public GUI() {
        super("Dictionary Program");
        setSize(800, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE); //closes program when you click the X
        
        GridLayout buttons = new GridLayout(0, 1); //sets up the layout
        setLayout(buttons); //sets the layout

        for(String obj: basicInputNames) {//advanced for loop, put in array, loops for the length, then it adds a button.
            JButton temp = new JButton(obj);
            temp.addActionListener(this); //because of this, it looks and goes to action performed
            buttonListeners.add(temp); //adds listener to buttons
            add(temp); //adds to the ArrayList
            
        }
        setVisible(true); //sets visible after it creates the buttons
    }

    /**************************************************************/
    /* Method: actionPeformed() */
    /* Purpose: reads the input and goes to the correct case, to load the correct class */
    /* Parameters: ActionEvent e */
    /* Returns: output */
    /**************************************************************/
    @Override //supersedes actionlistner method
    public void actionPerformed(ActionEvent e) {  
        JButton button = (JButton) e.getSource(); //allows you to get the source of button click

        switch (button.getText()) { //this gets the text name from the buttons, so we know which case to use
            case "Define a term": //this is for defining the term, have user input term and then return the definition
                Define myD = new Define(); //invokes construrtor method           
                /* each window gets a class, to me, that seemed like the most straight foward
                and simplist way for me to understand and complete this project */
                
                    break;
            case "Search for matching terms": //this is for searching for matching terms, have user input term and return a match
                Search mySearch = new Search(); //invokes constructor method 
                    break;
            case "Print all records": //this is for Printing all records, print all records
                Print myPrint = new Print(); //invokes constructor method 

                    break;
            case "Add new definition": //this is for the user creating new enteries, including, term, number, and definition.
                NewEntery myEntery = new NewEntery(); //invokes constructor method 

                    break;
            case "delete a definition": //allows the user to delete a definition from the menu
                ChadSearch myChad = new ChadSearch(); //invokes constructor method 

                    break;
            case "print to text file": //writes a text file
                WriteTime myTime = new WriteTime(); //invokes constructor method 

                    break;
            case "re-initialize the database": //recreates the database
                StartOver freshStart = new StartOver(); //invokes constructor method 

                break;
            case "Exit": //this is for exiting, exit program
                System.exit(0);
                    break;
        }
    }
}
