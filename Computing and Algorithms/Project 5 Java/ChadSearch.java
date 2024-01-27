/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 5 */
/* ChadSearch class: creates window for deleting a definition */
/**************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChadSearch extends JFrame implements ActionListener {
    Prog5 myP = new Prog5(); //allows the use for the same database across all files
    Database myDb = myP.getDatabase(); //allows the use for the same database across all files
    JTextField termInTextField; //term input textfield
    JTextField intInTextField; //number input textfield
    JButton submit = new JButton(); //creates the JButton

    int numberIn; //number input
    String termIn; //term input
    JTextArea output; //output of text

    /**************************************************************/
    /* Method: ChadSearch() */
    /* Purpose: sets up the window for the ChadSearch class */
    /* Parameters: */
    /* Returns: output */
    /**************************************************************/
    public ChadSearch() {
        super("ChadSearch"); //adds name to the top of window
        setSize(350, 400); //sets the default size of the window
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //closes window when you hit X
        setVisible(true); //makes window visible
       
        Container Screen1; //sets up the container which holds all of the elements of the window              
        
        Screen1 = getContentPane();
        Screen1.setLayout(new FlowLayout());
        termInTextField = new JTextField("", 30); //sets the size of the text input
        intInTextField = new JTextField("", 30);

        submit = new JButton("Submit"); //sets up the button
        submit.addActionListener(this); //sets up the reading input of button
       
        JLabel inpLabel = new JLabel("Enter a term"); //adds label
        JLabel numLabel = new JLabel("Input number of definition "); //adds label

        output = new JTextArea(1, 30); //should set size, this should be good enough to work
        output.setEditable(false);
       
        //this adds these to the container, which is like an object holding all of this
        Screen1.add(inpLabel);
        Screen1.add(termInTextField);

        Screen1.add(numLabel); //these just add theses objects to the container
        Screen1.add(intInTextField);

        Screen1.add(output); 
        Screen1.add(submit);
    }

    /**************************************************************/
    /* Method: actionPeformed() */
    /* Purpose: reads the input and does the function contained in it */
    /* In this case it calls ChadSearch() */
    /* Parameters: ActionEvent e*/
    /* Returns: output */
    /**************************************************************/
    @Override
    public void actionPerformed(ActionEvent e) {
        output.selectAll();
        output.replaceSelection(null); //this resets the output field

        try {
            numberIn = Integer.valueOf(intInTextField.getText()); //This is to get the int, and to set it from a string to an int
            termIn = termInTextField.getText();

            if(numberIn > 0) {
                    myDb.numToDelete = numberIn; //puts the number input into the main database
                    myDb.termToRemove = termIn; //puts the term input into the main database
                    setVisible(false); //hides window so it can load one of the helpers
                    myDb.ChadSearch(output);
            } else {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException  exe) {
            output.setText("You have to put in a number for the number "); //outputs text
        }
    };
}
