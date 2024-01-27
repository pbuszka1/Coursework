/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 5 */
/* Define class: outputs definition of a inputed term */
/**************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Define extends JFrame implements ActionListener{
    Prog5 myP = new Prog5(); //creates instance of Prog5
    Database myDb = myP.getDatabase(); //gets database used in the file
    JTextField defineTextField; //creates text field
    String inputString; //for input
    JTextArea output; //for output

    /**************************************************************/
    /* Method: Define() */
    /* Purpose: sets up the window for the Define class */
    /* Parameters: */
    /* Returns: output */
    /**************************************************************/
    public Define() {
        super("Define"); //gives the window its name
        setSize(800, 400); //sets the size
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //closes only window
        setVisible(true); //sets to true
       
        Container Screen1; //creates the GUI for Define
        
        Screen1 = getContentPane(); //gets the content pane so you can add to it
        Screen1.setLayout(new FlowLayout()); //sets the layout of the window
        defineTextField = new JTextField("", 30);
        defineTextField.addActionListener(enter); //adds action listener
       
        JLabel inpLabel = new JLabel("Enter a term to define (hit enter to submit) "); //label for text area
        output = new JTextArea(1, 50); //should set size, this should be good enough to work
        output.setEditable(false);
       
        //this adds these to the container, which is like an object holding all of this
        Screen1.add(inpLabel);
        Screen1.add(defineTextField);
        Screen1.add(output); 
    }

    /**************************************************************/
    /* Method: actionPeformed() */
    /* Purpose: reads the input and puts it in method Define() */
    /* Parameters: ActionEvent e */
    /* Returns: output */
    /**************************************************************/
    Action enter = new AbstractAction() { //this is to let me use enter as an input
        @Override
        public void actionPerformed(ActionEvent e) {
            //this is for listening to the event
            inputString = defineTextField.getText(); //this should put the input text into define.
            myDb.defWeLookingFor = inputString;
            output.selectAll();
            output.replaceSelection(null); //this clears the JtextBook
            myDb.Define(output);
        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        //this has to be here so the IDE wont through an error
    }
}
