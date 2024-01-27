/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 5 */
/* StartOver class: interacts with the user, allows creating a new database */
/**************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StartOver extends JFrame implements ActionListener{
    Prog5 myP = new Prog5(); //creates instance of Prog5
    Database myDb = myP.getDatabase(); //maybe I need to setDatabase method, because I dont think it is getting set correctly
    JTextField defineTextField; //creates text field for input
    String inputString; //for input
    JTextArea output; //for output

    /**************************************************************/
    /* Method: StartOver() */
    /* Purpose: sets up the window for the StartOver class */
    /* Parameters: */
    /* Returns: output */
    /**************************************************************/
    public StartOver() { //constructor
        super("StartOver");
        setSize(800, 400); //sets size
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //closes window when you hit the X in the upper right corner
        setVisible(true); //makes visible
       
        Container Screen1; //creates the GUI for Define
        
        Screen1 = getContentPane();
        Screen1.setLayout(new FlowLayout()); //sets layout
        defineTextField = new JTextField("", 30);
        defineTextField.addActionListener(enter); //adds action listener so enter can work
       
        JLabel inpLabel = new JLabel("Enter a text file name for the new input (hit enter to submit) ");
        output = new JTextArea(1, 50); //should set size, this should be good enough to work
        output.setEditable(false); //makes it so you cant edit the JTextArea
       
        //this adds these to the container, which is like an object holding all of this
        Screen1.add(inpLabel);
        Screen1.add(defineTextField);
        Screen1.add(output); 
    }

    /**************************************************************/
    /* Method: actionPeformed() */
    /* Purpose: reads the inputs, and calls the method to create  */
    /*  the new database from the text file */
    /* Parameters: ActionEvent e */
    /* Returns: output */
    /**************************************************************/
    Action enter = new AbstractAction() { //this is to let me use enter as an input
        @Override
        public void actionPerformed(ActionEvent e) {
            inputString = defineTextField.getText(); //this should put the input text into writeTime
            myDb.newDbFileName = inputString; //sets database variable to inputString

            output.selectAll();
            output.replaceSelection(null); //this is supposed to clear the JtextBook

            if (myDb.isThere(output) == true) {
                myDb = new Database(); //idk if i need the myP, make sure you are using the same object, verify which object is being used
                myP.setDatabase(myDb); //try to get this to work after dinner
                myDb.newDbFileName = inputString; //be careful, this may be local varible
                output.setText("New database has been created "); //sets output if database is created
            }
            myDb.startOver(); //calls method in database
        }
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        //this has to be here so the IDE wont through an error
    }
}

