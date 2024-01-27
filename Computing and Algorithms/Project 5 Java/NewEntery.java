/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 5 */
/* NewEntery class: interacts with the user, allows for adding definition */
/**************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NewEntery extends JFrame implements ActionListener {
    Prog5 myP = new Prog5(); //creates instance of Prog5
    Database myDb = myP.getDatabase(); //gets program used database
    JTextField termInTextField; //creates term text field
    JTextField intInTextField; //creates int text field
    JTextField defInTextField; //creates definition text field
    JButton submit = new JButton(); //initializes JButton 

    String termIn; //for term
    int numberIn; //for number
    String defIn; //for definition
    JTextArea output; //for output

    /**************************************************************/
    /* Method: NewEntery() */
    /* Purpose: sets up the window for the NewEntery class */
    /* Parameters: */
    /* Returns: output */
    /**************************************************************/
    public NewEntery() {
        super("NewEntery"); //gives the window its name
        setSize(350, 400); //sets size
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //closes only the NewEntery window on close
        setVisible(true); //makes visible 
       
        Container Screen1; //creates object to hold everything             
        
        Screen1 = getContentPane();
        Screen1.setLayout(new FlowLayout());
        termInTextField = new JTextField("", 30); //creates the default textfield area
        intInTextField = new JTextField("", 30); //creates the default textfield area
        defInTextField = new JTextField("", 30); //creates the default textfield area

        submit = new JButton("Submit"); //creates the JButton
        submit.addActionListener(this); //adds action listener to it
       
        JLabel inpLabel = new JLabel("Input new Term "); //creates the labels for each of the textfield
        JLabel numLabel = new JLabel("Input new number ");
        JLabel defLabel = new JLabel("Input new Definition ");

        output = new JTextArea(1, 30); //should set size, this should be good enough to work
        output.setEditable(false);
       
        //this adds these to the container, which is like an object holding all of this
        Screen1.add(inpLabel);
        Screen1.add(termInTextField);
        Screen1.add(numLabel);
        Screen1.add(intInTextField); //there is a lot of things added here, because each field has its label
        Screen1.add(defLabel);
        Screen1.add(defInTextField);
        Screen1.add(output); 
        Screen1.add(submit);
    }

        /**************************************************************/
        /* Method: actionPeformed() */
        /* Purpose: reads the inputs, and calls the method to add it to the database */
        /* Parameters: ActionEvent e */
        /* Returns: output */
        /**************************************************************/
        @Override
        public void actionPerformed(ActionEvent e) {
            termIn = termInTextField.getText(); //this should put the input text into define.
            myDb.newTermInput = termIn; //sets database newTermInput to termIn

            defIn = defInTextField.getText(); //this should put the input text into define.
            myDb.newDefinitionInput = defIn; //sets newDefinitionInput to defIn

            output.selectAll();
            output.replaceSelection(null); //empties the output field

            try { //for exception handling
                numberIn = Integer.valueOf(intInTextField.getText()); //this will set the value of numberIn to zero, if its not a number
                
                if(numberIn > 0) { //makes sure the number is greater than zero
                    myDb.newNumInput = numberIn; //sets the database variable to numberIn
                    myDb.newEntery(output);
                } else {
                    throw new NumberFormatException(); //throws exception to catch if not number
                }
            } catch (NumberFormatException  exe) {
                output.setText("You have to put in a number for the number ");
            }
        };
}
