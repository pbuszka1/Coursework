/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 5 */
/* ChadSearchHelp class: interacts with the user, helps the ChadSeach class */
/**************************************************************/
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChadSearchHelp extends JFrame implements ActionListener {
    Prog5 myP = new Prog5(); //instance of Prog5
    Database myDb = myP.getDatabase(); //gets database used in entire file
    JTextField yesOrNo; //textfield for yesOrNoIn
    JButton submit = new JButton(); //creates the submit button

    String yesOrNoIn; //input for yes or no
    JTextArea outputTemp; //output

    /**************************************************************/
    /* Method: ChadSearchHelp() */
    /* Purpose: sets up the window for the ChadSearchHelp class */
    /* Parameters: output*/
    /* Returns: output */
    /**************************************************************/
    public ChadSearchHelp(JTextArea output) {
        super("ChadSearchHelp");
        setSize(350, 400); //sets size
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //closes window when hit the X
        setVisible(true); //makes window visible
       
        Container Screen1; //creates object to hold all of the GUI elements      
        
        Screen1 = getContentPane();
        Screen1.setLayout(new FlowLayout());//sets layout
        yesOrNo = new JTextField("", 30); //sets up the textfield

        submit = new JButton("Submit"); //sets up JButton
        submit.addActionListener(this); //adds action listener
       
        JLabel inpLabel = new JLabel("Would you like to delete this definition? (yes or no) ");

        output = new JTextArea(1, 30); //should set size, this should be good enough to work
        output.setEditable(false);
       
        //this adds these to the container, which is like an object holding all of this
        Screen1.add(inpLabel);
        Screen1.add(yesOrNo); //adds elements to window

        Screen1.add(output); 
        Screen1.add(submit);
        outputTemp = output; //sets outputTemp to output
    }

    /**************************************************************/
    /* Method: actionPeformed() */
    /* Purpose: reads the input and does the function contained in it */
    /* In this case it calls DeleteDef */
    /* Parameters: ActionEvent e */
    /* Returns: output */
    /**************************************************************/
    @Override
    public void actionPerformed(ActionEvent e) {
        outputTemp.selectAll();
        outputTemp.replaceSelection(null); //empties the output JTextArea

        yesOrNoIn = yesOrNo.getText(); //gets the input and sets it to yesOrNoIn
        myDb.yesNoDelete = yesOrNoIn; //sets the database variable 
        myDb.DeleteDef(outputTemp); //calls DeleteDef
    };
}
