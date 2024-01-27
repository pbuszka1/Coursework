/**************************************************************/
/* Parker Buszka */
/* CS-102, Winter 2022 */
/* Programming Assignment 5 */
/* Print class: interacts with the user, prints out file to window */
/**************************************************************/
import javax.swing.*;
import java.awt.*;

public class Print extends JFrame {
    Prog5 myP = new Prog5(); //instance of Prog5
    Database myDb = myP.getDatabase(); //allows you to access the same database as the rest of the program
    String inputString; //the input
    JTextArea output; //the output


    /**************************************************************/
    /* Method: Print() */
    /* Purpose: sets up the window for the Print class */
    /* Parameters: */
    /* Returns: output */
    /**************************************************************/
    public Print() {
        super("Print"); //sets the top name of the window
        setSize(800, 600); //sets the size of the window
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); //when you hit the X, it will only kill the window, not the program
        setVisible(true); //makes the window visible
       
        Container Screen1; //holds all of the elements of the window in it
        
        Screen1 = getContentPane();
        Screen1.setLayout(new FlowLayout()); //sets the layout of the window
       
        output = new JTextArea(1, 50); //should set size, this should be good enough to work
        output.setEditable(false);
       
        //this adds these to the container, which is like an object holding all of this
        Screen1.add(output); 
        myDb.TermsList.printTree(output);//outputs the tree to the window
    }  
}

