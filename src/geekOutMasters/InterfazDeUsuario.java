package geekOutMasters;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This class is used for ...
 * @autor Jhoni ipia
 * @version v.1.0.0 date:21/03/2023
 */
public class InterfazDeUsuario extends JFrame {

    private Header headerProject;
    private JButton dado1, dado2, dado3, dado4, dado5, dado6, dado7, dado8, dado9, dado10;
    private ImageIcon imageDado, imagenNuevoTamanho;
    private Image imagenOtroTamanho;
    private JButton ayuda, salir, nuevaRonda;
    private JPanel panelDadosUtilizados, panelDadosInactivos, panelDadosActivos, panelEspacioEnBlanco1,
            panelEspacioEnBlanco2, panelEspacioEnBlanco3, panelEspacioEnBlanco4, panelInstrucciones, panelAccionesDados;
    private JTextArea numeroRonda, puntaje, instrucciones, mensajesAccionesDados;
    private String mensajeFinal = "";
    private String poder = "";
    private String[] estadoToString;
    private JScrollPane scroll;
    private int ronda, puntos, seleccionDado, boton, unBoton, estado;
    private ArrayList<JButton> botonesEnDadosUtilizados, botonesEnDadosInactivos, botonesEnDadosActivos;
    private static final String MENSAJE_INICIO ="Bienvenido a Geek Out Masters \n";

    private static final String INSTRUCCIONES = "Aqui van las instruciones del juego";

    private Escucha escucha;

    private  ModelGeekOutMasters game;

    /**
     * Constructor of GUI class
     */
    public InterfazDeUsuario(){
        initGUI();

        //Default JFrame configuration
        this.setTitle("Geek out master");
        this.setSize(1000,600);
        this.setResizable(true);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(new Color(255,255,255));

    }

    /**
     * This method is used to set up the default JComponent Configuration,
     * create Listener and control Objects used for the GUI class
     */
    private void initGUI() {
        //Set up JFrame Container's Layout
        this.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        //Create Listener Object and Control Object
        escucha = new Escucha();
        game = new ModelGeekOutMasters();
        //Set up JComponents
        botonesEnDadosActivos = new ArrayList<JButton>();
        botonesEnDadosInactivos = new ArrayList<JButton>();
        botonesEnDadosUtilizados = new ArrayList<JButton>();

        seleccionDado = 0;
        unBoton=0;
        estado=10;

        estadoToString = new String[1];

        panelInstrucciones = new JPanel();
        panelInstrucciones.setPreferredSize(new Dimension(410,1005));
        panelInstrucciones.setBackground(Color.WHITE);
        panelInstrucciones.setBorder(BorderFactory.createTitledBorder("Instrucciones del juego."));
        panelInstrucciones.setLayout(new BorderLayout());

        instrucciones = new JTextArea();
        instrucciones.setText(INSTRUCCIONES);
        instrucciones.setLineWrap(true);
        instrucciones.setPreferredSize(new Dimension(408, 1000));
        instrucciones.setWrapStyleWord(true);
        instrucciones.setLineWrap(true);
        instrucciones.setEditable(false);

        imageDado =new ImageIcon(getClass().getResource("/resources/7.png"));
        imagenOtroTamanho =imageDado.getImage().getScaledInstance(60,60,Image.SCALE_SMOOTH);
        imagenNuevoTamanho =new ImageIcon(imagenOtroTamanho);

        dado1 = new JButton(imagenNuevoTamanho);
        dado1.setBackground(Color.WHITE);
        dado2 = new JButton(imagenNuevoTamanho);
        dado2.setBackground(Color.WHITE);
        dado3 = new JButton(imagenNuevoTamanho);
        dado3.setBackground(Color.WHITE);
        dado4 = new JButton(imagenNuevoTamanho);
        dado4.setBackground(Color.WHITE);
        dado5 = new JButton(imagenNuevoTamanho);
        dado5.setBackground(Color.WHITE);
        dado6 = new JButton(imagenNuevoTamanho);
        dado6.setBackground(Color.WHITE);
        dado7 = new JButton(imagenNuevoTamanho);
        dado7.setBackground(Color.WHITE);
        dado8 = new JButton(imagenNuevoTamanho);
        dado8.setBackground(Color.WHITE);
        dado9 = new JButton(imagenNuevoTamanho);
        dado9.setBackground(Color.WHITE);
        dado10 = new JButton(imagenNuevoTamanho);
        dado10.setBackground(Color.WHITE);




        createHeader(constraints);
        createSpace1(constraints);
        createPlayButton(constraints);
        createHelpButton(constraints);
        createPointCounter(constraints);
        createRoundCount(constraints);
        createNewRoundButton(constraints);
        createDadosUtilizadosPane(constraints);
        

    }

    /**
     * Creates a header for the application with a title and a background color.
     * @param constraints Grid constraints for placing the header in the main container.
     */
    public void createHeader(GridBagConstraints constraints){
        headerProject = new Header("GEEK OUT MASTERS", Color.white);
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 7;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.1;

        this.add(headerProject, constraints);

    }


    public void createSpace1(GridBagConstraints constraints) {
        panelEspacioEnBlanco1 = new JPanel();
        panelEspacioEnBlanco1.setOpaque(false);
        panelEspacioEnBlanco1.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));

        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 7;
        constraints.gridheight = 1;



        this.add(panelEspacioEnBlanco1, constraints);

    }

    public void createPlayButton(GridBagConstraints constraints) {
        salir = new JButton("Play");
        salir.setFont(new Font("SansSerif", Font.BOLD + Font.PLAIN, 18));
        salir.setForeground(Color.WHITE);
        salir.setBackground(new Color(255, 81, 51));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.WEST;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(10,10,10,10);
    
        this.add(salir, constraints);
    }

    public void createHelpButton(GridBagConstraints constraints) {
        ayuda = new JButton(" ? ");
        ayuda.setFont(new Font("SansSerif", Font.BOLD + Font.PLAIN, 24));
        ayuda.setForeground(Color.white);
        ayuda.setBackground(new Color(0, 102, 255));
        ayuda.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
        constraints.gridx = 6;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.NORTHEAST;
        constraints.insets = new Insets(10,10,10,10);

        this.add(ayuda, constraints);
    }

    public void createPointCounter(GridBagConstraints constraints) {
        puntos = 0;
    
        puntaje = new JTextArea(1, 5);
        puntaje.setPreferredSize(new Dimension(80, 20));
        puntaje.setFont(new Font("SansSerif", Font.BOLD + Font.PLAIN, 14));
        puntaje.setText("Puntaje: " + puntos);
        puntaje.setBackground(Color.WHITE); 
        puntaje.setForeground(Color.BLACK);
        puntaje.setEditable(false);
        puntaje.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    
        constraints.gridx = 2; 
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.WEST;
        add(puntaje, constraints);
    }

    public void createRoundCount(GridBagConstraints constraints) {
    ronda = 0;

    numeroRonda = new JTextArea(1, 5); // cambiar el tamaño del JTextArea
    numeroRonda.setPreferredSize(new Dimension(70, 20));
    numeroRonda.setFont(new Font("SansSerif", Font.BOLD + Font.PLAIN, 14));
    numeroRonda.setText("Ronda: " + ronda);
    numeroRonda.setBackground(Color.WHITE); 
    numeroRonda.setForeground(Color.BLACK); 
    numeroRonda.setEditable(false);
    numeroRonda.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // establecer el borde en negro con grosor 2

    constraints.gridx = 3;
    constraints.gridy = 2;
    constraints.gridwidth = 1;
    constraints.fill = GridBagConstraints.NONE;
    constraints.anchor = GridBagConstraints.WEST;
    add(numeroRonda, constraints);
}

public void createNewRoundButton(GridBagConstraints constraints)
    {
        nuevaRonda = new JButton("NEXT");
        nuevaRonda.setFont(new Font("SansSerif", Font.BOLD + Font.PLAIN, 14));
        nuevaRonda.setForeground(Color.BLACK);
        nuevaRonda.setBackground(Color.WHITE);
        nuevaRonda.setFocusable(true);
        nuevaRonda.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        constraints.gridx = 0;
        constraints.gridy = 12;
        constraints.gridwidth = 7;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(nuevaRonda, constraints);
    }

    public void createDadosUtilizadosPane(GridBagConstraints constraints) {
        panelDadosUtilizados = new JPanel();
        panelDadosUtilizados.setMinimumSize(new Dimension(500, 200));
        panelDadosUtilizados.setBorder(BorderFactory.createTitledBorder("Dados utilizados"));
        panelDadosUtilizados.setBackground(Color.WHITE);
        
        constraints.gridx = 4;
        constraints.gridy = 4;
        constraints.gridwidth = 3;
        constraints.gridheight = 1;
        constraints.fill = GridBagConstraints.NONE;
        constraints.anchor = GridBagConstraints.LINE_END;

        this.add(panelDadosUtilizados, constraints);
    }


    /**
     * Main process of the Java program
     * @param args Object used in order to send input data from command line when
     * the program is execute by console.
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
