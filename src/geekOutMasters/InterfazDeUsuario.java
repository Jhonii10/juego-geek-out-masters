package geekOutMasters;

import javax.swing.*;
import java.awt.*;

/**
 * This class is used for ...
 * @autor Jhoni ipia
 * @version v.1.0.0 date:21/03/2023
 */
public class InterfazDeUsuario extends JFrame {

    private Header headerProject;

    /**
     * Constructor of GUI class
     */
    public InterfazDeUsuario(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Geek out master");
        this.setSize(200,100);
        //this.pack();
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        //Create Listener Object and Control Object
        //Set up JComponents
        headerProject = new Header("Geek out masters", Color.BLACK);

        this.add(headerProject,BorderLayout.NORTH); //Change this line if you change JFrame Container's Layout
    }

    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     *             the program is execute by console.
     */
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            InterfazDeUsuario miProjectGUI = new InterfazDeUsuario();
        });
    }

    /**
     * inner class that extends an Adapter Class or implements Listeners used by GUI class
     */
    private class Escucha {

    }
}
