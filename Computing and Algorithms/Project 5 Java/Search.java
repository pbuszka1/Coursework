/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 5 */
/* Search class: interacts with the user, allows to seach for matching terms */
/**************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Search extends JFrame implements ActionListener{
    Prog5 myP = new Prog5(); //creates instance of Prog5
    Database myDb = myP.getDatabase(); //gets database used through entire program
    JTextField defineTextField; //textfield for term
    String inputString; //intput
    JTextArea output; //adds output

    /**************************************************************/
    /* Method: Search() */
    /* Purpose: sets up the window for the Search class */
    /* Parameters: */
    /* Returns: output */
    /**************************************************************/
    public Search() {
        super("Search"); //sets the name of window
        setSize(800, 400); //sets size
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //allows the window to close when you hit X
        setVisible(true); //makes visible
       
        Container Screen1; //creates object to hold everything        
        
        Screen1 = getContentPane();
        Screen1.setLayout(new FlowLayout()); //sets layout
        defineTextField = new JTextField("", 30);
        defineTextField.addActionListener(enter); //adds the enter action listener
       
        JLabel inpLabel = new JLabel("Enter a prefix to search for (hit enter to submit)");
        output = new JTextArea(1, 50); //should set size, this should be good enough to work
        output.setEditable(false);
       
        //this adds these to the container, which is like an object holding all of this
        Screen1.add(inpLabel);
        Screen1.add(defineTextField);
        Screen1.add(output); 
    }

    /**************************************************************/
    /* Method: actionPeformed() */
    /* Purpose: reads the inputs, and calls the method to search  */
    /*  the database for the input */
    /* Parameters: ActionEvent e */
    /* Returns: output */
    /**************************************************************/
    Action enter = new AbstractAction() { //this is to let me use enter as an input
        @Override
        public void actionPerformed(ActionEvent e) {
            //this is for listening to the event 
            inputString = defineTextField.getText(); //this should put the input text into define.
            myDb.termEntered = inputString; //sets databse variable to inputString
            output.selectAll();
            output.replaceSelection(null); //this is supposed to clear the JtextBook
            myDb.Search(output);
        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        //this has to be here so the IDE wont through an error
    }
}
