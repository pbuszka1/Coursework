/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 5 */
/* WriteTime class: interacts with the user, prints text to file */
/**************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WriteTime extends JFrame implements ActionListener{
    Prog5 myP = new Prog5(); //gets instance of Prog5
    Database myDb = myP.getDatabase(); //gets the same database as the rest of the program
    JTextField defineTextField; //text field for inputting define
    String inputString; //for input
    JTextArea output; //for output

    /**************************************************************/
    /* Method: WriteTime() */
    /* Purpose: sets up the window for the WriteTime class */
    /* Parameters: */
    /* Returns: output */
    /**************************************************************/
    public WriteTime() {
        super("WriteTime"); //gives the window its name
        setSize(800, 400); //sets the size
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //closes only window
        setVisible(true);
       
        Container Screen1; //creates the GUI for Define
        
        Screen1 = getContentPane();
        Screen1.setLayout(new FlowLayout()); //sets layout
        defineTextField = new JTextField("", 30); //sets up the textfield
        defineTextField.addActionListener(enter);
       
        JLabel inpLabel = new JLabel("Enter a text file name (hit enter to submit) ");
        output = new JTextArea(1, 50); //should set size, this should be good enough to work
        output.setEditable(false);
       
        //this adds these to the container, which is like an object holding all of this
        Screen1.add(inpLabel);
        Screen1.add(defineTextField);
        Screen1.add(output); 
    }

    /**************************************************************/
    /* Method: actionPeformed() */
    /* Purpose: reads the input and does the function contained in it */
    /* In this case it calls writeTime */
    /* Parameters: ActionEvent e */
    /* Returns: output */
    /**************************************************************/
    Action enter = new AbstractAction() { //this is to let me use enter as an input
        @Override
        public void actionPerformed(ActionEvent e) {
            inputString = defineTextField.getText(); //this should put the input text into writeTime
            myDb.textFileName = inputString; //sets database varible to inputstring
            output.selectAll();
            output.replaceSelection(null); //this is supposed to clear the JtextBook
            myDb.writeTime(output);
        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        //this has to be here so the IDE wont through an error
    }
}

