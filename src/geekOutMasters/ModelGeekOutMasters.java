package geekOutMasters;
import java.util.ArrayList;
/***
 * this class was created by
 * @autor jhoni ipia jhonier.ipia@correounivalle.edu.co
 * @autor Alexandra Lerma, alexandra.lerma@correounivalle.edu.co
 * @version v.1.0.0
 * @date: april 29, 2023
 */

 public class ModelGeekOutMasters {
    private Dados dado1, dado2, dado3, dado4, dado5, dado6, dado7, dado8, dado9, dado10;
    private int puntos, puntaje, ronda, unDado, dados42, estado;
    private boolean terminar;
    public ArrayList<Dados> dadosUtilizadosArray, dadosInactivosArray, dadosActivosArray, unArray;
    private String[] estadoToString;

    /**
     * Class Constructor
     */

    public ModelGeekOutMasters() {
        dado1 = new Dados();
        dado2 = new Dados();
        dado3 = new Dados();
        dado4 = new Dados();
        dado5 = new Dados();
        dado6 = new Dados();
        dado7 = new Dados();
        dado8 = new Dados();
        dado9 = new Dados();
        dado10 = new Dados();

        ronda = 0;
        puntaje = 0;
        puntos = 0;
        estado = 0;

        dadosActivosArray = new ArrayList<Dados>();
        dadosInactivosArray = new ArrayList<Dados>();
        dadosUtilizadosArray = new ArrayList<Dados>();
        unArray = new ArrayList<Dados>();

        determinateDadosActivos();
        determinateDadosInactivos();
        estadoToString = new String[7];
    }

    This function addes the active dices an DadosActivos array.
     */

    public void determinateDadosActivos() {
        dadosActivosArray.add(dado1);
        dadosActivosArray.add(dado2);
        dadosActivosArray.add(dado3);
        dadosActivosArray.add(dado4);
        dadosActivosArray.add(dado5);
        dadosActivosArray.add(dado6);
        dadosActivosArray.add(dado7);
    }

    /**
     * This function addes the active dices an DadosActivos array.
     */

    public void determinateDadosInactivos() {
        dadosInactivosArray.add(dado8);
        dadosInactivosArray.add(dado9);
        dadosInactivosArray.add(dado10);
    }

    public void removeDiceFromArray(int posicionDado, ArrayList<Dados> array) {
        array.remove(posicionDado);
    }

    /**
     * This function adds dice to the array at a given position.
     *
     * @param array
     * @param dado
     * @param posicion
     */
    public void addDiceFromArray(ArrayList<Dados> array, Dados dado, int posicion) {
        array.add(posicion, dado);
    }

    /**
     * This function adds dices to array.
     *
     * @param array
     * @param dado
     */

    public void addDiceFromArray(ArrayList<Dados> array, Dados dado) {
        array.add(dado);
    }