package geekOutMasters;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

/**
 * This class is used for ...
 * @autor Jhoni ipia
 * @autor Alexandra Lerma
 * @version v.1.0.0 date:21/03/2023
 */
public class InterfazDeUsuario extends JFrame {

    private Header headerProject;
    private JButton dado1, dado2, dado3, dado4, dado5, dado6, dado7, dado8, dado9, dado10;
    private ImageIcon imageDado, imagenNuevoTamanho;
    private Image imagenOtroTamanho;
    private JButton ayuda, play, nuevaRonda;
    private JPanel panelDadosUtilizados, panelDadosInactivos, panelDadosActivos, panelInstrucciones, panelAccionesDados;
    private JTextArea numeroRonda, puntaje, instrucciones, mensajesAccionesDados;
    private String mensajeFinal = "";
    private String poder = "";
    private String[] estadoToString;
    private JScrollPane scroll;
    private int ronda, puntos, seleccionDado, boton, unBoton, estado;
    private ArrayList<JButton> botonesEnDadosUtilizados, botonesEnDadosInactivos, botonesEnDadosActivos;
    private static final String MENSAJE_INICIO ="Bienvenido a Geek Out Masters \n";

    /**Game instructions */

    private static final String INSTRUCCIONES = "Lo primero que debes saber es que los dados aparecen aleatoriamente"
    + " después de presionar \"Nueva ronda.\"\n"
    + "\nDe los 10 dados que trae el juego se toman 3 y se colocan en el sector de \"Dados\""
    + "Inactivos\". Los otros 7 dados se tiran y pasan a ser los \"Dados Activos\".\n"
    + "\nSe van eligiendo los dados a utilizar según las habilidades de sus caras y se pasan"
    + " al sector de \"Dados Utilizados\".\n"
    + "\nSi como último dado activo queda un Dragón, se perderán todos los puntos acumulados.\n"
    + "\nEste juego lo jugará un único jugador y ganará si logra sumar 30 puntos en 5 rondas"
    + " consecutivas de juego.\n"
    + "\nTienes disponibles los siguientes dados:\n"
    + "\n 1. El Meeple permite relanzar otro dado en juego, es decir, de la sección dados activos.\n"
    + "\n 2. La Nave Espacial envía un dado no usado (de la sección dados activos) a la sección de"
    + "  dados inactivos.\n"
    + "\n 3. El Superhéroe permite que cualquier dado no usado (sección dados activos) sea volteado"
    + "  y colocado en su cara opuesta.\n"
    + "\n 4. El Corazón permite tomar un dado de la sección de dados inactivos y lanzarlo para que"
    + "  sea un nuevo dado activo.\n"
    + "\n 5. El Dragón es la cara que se quiere evitar, ya que, si al final de la ronda es el último"
    + "  dado activo que queda se habrán perdido todos los puntos ganados y acumulados.\n"
    + "\n 6. 42 es la cara que permite sumar puntos al final de la ronda.\n"
    + "\nEs importante que sepas que las caras contrarias del dado corresponden a sus colores, es"
    + " decir, la cara contraria al Corazón es el 42, ya que, tienen el mismo color (rojo); la cara"
    + " contraria del Meeple es la Nave Espacial y la cara contraria del Superhéroe es el Dragón.\n"
    + "\nPara activar el poder de un dado, debes presionar el dado que vas a activar y luego presionar"
    + " el dado al que le vas a aplicar el poder.\n"
    + "\nAl finalizar cada turno o ronda se cuenta la cantidad de dados con cara 42 en el área de dados"
    + " puntuados teniendo en cuenta las reglas de la siguiente imagen. Se debe tener presente que, en"
    + " cualquier ronda, existe el riesgo de quedar con un dragón en la zona de dados activos, en cuyo"
    + " caso, los puntos dela ronda como los puntos acumulados (si se han jugado varias rondas) son"
    + " eliminados, quedando con cero puntos.\n"
    + "\nA continuación se listan las reglas para la asignación de puntos:\n"
    + "\n1 Dado 42 -> 1 Punto       6 Dados 42 -> 21 Puntos\n"
    + "\n2 Dados 42 -> 3 Puntos     7 Dados 42 -> 28 Puntos\n"
    + "\n3 Dados 42 -> 6 Puntos     8 Dados 42 -> 36 Puntos\n"
    + "\n4 Dados 42 -> 10 Puntos    9 Dados 42 -> 45 Puntos\n"
    + "\n5 Dados 42 -> 15 Puntos    10 Dados 42 -> 55 Puntos\n";

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
        this.setMinimumSize(new Dimension(1100, 500));
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
        createPlayButton(constraints);
        createHelpButton(constraints);
        createPointCounter(constraints);
        createRoundCount(constraints);
        createDadosUtilizadosPane(constraints);
        createDadosInactivosPane(constraints);
        createAccionesDadosPane(constraints);
        createDadosActivosPane(constraints);
        createNewRoundButton(constraints);


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
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;

        this.add(headerProject, constraints);

    }

    /**
     * This function creates the play button.
     * this button starts the game
     * @param constraints
     */

    public void createPlayButton(GridBagConstraints constraints) {
        play = new JButton("Play");
        play.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,18));
        play.setForeground(Color.WHITE);
        play.addMouseListener(escucha);
        play.setBackground(new Color(255, 81, 51));
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.WEST;
        constraints.anchor = GridBagConstraints.LINE_START;
        constraints.insets = new Insets(10,10,10,10);
    
        this.add(play, constraints);
    }

    /**
     * This function creates the text box to display the score.
     * @param constraints
     */
    public void createPointCounter(GridBagConstraints constraints) {
        puntos = 0;

        puntaje = new JTextArea(1, 5);
        puntaje.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,18));
        puntaje.setText("PUNTAJE: " + puntos);
        puntaje.setBackground(Color.WHITE);
        puntaje.setForeground(Color.BLACK);
        puntaje.setEditable(false);
        puntaje.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

        constraints.gridx = 2;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.WEST;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10,10,10,50);
        add(puntaje, constraints);
    }

    /**
     * This function creates the text box to display the number of rounds.
     * @param constraints
     */
    public void createRoundCount(GridBagConstraints constraints) {
        ronda = 0;

        numeroRonda = new JTextArea(1, 5); // cambiar el tamaño del JTextArea
        numeroRonda.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,18));
        numeroRonda.setText("RONDA: " + ronda);
        numeroRonda.setBackground(Color.WHITE);
        numeroRonda.setForeground(Color.BLACK);
        numeroRonda.setEditable(false);
        numeroRonda.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1)); // establecer el borde en negro con grosor 2

        constraints.gridx = 3;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.WEST;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10,10,10,50);
        add(numeroRonda, constraints);
    }

    /**
     * This function creates the help button.
     * @param constraints
     */
    public void createHelpButton(GridBagConstraints constraints) {
        ayuda = new JButton(" ? ");
        ayuda.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,18));
        ayuda.setForeground(Color.white);
        ayuda.addMouseListener(escucha);
        ayuda.setBackground(new Color(0, 102, 255));
        constraints.gridx = 6;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.WEST;
        constraints.anchor = GridBagConstraints.NORTHEAST;
        constraints.insets = new Insets(10,10,10,10);

        this.add(ayuda, constraints);
    }

    /**
     * This function creates the DadosUtilizados panel.
     * @param constraints
     */
    public void createDadosUtilizadosPane(GridBagConstraints constraints) {
        panelDadosUtilizados = new JPanel();
        panelDadosUtilizados.setPreferredSize(new Dimension(350, 150)); // Cambia el tamaño mínimo del panel;
        panelDadosUtilizados.setBorder(BorderFactory.createTitledBorder("Dados utilizados"));
        panelDadosUtilizados.setBackground(Color.WHITE);
        panelDadosUtilizados.removeMouseListener(escucha);
        rePaintDadosUtilizados();

        constraints.gridx = 2;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10,10,10,10);;

        this.add(panelDadosUtilizados, constraints);
    }


    /**
     * This function creates the DadosInactivos panel.
     * @param constraints
     */

    public void createDadosInactivosPane(GridBagConstraints constraints) {
        panelDadosInactivos = new JPanel();
        panelDadosInactivos.setPreferredSize(new Dimension(350, 150)); // Cambia el tamaño mínimo del panel
        panelDadosInactivos.setBorder(BorderFactory.createTitledBorder("Dados inactivos"));
        panelDadosInactivos.setBackground(Color.WHITE);

        botonesEnDadosInactivos.add(dado8);
        botonesEnDadosInactivos.add(dado9);
        botonesEnDadosInactivos.add(dado10);


        constraints.gridx = 3;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.CENTER;
        constraints.insets = new Insets(10,10,10,10);

        this.add(panelDadosInactivos, constraints);

        determinateBotonesInactivos();
    }

    /**
     * This function determines the inactive buttons.
     * @param constraints
     */

    public void determinateBotonesInactivos()
    {
        imageDado = new ImageIcon(getClass().getResource("/resources/7.png"));
        imagenOtroTamanho = imageDado.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
        imagenNuevoTamanho = new ImageIcon(imagenOtroTamanho);
        for (boton = 0; boton < botonesEnDadosInactivos.size(); boton++) {
            botonesEnDadosInactivos.get(boton).setIcon(imagenNuevoTamanho);
            botonesEnDadosInactivos.get(boton).setBackground(Color.WHITE);
        }
        rePaintDadosInactivos();
    }

    /**
     * This function creates the AccionesDados panel.
     * @param constraints
     */
    public void createAccionesDadosPane(GridBagConstraints constraints)
    {
        panelAccionesDados = new JPanel();
        panelAccionesDados.setBorder(BorderFactory.createTitledBorder("Acciones que está realizando"));
        panelAccionesDados.setPreferredSize(new Dimension(350, 150));;
        panelAccionesDados.setBackground(Color.WHITE);

        mensajesAccionesDados = new JTextArea(20,30);
        mensajesAccionesDados.setFont(new Font("SansSerif", Font.BOLD, 12));
        mensajesAccionesDados.setWrapStyleWord(true);
        mensajesAccionesDados.setLineWrap(true);
        mensajesAccionesDados.setEditable(false);

        constraints.gridx = 2;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.LINE_START;

        mensajesAccionesDados.setText(getEstadoToString()[0]);
        panelAccionesDados.add(mensajesAccionesDados);
        this.add(panelAccionesDados, constraints);
    }


    //------------------------------------------------------------------------------------------------------------------------------------------

    /**
     * This function creates the DadosActivos panel.
     * @param constraints
     */

    public void createDadosActivosPane(GridBagConstraints constraints)
    {
        panelDadosActivos = new JPanel();
        panelDadosActivos.setPreferredSize(new Dimension(350, 150));
        panelDadosActivos.setBorder(BorderFactory.createTitledBorder("Dados activos"));
        panelDadosActivos.setBackground(Color.WHITE);

        botonesEnDadosActivos.add(dado1);
        botonesEnDadosActivos.add(dado2);
        botonesEnDadosActivos.add(dado3);
        botonesEnDadosActivos.add(dado4);
        botonesEnDadosActivos.add(dado5);
        botonesEnDadosActivos.add(dado6);
        botonesEnDadosActivos.add(dado7);

        constraints.gridx = 3;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.fill = GridBagConstraints.BOTH;
        constraints.anchor = GridBagConstraints.LINE_END;

        this.add(panelDadosActivos, constraints);


        determinateBotonesActivos();
    }


    /**
     * This function determines which face the dice will have in the DadosActivos panel.
     */

     public void determinateBotonesActivos()
     {
         for(boton=0;boton<botonesEnDadosActivos.size();boton++)
         {
             botonesEnDadosActivos.get(boton).removeMouseListener(escucha);
             if(ronda==0)
             {
                 imageDado = new ImageIcon(getClass().getResource("/resources/7.png"));
                 imagenOtroTamanho = imageDado.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                 imagenNuevoTamanho = new ImageIcon(imagenOtroTamanho);
             }
             else
             {
                 imageDado = new ImageIcon(getClass().getResource("/resources/" + game.dadosActivosArray.get(boton).newCara() + ".png"));
                 imagenOtroTamanho = imageDado.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                 imagenNuevoTamanho = new ImageIcon(imagenOtroTamanho);
             }
             botonesEnDadosActivos.get(boton).setIcon(imagenNuevoTamanho);
             botonesEnDadosActivos.get(boton).setBackground(Color.WHITE);
             botonesEnDadosActivos.get(boton).addMouseListener(escucha);
 
         }
         rePaintDadosActivos();
     }

      /**
     * This function creates the Nueva Ronda button.
     * @param constraints
     */
    public void createNewRoundButton(GridBagConstraints constraints)
    {
        nuevaRonda = new JButton("NUEVA RONDA");
        nuevaRonda.setFont(new Font(Font.DIALOG_INPUT,Font.BOLD,20));
        nuevaRonda.setForeground(Color.WHITE);
        nuevaRonda.addMouseListener(escucha);
        nuevaRonda.setBackground(new Color(0, 0, 0));
        nuevaRonda.setFocusable(true);

        constraints.gridx = 2;
        constraints.gridy = 5;
        constraints.gridwidth = 2;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.anchor = GridBagConstraints.CENTER;
        this.add(nuevaRonda, constraints);
    }

     /**
     * This function updates DadosActivos panel.
     */

     public void rePaintDadosActivos()
    {
        panelDadosActivos.removeAll();

        panelDadosActivos.setLayout(new GridBagLayout());
        GridBagConstraints constraintsPanelActivos = new GridBagConstraints();
        for(boton=0;boton<botonesEnDadosActivos.size();boton++)
        {
            switch (boton)
            {
                case 0:
                    constraintsPanelActivos.gridx = 1;
                    constraintsPanelActivos.gridy = 8;
                    constraintsPanelActivos.gridwidth = 1;
                    constraintsPanelActivos.fill = GridBagConstraints.NONE;
                    constraintsPanelActivos.anchor = GridBagConstraints.LINE_START;

                    panelDadosActivos.add(botonesEnDadosActivos.get(0), constraintsPanelActivos);
                    break;
                case 1:
                    constraintsPanelActivos.gridx = 2;
                    constraintsPanelActivos.gridy = 8;
                    constraintsPanelActivos.gridwidth = 1;
                    constraintsPanelActivos.fill = GridBagConstraints.NONE;
                    constraintsPanelActivos.anchor = GridBagConstraints.LINE_START;

                    panelDadosActivos.add(botonesEnDadosActivos.get(1), constraintsPanelActivos);
                    break;
                case 2:
                    constraintsPanelActivos.gridx = 3;
                    constraintsPanelActivos.gridy = 8;
                    constraintsPanelActivos.gridwidth = 1;
                    constraintsPanelActivos.fill = GridBagConstraints.NONE;
                    constraintsPanelActivos.anchor = GridBagConstraints.LINE_START;

                    panelDadosActivos.add(botonesEnDadosActivos.get(2), constraintsPanelActivos);
                    break;
                case 3:
                    constraintsPanelActivos.gridx = 4;
                    constraintsPanelActivos.gridy = 8;
                    constraintsPanelActivos.gridwidth = 1;
                    constraintsPanelActivos.fill = GridBagConstraints.NONE;
                    constraintsPanelActivos.anchor = GridBagConstraints.LINE_START;

                    panelDadosActivos.add(botonesEnDadosActivos.get(3), constraintsPanelActivos);
                    break;
                case 4:
                    constraintsPanelActivos.gridx = 5;
                    constraintsPanelActivos.gridy = 8;
                    constraintsPanelActivos.gridwidth = 1;
                    constraintsPanelActivos.fill = GridBagConstraints.NONE;
                    constraintsPanelActivos.anchor = GridBagConstraints.LINE_END;

                    panelDadosActivos.add(botonesEnDadosActivos.get(4), constraintsPanelActivos);
                    break;
                case 5:
                    constraintsPanelActivos.gridx = 2;
                    constraintsPanelActivos.gridy = 9;
                    constraintsPanelActivos.gridwidth = 1;
                    constraintsPanelActivos.fill = GridBagConstraints.NONE;
                    constraintsPanelActivos.anchor = GridBagConstraints.LINE_START;

                    panelDadosActivos.add(botonesEnDadosActivos.get(5), constraintsPanelActivos);
                    break;
                case 6:
                    constraintsPanelActivos.gridx = 4;
                    constraintsPanelActivos.gridy = 9;
                    constraintsPanelActivos.gridwidth = 1;
                    constraintsPanelActivos.fill = GridBagConstraints.NONE;
                    constraintsPanelActivos.anchor = GridBagConstraints.LINE_START;

                    panelDadosActivos.add(botonesEnDadosActivos.get(6), constraintsPanelActivos);
                    break;
            }
        }
        revalidate();
        repaint();
    }

    /**
     * This function updates DadosInactivos panel.
     */


    private void rePaintDadosInactivos()
    {
        panelDadosInactivos.removeAll();

        panelDadosInactivos.setLayout(new GridBagLayout());
        GridBagConstraints constraintsPanelInactivos = new GridBagConstraints();

        for (boton = 0; boton < botonesEnDadosInactivos.size(); boton++) {
            switch (boton) {
                case 0:
                    constraintsPanelInactivos.gridx = 4;
                    constraintsPanelInactivos.gridy = 4;
                    constraintsPanelInactivos.gridwidth = 1;
                    constraintsPanelInactivos.fill = GridBagConstraints.NONE;
                    constraintsPanelInactivos.anchor = GridBagConstraints.CENTER;

                    panelDadosInactivos.add(botonesEnDadosInactivos.get(0), constraintsPanelInactivos);
                    break;
                case 1:
                    constraintsPanelInactivos.gridx = 5;
                    constraintsPanelInactivos.gridy = 4;
                    constraintsPanelInactivos.gridwidth = 1;
                    constraintsPanelInactivos.fill = GridBagConstraints.NONE;
                    constraintsPanelInactivos.anchor = GridBagConstraints.CENTER;

                    panelDadosInactivos.add(botonesEnDadosInactivos.get(1), constraintsPanelInactivos);
                    break;
                case 2:
                    constraintsPanelInactivos.gridx = 6;
                    constraintsPanelInactivos.gridy = 4;
                    constraintsPanelInactivos.gridwidth = 1;
                    constraintsPanelInactivos.fill = GridBagConstraints.NONE;
                    constraintsPanelInactivos.anchor = GridBagConstraints.CENTER;

                    panelDadosInactivos.add(botonesEnDadosInactivos.get(2), constraintsPanelInactivos);
                    break;
                case 3:
                    constraintsPanelInactivos.gridx = 4;
                    constraintsPanelInactivos.gridy = 5;
                    constraintsPanelInactivos.gridwidth = 1;
                    constraintsPanelInactivos.fill = GridBagConstraints.NONE;
                    constraintsPanelInactivos.anchor = GridBagConstraints.CENTER;

                    panelDadosInactivos.add(botonesEnDadosInactivos.get(3), constraintsPanelInactivos);
                    break;
                case 4:
                    constraintsPanelInactivos.gridx = 5;
                    constraintsPanelInactivos.gridy = 5;
                    constraintsPanelInactivos.gridwidth = 1;
                    constraintsPanelInactivos.fill = GridBagConstraints.NONE;
                    constraintsPanelInactivos.anchor = GridBagConstraints.CENTER;

                    panelDadosInactivos.add(botonesEnDadosInactivos.get(4), constraintsPanelInactivos);
                    break;
                case 5:
                    constraintsPanelInactivos.gridx = 6;
                    constraintsPanelInactivos.gridy = 5;
                    constraintsPanelInactivos.gridwidth = 1;
                    constraintsPanelInactivos.fill = GridBagConstraints.NONE;
                    constraintsPanelInactivos.anchor = GridBagConstraints.CENTER;

                    panelDadosInactivos.add(botonesEnDadosInactivos.get(5), constraintsPanelInactivos);
                    break;
                case 6:
                    constraintsPanelInactivos.gridx = 4;
                    constraintsPanelInactivos.gridy = 6;
                    constraintsPanelInactivos.gridwidth = 1;
                    constraintsPanelInactivos.fill = GridBagConstraints.NONE;
                    constraintsPanelInactivos.anchor = GridBagConstraints.CENTER;

                    panelDadosInactivos.add(botonesEnDadosInactivos.get(6), constraintsPanelInactivos);
                    break;
                case 7:
                    constraintsPanelInactivos.gridx = 5;
                    constraintsPanelInactivos.gridy = 6;
                    constraintsPanelInactivos.gridwidth = 1;
                    constraintsPanelInactivos.fill = GridBagConstraints.NONE;
                    constraintsPanelInactivos.anchor = GridBagConstraints.CENTER;

                    panelDadosInactivos.add(botonesEnDadosInactivos.get(7), constraintsPanelInactivos);
                    break;
                case 8:
                    constraintsPanelInactivos.gridx = 6;
                    constraintsPanelInactivos.gridy = 6;
                    constraintsPanelInactivos.gridwidth = 1;
                    constraintsPanelInactivos.fill = GridBagConstraints.NONE;
                    constraintsPanelInactivos.anchor = GridBagConstraints.CENTER;

                    panelDadosInactivos.add(botonesEnDadosInactivos.get(8), constraintsPanelInactivos);
                    break;
            }
        }
        revalidate();
        panelDadosInactivos.updateUI();
    }

    /**
     * This function updates DadosUtilizados panel.
     */
    private void rePaintDadosUtilizados()
    {
        panelDadosUtilizados.removeAll();

        panelDadosUtilizados.setPreferredSize(new Dimension(800, 600));
        panelDadosUtilizados.setLayout(new GridBagLayout());
        GridBagConstraints constraintsPanelUtilizados = new GridBagConstraints();

        for (boton = 0; boton < botonesEnDadosUtilizados.size(); boton++) {
            switch (boton) {
                case 0:
                    constraintsPanelUtilizados.gridx = 0;
                    constraintsPanelUtilizados.gridy = 4;
                    constraintsPanelUtilizados.gridwidth = 1;
                    constraintsPanelUtilizados.fill = GridBagConstraints.NONE;
                    constraintsPanelUtilizados.anchor = GridBagConstraints.CENTER;

                    panelDadosUtilizados.add(botonesEnDadosUtilizados.get(0), constraintsPanelUtilizados);
                    break;
                case 1:
                    constraintsPanelUtilizados.gridx = 1;
                    constraintsPanelUtilizados.gridy = 4;
                    constraintsPanelUtilizados.gridwidth = 1;
                    constraintsPanelUtilizados.fill = GridBagConstraints.NONE;
                    constraintsPanelUtilizados.anchor = GridBagConstraints.CENTER;

                    panelDadosUtilizados.add(botonesEnDadosUtilizados.get(1), constraintsPanelUtilizados);
                    break;
                case 2:
                    constraintsPanelUtilizados.gridx = 2;
                    constraintsPanelUtilizados.gridy = 4;
                    constraintsPanelUtilizados.gridwidth = 1;
                    constraintsPanelUtilizados.fill = GridBagConstraints.NONE;
                    constraintsPanelUtilizados.anchor = GridBagConstraints.CENTER;

                    panelDadosUtilizados.add(botonesEnDadosUtilizados.get(2), constraintsPanelUtilizados);
                    break;
                case 3:
                    constraintsPanelUtilizados.gridx = 0;
                    constraintsPanelUtilizados.gridy = 5;
                    constraintsPanelUtilizados.gridwidth = 1;
                    constraintsPanelUtilizados.fill = GridBagConstraints.NONE;
                    constraintsPanelUtilizados.anchor = GridBagConstraints.CENTER;

                    panelDadosUtilizados.add(botonesEnDadosUtilizados.get(3), constraintsPanelUtilizados);
                    break;
                case 4:
                    constraintsPanelUtilizados.gridx = 1;
                    constraintsPanelUtilizados.gridy = 5;
                    constraintsPanelUtilizados.gridwidth = 1;
                    constraintsPanelUtilizados.fill = GridBagConstraints.NONE;
                    constraintsPanelUtilizados.anchor = GridBagConstraints.CENTER;

                    panelDadosUtilizados.add(botonesEnDadosUtilizados.get(4), constraintsPanelUtilizados);
                    break;
                case 5:
                    constraintsPanelUtilizados.gridx = 2;
                    constraintsPanelUtilizados.gridy = 5;
                    constraintsPanelUtilizados.gridwidth = 1;
                    constraintsPanelUtilizados.fill = GridBagConstraints.NONE;
                    constraintsPanelUtilizados.anchor = GridBagConstraints.CENTER;

                    panelDadosUtilizados.add(botonesEnDadosUtilizados.get(5), constraintsPanelUtilizados);
                    break;
                case 6:
                    constraintsPanelUtilizados.gridx = 0;
                    constraintsPanelUtilizados.gridy = 6;
                    constraintsPanelUtilizados.gridwidth = 1;
                    constraintsPanelUtilizados.fill = GridBagConstraints.NONE;
                    constraintsPanelUtilizados.anchor = GridBagConstraints.CENTER;

                    panelDadosUtilizados.add(botonesEnDadosUtilizados.get(6), constraintsPanelUtilizados);
                    break;
                case 7:
                    constraintsPanelUtilizados.gridx = 1;
                    constraintsPanelUtilizados.gridy = 6;
                    constraintsPanelUtilizados.gridwidth = 1;
                    constraintsPanelUtilizados.fill = GridBagConstraints.NONE;
                    constraintsPanelUtilizados.anchor = GridBagConstraints.CENTER;

                    panelDadosUtilizados.add(botonesEnDadosUtilizados.get(7), constraintsPanelUtilizados);
                    break;
                case 8:
                    constraintsPanelUtilizados.gridx = 2;
                    constraintsPanelUtilizados.gridy = 6;
                    constraintsPanelUtilizados.gridwidth = 1;
                    constraintsPanelUtilizados.fill = GridBagConstraints.NONE;
                    constraintsPanelUtilizados.anchor = GridBagConstraints.CENTER;

                    panelDadosUtilizados.add(botonesEnDadosUtilizados.get(8), constraintsPanelUtilizados);
                    break;
            }
        }
        revalidate();
        panelDadosUtilizados.updateUI();
    }

    /**
     * This function verify Inactives dices.
     */

    private void verifyDicesInDadosActivos() {
        GridBagConstraints constraints = new GridBagConstraints();
        if (botonesEnDadosActivos.size()==0) {
            nuevaRonda.addMouseListener(escucha);
            nuevaRonda.setBackground(Color.green);
            estado=8;
            mensajesAccionesDados.setText(getEstadoToString()[0]);
            panelAccionesDados.add(mensajesAccionesDados);
        } else if (botonesEnDadosActivos.size() == 1) {
            if (game.dadosActivosArray.get(0).getCara() == 1 | game.dadosActivosArray.get(0).getCara() == 2 | game.dadosActivosArray.get(0).getCara() == 3) {
                nuevaRonda.addMouseListener(escucha);
                nuevaRonda.setBackground(Color.green);
                estado=8;
                mensajesAccionesDados.setText(getEstadoToString()[0]);
                panelAccionesDados.add(mensajesAccionesDados);
            } else if (game.dadosActivosArray.get(0).getCara() == 5 | game.dadosActivosArray.get(0).getCara() == 6) {
                nuevaRonda.addMouseListener(escucha);
                nuevaRonda.setBackground(Color.green);
                estado=8;
                mensajesAccionesDados.setText(getEstadoToString()[0]);
                panelAccionesDados.add(mensajesAccionesDados);
            }
        } else {
            switch (botonesEnDadosActivos.size()) {
                case 2:
                    if (game.dadosActivosArray.get(0).getCara() == 5 | game.dadosActivosArray.get(0).getCara() == 6) {
                        if (game.dadosActivosArray.get(1).getCara() == 5 | game.dadosActivosArray.get(1).getCara() == 6) {
                            nuevaRonda.addMouseListener(escucha);
                            nuevaRonda.setBackground(Color.green);
                            estado=8;
                            mensajesAccionesDados.setText(getEstadoToString()[0]);
                            panelAccionesDados.add(mensajesAccionesDados);
                        }
                    }
                    break;
                case 3:
                    if (game.dadosActivosArray.get(0).getCara() == 5 | game.dadosActivosArray.get(0).getCara() == 6) {
                        if (game.dadosActivosArray.get(1).getCara() == 5 | game.dadosActivosArray.get(1).getCara() == 6) {
                            if (game.dadosActivosArray.get(2).getCara() == 5 | game.dadosActivosArray.get(2).getCara() == 6) {
                                nuevaRonda.addMouseListener(escucha);
                                nuevaRonda.setBackground(Color.green);
                                estado=8;
                                mensajesAccionesDados.setText(getEstadoToString()[0]);
                                panelAccionesDados.add(mensajesAccionesDados);
                            }
                        }
                    }
                    break;
                case 4:
                    if (game.dadosActivosArray.get(0).getCara() == 5 | game.dadosActivosArray.get(0).getCara() == 6) {
                        if (game.dadosActivosArray.get(1).getCara() == 5 | game.dadosActivosArray.get(1).getCara() == 6) {
                            if (game.dadosActivosArray.get(2).getCara() == 5 | game.dadosActivosArray.get(2).getCara() == 6) {
                                if (game.dadosActivosArray.get(3).getCara() == 5 | game.dadosActivosArray.get(3).getCara() == 6) {
                                    nuevaRonda.addMouseListener(escucha);
                                    nuevaRonda.setBackground(Color.green);
                                    estado=8;
                                    mensajesAccionesDados.setText(getEstadoToString()[0]);
                                    panelAccionesDados.add(mensajesAccionesDados);
                                }
                            }
                        }
                    }
                    break;
                case 5:
                    if (game.dadosActivosArray.get(0).getCara() == 5 | game.dadosActivosArray.get(0).getCara() == 6) {
                        if (game.dadosActivosArray.get(1).getCara() == 5 | game.dadosActivosArray.get(1).getCara() == 6) {
                            if (game.dadosActivosArray.get(2).getCara() == 5 | game.dadosActivosArray.get(2).getCara() == 6) {
                                if (game.dadosActivosArray.get(3).getCara() == 5 | game.dadosActivosArray.get(3).getCara() == 6) {
                                    if (game.dadosActivosArray.get(4).getCara() == 5 | game.dadosActivosArray.get(4).getCara() == 6) {
                                        nuevaRonda.addMouseListener(escucha);
                                        nuevaRonda.setBackground(Color.green);
                                        estado=8;
                                        mensajesAccionesDados.setText(getEstadoToString()[0]);
                                        panelAccionesDados.add(mensajesAccionesDados);
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 6:
                    if (game.dadosActivosArray.get(0).getCara() == 5 | game.dadosActivosArray.get(0).getCara() == 6) {
                        if (game.dadosActivosArray.get(1).getCara() == 5 | game.dadosActivosArray.get(1).getCara() == 6) {
                            if (game.dadosActivosArray.get(2).getCara() == 5 | game.dadosActivosArray.get(2).getCara() == 6) {
                                if (game.dadosActivosArray.get(3).getCara() == 5 | game.dadosActivosArray.get(3).getCara() == 6) {
                                    if (game.dadosActivosArray.get(4).getCara() == 5 | game.dadosActivosArray.get(4).getCara() == 6) {
                                        if (game.dadosActivosArray.get(5).getCara() == 5 | game.dadosActivosArray.get(5).getCara() == 6) {
                                            nuevaRonda.addMouseListener(escucha);
                                            nuevaRonda.setBackground(Color.green);
                                            estado=8;
                                            mensajesAccionesDados.setText(getEstadoToString()[0]);
                                            panelAccionesDados.add(mensajesAccionesDados);
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
                case 7:
                    if (game.dadosActivosArray.get(0).getCara() == 5 | game.dadosActivosArray.get(0).getCara() == 6) {
                        if (game.dadosActivosArray.get(1).getCara() == 5 | game.dadosActivosArray.get(1).getCara() == 6) {
                            if (game.dadosActivosArray.get(2).getCara() == 5 | game.dadosActivosArray.get(2).getCara() == 6) {
                                if (game.dadosActivosArray.get(3).getCara() == 5 | game.dadosActivosArray.get(3).getCara() == 6) {
                                    if (game.dadosActivosArray.get(4).getCara() == 5 | game.dadosActivosArray.get(4).getCara() == 6) {
                                        if (game.dadosActivosArray.get(5).getCara() == 5 | game.dadosActivosArray.get(5).getCara() == 6) {
                                            if (game.dadosActivosArray.get(6).getCara() == 5 | game.dadosActivosArray.get(6).getCara() == 6) {
                                                nuevaRonda.addMouseListener(escucha);
                                                nuevaRonda.setBackground(Color.green);
                                                estado=8;
                                                mensajesAccionesDados.setText(getEstadoToString()[0]);
                                                panelAccionesDados.add(mensajesAccionesDados);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    break;
            }
        }
    }



    public String[] getEstadoToString () {
        switch (estado) {
            case 1:
                estadoToString[0] = "Has seleccionado el Meeple: puedes relanzar otro dado de la sección dados activos";
                break;
            case 2:
                estadoToString[0] = "Has ejecutado el poder del Meeple. Continúa jugando.\n" + "\nRecuerda pasar el"
                        + " mouse por las secciones dados inactivos y dados utilizados para ver los dados que estén allí.";
                break;
            case 3:
                estadoToString[0] = "Has seleccionado la Nave Espacial: puedes enviar un dado no usado (de la sección"
                        + " dados activos) a la sección de dados inactivos";
                break;
            case 4:
                estadoToString[0] = "Has ejecutado el poder de la Nave Espacial. Continúa jugando.\n"
                        + "\nRecuerda pasar el mouse por las secciones dados inactivos y dados utilizados para ver los"
                        + " dados que estén allí.";;
                break;
            case 5:
                estadoToString[0] = "Has seleccionado el Superheroe: puedes hacer que cualquier dado no usado (sección"
                        + " dados activos) sea volteado y colocado en su cara opuesta.";
                break;
            case 6:
                estadoToString[0] = "Has ejecutado el poder del Superhéroe. Continúa jugando.\n" + "\nRecuerda pasar el"
                        + " mouse por las secciones dados inactivos y dados utilizados para ver los dados que estén allí.";;
                break;

            case 7:
                estadoToString[0] = "Has seleccionado el Corazón: toma un dado aleatorio de la sección de dados inactivos"
                        + " y lo lanza para que sea un nuevo dado activo.";
                break;
            case 8:
                estadoToString[0] = "Puedes pasar a la siguiente ronda.";
                break;
            case 9:
                estadoToString[0] = "Has iniciado una nueva ronda. Buena suerte.";
                break;
            case 10:
                estadoToString[0] = MENSAJE_INICIO;
                break;
            case 11:
                estadoToString[0] = "Lo sentimos. Hubo un problema :/ Por favor intenta de nuevo.";
        }
        return estadoToString;
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

     /**
     * This function displays on the screen what the chosen die does.
     */
    private class Escucha extends MouseAdapter {

        private String kk;
        public void firstSelection(int boton) {
            if (seleccionDado == 1) {
                if ((game.dadosActivosArray.get(boton).getCara()) == 1 | (game.dadosActivosArray.get(boton).getCara()) == 2 | (game.dadosActivosArray.get(boton).getCara()) == 3) {
                    botonesEnDadosActivos.get(boton).removeMouseListener(escucha);
                    botonesEnDadosUtilizados.add(botonesEnDadosActivos.get(boton));
                    botonesEnDadosActivos.remove(boton);
                    if ((game.dadosActivosArray.get(boton).getCara()) == 1) {
                        poder = "meeple";
                        estado = 1;
                        mensajesAccionesDados.setText(getEstadoToString()[0]);
                        panelAccionesDados.add(mensajesAccionesDados);
                    } else if ((game.dadosActivosArray.get(boton).getCara()) == 2) {
                        poder = "cohete";
                        estado = 3;
                        mensajesAccionesDados.setText(getEstadoToString()[0]);
                        panelAccionesDados.add(mensajesAccionesDados);
                    } else if ((game.dadosActivosArray.get(boton).getCara()) == 3) {
                        poder = "superheroe";
                        estado = 5;
                        mensajesAccionesDados.setText(getEstadoToString()[0]);
                        panelAccionesDados.add(mensajesAccionesDados);
                    }
                    game.powers(boton);
                    seleccionDado = 2;

                } else if ((game.dadosActivosArray.get(boton).getCara()) == 4) {
                    botonesEnDadosActivos.get(boton).removeMouseListener(escucha);
                    botonesEnDadosUtilizados.add(botonesEnDadosActivos.get(boton));
                    botonesEnDadosActivos.remove(boton);

                    if (botonesEnDadosInactivos.size() != 0) {
                        game.powers(boton);
                        game.heart(boton);
                        estado = 7;
                        mensajesAccionesDados.setText(getEstadoToString()[0]);
                        panelAccionesDados.add(mensajesAccionesDados);

                        imageDado = new ImageIcon(getClass().getResource("/resources/" + game.dadosActivosArray.get(boton).getCara() + ".png"));
                        imagenOtroTamanho = imageDado.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                        imagenNuevoTamanho = new ImageIcon(imagenOtroTamanho);
                        botonesEnDadosInactivos.get(unBoton).setIcon(imagenNuevoTamanho);
                        botonesEnDadosInactivos.get(unBoton).addMouseListener(escucha);
                        botonesEnDadosActivos.add(boton, botonesEnDadosInactivos.get(unBoton));
                        botonesEnDadosInactivos.remove(unBoton);

                        verifyDicesInDadosActivos();

                        seleccionDado = 1;
                    }
                } else if ((game.dadosActivosArray.get(boton).getCara()) == 5 | (game.dadosActivosArray.get(boton).getCara()) == 6) {
                    verifyDicesInDadosActivos();
                    seleccionDado = 1;
                } else {
                    verifyDicesInDadosActivos();
                    seleccionDado = 1;
                }
            }
            else
            {
                estado = 11;
                mensajesAccionesDados.setText(getEstadoToString()[0]);
                panelAccionesDados.add(mensajesAccionesDados);

            }
        }

        /**
        * This function shows on the screen what the first die did on the second chosen die .
        */

        public void secondSelecction(int boton)
        {
            if(seleccionDado==2) {
                if (poder == "meeple") {
                    game.meeple(boton);
                    estado = 2;
                    mensajesAccionesDados.setText(getEstadoToString()[0]);
                    panelAccionesDados.add(mensajesAccionesDados);

                    imageDado = new ImageIcon(getClass().getResource("/resources/" + ((game.dadosActivosArray).get(boton)).getCara() + ".png"));
                    imagenOtroTamanho = imageDado.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                    imagenNuevoTamanho = new ImageIcon(imagenOtroTamanho);
                    botonesEnDadosActivos.get(boton).setIcon(imagenNuevoTamanho);
                } else if (poder == "cohete") {
                    game.spaceship(boton);
                    estado = 4;
                    mensajesAccionesDados.setText(getEstadoToString()[0]);
                    panelAccionesDados.add(mensajesAccionesDados);

                    botonesEnDadosActivos.get(boton).removeMouseListener(escucha);
                    botonesEnDadosInactivos.add(botonesEnDadosActivos.get(boton));
                    botonesEnDadosActivos.remove(boton);
                } else if (poder == "superheroe") {
                    game.superhero(boton);
                    estado = 6;
                    mensajesAccionesDados.setText(getEstadoToString()[0]);
                    panelAccionesDados.add(mensajesAccionesDados);

                    imageDado = new ImageIcon(getClass().getResource("/resources/" + ((game.dadosActivosArray).get(boton)).getCara() + ".png"));
                    imagenOtroTamanho = imageDado.getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH);
                    imagenNuevoTamanho = new ImageIcon(imagenOtroTamanho);
                    botonesEnDadosActivos.get(boton).setIcon(imagenNuevoTamanho);
                }
                verifyDicesInDadosActivos();
                poder = "";
            }
            else
            {
                estado = 11;
                mensajesAccionesDados.setText(getEstadoToString()[0]);
                panelAccionesDados.add(mensajesAccionesDados);
            }
        }

        /**
     * This function shows on the screen what the first die did on the second chosen die .
     */

        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == nuevaRonda || e.getSource() == play ) {
                estado = 9;
                mensajesAccionesDados.setText(getEstadoToString()[0]);
                panelAccionesDados.add(mensajesAccionesDados);
                nuevaRonda.removeMouseListener(escucha);
                nuevaRonda.setBackground(Color.darkGray);
                if (ronda == 5) {
                    game.roundPoints();
                    game.drakeDices();
                    game.determinateScore();
                    if (game.endGame()) {
                        mensajeFinal = "¡¡Felicidades!! Has ganado con: " + puntos + " puntos"
                                + "\nPuedes volver a jugar empezando una nueva ronda.";
                    } else {
                        mensajeFinal = "Has perdido con: " + puntos + " puntos" + "\nPuedes volver a jugar empezando una nueva ronda.";
                    }
                    ronda = 0;
                    JOptionPane.showMessageDialog(null, mensajeFinal);
                } else {
                    if (ronda > 0) {
                        game.roundPoints();
                        game.drakeDices();
                        game.determinateScore();

                        estado = 8;
                        mensajesAccionesDados.setText(getEstadoToString()[0]);
                        panelAccionesDados.add(mensajesAccionesDados);

                        remove(panelDadosActivos);
                        remove(panelDadosInactivos);
                        remove(panelDadosUtilizados);
                    }
                }
                game.nextRound();

                ronda = game.getRonda();
                numeroRonda.setText("Ronda: " + ronda);

                puntos = game.getPuntaje();
                puntaje.setText("Puntaje: " + puntos);

                GridBagConstraints constraints = new GridBagConstraints();

                //------------------------------------------------------------------------------------------------------------------------------------------

                if (botonesEnDadosUtilizados.size() != 0) {
                    botonesEnDadosUtilizados.clear();
                }

                createDadosUtilizadosPane(constraints);

                //------------------------------------------------------------------------------------------------------------------------------------------

                if (botonesEnDadosInactivos.size() != 0) {
                    botonesEnDadosInactivos.clear();
                }

                createDadosInactivosPane(constraints);

                //------------------------------------------------------------------------------------------------------------------------------------------

                if (botonesEnDadosActivos.size() != 0) {
                    botonesEnDadosActivos.clear();
                }
                createDadosActivosPane(constraints);

                //------------------------------------------------------------------------------------------------------------------------------------------

                verifyDicesInDadosActivos();

                seleccionDado=1;

                revalidate();
                repaint();
            }

            else if (e.getSource() == ayuda)
            {
                panelInstrucciones.add(instrucciones, BorderLayout.LINE_START);

                JScrollPane scroll = new JScrollPane(panelInstrucciones);
                scroll.setPreferredSize(new Dimension(440, 455));

                JOptionPane.showMessageDialog(null, scroll, "Instrucciones del juego", JOptionPane.INFORMATION_MESSAGE);
            }
            else
            {
                if (seleccionDado == 2) {
                    for (boton = 0; boton < botonesEnDadosActivos.size(); boton++) {
                        if (e.getSource() == botonesEnDadosActivos.get(boton)) {
                            secondSelecction(boton);
                        }
                    }
                    seleccionDado = 1;
                } else {
                    for (boton = 0; boton < botonesEnDadosActivos.size(); boton++) {
                        if (e.getSource() == botonesEnDadosActivos.get(boton)) {
                            firstSelection(boton);
                        }
                    }
                }
            }
            rePaintDadosInactivos();
            rePaintDadosUtilizados();
            rePaintDadosActivos();
        }
    }

}

